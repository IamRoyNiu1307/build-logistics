package com.zoo.logistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zoo.logistics.entity.Car;
import com.zoo.logistics.mapper.CarMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆service
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * 获取当前站点内所有的车辆
     * @param stationId 站点id
     * @param pageNum 分页的当前页码
     * @param pageSize 每页的数据数
     * @return pageInfo
     */
    public PageInfo getAllCarInCurrentStation(int stationId,int pageNum,int pageSize){
//        List<Car> allCarInCurrentStation = carMapper.getAllCarInCurrentStation(stationId,new RowBounds(start,limit));
        PageHelper.startPage(pageNum, pageSize);
        List<Car> allCarInCurrentStation = carMapper.getAllCarInCurrentStation(stationId);
        PageInfo pageInfo = new PageInfo(allCarInCurrentStation);
        return pageInfo;

    }

    /**
     * 更新车辆的状态为运输中(将状态id更改为2或4)
     * @param licenseNumber 车牌号
     */
    public void updateCarStatus(String licenseNumber){
        Car car = carMapper.selectByLicenseNumber(licenseNumber);
        car.setCarStatus(car.getCarStatus()+1);
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 根据交接单id查找该交接单所在的车辆
     * @param equipmentReceiptId 交接单id
     * @return 绑定了该交接单的车辆
     */
    public Car selectByEquipmentReceiptId(int equipmentReceiptId){
        return carMapper.selectByEquipmentReceiptId(equipmentReceiptId);
    }

    public void updateByPrimaryKeySelective(Car car){
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 获取此表中的数据总数量
     *
     * @return 返回表中的数据总量
     */
    public int CarCount() {
        int count = carMapper.CarCount();
        return count;
    }

    /**
     * 根据id查询对应车辆
     * @param id
     * @return
     */
    public  Car FindCarByID(int id){
        return carMapper.selectByPrimaryKey(id);
    }


    /**
     * 返回所有的车辆信息列表
     *
     * @return 车辆列表（所有的，不分页）
     */
    public List<Car> listAll() {

        //调用mapper从数据库中读取所有的数据
        List<Car> carList = carMapper.selectAll();
        return carList;
    }

    /**
     * 分页返回所有的车辆信息列表
     *
     * @param request
     * @return 返回车辆信息列表
     */
    public List<Car> listAllCarPaging(HttpServletRequest request) {
        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;

        try {
            // 获取从服务器端传来的参数，来实现换页的效果
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传入参数start时
        }


        // 计算下一页，上一页的起始值
        int next = start + count;
        int pre = start - count;

        // 计算尾页的起始值
        int total = carMapper.CarCount();
        ;
//        System.out.println("total=" + total);
        if (0 == total % count) {
            total = total - count;
        } else {
            total = total - (total % count);
        }

        // 边界处理（首页和尾页 点击上一页/下一页 的限制）
        pre = pre < 0 ? 0 : pre;
        next = next > total ? total : next;

        // 将对应的值传递给服务器端-jsp文件中对应的参数
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("total", total);

        //获取表中所有的车辆信息并分页
        List<Car> carList = carMapper.selectAll(new RowBounds(start, count));

        return carList;
    }


    /**
     * 判断车牌号是否存在
     *
     * @param carnum
     * @return list<Car>
     */
    public List<Car> existCarBylicenseNumber(String carnum) {
        List<Car> cars = carMapper.selectBylicenseNumber(carnum);
        return cars;
    }


    /**
     * 根据车牌号进行查询
     *
     * @param condition
     * @param request
     * @return 符合条件的车辆信息列表
     */
    public List<Car> listCarBylicenseNumber(String condition, HttpServletRequest request) {
        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;

        List<Car> cars = carMapper.selectBylicenseNumber(condition);

        try {
            // 获取从服务器端传来的参数，来实现换页的效果
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传入参数start时
        }


        // 计算下一页，上一页的起始值
        int next = start + count;
        int pre = start - count;

        // 计算尾页的起始值
        int total = cars.size();
//        System.out.println("total=" + total);
        if (0 == total % count) {
            total = total - count;
        } else {
            total = total - (total % count);
        }

        // 边界处理（首页和尾页 点击上一页/下一页 的限制）
        pre = pre < 0 ? 0 : pre;
        next = next > total ? total : next;

        // 将对应的值传递给服务器端-jsp文件中对应的参数
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("total", total);

        List<Car> carList = carMapper.selectBylicenseNumber(condition, new RowBounds(start, count));

        return carList;


    }

    /**
     * 根据车辆类型 进行查询
     *
     * @param carCategoryid
     * @param request
     * @return 符合条件的车辆信息列表
     */
    public List<Car> listCarBycarCategory(int carCategoryid, HttpServletRequest request) {
        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;

        List<Car> cars = carMapper.selectBycarCategoryId(carCategoryid);

        try {
            // 获取从服务器端传来的参数，来实现换页的效果
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传入参数start时
        }


        // 计算下一页，上一页的起始值
        int next = start + count;
        int pre = start - count;

        // 计算尾页的起始值
        int total = cars.size();
//        System.out.println("total=" + total);
        if (0 == total % count) {
            total = total - count;
        } else {
            total = total - (total % count);
        }

        // 边界处理（首页和尾页 点击上一页/下一页 的限制）
        pre = pre < 0 ? 0 : pre;
        next = next > total ? total : next;

        // 将对应的值传递给服务器端-jsp文件中对应的参数
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("total", total);

        List<Car> carList = carMapper.selectBycarCategoryId(carCategoryid, new RowBounds(start, count));

        return carList;


    }

    /**
     * 根据车辆状态进行查询
     *
     * @param a
     * @param b
     * @param request
     * @return 符合条件的车辆信息列表
     */
    public List<Car> listCarBycarStatus(int a, int b, HttpServletRequest request) {
        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;
        System.out.println("a======" + a + ",b======" + b);
        Map map = new HashMap();
        map.put("carStatus1", a);
        map.put("carStatus2", b);

        List<Car> cars = carMapper.selectBycarStatus(map);

        try {
            // 获取从服务器端传来的参数，来实现换页的效果
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传入参数start时
        }


        // 计算下一页，上一页的起始值
        int next = start + count;
        int pre = start - count;

        // 计算尾页的起始值
        int total = cars.size();
//        System.out.println("total=" + total);
        if (0 == total % count) {
            total = total - count;
        } else {
            total = total - (total % count);
        }

        // 边界处理（首页和尾页 点击上一页/下一页 的限制）
        pre = pre < 0 ? 0 : pre;
        next = next > total ? total : next;

        // 将对应的值传递给服务器端-jsp文件中对应的参数
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("total", total);

        List<Car> carList = carMapper.selectBycarStatus(map, new RowBounds(start, count));

        return carList;


    }

    /**
     * 根据车辆类型+状态进行查询
     * @param id
     *
     */
    /**
     * 根据车辆状态进行查询
     *
     * @param a
     * @param b
     * @param request
     * @return 符合条件的车辆信息列表
     */
    public List<Car> listCarByCategoryStatus(int a, int b, int carCategoryId, HttpServletRequest request) {
        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;
        System.out.println("a======" + a + ",b======" + b);
        Map map = new HashMap();
        map.put("carStatus1", a);
        map.put("carStatus2", b);
        map.put("carCategoryId", carCategoryId);
        List<Car> cars = carMapper.selectByCategoryStatus(map);

        try {
            // 获取从服务器端传来的参数，来实现换页的效果
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传入参数start时
        }


        // 计算下一页，上一页的起始值
        int next = start + count;
        int pre = start - count;

        // 计算尾页的起始值
        int total = cars.size();
        System.out.println("车辆状态为"+carCategoryId+"的车辆的个数为"+total);
//        System.out.println("total=" + total);
        if (0 == total % count) {
            total = total - count;
        } else {
            total = total - (total % count);
        }

        // 边界处理（首页和尾页 点击上一页/下一页 的限制）
        pre = pre < 0 ? 0 : pre;
        next = next > total ? total : next;

        // 将对应的值传递给服务器端-jsp文件中对应的参数
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("total", total);

        List<Car> carList = carMapper.selectByCategoryStatus(map, new RowBounds(start, count));

        return carList;


    }


    /**
     * 根据ID删除指定的车辆信息
     *
     * @param id
     */
    public void deleteCarById(int id) {
        carMapper.deleteByPrimaryKey(id);
    }


    /**
     * 添加对应的车辆信息
     *
     * @param car
     */
    public void addCar(Car car) {

        carMapper.insertSelective(car);
    }



    public  void UpdateCarInfo(Car car){

        carMapper.updateByPrimaryKeySelective(car);
    }
}
