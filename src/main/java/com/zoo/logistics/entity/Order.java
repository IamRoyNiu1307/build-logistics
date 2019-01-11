package com.zoo.logistics.entity;

import java.io.Serializable;
import java.util.Date;

public class Order extends OrderKey implements Serializable {
    private String senderName;

    private String senderArea;

    private String senderDetail;

    private String receiverName;

    private String receiverArea;

    private String receiverDetail;

    private Integer statusId;

    private Integer equipmentReceiptId;

    private Integer categoryId;

    private Integer volume;

    private Integer weight;

    private Integer startStation;

    private Integer currentStation;

    private String createrAccount;

    private Date createDate;

    private Date finishDate;

    private static final long serialVersionUID = 1L;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea == null ? null : senderArea.trim();
    }

    public String getSenderDetail() {
        return senderDetail;
    }

    public void setSenderDetail(String senderDetail) {
        this.senderDetail = senderDetail == null ? null : senderDetail.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea == null ? null : receiverArea.trim();
    }

    public String getReceiverDetail() {
        return receiverDetail;
    }

    public void setReceiverDetail(String receiverDetail) {
        this.receiverDetail = receiverDetail == null ? null : receiverDetail.trim();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getEquipmentReceiptId() {
        return equipmentReceiptId;
    }

    public void setEquipmentReceiptId(Integer equipmentReceiptId) {
        this.equipmentReceiptId = equipmentReceiptId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Integer currentStation) {
        this.currentStation = currentStation;
    }

    public String getCreaterAccount() {
        return createrAccount;
    }

    public void setCreaterAccount(String createrAccount) {
        this.createrAccount = createrAccount == null ? null : createrAccount.trim();
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