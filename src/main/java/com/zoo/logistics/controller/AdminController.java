package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.service.AdminService;
import com.zoo.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class AdminController {

    @Autowired
    public AdminService adminService;


    /**
     *  获取并设置相关的页面
     * @param request
     * @param response
     * @return 跳转的页面
     * <p>
     *
     */
    @RequestMapping("/adminManage")
    public String toPage1(HttpServletRequest request, HttpServletResponse response) {

        //System.out.println("进入控制器");


        //获取所有的后台用户信息并分页
        List<Admin> adminList = adminService.listAllPaging(request);

        //设置页面上需要的相关属性
        request.setAttribute("admins", adminList);
        request.setAttribute("adminindex", "/adminManage");

        return "admin-manage";

    }


    /**
     * 获取要更新的用户信息，ajax获取指定ID的对应信息
     * @param request
     * @param tempMap
     * @return map
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/queryAdmin")
    public Map querySite(HttpServletRequest request, @RequestBody Map tempMap) throws IOException {

        System.out.println("获取要更新的用户信息！");

        Map map = new HashMap();


        //获取从页面上传来的id信息
        String id = tempMap.get("id").toString();

        //根据对应的ID信息，获取对应的用户的所有的信息
        Admin admin = adminService.selectByPrimaryKey(Integer.parseInt(id));

        System.out.println(admin);


        //将用户的所有的信息放进map中，传回页面
        map.put("id", admin.getId());
        map.put("account", admin.getAccount());
        map.put("stationId", admin.getStationId());
        map.put("stationName", admin.getStation().getStationName());
        map.put("level", admin.getLevel());

        return map;

    }


    /**
     * 更新用户信息
     * @param request
     * @param response
     * @return 显示用户信息的主页面
     *
     */
    @RequestMapping("/updateAdmin")
    public String UpdateAdmin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("更新用户信息！");

        //获取从form表单中传递的id信息和level信息
        String id = request.getParameter("adminId");
        String level = request.getParameter("adminLevel");

        System.out.println("要修改的用户id为：" + id);
        System.out.println("用户级别修改为：" + level);

        //获取对应的admin对象
        Admin admin = adminService.selectByPrimaryKey(Integer.parseInt(id));
        //修改对应的级别属性
        admin.setLevel(Integer.parseInt(level));

        //数据库中执行修改操作
        adminService.updateAdminInfo(admin);


        //获取所有的信息并分页
        List<Admin> adminList = adminService.listAllPaging(request);

        //设置页面上的相关属性
        request.setAttribute("admins", adminList);
        request.setAttribute("adminindex", "/adminManage");

        return "admin-manage";


    }


    /**
     * 删除指定的用户的信息
     * @param request
     * @param response
     * @return 显示用户信息的主页面
     *
     */
    @RequestMapping("/deleteAdmin")
    public String DeleteAdmin(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("删除用户信息！");


        //获取要删除的用户的id
        int id = Integer.parseInt(request.getParameter("id"));

        //执行删除操作
        adminService.deleteAdminById(id);

        //获取所有的信息并分页
        List<Admin> adminList = adminService.listAllPaging(request);

        //设置页面上的相关属性
        request.setAttribute("admins", adminList);
        request.setAttribute("adminindex", "/adminManage");

        return "admin-manage";


    }


    /**
     *根据指定条件执行相关的查询操作
     * @param request
     * @param response
     * @return 显示用户信息的主页面
     *
     */
    @RequestMapping("/selectAdmin")
    public String toPage2(HttpServletRequest request, HttpServletResponse response) {


        //获取从表单提交的数据--站点名称/用户级别
        String stationName = request.getParameter("stationName");
        String level = request.getParameter("level");

        System.out.println("stationName===" + stationName);
        System.out.println("level===" + level);

        //初始化一个list，用于存放对应的admin集合
        List<Admin> adminList = new ArrayList<Admin>();

        //对表单中获取到的数据进行分析
        if (stationName != "" && level != "") {
            System.out.println("两者都不为空");
            //都不为空，执行全查询
            adminList = adminService.listByAllPaging(stationName, level, request);


        } else if (stationName != "") {
            System.out.println("level为空");
            //level为空，执行stattionName查询
            adminList = adminService.ListAllByStationName(stationName, request);


        } else if (level != "") {
            System.out.println("stationName为空");
            //stattionName为空，执行level查询
            adminList = adminService.ListAllByLevel(level, request);


        } else {
            System.out.println("两者都为空");
            //两者都为空，执行初始化查询
            adminList = adminService.listAllPaging(request);
        }


        //设置页面上的相关属性
        request.setAttribute("admins", adminList);
        request.setAttribute("adminindex", "/adminManage");

        return "admin-manage";
    }


}
