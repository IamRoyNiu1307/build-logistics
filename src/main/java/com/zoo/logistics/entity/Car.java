package com.zoo.logistics.entity;

import java.io.Serializable;

public class Car implements Serializable {

    //车辆id
    private Integer id;

    //车牌号码
    private String licenseNumber;

    //线路id
    private Integer routeId;

    //车辆状态id
    private Integer carStatus;

    //车辆种类id
    private Integer carCategoryId;

    //车辆种类实体类
    private CarCategory carCategory;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber == null ? null : licenseNumber.trim();
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(Integer carCategoryId) {
        this.carCategoryId = carCategoryId;
    }

    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", routeId=" + routeId +
                ", carStatus=" + carStatus +
                ", carCategoryId=" + carCategoryId +
                ", carCategory=" + carCategory +
                '}';
    }
}