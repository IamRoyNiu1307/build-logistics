package com.zoo.logistics.service;



import com.zoo.logistics.entity.Cities;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.CitiesMapper;
import com.zoo.logistics.mapper.StationMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 站点service
 */
@Service
public class SiteService {
    @Autowired
    public StationMapper stationMapper;
    @Autowired
    public CitiesMapper citiesMapper;

    /**
     * 根据指定的ID查询对应的站点
     * @param request
     * @param id
     * @return Station对象
     */
    public Station FindStationByID(HttpServletRequest request,int id){

        Station station = stationMapper.selectByPrimaryKey(id);
        return station;

    }

    /**
     * 获取所有的站点信息
     * @param request
     * @return List
     */
    public List<Station> listAllStation(HttpServletRequest request){
        List<Station> stationList = stationMapper.selectAll();

        return stationList;
    }

    /**
     * 分页返回所有的站点信息列表并分页
     * @param request
     * @return List
     */
    public List<Station> listAllStationPaging(HttpServletRequest request) {
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
        int total =stationMapper.selectAll().size();;
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

        List<Station> stationList = stationMapper.selectAll(new RowBounds(start, count));

        return stationList;
    }

    /**
     * 根据城市名称查询城市内所有的站点并分页
     * @param request
     * @param cityname
     * @return List
     */
    public List<Station> listSiteByCityNamePaging(HttpServletRequest request,String cityname) {

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

        String id =citiesMapper.selectByCityName(cityname).getCityid();
        System.out.println(id+"============");

        // 计算尾页的起始值
        int total =stationMapper.selectByCityId(citiesMapper.selectByCityName(cityname).getCityid()).size();
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

        List<Station> stationList = stationMapper.selectByCityId(citiesMapper.selectByCityName(cityname).getCityid(),new RowBounds(start, count));

        return stationList;
    }

    /**
     * 根据指定的ID删除对应的站点信息
     * @param id
     */
    public void deleteSiteById(int id) {
        stationMapper.deleteByPrimaryKey(id);
    }


    /**
     * 执行更新（修改）操作
     * @param station
     *
     */
    public void updateSiteInfo(Station station){
        stationMapper.updateByPrimaryKeySelective(station);
    }



}
