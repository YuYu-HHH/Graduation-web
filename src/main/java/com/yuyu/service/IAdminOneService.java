package com.yuyu.service;

import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Adminone;
import com.yuyu.pojo.Admintwo;

public interface IAdminOneService {

    ServerResponse<Adminone> Login(String username, String password);

    ServerResponse<String> Register(Adminone adminone);

    ServerResponse<String> AddAdmintwo(Admintwo admintwo);

    ServerResponse<String> DeleteAdmintwo(String phone);
}
