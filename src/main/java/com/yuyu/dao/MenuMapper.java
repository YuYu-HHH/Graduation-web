package com.yuyu.dao;

import com.yuyu.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    int selectid(String foodname);

    List<Menu> selectAll();

    int getMenuId(String foodname);
}