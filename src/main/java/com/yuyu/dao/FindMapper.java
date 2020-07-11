package com.yuyu.dao;

import com.yuyu.pojo.Find;

import java.util.List;

public interface FindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Find record);

    int insertSelective(Find record);

    Find selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Find record);

    int updateByPrimaryKey(Find record);

    List<Find> selectAll();
}