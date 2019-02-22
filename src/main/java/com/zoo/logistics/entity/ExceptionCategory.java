package com.zoo.logistics.entity;

import java.io.Serializable;

public class ExceptionCategory implements Serializable {
    private Integer id;

    private String exceptionName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName == null ? null : exceptionName.trim();
    }
}