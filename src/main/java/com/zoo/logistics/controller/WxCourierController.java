package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Order;
import com.zoo.logistics.entity.OrderAmount;
import com.zoo.logistics.service.LogService;
import com.zoo.logistics.service.MissionService;
import com.zoo.logistics.service.OrderAmountService;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxCourierController {

    @Autowired
    private MissionService missionService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderAmountService orderAmountService;
    @Autowired
    private LogService logService;

    /**
     * 快递员获取揽件/配送任务
     * @param account
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMission")
    public Map getMission(@RequestBody String account){
        Map map = new HashMap();
        List missions = missionService.selectByAccount(account);
        if(missions.size()>0){
            map.put("status",1);
            map.put("missions",missions);
        }else{
            map.put("status",0);
        }
        return map;
    }

    /**
     * 快递员揽件，确认货物重量和体积
     * @param orderInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/submitOrderInfo")
    public Map submitOrderInfo(@RequestBody Map orderInfo){
        Map map = new HashMap();
        Order order = orderService.selectByOrderId(orderInfo.get("orderId").toString());
        order.setWeight(Integer.parseInt(orderInfo.get("weight").toString()));
        order.setVolume(Integer.parseInt(orderInfo.get("volume").toString()));
        orderService.update(order);
        orderAmountService.insertSelective(new OrderAmount(order.getOrderId(),Float.valueOf(orderInfo.get("price").toString())));
        missionService.updateMissionStatus(order.getOrderId());
        map.put("status",1);
        return map;
    }

    /**
     * 快递员完成配送任务
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/finishMission")
    public Map finishMission(@RequestBody String orderId){
        Map map = new HashMap();
        Order order = orderService.selectByOrderId(orderId);
        missionService.updateMissionStatus(orderId);
        order.setStatusId(5);
        order.setCurrentStation(0);
        order.setFinishDate(new Date());
        //更新状态
        orderService.update(order);
        //更新日志
        logService.insertLog(order.getOrderId(),"订单已配送");
        map.put("status",1);
        return map;
    }
}
