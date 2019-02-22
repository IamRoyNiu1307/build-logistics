package com.zoo.logistics.service;

import com.zoo.logistics.entity.CarApplication;
import com.zoo.logistics.mapper.CarApplicationMapper;
import com.zoo.logistics.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClassDetailService {

    @Autowired
    public CarApplicationMapper classMapper;

    @Autowired
    public StationMapper stationMapper;


    //获得页面基础信息
    public Map getAttr(int id, HttpServletRequest request){

       CarApplication carApplication = classMapper.selectByPrimaryKey(id);

       //获取站点名称
       String stationName = carApplication.getStation().getStationName();

       //路线详细信息
        int RouteId = carApplication.getRouteId();
        String startStationName =  carApplication.getRoute().getStartstation().getStationName();
        String endStationName = carApplication.getRoute().getEndstation().getStationName();

        //申请车辆类型
        String carCategory = carApplication.getCarCategory().getName();

        //申请车辆数量
        int carCount = carApplication.getCarCount();

        //状态
        String  status = "";
        int statusID =carApplication.getStatus();

        if (statusID == 0 ){
            status ="待审核";
        }else if (statusID == 1){
            status ="已审核";
        }else {

        }

        System.out.println("stationName==="+stationName);
        System.out.println("startStationName==="+startStationName);
        System.out.println("endStationName==="+endStationName);
        System.out.println("carCategory==="+carCategory);
        System.out.println("carCount==="+carCount);
        System.out.println("status==="+status);

       Map map = new HashMap();
       map.put("stationName",stationName);
       map.put("pathdetails",startStationName+"   --------->   "+endStationName);
       map.put("RouteId",RouteId);
       map.put("carCategory",carCategory);
       map.put("carCount",carCount);
       map.put("status",status);


       return  map;
    }

}
