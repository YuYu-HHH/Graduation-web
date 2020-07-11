package com.yuyu.dao;

import com.yuyu.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    int checkPhone(String phone);

    int checkId(String phone);

    String check(@Param("phone") String phone);

    int checkUandP(@Param("phone") String phone,@Param("password") String password);

    //int getId(@Param("username")String username);
}