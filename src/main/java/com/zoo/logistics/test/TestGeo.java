package com.zoo.logistics.test;

import com.zoo.logistics.api.Distance;
import com.zoo.logistics.api.Geo;
import com.zoo.logistics.api.ReGeo;
import com.zoo.logistics.entity.Car;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.mapper.CarMapper;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.TreeSet;

public class TestGeo {
    @Test
    public void testGeo(){
        String location = Geo.geo("广东省/深圳市/深圳市世界之窗");
        System.out.println(location);
    }
    @Test
    public void testReGeo(){
        Map map = ReGeo.reGeo("113.625368","34.746599");
        System.out.println(map);
    }

    @Test
    public void testCreateOrderId(){
        String orderId = String.valueOf(System.currentTimeMillis());
        System.out.println(orderId);
    }

    @Test
    public void testOrderPosGeo(){
        Order order = new Order();
        order.setSenderArea("河南省/郑州市/金水区");
        order.setSenderStreet("杨金路牛顿国际");
        order.setReceiverArea("广东省/深圳市");
        order.setReceiverStreet("大芬站");

        String area = order.getSenderArea();

    }

    @Test
    public void testSet(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(1);
        set.add(10);
        set.add(5);
        set.add(3);
        System.out.println(set);
    }

    @Test
    public void getDist(){
        System.out.println(Distance.getDistance("113.779371,34.759051", "113.666427,34.751362"));
    }
}
