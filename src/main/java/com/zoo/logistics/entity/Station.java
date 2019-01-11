package com.zoo.logistics.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Station implements Serializable {
    private Integer id;

    private String stationCityid;

    private BigDecimal posLng;

    private BigDecimal posLat;

    private Integer level;

    private Integer superior;

    private Integer warehouseCapacity;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationCityid() {
        return stationCityid;
    }

    public void setStationCityid(String stationCityid) {
        this.stationCityid = stationCityid == null ? null : stationCityid.trim();
    }

    public BigDecimal getPosLng() {
        return posLng;
    }

    public void setPosLng(BigDecimal posLng) {
        this.posLng = posLng;
    }

    public BigDecimal getPosLat() {
        return posLat;
    }

    public void setPosLat(BigDecimal posLat) {
        this.posLat = posLat;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSuperior() {
        return superior;
    }

    public void setSuperior(Integer superior) {
        this.superior = superior;
    }

    public Integer getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(Integer warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", stationCityid='" + stationCityid + '\'' +
                ", posLng=" + posLng +
                ", posLat=" + posLat +
                ", level=" + level +
                ", superior=" + superior +
                ", warehouseCapacity=" + warehouseCapacity +
                '}';
    }
}