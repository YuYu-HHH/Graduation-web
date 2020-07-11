package com.yuyu.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer adminid;

    private String orderNo;

    private Integer itemId;

    private Integer userId;

    private Date paymentTime;

    private String payment;

    private Date createTime;

    private Date updateTime;

    private Integer evaluationId;

    private String situation;

    public Order(Integer id, Integer adminid, String orderNo, Integer itemId, Integer userId, Date paymentTime, String payment, Date createTime, Date updateTime, Integer evaluationId, String situation) {
        this.id = id;
        this.adminid = adminid;
        this.orderNo = orderNo;
        this.itemId = itemId;
        this.userId = userId;
        this.paymentTime = paymentTime;
        this.payment = payment;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.evaluationId = evaluationId;
        this.situation = situation;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation == null ? null : situation.trim();
    }
}