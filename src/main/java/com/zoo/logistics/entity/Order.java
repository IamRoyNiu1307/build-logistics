package com.zoo.logistics.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;

    private String orderId;

    private String senderName;

    private String senderTel;

    private String senderArea;

    private String senderStreet;

    private String receiverName;

    private String receiverTel;

    private String receiverArea;

    private String receiverStreet;

    private Integer statusId;

    private Integer equipmentReceiptId;

    private Integer categoryId;

    private Integer volume;

    private Integer weight;

    private Integer startStation;

    private Integer endStation;

    private Integer currentStation;

    private String createrAccount;

    private String createDate;

    private Date finishDate;

    private OrderCategory orderCategory;

    private Status status;

    private Log lastLog;

    private static final long serialVersionUID = 1L;

    private ExceptionOrder exceptionOrder;

    public ExceptionOrder getExceptionOrder() {
        return exceptionOrder;
    }

    public void setExceptionOrder(ExceptionOrder exceptionOrder) {
        this.exceptionOrder = exceptionOrder;
    }

    public Log getLastLog() {
        return lastLog;
    }

    public void setLastLog(Log lastLog) {
        this.lastLog = lastLog;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    public void setOrderCategory(OrderCategory orderCategory) {
        this.orderCategory = orderCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel == null ? null : senderTel.trim();
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea == null ? null : senderArea.trim();
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet == null ? null : senderStreet.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel == null ? null : receiverTel.trim();
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea == null ? null : receiverArea.trim();
    }

    public String getReceiverStreet() {
        return receiverStreet;
    }

    public void setReceiverStreet(String receiverStreet) {
        this.receiverStreet = receiverStreet == null ? null : receiverStreet.trim();
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

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
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

    public String getCreateDate() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date(Long.valueOf(orderId));
        String createDate=sdf.format(date);
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFinishDate() {


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

            if (finishDate == null){
                return null;
            }
            return sdf.format(finishDate);
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderTel='" + senderTel + '\'' +
                ", senderArea='" + senderArea + '\'' +
                ", senderStreet='" + senderStreet + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverTel='" + receiverTel + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverStreet='" + receiverStreet + '\'' +
                ", statusId=" + statusId +
                ", equipmentReceiptId=" + equipmentReceiptId +
                ", categoryId=" + categoryId +
                ", volume=" + volume +
                ", weight=" + weight +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", currentStation=" + currentStation +
                ", createrAccount='" + createrAccount + '\'' +
//                ", createDate=" + createDate +
                ", finishDate=" + finishDate +
                '}';
    }
}