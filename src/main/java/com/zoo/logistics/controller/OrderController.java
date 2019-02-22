package com.zoo.logistics.controller;

import com.github.pagehelper.PageInfo;
import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.entity.EquipmentReceipt;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.service.*;
import com.zoo.logistics.service.CarService;
import com.zoo.logistics.service.EquipmentReceiptService;
import com.zoo.logistics.service.LogService;
import com.zoo.logistics.service.OrderService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private LogService logService;
    @Autowired
    private EquipmentReceiptService equipmentReceiptService;
    @Autowired
    private CarService carService;
    @Autowired
    private CourierService courierService;
    @Autowired
    private MissionService missionService;

    @RequestMapping("/createOrder")
    public String tocreateOrderPage(HttpServletResponse response){

        return "create-order";
    }

    @RequestMapping("/putin")
    public String toPutinPage(){
        return "putin";
    }

    /**
     * 客户端下单请求地址
     * @param order 订单信息
     * @return Map形式下单操作的执行结果
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/createOrder/submitOrderByClient",method = RequestMethod.POST)
    public Map orderSubmitByClient(@RequestBody Order order) throws UnsupportedEncodingException {
        System.out.println("get:"+order.toString());
        Map resultMap = orderService.createOrder(order);
        return resultMap;
    }

    /**
     * 客户端下单请求地址
     * @param order 订单信息
     * @return create-order.html
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/createOrder/submitOrder",method = RequestMethod.POST)
    public String orderSubmit(Order order) throws UnsupportedEncodingException {

        System.out.println("get:"+order.toString());

        orderService.createOrder(order);

        return "create-order";
    }

    /**
     * 查找包裹
     * @param map map中会携带一个todo的参数，根据参数值判断当前操作
     *            putin:货物入库，更新包裹信息和日志
     *            zhuangche:扫码装车，更新日志
     * @param request
     * @return 结果集
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/searchOrder")
    public Map searchOrder(@RequestBody Map map, HttpServletRequest request) throws UnsupportedEncodingException {

        Map resultMap = orderService.getOrder(String.valueOf(map.get("orderId")));
        if("查找成功".equals(resultMap.get("msg").toString())){
            //扫描入库操作
            if("putin".equals(map.get("todo"))){
                Admin admin = (Admin) request.getSession().getAttribute("admin");
                Order order = (Order) resultMap.get("order");
                order.setStatusId(2);
                order.setCurrentStation(admin.getStationId());
                //更新状态
                orderService.update(order);
                //更新日志
                logService.insertLog(order.getOrderId(),"【"+admin.getStation().getStationName()+"】已入库");
            }
            //扫描回执单操作
            if("confirm".equals(map.get("todo"))){
                Admin admin = (Admin) request.getSession().getAttribute("admin");
                Order order = (Order) resultMap.get("order");
                order.setStatusId(5);
                order.setCurrentStation(null);
                order.setFinishDate(new Date());
                //更新状态
                orderService.update(order);
                //更新日志
                logService.insertLog(order.getOrderId(),"订单已配送");
            }
        }
        return resultMap;
    }

    /**
     * 装车发货
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/consignment")
    public Map consignment(@RequestBody Map map,HttpServletRequest request) {
        Map resultMap = new HashMap();
        String[] orderIdList = map.get("orderIdList").toString().split(",");
        String licenseNumber = map.get("licenseNumber").toString();
        String destination = map.get("destination").toString();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //创建交接单
        EquipmentReceipt equipmentReceipt = new EquipmentReceipt(licenseNumber, 0, new Date());
        int id = equipmentReceiptService.insertSelective(equipmentReceipt);
        //更改包裹状态
        orderService.updateOrderListToTransport(orderIdList,id);
        //创建日志
        for(String orderId : orderIdList){
            logService.insertLog(orderId,"从【"+admin.getStation().getStationName()+"】发出，正在发往【"+destination+"】");
        }
        //更改车辆状态
        carService.updateCarStatus(licenseNumber);

        resultMap.put("status",1);
        resultMap.put("msg","提交成功");

        return resultMap;
    }

    /**
     * 根据交接单id查询交接单下的所有订单
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchOrderByEquipmentReceiptId")
    public Map searchOrderByEquipmentReceiptId(@RequestBody Map map,HttpServletRequest request) {
        Map resultMap = new HashMap();
        int equipmentReceiptId = Integer.parseInt(map.get("equipmentReceiptId").toString());
        List<Order> orderList = orderService.selectByEquipmentReceiptId(equipmentReceiptId);
        if(orderList.size()>0){
            resultMap.put("status",1);
            resultMap.put("orderList",orderList);
        }else{
            resultMap.put("status",0);
            resultMap.put("msg","没有订单");
        }
        return resultMap;
    }

    /**
     * 确认交接单时设置异常订单
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/orderException")
    public String orderException(@RequestBody Map map,HttpServletRequest request){

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //更改包裹状态 更改当前站点 清空交接单id
        String orderId = map.get("orderId").toString();

        orderService.orderException(orderId,admin.getStationId());

        //更新日志
        logService.insertLog(orderId,"货物异常,当前所在站点：【"+admin.getStation().getStationName()+"】");

        return "success";
    }

    //--------------------------------------------------------------

    /**
     * 点击订单修改时跳转到的页面及数据的分页
     * @param curPage 当前页数
     * @param map 返回的数据存储在map中
     * @param request
     * @return 跳转页面
     */
    @RequestMapping(value = "/editOrder/page/{curPage}")
    public String toeditOrderPage(@PathVariable(value = "curPage") int curPage,
                                  Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 8;
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("req get:"+request.getSession().getAttribute("admin"));

        //获取所有状态为待揽件的订单
        PageInfo orderinfo=orderService.selectByStatusId(admin.getStationId(),curPage,limit);
        map.put("orderList",orderinfo.getList());
        map.put("prePage",orderinfo.getPrePage());
        map.put("nextPage",orderinfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",orderinfo.getFirstPage());
        map.put("lastPage",orderinfo.getLastPage());
        System.out.println(admin.getStationId());
        return "edit-order";
    }

    /**
     * 接收要修改的数据,对数据库中的数据进行修改
     * @param map
     * @param request
     * @return 修改结果
     */
    @ResponseBody
    @RequestMapping(value = "/edit")
    public Map edit(@RequestBody Map map,HttpServletRequest request){
        Map resultMap=new HashMap();
        String orderid=map.get("orderId").toString();
        Order order = orderService.selectByOrderId(orderid);
        order.setSenderName(map.get("sendName").toString());
        order.setSenderTel(map.get("sendTel").toString());
        order.setSenderArea(map.get("senderArea").toString());
        order.setSenderStreet(map.get("senderStreet").toString());
        order.setReceiverName(map.get("receiverName").toString());
        order.setReceiverTel(map.get("receiverTel").toString());
        order.setReceiverArea(map.get("receiverArea").toString());
        order.setReceiverStreet(map.get("receiverStreet").toString());
        orderService.update(order);
        resultMap.put("status",1);
        resultMap.put("msg","提交成功");

        return resultMap;
    }

    /**
     * 根据订单编号进行搜索
     * @param orderid 订单编号
     * @param request
     * @return 带有order的map
     */
    @ResponseBody
    @RequestMapping(value = "/select")
    public Map select(@RequestBody String orderid,HttpServletRequest request,HttpServletResponse response)throws IOException {
        System.out.println("进入查询");
        System.out.println(orderid);

        Map resultMap=new HashMap();
        //根据订单号查询订单
        Order order = orderService.selectByOrderId(orderid);
        System.out.println(order);
        //判断是否查询到订单
        if(order!=null){
            //判断订单当前站点是否为本站点
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(order.getCurrentStation()==admin.getStationId()){
                //判断该订单状态是否为“待揽件”
                if(order.getStatusId()==1){
                    System.out.println("查询成功");
                    resultMap.put("status",1);
                    resultMap.put("msg","查询成功");
                    resultMap.put("order",order);
                }else {
                    System.out.println("查询失败");
                    resultMap.put("status",0);
                    resultMap.put("msg","查询失败,不是待揽件订单");
                }
            }else {
                System.out.println("查询失败2");
                resultMap.put("status",0);
                resultMap.put("msg","查询失败,不是本站订单");
            }
        }else {
            resultMap.put("status",0);
            resultMap.put("msg","查询失败,订单号错误");
        }

//        PrintWriter writer = response.getWriter();
//        writer.print(resultMap);


        return resultMap;
    }

    /**
     * 点击待发订单查询时进行的页面跳转以及数据的分页处理
     * @param curPage
     * @param map
     * @param request
     * @return 跳转页面
     */
    @RequestMapping(value = "/waitedOrder/page/{curPage}")
    public String towaitedOrderPage(@PathVariable(value = "curPage") int curPage,
                                  Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 8;
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("req get:"+request.getSession().getAttribute("admin"));

        //获取所有状态为待揽件的订单
        PageInfo orderinfo=orderService.selectWaitedOrder(admin.getStationId(),curPage,limit);
        map.put("orderList",orderinfo.getList());
        map.put("prePage",orderinfo.getPrePage());
        map.put("nextPage",orderinfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",orderinfo.getFirstPage());
        map.put("lastPage",orderinfo.getLastPage());
        System.out.println(admin.getStationId());
        return "waited-order";
    }

    /**
     * 根据订单编号查询状态为“已入库的订单”
     * @param orderid 订单编号
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/waitedOrderSelect")
    public Map selectWaitedOrder(@RequestBody String orderid,HttpServletRequest request){
        Map resultMap=new HashMap();
        //根据订单编号查询订单
        Order order = orderService.selectByOrderId(orderid);
        if(order!=null){
            //判断该订单是否是本站订单
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(order.getCurrentStation()==admin.getStationId()){
                //判断该订单状态是否为已入库
                if(order.getStatusId()==2){
                    resultMap.put("status",1);
                    resultMap.put("msg","查询成功");
                    resultMap.put("order",order);
                }else {
                    resultMap.put("status",0);
                    resultMap.put("msg","查询失败,该订单不是代发订单");
                }
            }else {
                resultMap.put("status",0);
                resultMap.put("msg","查询失败,订单不属于本站");
            }
        }else {
            resultMap.put("status",0);
            resultMap.put("msg","查询失败,订单号错误");
        }
        return resultMap;
    }

    /**
     * 点击库存订单查询时进行的页面跳转及分页处理
     * @param curPage
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/stockOrder/page/{curPage}")
    public String toStockOrderPage(@PathVariable(value = "curPage") int curPage,
                                    Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 6;
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //获取所有状态为待揽件的订单
        PageInfo orderinfo=orderService.selectStockOrder(admin.getStationId(),curPage,limit);
        map.put("orderList",orderinfo.getList());
        map.put("prePage",orderinfo.getPrePage());
        map.put("nextPage",orderinfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",orderinfo.getFirstPage());
        map.put("lastPage",orderinfo.getLastPage());
        System.out.println(admin.getStationId());
        return "stock-order";
    }

    /**
     * 查询待配送库存订单
     * @param orderid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stockOrderSelect")
    public Map selectStockOrder(@RequestBody String orderid,HttpServletRequest request){
        Map resultMap=new HashMap();
        //根据订单编号查询订单
        Order order = orderService.selectByOrderId(orderid);
        if(order!=null){
            //判断该订单是否是本站订单
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(order.getCurrentStation()==admin.getStationId()){
                //判断该订单终点站是不是本站
                if(order.getEndStation()==admin.getStationId()){
                    //判断该订单状态是否为已入库
                    if(order.getStatusId()==2){
                        resultMap.put("status",1);
                        resultMap.put("msg","查询成功");
                        resultMap.put("order",order);
                    }else {
                        resultMap.put("status",0);
                        resultMap.put("msg","查询失败,该订单不是已入库订单");
                    }
                }else {
                    resultMap.put("status",0);
                    resultMap.put("msg","查询失败,该订单不属于本站库存订单");
                }
            }else {
                resultMap.put("status",0);
                resultMap.put("msg","查询失败,订单不属于本站");
            }
        }else {
            resultMap.put("status",0);
            resultMap.put("msg","查询失败,订单号错误");
        }
        return resultMap;
    }
    @RequestMapping("/takeOrder/page/{curPage}")
    public String toTakeOrderPage(@PathVariable(value = "curPage") int curPage,Map map,HttpServletRequest request){

        //每页显示的数据行数
        int limit = 12;

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //获取站点内所有待揽件的订单
        PageInfo orderPageInfo = orderService.selectWaitingOrder(admin.getStationId(), curPage, limit);

        //获取当前站点下所有的快递员及每个人的任务数量
        List courierList = courierService.selectCourierByStationId(admin.getStationId());

        map.put("orderList",orderPageInfo.getList());
        map.put("prePage",orderPageInfo.getPrePage());
        map.put("nextPage",orderPageInfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",orderPageInfo.getFirstPage());
        map.put("lastPage",orderPageInfo.getLastPage());
        map.put("courierList",courierList);

        return "take-order";
    }

    /**
     * 批量指派揽件任务
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/assignCourier")
    public Map assignCourier(@RequestBody Map map,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Map resultMap = new HashMap();
        String courierAccount = map.get("courierAccount").toString();
        String courierName = map.get("courierName").toString();
        String substring = map.get("orderId").toString().substring(1, map.get("orderId").toString().length()-1);
        String[] orders = substring.split(",");

        for(int i = 0;i<orders.length;i++){
            orders[i] = orders[i].trim();
            //插入日志
            logService.insertLog(orders[i],"快递员: "+courierName+" 正在揽件...");
        }
        //批量更改订单状态
        orderService.batchAssign(orders,admin.getStationId());

        //添加揽件员的任务
        missionService.batchInsertMission(orders,courierAccount);

        resultMap.put("status",1);
        return resultMap;
    }

    /**
     * 批量指派配送件任务
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/assignDelivery")
    public Map assignDelivery(@RequestBody Map map,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Map resultMap = new HashMap();
        String courierAccount = map.get("courierAccount").toString();
        String courierName = map.get("courierName").toString();
        String substring = map.get("orderId").toString().substring(1, map.get("orderId").toString().length()-1);
        String[] orders = substring.split(",");

        for(int i = 0;i<orders.length;i++){
            orders[i] = orders[i].trim();
            //插入日志
            logService.insertLog(orders[i],"快递员: "+courierName+" 正在配送...");
        }
        //批量更改订单状态
        orderService.batchDelivery(orders,admin.getStationId());

        //添加揽件员的任务
        missionService.batchInsertMission(orders,courierAccount);

        resultMap.put("status",1);
        return resultMap;
    }

    /**
     * 跳转配送页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/deliveryOrder")
    public String toDeliveryOrderPage(Map map,HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        //获取当前站点下所有的快递员及每个人的任务数量
        List courierList = courierService.selectCourierByStationId(admin.getStationId());
        map.put("courierList",courierList);
        return "delivery";
    }

    /**
     * 确认收货
     * @return
     */
    @RequestMapping("/confirmOrder")
    public String toConfirmOrderPage(){
        return "confirm-order";
    }

}
