package com.zoo.logistics.controller;

import com.zoo.logistics.api.Distance;
import com.zoo.logistics.entity.*;
import com.zoo.logistics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxOrderLogController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private LogService logService;
    @Autowired
    private StationService stationService;
    @Autowired
    private EquipmentReceiptService equipmentReceiptService;
    @Autowired
    private CarService carService;

    /**
     * 获取订单的历史信息
     * @param requestMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderLogs")
    public Map getOrderLogs(@RequestBody Map requestMap){
        String orderId = requestMap.get("orderId").toString();
        System.out.println(orderId);
        Map map = new HashMap();
        //获取货物信息
        Order order = orderService.selectByOrderId(orderId);
        Station startStation = stationService.selectByPrimaryKey(order.getStartStation());
        Station endStation = stationService.selectByPrimaryKey(order.getEndStation());
        map.put("startStation",startStation);
        map.put("endStation",endStation);
        map.put("currentStation",order.getCurrentStation()==null?null:stationService.selectByPrimaryKey(order.getCurrentStation()));
        map.put("order",order);
        //获取日志
        List<Log> logs = logService.selectByOrderIdDesc(orderId);
        System.out.println("logs:"+logs);
        map.put("logs",logs);
        if(order.getStatusId()==3){
            map.put("carStatus","working");
            Integer equipmentReceiptId = order.getEquipmentReceiptId();
            Car car = carService.selectByEquipmentReceiptId(equipmentReceiptId);
            if(car.getCarStatus()==2){
                map.put("lastStation",car.getRoute().getStartStation());
                map.put("nextStation",car.getRoute().getEndStation());

            }else if (car.getCarStatus()==4){
                map.put("nextStation",car.getRoute().getStartStation());
                map.put("lastStation",car.getRoute().getEndStation());
            }
            int dist = Distance.getDistance(car.getRoute().getStartStation().getPosLng()+","+car.getRoute().getStartStation().getPosLat(),
                    car.getRoute().getEndStation().getPosLng()+","+car.getRoute().getEndStation().getPosLat());
            map.put("scale",getScale(dist));
        }else if (order.getStatusId()==2){
            Station currentStation = stationService.selectByPrimaryKey(order.getCurrentStation());
            map.put("carStatus","inStation");
            map.put("currentPosLng",currentStation.getPosLng());
            map.put("currentPosLat",currentStation.getPosLat());
            map.put("scale",10);
        }else if(order.getStatusId()==1||order.getStatusId()==5){
            int dist = Distance.getDistance(startStation.getPosLng()+","+startStation.getPosLat(), endStation.getPosLng()+","+endStation.getPosLat());
            map.put("scale",getScale(dist));
        }

        return map;
    }

    /**
     * 根据距离求得map的缩放级别
     * @param dist 距离
     * @return scale值
     */
    private int getScale(int dist){
        if(dist>1700000)
            return 6;
        else if(dist>1000000)
            return 7;
        else if(dist>600000)
            return 8;
        else if(dist>300000)
            return 9;
        else if(dist>100000)
            return 10;
        else if(dist>60000)
            return 11;
        else if(dist>30000)
            return 12;
        else
            return 13;
    }

}
