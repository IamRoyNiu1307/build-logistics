package com.zoo.logistics.controller;


import com.google.gson.Gson;
import com.zoo.logistics.api.ReGeo;
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

/**
 * 站点管理控制
 */
@Controller
public class SiteController {

    @Autowired
    public SiteService siteService;


    /**
     * @param request
     * @param response
     * @return 显示所有的站点信息的页面
     */
    @RequestMapping("/siteManage")
    public String ToPage1(HttpServletRequest request, HttpServletResponse response) {
        //列表获取所有站点信息并分页
        List<Station> stations = siteService.listAllStationPaging(request);

        //设置页面需要的相关的属性
        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/siteManage");
        return "site-manage";
    }


    /**
     *
     * 根据查询条件返回对应的列表
     * @param request
     * @param response
     * @return 符合相关查询条件的所有的站点信息页面
     * <p>
     * <p>
     *
     */
    @RequestMapping("/selectSite")
    public String ToPage2(HttpServletRequest request, HttpServletResponse response) {

        //初始化站点地址
        String address = "";
        String cityName = "";

        //获取从页面表单中传递的查询的城市名称
        address = request.getParameter("selectByCityName");


        //城市名称的截取
        if (address.length() >= 7) {


            if (address.lastIndexOf("/") > address.indexOf("/")) {
                cityName = address.substring(address.indexOf("/") + 1, address.lastIndexOf("/"));
            } else {
                cityName = address.substring(address.indexOf("/") + 1);
            }

            System.out.println(cityName);

        }
        //列表获取所有的符合条件的站点信息并分页
        List<Station> stations = siteService.listSiteByCityNamePaging(request, cityName);

        //设置相关的属性值
        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/selectSite");
        request.setAttribute("selectByCityName", address);

        return "site-manage";


    }

    /**
     * ajax 获取指定站点的所有的相关信息，并返回相关的页面
     * @param request
     * @param tempMap
     * @return 指定ID的站点的所有信息
     * @throws IOException 根据ID查询对应的站点信息
     *
     */
    @ResponseBody
    @RequestMapping("/querySite")
    public Map QuerySite(HttpServletRequest request, @RequestBody Map tempMap) throws IOException {
        System.out.println("获取要更新的站点信息！");

        //初始化map存放相关的站点的信息
        Map map = new HashMap();

        //获取要修改的站点的ID
        String id = tempMap.get("id").toString();

        //根据站点ID获取到指定的站点对象
        Station station = siteService.FindStationByID(request, Integer.parseInt(id));

        //获取到站点对象的stationName属性
        String stationName = station.getStationName();
        System.out.println("stationName=====" + stationName);

        //获取到站点对象的Address属性
        //String stationAddress = stationName.substring(0, stationName.length() - 3);
        //System.out.println("stationAddress====" + stationAddress);

        //获取到站点对象的province属性
        //String province = stationAddress.substring(0, 3);
        //System.out.println("province===" + province);


//        String city = "";
//        if (stationAddress.length() > 5) {//省级总站点无城市选项
//            city = stationAddress.substring(3, stationAddress.length() - 1);
//        }

        Map reGeoMap = ReGeo.reGeo(station.getPosLng(), station.getPosLat());
        String province = reGeoMap.get("province").toString();
        String city = reGeoMap.get("city").toString();

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

    /**
     * 执行删除站点的操作
     * @param request
     * @return 执行了删除操作后的站点信息页面
     *
     */
    @RequestMapping("/deleteSite")
    public String ToPage3(HttpServletRequest request) {

        //获取接收到的要删除的id
        int id = Integer.parseInt(request.getParameter("id"));

        //执行删除操作
        siteService.deleteSiteById(id);

        //删除相关路线

        //列表获取最新的所有的站点信息
        List<Station> stations = siteService.listAllStationPaging(request);

        //设置相关的页面的属性值
        request.setAttribute("stations", stations);
        request.setAttribute("stationindex", "/siteManage");

        return "site-manage";
    }

    /**
     * 更新站点信息
     * @param request
     * @param response
     * @return 获取到表单提交的指定的ID的站点修改后的信息
     *
     */
    @RequestMapping("/updateSite")
    public String UpdateAdmin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("更新站点信息！");

        //获取从form表单中传递的id信息和level信息
        String id = request.getParameter("siteId");
        String phone = request.getParameter("phone");

        System.out.println("要修改的用户id为：" + id);
        System.out.println("联系电话修改为：" + phone);

        //获取对应的site对象
        Station station = siteService.FindStationByID(request, Integer.parseInt(id));
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
