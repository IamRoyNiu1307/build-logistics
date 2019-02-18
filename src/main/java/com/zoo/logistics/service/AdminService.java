package com.zoo.logistics.service;

import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.AdminMapper;
import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.mapper.StationMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    //自动注入userDao
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StationMapper stationMapper;

    //检查账号密码
    public Admin checkAccountAndPassword(Admin admin){
        //实际开发的写法
        Admin result = adminMapper.checkAccount(admin);
//        if(result!=null){
//            //说明账号密码正确
//            System.out.println(result.toString());
//
//            return result;
//        }
//        return null;
        return  result;
    }




    //返回表中的数据总量
    public int CarCount() {
        int count = adminMapper.AdminCount();
        System.out.println("后台管理员数量" + count);

        return count;
    }


    //返回用户列表（所有的）
    public List<Admin> listAll() {

        //调用mapper从数据库中读取所有的数据
        List<Admin> adminList = adminMapper.selectAll();
        System.out.println(adminList);

        return adminList;
    }

    public Admin selectByPrimaryKey(int id){
        Admin admin=adminMapper.selectByPrimaryKey(id);
        return  admin;
    }

    //分页返回所有的用户的列表
    public List<Admin> listAllPaging(HttpServletRequest request) {
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
        int total = adminMapper.AdminCount();
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

        List<Admin> adminList = adminMapper.selectAll(new RowBounds(start, count));

        return adminList;

    }




    //数据库中执行修改操作
    public void updateAdminInfo(Admin admin){
        adminMapper.updateByPrimaryKeySelective(admin);
    }


    //根据索引值删除对应的信息
    public void deleteAdminById(int id){
        adminMapper.deleteByPrimaryKey(id);
    }



    //根据站点名称查询对应的用户
    public List<Admin> ListAllByStationName(String stationName, HttpServletRequest request) {

        System.out.println("根据站点名称查询对应的用户");
        //根据起始地名字查询对应的站点信息
        List<Station> stations = stationMapper.selectByStationName(stationName);

        //定义一个集合存放所有符合查询条件的站点的id
        List<Integer> stationId = new ArrayList<Integer>();

        //将站点的id存放进集合中
        for (Station station : stations) {

            System.out.println("station.getId()====" + station.getId());
            stationId.add(station.getId());
        }


        for (int id : stationId) {
            System.out.println("stationID=====" + id);
        }
        //根据站点的id查询对应的路线(获取数量时需要)
        List<Admin> admins = adminMapper.selectByStationId(stationId);

        /*
        分页操作
         */
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
        int total = admins.size();
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


        //根据站点的id查询对应的用户
        List<Admin> adminList = adminMapper.selectByStationId(stationId, new RowBounds(start, count));

        return adminList;

    }

    //根据级别查询对应的用户
    public List<Admin> ListAllByLevel(String level, HttpServletRequest request) {

        System.out.println("根据级别查询对应的用户");
        //根据级别查询对应的用户(后面获取数量需要用到)
        List<Admin> admins = adminMapper.selectByLevel(Integer.parseInt(level));

        /*
        分页操作
         */
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
        int total = admins.size();
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


        //根据站点的id查询对应的路线
        List<Admin> adminList = adminMapper.selectByLevel(Integer.parseInt(level), new RowBounds(start, count));

        return adminList;

    }



    //根据站点名称和级别查询对应的用户
    public List<Admin> listByAllPaging(String stationName, String level, HttpServletRequest request) {

        System.out.println("根据站点名称和级别查询对应的用户");
        //根据起始地名字查询对应的站点信息
        List<Station> stations = stationMapper.selectByStationName(stationName);

        //根据站点的id查询对应的路线(获取数量时需要)
        List<Admin> admins =  adminMapper.selectByAll(stations,Integer.parseInt(level));


        /*
        分页操作
         */
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
        int total = stations.size();
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


        //根据站点的id查询对应的路线
        List<Admin> adminList = adminMapper.selectByAll(stations, Integer.parseInt(level), new RowBounds(start, count));

        return adminList;
    }


}
