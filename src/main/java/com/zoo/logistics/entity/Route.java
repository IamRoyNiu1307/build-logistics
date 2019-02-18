package com.zoo.logistics.entity;

import java.io.Serializable;

public class Route implements Serializable {
    private Integer id;

    private Integer startStationId;

    private Integer endStationId;

    private Station startstation;

    private Station endstation;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Integer startStationId) {
        this.startStationId = startStationId;
    }

    public Integer getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Integer endStationId) {
        this.endStationId = endStationId;
    }

    public Station getStartstation() {
        return startstation;
    }

    public void setStartstation(Station startstation) {
        this.startstation = startstation;
    }

    public Station getEndstation() {
        return endstation;
    }

    public void setEndstation(Station endstation) {
        this.endstation = endstation;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startStationId=" + startStationId +
                ", endStationId=" + endStationId +
                ", startstation=" + startstation +
                ", endstation=" + endstation +
                '}';
    }
}