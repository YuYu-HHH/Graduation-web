package com.yuyu.dao;

import com.yuyu.pojo.Evaluation;

import java.util.List;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);

    List<Evaluation> selectbymenuid(Integer menu_id);

    int getIdBymenuid(Integer menu_id);

    List<Evaluation> selectbystoreid(Integer store_id);
}