package com.zoo.logistics.entity;

import java.io.Serializable;

public class CarCategory implements Serializable {

    //车辆种类id
    private Integer id;

    //种类名称
    private String name;

    //容量
    private Integer capacity;

    //承重
    private Integer loadBearing;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getLoadBearing() {
        return loadBearing;
    }

    public void setLoadBearing(Integer loadBearing) {
        this.loadBearing = loadBearing;
    }

    @Override
    public String toString() {
        return "CarCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", loadBearing=" + loadBearing +
                '}';
    }
}