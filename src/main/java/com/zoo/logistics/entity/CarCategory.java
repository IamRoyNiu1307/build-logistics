package com.zoo.logistics.entity;

import java.io.Serializable;

public class CarCategory implements Serializable {

    //车辆种类id
    private Integer id;

    //种类名称
    private String name;

    //容量
    private Float capacity;

    //承重
    private Float loadBearing;

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

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Float getLoadBearing() {
        return loadBearing;
    }

    public void setLoadBearing(Float loadBearing) {
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