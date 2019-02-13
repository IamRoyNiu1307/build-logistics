package com.zoo.logistics.service;

import com.zoo.logistics.entity.Log;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 插入一条日志
     * @param orderId 订单编号
     * @param content 日志信息
     */
    public void insertLog(String orderId,String content){
        Log log = new Log(orderId, new Date(), content);
        logMapper.insertSelective(log);
    }

    /**
     * 批量更新日志
     * @param orderList 订单编号列表
     * @param content 日志内容
     */
    public void batchUpdate(List<Order> orderList,String content){
        Date now = new Date();
        for (Order order : orderList) {
            logMapper.insertSelective(new Log(order.getOrderId(), now, content));
        }
    }

    public List<Log> selectByOrderIdDesc(String orderId){
        return logMapper.selectByOrderIdDesc(orderId);
    }
}
