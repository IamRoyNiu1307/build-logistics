package com.zoo.logistics.entity;

import java.io.Serializable;

public class Car implements Serializable {
    private Integer id;

    private String licenseNumber;

    private Integer routeId;

    private Integer carStatus;

    private Integer carCategoryId;

    private CarCategory carCategory;

    private Route route;

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Car() {
    }

    public Car(Integer id, String licenseNumber, Integer routeId, Integer carStatus, Integer carCategoryId) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.routeId = routeId;
        this.carStatus = carStatus;
        this.carCategoryId = carCategoryId;
    }

    public Car(String licenseNumber, Integer routeId, Integer carStatus, Integer carCategoryId) {
        this.licenseNumber = licenseNumber;
        this.routeId = routeId;
        this.carStatus = carStatus;
        this.carCategoryId = carCategoryId;
    }


}