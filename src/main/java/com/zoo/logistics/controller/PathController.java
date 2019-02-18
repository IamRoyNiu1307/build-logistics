package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Route;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.service.PathService;
import com.zoo.logistics.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PathController {

    @Autowired
    public PathService pathService;
    @Autowired
    public SiteService siteService;

    @RequestMapping("/pathManage")
    public String topage(HttpServletRequest request, HttpServletResponse response) {

        //获取所有的信息并分页
        List<Route> routeList = pathService.listAllPaging(request);
//        List<Station> stations = siteService.listAllStation(request);



        //设置页面上的相关属性
//        request.setAttribute("stations", stations);
        request.setAttribute("routes", routeList);
        request.setAttribute("pathindex", "/pathManage");

        return "path-manage";
    }

    /*
    根据查询跳转页面
     */
    @RequestMapping("/selectPath")
    public String toPage2(HttpServletRequest request) {

        //获取起始地和目的地的信息
        String startArea = request.getParameter("startArea");
        String endArea = request.getParameter("endArea");

        System.out.println("startArea===" + startArea);
        System.out.println("endArea===" + endArea);

        //起始地，目的地都不为空
        if (startArea != "" && endArea != "") {
            System.out.println("两者都不为空的查询");

            //从service中的方法获取到对应条件下的相关信息列表
            List<Route> routeList = pathService.listByRoutePaging(startArea,endArea,request);

            for (Route route:routeList) {
                System.out.println("route====="+route);
            }

            //设置页面上的相关属性
            request.setAttribute("routes", routeList);
            request.setAttribute("pathindex", "/selectPath");
            request.setAttribute("startArea",startArea);
            request.setAttribute("endArea",endArea);


        }else if (startArea !="" && endArea == ""){//起始地不为空，目的地为空
            System.out.println("起始地不为空，目的地为空的查询");

            //从service中的方法获取到对应条件下的相关信息列表
            List<Route> routeList = pathService.ListAllByStartArea(startArea,request);

            for (Route route:routeList) {
                System.out.println("route====="+route);
            }

            //设置页面上的相关属性
            request.setAttribute("routes", routeList);
            request.setAttribute("pathindex", "/selectPath");
            request.setAttribute("startArea",startArea);
            request.setAttribute("endArea",endArea);




        }else if (startArea ==""&& endArea !=""){//起始地为空，目的地不为空
            System.out.println("起始地为空，目的地不为空的查询");
            //从service中的方法获取到对应条件下的相关信息列表
            List<Route> routeList = pathService.ListAllByEndArea(endArea,request);

            for (Route route:routeList) {
                System.out.println("route====="+route);
            }

            //设置页面上的相关属性
            request.setAttribute("routes", routeList);
            request.setAttribute("pathindex", "/selectPath");
            request.setAttribute("startArea",startArea);
            request.setAttribute("endArea",endArea);

        }else {//起始地，目的地都为空
            System.out.println("两者都为空的查询");
            List<Route> routeList = pathService.listAllPaging(request);

            //设置页面上的相关属性
            request.setAttribute("routes", routeList);
            request.setAttribute("pathindex", "/selectPath");
            request.setAttribute("startArea",startArea);
            request.setAttribute("endArea",endArea);
        }




        return "path-manage";
    }


    //删除车辆
    @RequestMapping("/deleteRoute")
    public String toPage3(HttpServletRequest request) {

        //获取接收到的要删除的id
        int id = Integer.parseInt(request.getParameter("id"));

        //执行删除操作
        pathService.deleteRouteById(id);

        //重新加载更改后的列表
        List<Route> routeList = pathService.listAllPaging(request);

        //设置相关的属性
        request.setAttribute("routes", routeList);
        request.setAttribute("pathindex", "/pathManage");

        return "path-manage";
    }

    @ResponseBody
    @RequestMapping("/displayStationName")
    public Map StationName(HttpServletRequest request, HttpServletResponse response,@RequestBody Map tempMap)throws IOException {

        System.out.println("根据id 获取station的名字");

        String StationNum = tempMap.get("context").toString();
       // Station station = siteService.FindStationByID(request, Integer.parseInt(id));
        System.out.println(StationNum);

        Station station =  null;


        //对传入的参数进行数字验证

        boolean isnum = isNum(StationNum);
        System.out.println("进行数字验证===="+isnum);

        //设置输出的格式
        response.setContentType("text/html;charset=UTF-8");

 //       PrintWriter pw = response.getWriter();
//        pw.write(station.getStationName());
        Map map = new HashMap();
        //标识起始点的情况
       int start_verify = 0 ;
        if (isnum) { //当输入的是一个数字
            //根据数字获得对应的station
            station = siteService.FindStationByID(request,Integer.parseInt(StationNum));

            //可以查询到对应的station
            if (station != null){
                System.out.println(station.getStationName());

                start_verify = 1;

                map.put("stationName",station.getStationName());
                map.put("stationverify",start_verify);
            }else {//不存在对应的station

                start_verify = 4;

                map.put("stationName","");
                map.put("stationverify",start_verify);
            }




        } else if (!isnum && StationNum!=""){   //不为空且不是一个数字
            start_verify = 2;

            map.put("stationName","");
            map.put("stationverify",start_verify);


        }else { //为空

            start_verify = 3;

            map.put("stationName","");
            map.put("stationverify",start_verify);
        }

        System.out.println("start_verify====="+start_verify);




//        map.put("stationName",station.getStationName());
//        map.put("stationverify",start_verify);
        return map;








    }

    //传入的参数的正则表达式验证
    public static boolean isNum(String number) {

        String numRegex = "^[0-9]*[1-9][0-9]*$";
        if (number == "" || number == null) return false;
        else return number.matches(numRegex);


    }



    @RequestMapping("/checkPath")
    public void checkPath(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("验证路线是否存在！！！！");
        //设置请求的编码格式
        request.setCharacterEncoding("UTF-8");
        //获取通过ajax设置的参数的值
        String end = request.getParameter("context");
        String start = request.getParameter("context2");
        System.out.println("start==="+start);
        System.out.println("end==="+end);

        boolean exist = pathService.RouteExist(Integer.parseInt(start),Integer.parseInt(end),request);

        System.out.println("路线是否存在"+exist);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        if (exist){
            pw.write("<font color=\"red\" size=\"3px\">路线已存在！</font>");
        }else {
            pw.write("<font color=\"green\" size=\"3px\">验证通过，可以添加此路线！</font>");
        }


    }


    @RequestMapping("/addPath")
    public String toPage4(HttpServletRequest request, HttpServletResponse response) {

        //获取起始地
        String startSiteNum = request.getParameter("startSiteNum");
        //获取目的地
        String endSiteNum = request.getParameter("endSiteNum");
        //获取备注信息
        String remark = request.getParameter("remark");

        System.out.println("startSiteNum====="+startSiteNum);
        System.out.println("endSiteNum======="+endSiteNum);
//        System.out.println(pathNum);
//        System.out.println(BuyTime);
//        System.out.println(province);
//        System.out.println(remark);

        //实例化car对象，并赋值
        Route route = new Route();
        route.setStartStationId(Integer.parseInt(startSiteNum));
        route.setEndStationId(Integer.parseInt(endSiteNum));

        //调用service的car方法
       pathService.addRoute(route);

        //获取所有的信息并分页
        List<Route> routeList = pathService.listAllPaging(request);


        //设置页面上的相关属性
        request.setAttribute("routes", routeList);
        request.setAttribute("pathindex", "/pathManage");

        return "path-manage";


    }


}
