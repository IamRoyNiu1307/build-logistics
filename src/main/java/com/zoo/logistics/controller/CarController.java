package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Car;
import com.zoo.logistics.entity.Route;
import com.zoo.logistics.service.CarService;
import com.zoo.logistics.service.PathService;
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
    @Autowired
    private PathService pathService;

    /**
     * 显示所有信息的页面及其分页
     * @return 车辆列表
     *
     */
    @RequestMapping("/carManage")
    public String toPage(HttpServletRequest request, HttpServletResponse response) {

        //获取所有的车辆信息并分页
        List<Car> cars = carService.listAllCarPaging(request);

        AddPath(cars);

        //设置页面上相关的属性值
        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");

        return "car-manage";
    }


    /**
     * 给对应的车辆按照路线id添加路线详情
     * @param cars
     */

    public void AddPath(List<Car> cars){
        //添加路线操作
        for (Car car:cars) {
            Route route = pathService.selectByPrimaryKey(car.getRouteId());
            String startStationName =  route.getStartstation().getStationName();
            String endStationName = route.getEndstation().getStationName();

            String pathdetails = startStationName+"    --------->    "+endStationName;

            car.setPathdetails(pathdetails);

        }


    }


    /**
     * 根据查询的条件显示对应的页面
     * @param request
     * @param response
     * @return  显示所有指定条件的车辆信息的页面
     *
     */
    @RequestMapping("/selectCar")
    public String toPage2(HttpServletRequest request, HttpServletResponse response) {

        //获取从页面表单所提交的相关查询条件---车牌号/车辆状态/车辆类型
        String Carnumber = request.getParameter("licenseNumber");
        String carStatus = request.getParameter("carStatus");
        String carCategory = request.getParameter("carCategory");

        System.out.println("Carnumber===" + Carnumber);
        System.out.println("carCategory===" + carCategory);
        System.out.println("carStatus===" + carStatus);

        //对表单传来的数据进行分析并执行相关的操作
        if (Carnumber != "") {

            System.out.println("根据车牌号查询");

            //根据车牌号获取相关的列表信息并分页
            List<Car> cars = carService.listCarBylicenseNumber(Carnumber, request);
            //设置页面上的相关的属性值
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

            System.out.println("根据车辆状态查询");
            System.out.println(carStatus);

           //初始化车辆状态值
            int carstatus1 = 0;
            int carstatus2 = 0;

            //根据页面传来的车辆状态属性设置对应的车辆状态值
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

            //获取指定的车辆状态的所有车辆列表
            List<Car> cars = carService.listCarBycarStatus(carstatus1, carstatus2, request);

            //添加对应的路线详情
            AddPath(cars);
            //设置页面的相关属性值
            request.setAttribute("cars", cars);

            request.setAttribute("carindex", "/selectCar");
            request.setAttribute("carStatus", carStatus);
            request.setAttribute("carCategory", carCategory);
        }else {
            //获取所有的车辆信息并分页
            List<Car> cars = carService.listAllCarPaging(request);

            //设置页面上相关的属性值
            request.setAttribute("cars", cars);
            request.setAttribute("carindex", "/carManage");
        }

         return "car-manage";


    }

    /**
     * 删除车辆
     * @param request
     * @return 显示所有车辆的页面
     *
     */
    @RequestMapping("/deleteCar")
    public String toPage3(HttpServletRequest request) {

        //获取从页面传来的要删除的车辆的id
        int id = Integer.parseInt(request.getParameter("id"));

        //执行相关的删除操作
        carService.deleteCarById(id);

        //显示所有车辆的列表并分页
        List<Car> cars = carService.listAllCarPaging(request);

        //添加对应的路线详情
        AddPath(cars);
        //设置相关的页面属性值
        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");

        return "car-manage";
    }




    /**
     * 添加车辆
     * @param request
     * @param response
     * @return 显示所有车辆信息的页面
     *
     */
    @RequestMapping("/AddCar")
    public String toPage4(HttpServletRequest request, HttpServletResponse response) {

        /*
        获取从页面表单提交的相关的属性值
         */

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

        //调用service的car方法，执行添加操作
        carService.addCar(car);


        //获取所有的列表并分页显示
        List<Car> cars = carService.listAllCarPaging(request);

        //添加对应的路线详情
        AddPath(cars);

        //设置相关的属性
        request.setAttribute("cars", cars);
        request.setAttribute("carindex", "/carManage");
        //返回网页
        return "car-manage";


    }

    /**
     * Ajax验证车辆是否存在（添加车辆时车牌号不能重复存在）
     * @param request
     * @param response
     * @throws IOException
     *
     */
    @RequestMapping("/checkLicenseNum")
    public void checkLicenseNum(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置请求的编码格式
        request.setCharacterEncoding("UTF-8");
        //获取通过ajax设置的参数的值
        String carNum = request.getParameter("context");
        System.out.println(carNum);

         int  verification = 0;

        //判断输入的参数是否是一个车牌号
        boolean isCar = isCarNum(carNum);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();


        if (isCar) { //当输入的是一个车牌号

            //检查对应的车牌号是否存在
            List<Car> cars = carService.existCarBylicenseNumber(carNum);
            System.out.println(cars.size());

            if (cars.size()==0){//不存在，则验证通过
                verification =1;
//                pw.write("<font color=\"green\" size=\"3px\">验证通过！</font>");
            }else {//存在，验证不通过，返回相关信息
                verification =2;
//                pw.write("<font color=\"red\" size=\"3px\">已存在！</font>");
            }



        } else if (!isCar && carNum!=""){//不是车牌号，返回格式错误
            verification =3;
//            pw.write("<font color=\"red\" size=\"3px\">格式错误！</font>");
        }else {
            verification =4;
//            pw.write("");
        }

        System.out.println("verification====="+verification);

       PrintWriter writer = response.getWriter();
       writer.print(verification);
        writer.flush();


    }

    /**
     * 车牌号的正则表达式验证
     * @param number
     * @return Boolean
     *
     */
    public static boolean isCarNum(String number) {

        String carnumRegex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        if (number == "" || number == null) return false;
        else return number.matches(carnumRegex);


    }


}
