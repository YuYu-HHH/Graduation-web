package com.yuyu.pojo;

import java.util.Date;

public class Customer {
    private Integer id;

    private String phone;

    private String password;

    private String petname;

    private String accounts;

    private Date createTime;

    private Date updateTime;

    private String love;

    public Customer(Integer id, String phone, String password, String petname, String accounts, Date createTime, Date updateTime, String love) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.petname = petname;
        this.accounts = accounts;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.love = love;
    }

    public Customer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname == null ? null : petname.trim();
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts == null ? null : accounts.trim();
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

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love == null ? null : love.trim();
    }
}