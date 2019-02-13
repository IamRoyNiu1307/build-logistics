package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.entity.EquipmentReceipt;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.service.CarService;
import com.zoo.logistics.service.EquipmentReceiptService;
import com.zoo.logistics.service.LogService;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("search order:"+map.get("orderId"));
        Map resultMap = orderService.getOrder(String.valueOf(map.get("orderId")));
        if("putin".equals(map.get("todo"))&&"查找成功".equals(resultMap.get("msg").toString())){
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            Order order = (Order) resultMap.get("order");
            order.setStatusId(2);
            order.setCurrentStation(admin.getStationId());
            //更新状态
            orderService.update(order);
            //更新日志
            logService.insertLog(order.getOrderId(),"【"+admin.getStation().getStationName()+"】已入库");
        }
        return resultMap;
    }

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

}
