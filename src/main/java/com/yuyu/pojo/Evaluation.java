package com.yuyu.pojo;

public class Evaluation {
    private Integer id;

    private String pictureone;

    private String picturetwo;

    private String picturethree;

    private String foodname;

    private String text;

    private String level;

    private Integer average;

    private Integer userId;

    private Integer menuId;

    private Integer storeId;

    public Evaluation(Integer id, String pictureone, String picturetwo, String picturethree, String foodname, String text, String level, Integer average, Integer userId, Integer menuId, Integer storeId) {
        this.id = id;
        this.pictureone = pictureone;
        this.picturetwo = picturetwo;
        this.picturethree = picturethree;
        this.foodname = foodname;
        this.text = text;
        this.level = level;
        this.average = average;
        this.userId = userId;
        this.menuId = menuId;
        this.storeId = storeId;
    }

    public Evaluation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureone() {
        return pictureone;
    }

    public void setPictureone(String pictureone) {
        this.pictureone = pictureone == null ? null : pictureone.trim();
    }

    public String getPicturetwo() {
        return picturetwo;
    }

    public void setPicturetwo(String picturetwo) {
        this.picturetwo = picturetwo == null ? null : picturetwo.trim();
    }

    public String getPicturethree() {
        return picturethree;
    }

    public void setPicturethree(String picturethree) {
        this.picturethree = picturethree == null ? null : picturethree.trim();
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}