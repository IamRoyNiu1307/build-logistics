package com.zoo.logistics.controller;

import com.github.pagehelper.PageInfo;
import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.entity.ExceptionCategory;
import com.zoo.logistics.entity.ExceptionOrder;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.service.ExceptionCategoryService;
import com.zoo.logistics.service.ExceptionOrderService;
import com.zoo.logistics.service.LogService;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class OrderExceptionController {
    @Autowired
    OrderService orderService;
    @Autowired
    LogService logService;
    @Autowired
    ExceptionCategoryService exceptionCategoryService;
    @Autowired
    ExceptionOrderService exceptionOrderService;

    /**
     * 页面打开时显示异常单数量
     * @param stationId 当前站点Id
     * @param request
     * @return 当前站点下的异常单的数量
     */
    @ResponseBody
    @RequestMapping(value = "/orderException")
    public int selectOrderException(@RequestBody String stationId, HttpServletRequest request){
        //查找当前站点的订单状态为“异常”的订单
        List<Order> orderExceptionList = orderService.selectOrderExceptionByStationId(Integer.parseInt(stationId));
        //获取异常单的数量
        int orderExceptionCount=orderExceptionList.size();
        request.getSession().setAttribute("orderExceptionCount",orderExceptionCount);
        return orderExceptionCount;
    }

    /**
     * 点击异常单登记处理时进行的跳转以及数据的分页处理
     * @param curPage 当前页数
     * @param map
     * @param request
     * @return 跳转页面
     */
    @RequestMapping(value = "/exceptionOrderRegister/page/{curPage}")
    public String exceptionOrderRegister(@PathVariable(value = "curPage") int curPage,
                                         Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 8;
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("req get:"+request.getSession().getAttribute("admin"));
        //获取所有状态为异常的订单
        PageInfo orderinfo=orderService.selectOrderExceptionByStationIdWithPageInfo(admin.getStationId(),curPage,limit);
        //获取所有的异常原因
        List<ExceptionCategory> exceptionCategoryList = exceptionCategoryService.selectAllExceptionCategory();
        map.put("orderList",orderinfo.getList());
        map.put("prePage",orderinfo.getPrePage());
        map.put("nextPage",orderinfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",orderinfo.getFirstPage());
        map.put("lastPage",orderinfo.getLastPage());
        map.put("exceptionCategoryList",exceptionCategoryList);
        return "exception-order-register";
    }

    /**
     * 对异常单的处理提交
     * @param exceptionOrder 异常单对象
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exceptionOrderRegister/edit")
    public Map edit(@RequestBody ExceptionOrder exceptionOrder, HttpServletRequest request){
        Map resultMap=new HashMap();
        //向异常单处理表中添加已处理的异常单信息
        exceptionOrderService.insertExceptionOrder(exceptionOrder);
        //获取当前时间
        Date finshDate=new Date();
        //根据订单ID查找到这个异常单
        Order order = orderService.selectByOrderId(exceptionOrder.getOrderId());
        //将异常单的状态改为异常已处理
        order.setStatusId(10);
        //将当前时间设置为订单的结束时间
        order.setFinishDate(finshDate);
        //更新订单的信息
        orderService.updateExceptionOrder(order);
        resultMap.put("status",1);
        resultMap.put("msg","提交成功");
        return resultMap;
    }

    /**
     * 异常单查询
     * @param orderid 订单id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exceptionOrderRegister/select")
    public Map selectExceptionOrder(@RequestBody String orderid,HttpServletRequest request){
        Map resultMap=new HashMap();
        //根据订单Id查询出订单
        Order order = orderService.selectByOrderId(orderid);
        //判断是否查询出订单
        if(order!=null){
            //判断该订单是否为本站订单
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(order.getCurrentStation()==admin.getStationId()) {
                //判断查询出的订单是否为异常单
                if (order.getStatusId() == 9) {
                    resultMap.put("status", 1);
                    resultMap.put("msg", "查询成功");
                    resultMap.put("order", order);
                } else {
                    resultMap.put("status", 0);
                    resultMap.put("msg", "查询失败,该订单不为异常单");
                }
            }else {
                resultMap.put("status",0);
                resultMap.put("msg","查询失败,该订单不属于本站");
        }
        }else {
            resultMap.put("status",0);
            resultMap.put("msg","查询失败,订单号错误");
        }
        return resultMap;
    }

    /**
     * 跳转到订单异常处理查询时执行的跳转以及数据分页
     * @param curPage
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/exceptionOrderRegisterSelect/page/{curPage}")
    public String exceptionOrderRegisterSelect(@PathVariable(value = "curPage") int curPage,
                                         Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 8;
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("req get:"+request.getSession().getAttribute("admin"));
        //获取所有状态为“异常已处理”的订单
        PageInfo exceptionOrderinfo=exceptionOrderService.selectExceptionOrderRegisterByStationIdWithPageInfo(admin.getStationId(),curPage,limit);
        map.put("exceptionOrderList",exceptionOrderinfo.getList());
        map.put("prePage",exceptionOrderinfo.getPrePage());
        map.put("nextPage",exceptionOrderinfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",exceptionOrderinfo.getFirstPage());
        map.put("lastPage",exceptionOrderinfo.getLastPage());
        return "exception-order-register-select";
    }

    /**
     * 根据orderId查询本站的异常处理单
     * @param orderid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exceptionOrderRegisterSelect/select")
    public Map selectExceptionOrderRegister(@RequestBody String orderid,HttpServletRequest request){
        Map resultMap=new HashMap();
        //根据订单Id查询出订单
        Order order = orderService.selectByOrderId(orderid);
        //判断是否查询出订单
        if(order!=null){
            //判断该订单是否为本站订单
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if(order.getCurrentStation()==admin.getStationId()){
                //判断查询出的订单是否为异常处理单
                if(order.getStatusId()==10){
                    resultMap.put("status",1);
                    resultMap.put("msg","查询成功");
                    resultMap.put("order",order);
                }else {
                    resultMap.put("status",0);
                    resultMap.put("msg","查询失败,该订单不为异常处理单");
                }
            }else {
                resultMap.put("status",0);
                resultMap.put("msg","查询失败,此订单不属于本站");
            }
        }else {
            resultMap.put("status",0);
            resultMap.put("msg","查询失败,订单号错误");
        }
        return resultMap;
    }
}
