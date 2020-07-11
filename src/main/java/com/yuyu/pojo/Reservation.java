package com.yuyu.pojo;

public class Reservation {
    private Integer id;

    private Integer rnumber;

    private Integer storeid;

    private Integer customerid;

    public Reservation(Integer id, Integer rnumber, Integer storeid, Integer customerid) {
        this.id = id;
        this.rnumber = rnumber;
        this.storeid = storeid;
        this.customerid = customerid;
    }

    public Reservation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRnumber() {
        return rnumber;
    }

    public void setRnumber(Integer rnumber) {
        this.rnumber = rnumber;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }
}