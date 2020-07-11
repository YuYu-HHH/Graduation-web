package com.yuyu.service;


import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Sit;

public interface ISitService {
    //管理员添加座位信息
    ServerResponse<Sit> addsit(Sit sit);
    //管理员更新座位信息
    ServerResponse<Sit> updatesit(Sit sit);
    //管理员删除座位信息
    ServerResponse<Sit> deletesit(Sit sit);
    //用户，管理员查看座位信息
    ServerResponse<PageInfo> checkSit(String username,int pageNum, int pageSize);

    ServerResponse<PageInfo> GetAllRefresh(String username,String size,int pageNum, int pageSize);

    ServerResponse<PageInfo> getAllSit(String storeid,int pageNum, int pageSize);

}
