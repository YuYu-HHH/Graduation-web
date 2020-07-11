package com.yuyu.pojo;

public class ReservationNow {
    private Integer id;

    private Integer numberNow;

    private Integer storeid;

    public ReservationNow(Integer id, Integer numberNow, Integer storeid) {
        this.id = id;
        this.numberNow = numberNow;
        this.storeid = storeid;
    }

    public ReservationNow() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberNow() {
        return numberNow;
    }

    public void setNumberNow(Integer numberNow) {
        this.numberNow = numberNow;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }
}