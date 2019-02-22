package com.zoo.logistics.entity;

import java.io.Serializable;

public class ExceptionOrder implements Serializable {
    private Integer id;

    private String orderId;

    private Integer exceptionCategoryId;

    private ExceptionCategory exceptionCategory;

    private Float compensate;

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

    public Integer getExceptionCategoryId() {
        return exceptionCategoryId;
    }

    public void setExceptionCategoryId(Integer exceptionCategoryId) {
        this.exceptionCategoryId = exceptionCategoryId;
    }

    public ExceptionCategory getExceptionCategory() {
        return exceptionCategory;
    }

    public void setExceptionCategory(ExceptionCategory exceptionCategory) {
        this.exceptionCategory = exceptionCategory;
    }

    public Float getCompensate() {
        return compensate;
    }

    public void setCompensate(Float compensate) {
        this.compensate = compensate;
    }
}