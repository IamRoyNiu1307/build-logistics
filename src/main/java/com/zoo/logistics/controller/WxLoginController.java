package com.zoo.logistics.controller;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.zoo.logistics.entity.Courier;
import com.zoo.logistics.entity.User;
import com.zoo.logistics.service.CourierService;
import com.zoo.logistics.service.UserService;
import com.zoo.logistics.util.HttpRequestUtil;
import com.zoo.logistics.util.MyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourierService courierService;

    private JsonParser jp=new JsonParser();

    /**
     * 微信小程序 用户登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/userLogin")
    public Map userLogin(@RequestBody User user){
        Map map = new HashMap();

        User resultUser = userService.selectByAccountAndPassword(user);

        if(resultUser!=null){
            //查询该微信号绑定的zoo账号，将其openid设为空
            userService.setOpenidEqNullIfExist(user.getOpenid());
            //将该zoo账号的openid设为此openid
            resultUser.setOpenid(user.getOpenid());
            userService.updateByPrimaryKeySelectiv(resultUser);
            map.put("status",1);
            map.put("user",resultUser);
        }else {
            map.put("status",0);
            map.put("msg","账号或密码错误");
        }

        return map;
    }

    /**
     * 微信小程序 快递员登录
     * @param courier
     * @return
     */
    @ResponseBody
    @RequestMapping("/courierLogin")
    public Map courierLogin(@RequestBody Courier courier){
        Map map = new HashMap();

        Courier resultCourier = courierService.selectByAccountAndPassword(courier);

        if(resultCourier!=null){
            //查询该微信号绑定的快递员账号，将其openid设为空
            courierService.setOpenidEqNullIfExist(courier.getOpenid());
            //将该快递员账号的openid设为此openid
            resultCourier.setOpenid(courier.getOpenid());
            courierService.updateByPrimaryKeySelectiv(resultCourier);
            map.put("status",1);
            map.put("courier",resultCourier);
        }else {
            map.put("status",0);
            map.put("msg","账号或密码错误");
        }

        return map;
    }

    /**
     * 根据wxLogin返回的code换取openid
     * @param code 微信登录后得到的code
     * @return Map
     */
    @ResponseBody
    @RequestMapping(value = "/getopenid",method = RequestMethod.GET)
    public Map getOpenid(String code){
        Map map = new HashMap();
        //login code不能为空
        if(code==null||code.length()==0){
            map.put("status",0);
            map.put("msg","code 不能为空");
            return map;
        }
        //通过login code去换取openId
        String param = "appid=" + MyConst.APPID + "&secret=" + MyConst.APPSECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String data = HttpRequestUtil.sendGet(MyConst.WxGetOpenIdUrl, param);
        //解析成json格式
        JsonObject json = jp.parse(data).getAsJsonObject();

        //获取sessionKey和openid
        String sessionKey = json.get("session_key").getAsString();
        String openId = json.get("openid").getAsString();

        map.put("sessionKey",sessionKey);
        map.put("openId",openId);
        map.put("status",1);
        map.put("msg","成功");

        return map;
    }

    /**
     * 根据openid查找绑定的用户账号
     * @param openid 微信号的唯一标示
     * @return 账号实体
     */
    @ResponseBody
    @RequestMapping(value = "/getUserAccount",method = RequestMethod.GET)
    public Map getUserAccountByOpenid(String openid){
        Map result = new HashMap();
        User user = userService.selectByOpenid(openid);
        if(user!=null){
            result.put("status",1);
            result.put("user",user);
        }else {
            result.put("status",0);
            result.put("msg","未绑定账号");
        }
        return result;
    }

    /**
     * 根据openid查找绑定的快递员账号
     * @param openid 微信号的唯一标示
     * @return 账号实体
     */
    @ResponseBody
    @RequestMapping(value = "/getCourierAccount",method = RequestMethod.GET)
    public Map getCourierAccountByOpenid(String openid){
        Map result = new HashMap();
        Courier courier = courierService.selectByOpenid(openid);
        if(courier!=null){
            result.put("status",1);
            result.put("courier",courier);
        }else {
            result.put("status",0);
            result.put("msg","未绑定账号");
        }
        return result;
    }
}
