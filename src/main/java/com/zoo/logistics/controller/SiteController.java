package com.zoo.logistics.controller;


import com.google.gson.Gson;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SiteController {

    @Autowired
    public SiteService siteService;

    //跳转向显示所有信息的页面
    @RequestMapping("/siteManage")
    public String toPage1(HttpServletRequest request, HttpServletResponse response) {
        List<Station> stations = siteService.listAllStationPaging(request);

        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/siteManage");
        return "site-manage";
    }


    //根据查询条件返回对应的列表
    @RequestMapping("/selectSite")
    public String toPage2(HttpServletRequest request, HttpServletResponse response) {

        String address = "";
        String cityName = "";

        address = request.getParameter("selectByCityName");


        if (address.length() >= 7) {


            if (address.lastIndexOf("/") > address.indexOf("/")) {
                cityName = address.substring(address.indexOf("/") + 1, address.lastIndexOf("/"));
            } else {
                cityName = address.substring(address.indexOf("/") + 1);
            }

            System.out.println(cityName);

        }
        List<Station> stations = siteService.listSiteByCityNamePaging(request, cityName);

        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/selectSite");
        request.setAttribute("selectByCityName", address);

        return "site-manage";


    }

    @ResponseBody
    @RequestMapping("/querySite")
    public Map querySite(HttpServletRequest request, @RequestBody Map tempMap) throws IOException {
        System.out.println("获取要更新的站点信息！");

        Map map = new HashMap();


        String id = tempMap.get("id").toString();
        Station station = siteService.FindStationByID(request, Integer.parseInt(id));

        String stationName = station.getStationName();
        System.out.println("stationName=====" + stationName);

        String stationAddress = stationName.substring(0, stationName.length() - 3);
        System.out.println("stationAddress====" + stationAddress);

        String province = stationAddress.substring(0, 3);
        System.out.println("province===" + province);


        String city = "";
        if (stationAddress.length() > 7) {//省级总站点无城市选项
            city = stationAddress.substring(4, stationAddress.length()-1);
        }


    //  String city = stationAddress.substring(4,stationAddress.length());
        System.out.println("city====" + city);

    //设置页面要使用的相关的属性
        System.out.println(station);
        map.put("id", station.getId());
        map.put("tel", station.getTel());
        //map.put("stationAddress",stationAddress);
        map.put("province", province);
        map.put("city", city);
        map.put("stationName", stationName);


        return map;

    }


//    @RequestMapping("/updateSite")
//    public String UpdateSite(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("更新站点信息！");
//        String id = request.getParameter("id");
//
//        System.out.println("要修改的站点的id为：" + id);
//
//        return "site-manage";
//
//
//    }

    //删除站点信息
    @RequestMapping("/deleteSite")
    public String toPage3(HttpServletRequest request) {

        //获取接收到的要删除的id
        int id = Integer.parseInt(request.getParameter("id"));

        //执行删除操作
        siteService.deleteSiteById(id);

        List<Station> stations = siteService.listAllStationPaging(request);

        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/siteManage");

        return "site-manage";
    }


    //更新站点信息
    @RequestMapping("/updateSite")
    public String UpdateAdmin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("更新站点信息！");

        //获取从form表单中传递的id信息和level信息
        String id = request.getParameter("siteId");
        String phone = request.getParameter("phone");

        System.out.println("要修改的用户id为：" + id);
        System.out.println("联系电话修改为：" + phone);

        //获取对应的site对象
        Station station = siteService.FindStationByID(request,Integer.parseInt(id));
        //修改对应的级别属性
        station.setTel(phone);

        //数据库中执行修改操作
        siteService.updateSiteInfo(station);


        List<Station> stations = siteService.listAllStationPaging(request);

        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/siteManage");

        return "site-manage";


    }


}
