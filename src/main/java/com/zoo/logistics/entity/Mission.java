package com.zoo.logistics.entity;

import java.io.Serializable;

public class Mission implements Serializable {
    private Integer id;

    private String orderId;

    private String courierAccount;

    private Integer status;

    private Order order;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCourierAccount() {
        return courierAccount;
    }

    public void setCourierAccount(String courierAccount) {
        this.courierAccount = courierAccount == null ? null : courierAccount.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Mission() {
    }

    public Mission(String orderId, String courierAccount, Integer status) {
        this.orderId = orderId;
        this.courierAccount = courierAccount;
        this.status = status;
    }
}