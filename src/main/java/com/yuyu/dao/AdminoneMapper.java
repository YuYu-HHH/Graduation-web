package com.yuyu.dao;

import com.yuyu.pojo.Adminone;
import org.apache.ibatis.annotations.Param;

public interface AdminoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adminone record);

    int insertSelective(Adminone record);

    Adminone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adminone record);

    int updateByPrimaryKey(Adminone record);

    int checkUsername(String username);

    Adminone selectLogin(@Param("username") String username, @Param("password") String password);

    int getAdminoneIdByusernmae(String username);

    int getstoreidbyusername(String username);

    int getId(String phone);
}