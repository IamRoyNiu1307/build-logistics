package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByOrderId(String orderId);

    List<Order> selectByEquipmentReceiptId(int equipmentReceiptId);

    void confirmEquipmentReceipt(int equipmentReceiptId,int currentStationId);

    void orderException(String orderId,int stationId);

    List<Order> selectByCreaterAccount(String createrAccount);

    List<Order> selectWaitingOrder(int stationId);

    Integer selectOrderCountByStationId(int stationId);

    Integer selectSumVolumeByStationId(int stationId);

    Integer selectSendOrderCountByStationId(int stationId);

    Integer selectDeliveryOrderCountByStationId(int stationId);

    Integer selectWarningOrderCountByStationId(int stationId);

    //---------------------------------------------------------------------
    //查询状态为待揽件的订单
    List<Order> selectByStatusId(int stationId);
    //查询异常单
    List<Order> selectOrderExceptionByStationId(int stationId);
    //查询已登记处理的异常单
    List<Order> selectExceptionOrderByStationId(int stationId);
    //查询本站状态为已入库的订单
    List<Order> selectWaitedOrder(int stationId);
    //查询本站和终点站相同并且在本站中的状态为入库的订单
    List<Order> selectStockOrder(int stationId);
}