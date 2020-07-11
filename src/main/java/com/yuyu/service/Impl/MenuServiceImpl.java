package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.MenuMapper;
import com.yuyu.pojo.Menu;
import com.yuyu.service.IMenuService;
import com.yuyu.util.DataTimeUtil;
import com.yuyu.util.PropertiesUtil;
import com.yuyu.vo.MenuListvo;
import com.yuyu.vo.menuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iMenuService")
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ServerResponse<Menu> addmenu(Menu menu) {
        /*int resultCount = menuMapper.selectid(menu.getFoodname());
        if (resultCount > 0 ){
            return ServerResponse.createByErrorMessage("此食品已经存在");
        }*/
        int resultCount = menuMapper.insert(menu);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccess("添加成功", menu);
    }

    @Override
    public ServerResponse<String> DeleteMenu(String foodname) {
        int resultCount = menuMapper.selectid(foodname);
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("此食品不存在");
        }
        resultCount = menuMapper.deleteByPrimaryKey(resultCount);
        if (resultCount == 0) {
            return ServerResponse.createBySuccess("删除失败");
        }
        return ServerResponse.createBySuccess("删除成功");
    }

    @Override
    public ServerResponse<Menu> UpdateMenu(Menu menu) {
        //menu.setId(resultid);
        int resultid = menuMapper.selectid(menu.getFoodname());
        menu.setId(resultid);
        int resultCount = menuMapper.updateByPrimaryKeySelective(menu);
        return ServerResponse.createBySuccess(menuMapper.selectByPrimaryKey(resultid));
    }

    @Override
    public ServerResponse<Menu> SelectMenuByname(String foodname) {
        int resultid = menuMapper.selectid(foodname);
        return ServerResponse.createBySuccess(menuMapper.selectByPrimaryKey(resultid));
    }

    @Override
    public ServerResponse<PageInfo> SelectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = menuMapper.selectAll();
        List<MenuListvo> menuListvoList = Lists.newArrayList();
        for (Menu menuItem : menus) {
            MenuListvo menuListvo = assembleMenuListVo(menuItem);
            menuListvoList.add(menuListvo);
        }
        PageInfo pageResult = new PageInfo(menus);
        pageResult.setList(menuListvoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private menuDetailVo assembleMenuDtailVo(Menu menu) {
        menuDetailVo menuDetailvo = new menuDetailVo();
        menuDetailvo.setId(menu.getId());
        menuDetailvo.setFoodname(menu.getFoodname());
        menuDetailvo.setExistence(menu.getExistence());
        menuDetailvo.setPicture(menu.getPicture());
        menuDetailvo.setPrice(menu.getPrice());
        menuDetailvo.setTagone(menu.getTagone());
        menuDetailvo.setTagtwo(menu.getTagtwo());
        menuDetailvo.setTagthree(menu.getTagthree());
        menuDetailvo.setTagfour(menu.getTagfour());
        menuDetailvo.setTagfive(menu.getTagfive());
        menuDetailvo.setTagesix(menu.getTagesix());
        menuDetailvo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefi", "47.103.5.243/upload/"));

        menuDetailvo.setCreateTime(DataTimeUtil.dateToStr(menu.getCreateTime()));
        menuDetailvo.setUpdateTime(DataTimeUtil.dateToStr(menu.getUpdateTime()));
        return menuDetailvo;
    }

    private MenuListvo assembleMenuListVo(Menu menu) {
        MenuListvo menuListvo = new MenuListvo();
        menuListvo.setId(menu.getId());
        menuListvo.setFoodname(menu.getFoodname());
        menuListvo.setExistence(menu.getExistence());
        menuListvo.setPicture(menu.getPicture());
        menuListvo.setPrice(menu.getPrice());
        menuListvo.setTagone(menu.getTagone());
        menuListvo.setTagtwo(menu.getTagtwo());
        menuListvo.setTagthree(menu.getTagthree());
        menuListvo.setTagfour(menu.getTagfour());
        menuListvo.setTagfive(menu.getTagfive());
        menuListvo.setTagesix(menu.getTagesix());
        menuListvo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "47.103.5.243"));
        return menuListvo;
    }
}
