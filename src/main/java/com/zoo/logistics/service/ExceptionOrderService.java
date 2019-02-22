package com.zoo.logistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoo.logistics.entity.ExceptionOrder;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.mapper.ExceptionOrderMapper;
import com.zoo.logistics.mapper.OrderMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionOrderService {
    @Autowired
    private ExceptionOrderMapper exceptionOrderMapper;
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 增加已经登记的异常单
     * @param exceptionOrder 已登记的异常单对象
     */
    public void insertExceptionOrder(ExceptionOrder exceptionOrder){
        exceptionOrderMapper.insert(exceptionOrder);
    }

    /**
     * 查找本站所有的已处理的异常单（分页用）
     * @param stationId 本站id
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo selectExceptionOrderRegisterByStationIdWithPageInfo(int stationId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        //查找出所有已处理的异常单
        List<Order> allOrderInfo=orderMapper.selectExceptionOrderByStationId(stationId);
        //根据已处理异常单的orderId查找出该异常单的处理信息
        for (Order order:allOrderInfo
             ) {
            order.setExceptionOrder(exceptionOrderMapper.selectByOrderId(order.getOrderId()));
        }
        PageInfo pageInfo=new PageInfo(allOrderInfo);
        return pageInfo;
    }
}
