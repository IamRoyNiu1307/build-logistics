package com.zoo.logistics.entity;

import java.io.Serializable;

public class OrderAmount implements Serializable {
    private Integer id;

    private String orderid;

    private Float freightCharge;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Float getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(Float freightCharge) {
        this.freightCharge = freightCharge;
    }

    public OrderAmount() {
    }

    public OrderAmount(String orderid, Float freightCharge) {
        this.orderid = orderid;
        this.freightCharge = freightCharge;
    }
}