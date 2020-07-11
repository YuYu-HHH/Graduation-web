package com.yuyu.dao;

import com.yuyu.pojo.ReservationNow;

public interface ReservationNowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReservationNow record);

    int insertSelective(ReservationNow record);

    ReservationNow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReservationNow record);

    int updateByPrimaryKey(ReservationNow record);

    int getidBystoreid(int storeid);
}