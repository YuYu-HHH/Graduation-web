package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.SitMapper;
import com.yuyu.pojo.Sit;
import com.yuyu.service.ISitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iSitService")
public class SitServiceImpl implements ISitService {

    @Autowired
    private SitMapper sitMapper;

    @Autowired
    private AdminoneMapper adminoneMapper;


    /**
     * 管理员添加座位信息
     * @param sit
     * @return
     */
    @Override
    public ServerResponse<Sit> addsit(Sit sit) {
        int SitNumber = sit.getSitnumber();
        System.out.println(SitNumber);
        int Result = sitMapper.checksitnumber(SitNumber);
        System.out.println("出现one");
        if (Result > 0){
            return ServerResponse.createByErrorMessage("提交信息有误，请检查");
        }
        else {
            System.out.println("出现Two");
            int ResultCount = sitMapper.insert(sit);
            if (ResultCount > 0) {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
            return ServerResponse.createByErrorMessage("添加失败");
        }
    }

    /**
     * 管理员更新座位信息
     * @param sit
     * @return
     */
    @Override
    public ServerResponse<Sit> updatesit(Sit sit) {
        int ResultCount = sitMapper.updateByPrimaryKeySelective(sit);
        if (ResultCount > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    /**
     * 管理员删除座位信息
     * @param sit
     * @return
     */
    @Override
    public ServerResponse<Sit> deletesit(Sit sit) {
        int ResultId = sitMapper.checkidsitnumber(sit.getSitnumber());
        if (ResultId == sit.getId()){
            int ResultCount = sitMapper.deleteByPrimaryKey(ResultId);
            if (ResultCount > 0){
                return ServerResponse.createBySuccessMessage("删除成功,"+sit.getSitnumber()+"座位");
            }
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 用户，管理员查看座位信息
     * @return
     */
    @Override
    public ServerResponse<PageInfo> checkSit(String username,int pageNum, int pageSize) {
        int storeid = adminoneMapper.getstoreidbyusername(username);
        PageHelper.startPage(pageNum, pageSize);
        List<Sit> sits = sitMapper.selectbystoreid(storeid);
        List<Sit> sitList = Lists.newArrayList();
        for (Sit sitItem : sits) {
            Sit sit = assembleSitDtailVo(sitItem);
            sitList.add(sit);
        }
        PageInfo pageResult = new PageInfo(sits);
        pageResult.setList(sitList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> GetAllRefresh(String username, String size, int pageNum, int pageSize) {
        int storeid = adminoneMapper.getstoreidbyusername(username);
        PageHelper.startPage(pageNum, pageSize);
        List<Sit> sits = sitMapper.selectbystoreid(storeid);
        List<Sit> sits1 = Lists.newArrayList();
        for (int i=Integer.valueOf(size);i<sits.size();i++){
            sits1.add(sits.get(i));
        }
        List<Sit> sitList = Lists.newArrayList();
        for (Sit sitItem : sits1) {
            Sit sit = assembleSitDtailVo(sitItem);
            sitList.add(sit);
        }
        PageInfo pageResult = new PageInfo(sits);
        pageResult.setList(sitList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> getAllSit(String storeid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sit> sits = sitMapper.selectbystoreid(Integer.valueOf(storeid));
        List<Sit> sitList = Lists.newArrayList();
        for (Sit sitItem : sits) {
            Sit sit = assembleSitDtailVo(sitItem);
            sitList.add(sit);
        }
        PageInfo pageResult = new PageInfo(sits);
        pageResult.setList(sitList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private Sit assembleSitDtailVo(Sit sit) {
        Sit sitnew = new Sit();

        sitnew.setId(sit.getId());
        sitnew.setSitnumber(sit.getSitnumber());
        sitnew.setStoreid(sit.getStoreid());
        sitnew.setYn(sit.getYn());

        return sitnew;
    }
}
