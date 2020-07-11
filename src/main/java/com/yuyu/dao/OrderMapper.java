package com.yuyu.dao;

import com.yuyu.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectall(@Param("userid") int user_id);

    List<Order> selectalBystore(int order_no);

    int updateEvaluationIdByitemid(@Param("itemId") int itemId,@Param("evaluationId") int evaluationId);
}