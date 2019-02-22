package com.zoo.logistics.entity;

import java.io.Serializable;

public class CarApplication implements Serializable {
    private Integer id;

    private Integer stationId;

    private Integer routeId;

    private Integer status;

    private Integer carCategoryId;

    private Integer carCount;

    private Station station;

    private CarCategory carCategory;

    private Route route;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(Integer carCategoryId) {
        this.carCategoryId = carCategoryId;
    }

    public Integer getCarCount() {
        return carCount;
    }

    public void setCarCount(Integer carCount) {
        this.carCount = carCount;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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

    @Override
    public String toString() {
        return "CarApplication{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", routeId=" + routeId +
                ", status=" + status +
                ", carCategoryId=" + carCategoryId +
                ", carCount=" + carCount +
                ", station=" + station +
                ", carCategory=" + carCategory +
                ", route=" + route +
                '}';
    }
}