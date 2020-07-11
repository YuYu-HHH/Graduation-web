package com.yuyu.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Find;

public interface IFindService {
    ServerResponse<PageInfo> SelectAll(int pageNum, int pageSize);

    ServerResponse<Find> selectfindByid(int id);

    ServerResponse<Find> Addfind(Find find);
}
