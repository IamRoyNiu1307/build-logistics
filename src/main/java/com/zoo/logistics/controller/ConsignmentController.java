package com.zoo.logistics.controller;

import com.github.pagehelper.PageInfo;
import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.entity.Car;
import com.zoo.logistics.mapper.CarMapper;
import com.zoo.logistics.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consignment")
public class ConsignmentController {

    @Autowired
    private CarService carService;

    /**
     * 跳转到发货页面
     * @param curPage 当前页码
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/page/{curPage}")
    public String toConsignmentPage(@PathVariable(value = "curPage") int curPage,
                                    Map map, HttpServletRequest request){
        //每页显示的数据行数
        int limit = 8;

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("req get:"+request.getSession().getAttribute("admin"));

        //获取站中所有车辆信息
        PageInfo carPageInfo = carService.getAllCarInCurrentStation(admin.getStationId(), curPage, limit);

        map.put("carList",carPageInfo.getList());
        map.put("prePage",carPageInfo.getPrePage());
        map.put("nextPage",carPageInfo.getNextPage());
        map.put("curPage",curPage);
        map.put("firstPage",carPageInfo.getFirstPage());
        map.put("lastPage",carPageInfo.getLastPage());

        return "consignment";
    }
}
