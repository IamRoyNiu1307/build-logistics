package com.zoo.logistics.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Station implements Serializable {
    //编号
    private Integer id;

    //站点名称
    private String stationName;

    //站点城市id
    private String stationCityid;

    //站点联系电话
    private String tel;

    //站点经纬度
    private BigDecimal posLng;

    private BigDecimal posLat;

    //站点级别
    private Integer level;

    //站点上级id
    private Integer superior;

    //仓库容量
    private Integer warehouseCapacity;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
                ", stationName='" + stationName + '\'' +
                ", stationCityid='" + stationCityid + '\'' +
                ", tel='" + tel + '\'' +
                ", posLng=" + posLng +
                ", posLat=" + posLat +
                ", level=" + level +
                ", superior=" + superior +
                ", warehouseCapacity=" + warehouseCapacity +
                '}';
    }
}