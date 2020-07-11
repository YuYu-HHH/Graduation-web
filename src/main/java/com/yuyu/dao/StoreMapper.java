package com.yuyu.dao;

import com.yuyu.pojo.Store;
import org.apache.ibatis.annotations.Param;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    //通过id查看一家店铺所有信息
    Store selectByAdminId(@Param("id") Integer adminid);
    //查看数据库开闭的信息
    String checkopen(@Param("id") Integer id);

    int updateopen(Integer id);

    int updateclose(Integer id);

    int getstoreid(String storename);

    int updatelicesepicture(@Param("storeid") Integer storeid,@Param("license") String pictureuri);
}