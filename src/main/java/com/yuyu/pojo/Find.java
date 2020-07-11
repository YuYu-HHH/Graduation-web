package com.yuyu.pojo;

public class Find {
    private Integer id;

    private String title;

    private String pictureone;

    private String picturetwo;

    private String picturethree;

    private String text;

    private Integer adminid;

    private Integer userId;

    private String tagone;

    private String tagtwo;

    private String tagthree;

    public Find(Integer id, String title, String pictureone, String picturetwo, String picturethree, String text, Integer adminid, Integer userId, String tagone, String tagtwo, String tagthree) {
        this.id = id;
        this.title = title;
        this.pictureone = pictureone;
        this.picturetwo = picturetwo;
        this.picturethree = picturethree;
        this.text = text;
        this.adminid = adminid;
        this.userId = userId;
        this.tagone = tagone;
        this.tagtwo = tagtwo;
        this.tagthree = tagthree;
    }

    public Find() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTagone() {
        return tagone;
    }

    public void setTagone(String tagone) {
        this.tagone = tagone == null ? null : tagone.trim();
    }

    public String getTagtwo() {
        return tagtwo;
    }

    public void setTagtwo(String tagtwo) {
        this.tagtwo = tagtwo == null ? null : tagtwo.trim();
    }

    public String getTagthree() {
        return tagthree;
    }

    public void setTagthree(String tagthree) {
        this.tagthree = tagthree == null ? null : tagthree.trim();
    }
}