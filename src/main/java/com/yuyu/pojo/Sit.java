package com.yuyu.pojo;

public class Sit {
    private Integer id;

    private Integer storeid;

    private Integer sitnumber;

    private Integer yn;

    public Sit(Integer id, Integer storeid, Integer sitnumber, Integer yn) {
        this.id = id;
        this.storeid = storeid;
        this.sitnumber = sitnumber;
        this.yn = yn;
    }

    public Sit() {
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

    public Integer getSitnumber() {
        return sitnumber;
    }

    public void setSitnumber(Integer sitnumber) {
        this.sitnumber = sitnumber;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}