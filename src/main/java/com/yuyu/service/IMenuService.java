package com.yuyu.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Menu;

import java.util.List;

public interface IMenuService {
    ServerResponse<Menu> addmenu(Menu menu);

    ServerResponse<String> DeleteMenu(String foodname);

    ServerResponse<Menu> UpdateMenu(Menu menu);

    ServerResponse<Menu> SelectMenuByname(String foodname);

    ServerResponse<PageInfo> SelectAll(int pageNum, int pageSize);

}
