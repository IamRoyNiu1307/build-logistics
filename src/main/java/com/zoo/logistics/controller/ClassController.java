package com.zoo.logistics.controller;

import com.zoo.logistics.entity.CarApplication;
import com.zoo.logistics.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClassController {

    @Autowired
    public ClassService classService;

    @RequestMapping("/classManage")
    public String ToPage1(HttpServletRequest request){

        //列表获取所有站点信息并分页
        List<CarApplication> classList = classService.ListAllPaging(request);

        //设置页面需要的相关的属性
        request.setAttribute("classes", classList);
        request.setAttribute("classindex", "/classManage");

        return  "class-manage";
    }

    @RequestMapping("/selectClass")
    public String ToPage2(HttpServletRequest request){

        String stationName = request.getParameter("stationName");
        String classStatus = request.getParameter("classStatus");


        System.out.println("stationName==="+stationName);
        System.out.println("classStatus"+classStatus);

        List<CarApplication> classList;

        if (!"".equals(stationName)) {   //站点名不为空，则根据站点名称查询
            System.out.println("站点名查询");
            classList = classService.ListAllByStationNamePaging(stationName, request);

        }
//        else if (!"haha".equals(classStatus)){
//            System.out.println("申请状态查询");
//            classList = classService.ListAllByClassStatusPaging(Integer.parseInt(classStatus),request);
//
//        }
        else {
            System.out.println("空查询");
            classList = classService.ListAllPaging(request);
        }



        //设置页面需要的相关的属性
        request.setAttribute("classes", classList);


        request.setAttribute("classindex", "/selectClass");

        request.setAttribute("stationName", stationName);
//        request.setAttribute("classStatus", classStatus);

        return  "class-manage";
    }


}
