package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 登录Controlle
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 主页，登录界面
     *
     * @return login.html
     */
    @RequestMapping("/login")
    public String toLoginPage() {
        return "login";
    }

    /**
     * 表单提交，用户登录
     *
     * @param admin   管理员账号实体类
     * @param request
     * @return 成功：index.html
     * 失败：login.html
     */
    @RequestMapping("/toLogin")
    public String login(Admin admin, HttpServletRequest request) {


        //System.out.println(admin);
        //获取Adminservice中传递的admin对象，进行判断
        Admin result = adminService.checkAccountAndPassword(admin);

        //返回结果不为空，说明存在相关的账号，对账号权限进行判断，判断跳转向哪个页面
        if (result != null) {
            //在session中，设置admin属性，在所有的页面中进行判断，看现在用户是否登录
            request.getSession().setAttribute("admin", result);

            //获取登录的用户的级别
            int level = result.getStation().getLevel();

            //级别1，跳转向总站
            if (level == 1) {
                //在spring-servlet.xml中配置了Thymeleaf视图解析器，Thymeleaf会将返回的字符串拼接上解析器中的前缀和后缀
                return "index-master";//会被解析成 /templates/index-master.html

            } else {
                //级别2，跳转向分站的站点
                return "index-sub";
            }

        } else {
            //查无此号，说明登录失败，返回登录页面
            return "login";//会被解析成 /templates/login.html
        }
    }

    @RequestMapping("/index")
    public String toIndexPage() {
        return "index-master";
    }
}
