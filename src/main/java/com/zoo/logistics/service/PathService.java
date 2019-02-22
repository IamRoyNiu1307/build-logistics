package com.zoo.logistics.service;


import com.zoo.logistics.entity.Route;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.RouteMapper;
import com.zoo.logistics.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

@Service
public class PathService {
    @Autowired
    public RouteMapper routeMapper;
    @Autowired
    public StationMapper stationMapper;

    /**
     * 返回表中的数据总数量
     * @return int
     */
    public int CarCount() {
        int count = routeMapper.RouteCount();
        System.out.println("路线数量" + count);

        return count;
    }

    /**
     * 返回表中所有的路线列表
     * @return List
     */
    public List<Route> listAll() {

        //调用mapper从数据库中读取所有的数据
        List<Route> routeList = routeMapper.selectAll();
        System.out.println(routeList);

        return routeList;
    }

    /**
     * 根据路线ID查询对应的路线信息
     * @param id
     * @return Route对象
     */
    public Route selectByPrimaryKey(int id){
        Route route=routeMapper.selectByPrimaryKey(id);
        return  route;
    }

    /**
     * 分页返回所有的路线信息列表
     * @param request
     * @return List
     */
    public List<Route> listAllPaging(HttpServletRequest request) {
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
        int total = routeMapper.RouteCount();
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

        List<Route> routeList = routeMapper.selectAll(new RowBounds(start, count));

        return routeList;

    }

    /**
     *  根据起始地查询对应的路线信息列表并分页
     * @param startarea
     * @param request
     * @return List
     *
     */
    public List<Route> ListAllByStartArea(String startarea, HttpServletRequest request) {

        System.out.println("根据起始地查询02");
        //根据起始地名字查询对应的站点信息
        List<Station> stations = stationMapper.selectByStationName(startarea);

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
        List<Route> routes = routeMapper.selectByStartArea(stationId);

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
        int total = routes.size();
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
        List<Route> routeList = routeMapper.selectByStartArea(stationId, new RowBounds(start, count));

        return routeList;

    }

    /**
     * 根据目的地查询对应的路线信息列表
     * @param endarea
     * @param request
     * @return List
     */
    public List<Route> ListAllByEndArea(String endarea, HttpServletRequest request) {

        System.out.println("根据目的地查询02");
        //根据起始地名字查询对应的站点信息
        List<Station> stations = stationMapper.selectByStationName(endarea);

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
        List<Route> routes = routeMapper.selectByEndArea(stationId);

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
        int total = routes.size();
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
        List<Route> routeList = routeMapper.selectByEndArea(stationId, new RowBounds(start, count));

        return routeList;

    }

    /**
     * 根据起始地和目的地查询指定的路线信息列表
     * @param startarea
     * @param endarea
     * @param request
     * @return List
     */
    public List<Route> listByRoutePaging(String startarea, String endarea, HttpServletRequest request) {

        System.out.println("根据路线查询02");
        //根据起始地名字查询对应的站点信息
        List<Station> stations1 = stationMapper.selectByStationName(startarea);
        List<Station> stations2 = stationMapper.selectByStationName(endarea);


        //根据站点的id查询对应的路线(获取数量时需要)
        List<Route> routes =  routeMapper.selectByRoute(stations1, stations2);


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
        int total = routes.size();
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
        List<Route> routeList = routeMapper.selectByRoute(stations1, stations2, new RowBounds(start, count));

        return routeList;
    }

    /**
     * 根据指定的ID删除对应的路线信息
     * @param id
     */
    public void deleteRouteById(int id) {
        routeMapper.deleteByPrimaryKey(id);
    }


    /**
     * 添加对应的路线信息
     * @param route
     */
    public void addRoute(Route route) {

        System.out.println("执行插入操作----插入新路线");
        routeMapper.insertSelective(route);
    }



    /**
     * 查询对应的路线是否存在
     * @param start
     * @param end
     * @param request
     * @return 存在-true/不存在-false
     *
     */
    public boolean RouteExist(int start, int end, HttpServletRequest request){

        Route route = new Route();
        route.setStartStationId(start);
        route.setEndStationId(end);

        Route route2 = routeMapper.isexist(route);
        System.out.println(route2);

        if (route2 == null){
            return false;
        }else {
            return  true;
        }


    }



}
