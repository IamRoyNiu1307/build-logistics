package com.zoo.logistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoo.logistics.api.Distance;
import com.zoo.logistics.api.Geo;
import com.zoo.logistics.entity.Log;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.LogMapper;
import com.zoo.logistics.mapper.OrderMapper;
import com.zoo.logistics.mapper.StationMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private LogMapper logMapper;

    public Order selectByOrderId(String orderId){
        return orderMapper.selectByOrderId(orderId);
    }

    /**
     * 创建订单
     * @param order 订单实体
     * @return
     */
    public Map createOrder(Order order){
        Map resultMap = new HashMap();
        // 创建订单编号
        long now = System.currentTimeMillis();
        String orderId = String.valueOf(now);
        order.setOrderId(orderId);
        // 设置状态
        order.setStatusId(1);

        if(order.getSenderArea().length()>=7 && order.getReceiverArea().length()>=7){
            //获取发件城市和收件城市
            String senderCity = "";
            String receiverCity = "";
            if(order.getSenderArea().lastIndexOf("/")>order.getSenderArea().indexOf("/")){
                senderCity = order.getSenderArea().substring(order.getSenderArea().indexOf("/")+1,order.getSenderArea().lastIndexOf("/"));
            }else{
                senderCity = order.getSenderArea().substring(order.getSenderArea().indexOf("/")+1);
            }
            if(order.getReceiverArea().lastIndexOf("/")>order.getReceiverArea().indexOf("/")){
                receiverCity = order.getReceiverArea().substring(order.getReceiverArea().indexOf("/")+1,order.getReceiverArea().lastIndexOf("/"));
            }else{
                receiverCity = order.getReceiverArea().substring(order.getReceiverArea().indexOf("/")+1);
            }
            System.out.println(senderCity+"  "+receiverCity);
            //获取发件城市和收件城市所有的站点
            List<Station> startStations = stationMapper.selectByCityName(senderCity);
            List<Station> endStations = stationMapper.selectByCityName(receiverCity);
            System.out.println(startStations.size()+"   "+endStations.size());
            if(startStations.size()>0&&endStations.size()>0){
                //拼接地址
                String startAddr = order.getSenderArea().replaceAll("/","")+order.getSenderStreet();
                String endAddr = order.getReceiverArea().replaceAll("/","")+order.getReceiverStreet();
                //解析经纬度
                String startLocation = Geo.geo(startAddr);
                String endLocation = Geo.geo(endAddr);

                //匹配最近站点
                Station nearestStartStation = Distance.getNearestStation(startStations, startLocation);
                Station nearestEndStation = Distance.getNearestStation(endStations, endLocation);
                //插入到数据库
                order.setStartStation(nearestStartStation.getId());
                order.setEndStation(nearestEndStation.getId());
                Log log = new Log(orderId, new Date(now),"已下单，等待揽件");
                System.out.println(order);
                orderMapper.insert(order);
                logMapper.insertSelective(log);
                resultMap.put("status",1);
                resultMap.put("msg","下单成功");
            }else {
                resultMap.put("status",0);
                resultMap.put("msg","暂无站点信息，无法创建订单");
            }

        }else {
            resultMap.put("status",0);
            resultMap.put("msg","请填写正确的地址");
        }

        return resultMap;
    }

    /**
     * 获取订单
     * @param orderId 订单编号
     * @return 查询结果集
     */
    public Map getOrder(String orderId){
        Map result = new HashMap();
        Order order = orderMapper.selectByOrderId(orderId);
        if (order!=null){
            result.put("status",1);
            result.put("msg","查找成功");
            result.put("order",order);
        }else{
            result.put("status",0);
            result.put("msg","没有找到");
        }
        return result;
    }

    /**
     * 更新订单
     * @param order 订单实体
     */
    public void update(Order order){
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 根据传来的订单编号数组，查询到对应的所有订单，并把每个订单的状态设为运输中，当前所在站点设为空
     * @param orderIdList 订单编号数组
     */
    public void updateOrderListToTransport(String[] orderIdList,int equipmentReceiptId){
        for(String orderId : orderIdList){
            Order order = orderMapper.selectByOrderId(orderId);
            order.setCurrentStation(null);
            order.setStatusId(3);
            order.setEquipmentReceiptId(equipmentReceiptId);
            orderMapper.updateByPrimaryKey(order);
        }
    }

    /**
     * 根据交接单id查询所有的订单
     * @param equipmentReceiptId 交接单id
     * @return 交接单下的所有订单
     */
    public List<Order> selectByEquipmentReceiptId(int equipmentReceiptId){
        return orderMapper.selectByEquipmentReceiptId(equipmentReceiptId);
    }

    /**
     * 确认交接单的时候，将订单所属的交接单id设为空
     * @param equipmentReceiptId
     */
    public void confirmEquipmentReceipt(int equipmentReceiptId,int stationId){
        orderMapper.confirmEquipmentReceipt(equipmentReceiptId,stationId);
    }

    /**
     * 设置异常单
     * @param orderId
     * @param currentStation
     */
    public void orderException(String orderId,int currentStation){
        orderMapper.orderException(orderId,currentStation);
    }

    /**
     * 选择所有某账号下的单
     * @param createrAccount 账号
     * @return 账号所创建的所有订单
     */
    public List<Order> selectByCreaterAccount(String createrAccount){
        return orderMapper.selectByCreaterAccount(createrAccount);
    }

//------------------------------------------------------------------


    /**
     * 查询当前站点下所有状态为“待揽件”的订单
     * @param stationId 站点id
     * @param pageNum 分页的当前页码
     * @param pageSize 每页的数据数
     * @return 当前站点下状态为“待揽件”的所有订单
     */
    public PageInfo selectByStatusId(int stationId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> allOrderInfo=orderMapper.selectByStatusId(stationId);
        PageInfo pageInfo=new PageInfo(allOrderInfo);
        return pageInfo;
    }

    /**
     * 查询当前站点下所有状态为“已入库”的订单
     * @param stationId 当前站点Id
     * @param pageNum
     * @param pageSize
     * @return 当前站点下状态为“已入库”的所有订单
     */
    public PageInfo selectWaitedOrder(int stationId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> allOrderInfo=orderMapper.selectWaitedOrder(stationId);
        PageInfo pageInfo=new PageInfo(allOrderInfo);
        return pageInfo;
    }

    /**
     * 查询终点站是本站的并且当前站是本站的状态为“已入库”的所有订单
     * @param stationId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo selectStockOrder(int stationId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> allOrderInfo=orderMapper.selectStockOrder(stationId);
        PageInfo pageInfo=new PageInfo(allOrderInfo);
        return pageInfo;
    }


    /**
     * 查询当前站点下所有状态为“异常”的订单(用来显示侧边栏的异常单个数)
     * @param stationId 站点Id
     * @return 当前站点下状态位“异常”的所有订单
     */
    public List<Order> selectOrderExceptionByStationId (int stationId){
        List<Order> orderList = orderMapper.selectOrderExceptionByStationId(stationId);
        return orderList;
    }

    /**
     * 查询当前站点下所有状态为“异常”的订单并进行分页处理
     * @param stationId 当前站点
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo selectOrderExceptionByStationIdWithPageInfo(int stationId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> allOrderInfo=orderMapper.selectOrderExceptionByStationId(stationId);
        PageInfo pageInfo=new PageInfo(allOrderInfo);
        return pageInfo;
    }

    /**
     * 更改异常单信息
     * @param order
     */
    public void updateExceptionOrder(Order order){
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
