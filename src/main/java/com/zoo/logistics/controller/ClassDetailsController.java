package com.zoo.logistics.controller;


import com.zoo.logistics.entity.Car;
import com.zoo.logistics.entity.CarApplication;
import com.zoo.logistics.service.CarService;
import com.zoo.logistics.service.ClassDetailService;
import com.zoo.logistics.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.ldap.StartTlsRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ClassDetailsController {
    @Autowired
    public ClassDetailService classDetailService;
    @Autowired
    public CarService carService;
    @Autowired
    public  ClassService classService;

    @RequestMapping("/classDetails")
    public String ToPage1(HttpServletRequest request) {

        //审核信息详情页面
        //获取要审核的信息编号
        String id = request.getParameter("id");
        System.out.println("id=====" + id);


        //获取所有的车辆信息并分页
        List<Car> cars = carService.listCarBycarStatus(1, 3, request);

        //设置页面上相关的属性值
        request.setAttribute("cars", cars);

        System.out.println("车辆设置完毕");

        //调用设置属性的方法
        setAttr(Integer.parseInt(id), request);

        //设置索引
        request.setAttribute("classindex", "/classDetails");

        return "class-details";
    }



    /**
     * 设置页面需要的相关的公共属性
     *
     * @param id
     * @param request
     */
    public void setAttr(int id, HttpServletRequest request) {


        Map map = classDetailService.getAttr(id, request);

        request.setAttribute("id", id);
        request.setAttribute("stationName", map.get("stationName"));
        request.setAttribute("pathdetails", map.get("pathdetails"));
        request.setAttribute("applyCarcategory", map.get("carCategory"));
        request.setAttribute("applyCount", map.get("carCount"));
        request.setAttribute("status", map.get("status"));


    }

    @RequestMapping("/freecategoryCar")
    public String SelectCar(HttpServletRequest request) {

        System.out.println("修改了车型");
        String category = request.getParameter("carCategory");
        String id = request.getParameter("id");

        //根据id设置相关的公共属性
        setAttr(Integer.parseInt(id), request);


        //初始化类型编号
        int carCategoryid = 0;

        //根据车型设置页面
        if (!"haha".equals(category)) {
            System.out.println("根据车型查询");
            System.out.println(category);

            //根据页面传来的值修改对应的类型编号
            if (category.equals("BC")) {
                System.out.println("大车");
                carCategoryid = 1;
                List<Car> cars = carService.listCarByCategoryStatus(1, 3, carCategoryid, request);
                request.setAttribute("cars", cars);

            } else if (category.equals("MC")) {
                System.out.println("中车");
                carCategoryid = 2;
                List<Car> cars = carService.listCarByCategoryStatus(1, 3, carCategoryid, request);
                request.setAttribute("cars", cars);

            } else {
                System.out.println("小车");
                carCategoryid = 3;
                List<Car> cars = carService.listCarByCategoryStatus(1, 3, carCategoryid, request);
                request.setAttribute("cars", cars);

            }
        } else {
            //获取所有的车辆信息并分页
            List<Car> cars = carService.listCarBycarStatus(1, 3, request);

            //设置页面上相关的属性值
            request.setAttribute("cars", cars);
        }


        //设置索引
        request.setAttribute("classindex", "/freecategoryCar");
        request.setAttribute("carCategory", category);

        return "class-details";
    }


    @RequestMapping("/supplyCar")
    public String supplyCar(HttpServletRequest request) {

        //获取配的车辆的id
        String carID = request.getParameter("carID");

        //获取申请的编号
        String id =request.getParameter("id");


        System.out.println("配的车辆的id为===" + carID);

        //字符串分割，获取所有的车辆的id
        String [] carid = carID.split(",");

//        for(int a = 0;a<result.length;a++){
//            System.out.print(result[a]+"\t");
//        }


        //根据申请编号获取所有的属性
        Map map = classDetailService.getAttr(Integer.parseInt(id),request);

        //获取路线的id
        String RouteId = map.get("RouteId").toString();


        //对每辆车进行信息的更改
        for (int i = 0;i<carid.length;i++){
            //根据Id获取指定的车辆
            Car car = carService.FindCarByID(Integer.parseInt(carid[i]));

            //修改车辆的路线为之后的路线
            car.setRouteId(Integer.parseInt(RouteId));

            //修改车辆的状态为：工作中
            car.setCarStatus(2);

            System.out.println(car);

            //更新车辆信息
            carService.UpdateCarInfo(car);

        }

        //
        CarApplication carApplication = classService.FindOneById(Integer.parseInt(id));
        carApplication.setStatus(1);

        System.out.println(carApplication);

        //更新申请列表，将处理状态设置为已处理
        classService.UpdateClass(carApplication);


        //列表获取所有站点信息并分页
        List<CarApplication> classList = classService.ListAllPaging(request);

        //设置页面需要的相关的属性
        request.setAttribute("classes", classList);
        request.setAttribute("classindex", "/classManage");

        //跳转向申请页面
        return "class-manage";
    }

}
