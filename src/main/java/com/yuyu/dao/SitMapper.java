package com.yuyu.dao;

import com.yuyu.pojo.Sit;

import java.util.List;

public interface SitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sit record);

    int insertSelective(Sit record);

    Sit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sit record);

    int updateByPrimaryKey(Sit record);

    int checkidsitnumber(int sitnumber);

    List<Sit> selectAll();

    List<Sit> selectbystoreid(int storeid);

    int checksitnumber(int sitnumber);
}