package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.service.AdminService;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 登录Controlle
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OrderService orderService;

    /**
     * 主页，登录界面
     * @return login.html
     */
    @RequestMapping("/login")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    /**
     * 表单提交，用户登录
     * @param admin 管理员账号实体类
     * @param request
     * @return 成功：index.html
     *         失败：login.html
     */
    @RequestMapping("/toLogin")
    public String login(Admin admin, HttpServletRequest request){
        Admin result = adminService.checkAccountAndPassword(admin);
        if(result!=null){

            Map subStatement = orderService.getSubStatement(result.getStationId());

            request.getSession().setAttribute("admin",result);
            request.getSession().setAttribute("subStatement",subStatement);

            //在spring-servlet.xml中配置了Thymeleaf视图解析器，Thymeleaf会将返回的字符串拼接上解析器中的前缀和后缀
            return "redirect:/index";
        }else {
            return "login";//会被解析成 /templates/login.html
        }
    }
}
