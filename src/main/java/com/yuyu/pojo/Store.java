package com.yuyu.pojo;

import java.util.Date;

public class Store {
    private Integer id;

    private Integer storeid;

    private String storename;

    private String storetype;

    private String location;

    private String openningtime;

    private String introduce;

    private Integer adminid;

    private String businessid;

    private String open;

    private Date createTime;

    private Date updateTime;

    private String license;

    public Store(Integer id, Integer storeid, String storename, String storetype, String location, String openningtime, String introduce, Integer adminid, String businessid, String open, Date createTime, Date updateTime, String license) {
        this.id = id;
        this.storeid = storeid;
        this.storename = storename;
        this.storetype = storetype;
        this.location = location;
        this.openningtime = openningtime;
        this.introduce = introduce;
        this.adminid = adminid;
        this.businessid = businessid;
        this.open = open;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.license = license;
    }

    public Store() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype == null ? null : storetype.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getOpenningtime() {
        return openningtime;
    }

    public void setOpenningtime(String openningtime) {
        this.openningtime = openningtime == null ? null : openningtime.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid == null ? null : businessid.trim();
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open == null ? null : open.trim();
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }
}