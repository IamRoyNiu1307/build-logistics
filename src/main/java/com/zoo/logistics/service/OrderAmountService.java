package com.zoo.logistics.service;

import com.zoo.logistics.entity.OrderAmount;
import com.zoo.logistics.mapper.OrderAmountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAmountService {

    @Autowired
    private OrderAmountMapper orderAmountMapper;

    public void insertSelective(OrderAmount orderAmount){
        orderAmountMapper.insertSelective(orderAmount);
    }
}
