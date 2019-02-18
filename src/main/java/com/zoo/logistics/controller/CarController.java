package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Car;
import com.zoo.logistics.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.TextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller

public class CarController {

    @Autowired
    private CarService carService;

    /**
     * @return 车辆列表
     */

    //显示所有信息的页面及其分页
    @RequestMapping("/carManage")
    public String toPage(HttpServletRequest request, HttpServletResponse response) {

//        int num = carService.CarCount();
        List<Car> cars = carService.listAllCarPaging(request);

        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");
        return "car-manage";
    }


    //根据查询的条件显示对应的页面
    @RequestMapping("/selectCar")
    public String toPage2(HttpServletRequest request, HttpServletResponse response) {

        String Carnumber = request.getParameter("licenseNumber");
        String carStatus = request.getParameter("carStatus");
        String carCategory = request.getParameter("carCategory");

        System.out.println("Carnumber===" + Carnumber);
        System.out.println("carCategory===" + carCategory);
        System.out.println("carStatus===" + carStatus);


        if (Carnumber != "") {
            System.out.println("根据车牌号查询");
            List<Car> cars = carService.listCarBylicenseNumber(Carnumber, request);
            request.setAttribute("cars", cars);
            request.setAttribute("carindex", "/selectCar");
            request.setAttribute("licenseNumber", Carnumber);

        } else if (!"haha".equals(carCategory) && !"haha".equals(carStatus)) {

            System.out.println("根据类型+状态查询");


        } else if (!"haha".equals(carCategory)) {
            System.out.println("根据类型查询");
            System.out.println(carCategory);

            //初始化类型编号
            int carCategoryid = 0;

            //根据页面传来的值修改对应的类型编号
            if (carCategory.equals("BC")) {
                carCategoryid = 1;
            } else if (carCategory.equals("MC")) {
                carCategoryid = 2;
            } else {
                carCategoryid = 3;
            }

            List<Car> cars = carService.listCarBycarCategory(carCategoryid, request);
            request.setAttribute("cars", cars);

            request.setAttribute("carindex", "/selectCar");
            request.setAttribute("carCategory", carCategory);
            request.setAttribute("carStatus", carStatus);


        } else if (!"haha".equals(carStatus)) {
            System.out.println("根据状态查询");
            System.out.println(carStatus);
            int carstatus1 = 0;
            int carstatus2 = 0;
            if (carStatus.equals("free")) {
                carstatus1 = 1;
                carstatus2 = 3;
            } else if (carStatus.equals("work")) {
                carstatus1 = 2;
                carstatus2 = 4;
            } else {
                carstatus1 = 9;
                carstatus2 = 9;
            }
            System.out.println(carstatus1 + "," + carstatus2);

            List<Car> cars = carService.listCarBycarStatus(carstatus1, carstatus2, request);
            request.setAttribute("cars", cars);

            request.setAttribute("carindex", "/selectCar");
            request.setAttribute("carStatus", carStatus);
            request.setAttribute("carCategory", carCategory);
        }


//        if (Carnumber != null){//车牌号不为空，根据车牌号查询
//            //获取service中按照车牌号查询的
//            List<Car> cars = carService.listCarBylicenseNumber(Carnumber,request);
////            System.out.println(cars);
//            request.setAttribute("cars", cars);
//            request.setAttribute("carindex", "/selectCar");
//        }else if(carCategory != null &&carStatus != null){ //车辆状态和种类都不为空
//
//        }
//        else if (carCategory != null){//车辆种类不为空
//
//        }else  if (carStatus != null){//车辆状态不为空
//
//        }

        return "car-manage";


    }

    //删除车辆
    @RequestMapping("/deleteCar")
    public String toPage3(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        carService.deleteCarById(id);

        List<Car> cars = carService.listAllCarPaging(request);

        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");
        return "car-manage";
    }


    //添加车辆
    @RequestMapping("/AddCar")
    public String toPage4(HttpServletRequest request, HttpServletResponse response) {

        //获取车牌号
        String licenseNum = request.getParameter("licenseNum");
        //获取车辆类型编号
        String CarType = request.getParameter("CarType");
        //获取路线编号
        String pathNum = request.getParameter("pathNum");
        //获取购买时间
        String BuyTime = request.getParameter("BuyTime");
        //获取省份信息
        String province = request.getParameter("province");
        //获取备注信息
        String remark = request.getParameter("remark");

//        System.out.println(licenseNum);
//        System.out.println(CarType);
//        System.out.println(pathNum);
//        System.out.println(BuyTime);
//        System.out.println(province);
//        System.out.println(remark);

        //实例化car对象，并赋值
        Car car = new Car();
        car.setCarCategoryId(Integer.parseInt(CarType));
        car.setCarStatus(1);
        car.setLicenseNumber(licenseNum);
        car.setRouteId(Integer.parseInt(pathNum));

        //调用service的car方法
        carService.addCar(car);


        //获取所有的列表并分页显示
        List<Car> cars = carService.listAllCarPaging(request);
        //设置相关的属性
        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");
        //返回网页
        return "car-manage";


    }

    //Ajax验证车辆是否存在
    @RequestMapping("/checkLicenseNum")
    public void checkLicenseNum(HttpServletRequest request, HttpServletResponse response) throws IOException {



        //设置请求的编码格式
        request.setCharacterEncoding("UTF-8");
        //获取通过ajax设置的参数的值
        String carNum = request.getParameter("context");
        System.out.println(carNum);



        //判断输入的参数是否是一个车牌号
        boolean isCar = isCarNum(carNum);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();


        if (isCar) { //当输入的是一个车牌号

            List<Car> cars = carService.existCarBylicenseNumber(carNum);
            System.out.println(cars.size());
            if (cars.size()==0){
                pw.write("<font color=\"green\" size=\"3px\">验证通过！</font>");
            }else {
                pw.write("<font color=\"red\" size=\"3px\">已存在！</font>");
            }



        } else if (!isCar && carNum!=""){
            pw.write("<font color=\"red\" size=\"3px\">格式错误！</font>");
        }else {
            pw.write("");
        }


    }

    //车牌号的正则表达式验证
    public static boolean isCarNum(String number) {

        String carnumRegex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        if (number == "" || number == null) return false;
        else return number.matches(carnumRegex);


    }


}
