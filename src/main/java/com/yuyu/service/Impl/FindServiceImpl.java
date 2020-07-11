package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.FindMapper;
import com.yuyu.pojo.Find;
import com.yuyu.service.IFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iFindService")
public class FindServiceImpl implements IFindService {

    @Autowired
    private FindMapper findMapper;

    @Override
    public ServerResponse<PageInfo> SelectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Find> Finds = findMapper.selectAll();
        List<Find> FindListvoList = Lists.newArrayList();
        for (Find findItem : Finds) {
            Find FindListvo = assembleMenuListVo(findItem);
            FindListvoList.add(FindListvo);
        }
        PageInfo pageResult = new PageInfo(Finds);
        pageResult.setList(FindListvoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<Find> selectfindByid(int id) {
        if (id != 0){
            return ServerResponse.createBySuccess(findMapper.selectByPrimaryKey(id));
        }
        return ServerResponse.createByErrorMessage("信息错误，请查看后重试");
    }

    @Override
    public ServerResponse<Find> Addfind(Find find) {
        int i = findMapper.insert(find);
        if (i > 0){
            return ServerResponse.createBySuccess(find);
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    private Find assembleMenuListVo(Find find) {
        Find findListvo = new Find();
        findListvo.setId(find.getId());
        findListvo.setAdminid(find.getAdminid());
        findListvo.setPictureone(find.getPictureone());
        findListvo.setPicturetwo(find.getPicturetwo());
        findListvo.setPicturethree(find.getPicturethree());
        findListvo.setTitle(find.getTitle());
        findListvo.setText(find.getText());
        findListvo.setUserId(find.getUserId());
        findListvo.setTagone(find.getTagone());
        findListvo.setTagtwo(find.getTagtwo());
        findListvo.setTagthree(find.getTagthree());
        return findListvo;
    }
}
