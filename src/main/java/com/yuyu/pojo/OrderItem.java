package com.yuyu.pojo;

public class OrderItem {
    private Integer id;

    private String foodname;

    private String price;

    private Integer orderitemId;

    private Integer number;

    public OrderItem(Integer id, String foodname, String price, Integer orderitemId, Integer number) {
        this.id = id;
        this.foodname = foodname;
        this.price = price;
        this.orderitemId = orderitemId;
        this.number = number;
    }

    public OrderItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}