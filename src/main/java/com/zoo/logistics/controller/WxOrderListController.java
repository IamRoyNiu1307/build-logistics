package com.zoo.logistics.controller;

import com.zoo.logistics.entity.Order;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WxOrderListController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取用户的下单记录
     * @param account 账号
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserOrders")
    public Map getOrderList(@RequestBody String account){
        Map map = new HashMap();
        List<Order> orderList = orderService.selectByCreaterAccount(account);
        if(orderList.size()>0){
            map.put("status",1);
            map.put("orderList",orderList);
        }else {
            map.put("status",0);
        }
        return map;
    }
}
