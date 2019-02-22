package com.zoo.logistics.service;


import com.zoo.logistics.entity.CarApplication;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.CarApplicationMapper;
import com.zoo.logistics.mapper.StationMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {

    @Autowired
    public CarApplicationMapper classMapper;
    @Autowired
    public StationMapper stationMapper;



    public  CarApplication FindOneById(int id){

        return classMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有的站点信息
     * @param
     * @return List
     */
    public List<CarApplication> listAllApply(){
        List<CarApplication> classList = classMapper.selectAll();

        return classList;
    }


    /**
     * 获取所有的申请信息并分页
     * @return List
     */
    public List<CarApplication> ListAllPaging(HttpServletRequest request){

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
        int total =classMapper.Count();;
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
        List<CarApplication> classList = classMapper.selectAll(new RowBounds(start, count));

        return classList;
    }


    /**
     * 根据站点名称获取所有的申请信息并分页
     * @return List
     */
    public List<CarApplication> ListAllByStationNamePaging(String stationName,HttpServletRequest request){

        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;

        //根据站点名称获取指定的站点列表
        List<Station> stations = stationMapper.selectByStationName(stationName);

        //声明集合存放相关站点id
        List stationIDlist = new ArrayList();


        //
        for (Station station:stations) {
            stationIDlist.add(station.getId());
        }

        List<CarApplication> classes = classMapper.selectByStationId(stationIDlist);

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
        int total =classes.size();
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
        List<CarApplication> classList = classMapper.selectByStationId(stationIDlist,new RowBounds(start, count));

        return classList;
    }

    /**
     * 根据申请类型获取所有的申请信息并分页
     * @return List
     */
    public List<CarApplication> ListAllByClassStatusPaging(int statusId,HttpServletRequest request){

        // 设置页面初始化的值
        int start = 0;
        // 设置页面显示的个数
        int count = 10;

        List<CarApplication> classes = classMapper.selectByClassStatus(statusId);

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
        int total =classes.size();
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
        List<CarApplication> classList = classMapper.selectByClassStatus(statusId,new RowBounds(start, count));

        return classList;
    }


    /**
     * 更新班次申请表的信息
     * @param carApplication
     */
    public void UpdateClass(CarApplication carApplication){

        classMapper.updateByPrimaryKeySelective(carApplication);
    }


}
