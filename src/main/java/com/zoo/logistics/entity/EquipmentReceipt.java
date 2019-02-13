package com.zoo.logistics.entity;

import java.io.Serializable;
import java.util.Date;

public class EquipmentReceipt implements Serializable {
    private Integer id;

    private String carLicenseNumber;

    private Integer status;

    private Date createDate;

    private Date finishDate;

    private static final long serialVersionUID = 1L;

    public EquipmentReceipt() {
    }

    public EquipmentReceipt(String carLicenseNumber, Integer status, Date createDate) {
        this.carLicenseNumber = carLicenseNumber;
        this.status = status;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarLicenseNumber() {
        return carLicenseNumber;
    }

    public void setCarLicenseNumber(String carLicenseNumber) {
        this.carLicenseNumber = carLicenseNumber == null ? null : carLicenseNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}