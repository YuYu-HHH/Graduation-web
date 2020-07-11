package com.yuyu.service.Impl;

import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.AdmintwoMapper;
import com.yuyu.pojo.Adminone;
import com.yuyu.pojo.Admintwo;
import com.yuyu.service.IAdminOneService;
import com.yuyu.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iAdminOneService")
public class AdminOneServiceImpl implements IAdminOneService {

    @Autowired
    private AdminoneMapper adminoneMapper;

    @Autowired
    private AdmintwoMapper admintwoMapper;

    /**
     * 一级管理员登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<Adminone> Login(String username, String password) {
        int resultCount = adminoneMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名错误");
        }
        //todo 密码登录MD5
        String md5password = MD5Util.MD5EncodeUtf8(password);
        Adminone adminone = adminoneMapper.selectLogin(username, md5password);
        if (adminone == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        adminone.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", adminone);
    }

    /**
     * 一级管理员注册
     *
     * @param adminone
     * @return
     */
    @Override
    public ServerResponse<String> Register(Adminone adminone) {
        int resultCount = adminoneMapper.checkUsername(adminone.getUsername());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        adminone.setPassword(MD5Util.MD5EncodeUtf8(adminone.getPassword()));
        resultCount = adminoneMapper.insert(adminone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功");
    }


    /**
     * 一级管理员添加二级管理员
     *
     * @param admintwo
     * @return
     */
    @Override
    public ServerResponse<String> AddAdmintwo(Admintwo admintwo) {
        int resultCount = admintwoMapper.checkUsername(admintwo.getUsername());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("用户名已经存在");
        }
        admintwo.setPassword(MD5Util.MD5EncodeUtf8(admintwo.getPassword()));
        resultCount = admintwoMapper.insert(admintwo);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccess("注册成功");
    }

    @Override
    public ServerResponse<String> DeleteAdmintwo(String phone) {
        int ResultId = adminoneMapper.getId(phone);
        if (adminoneMapper.deleteByPrimaryKey(ResultId) > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }
}
