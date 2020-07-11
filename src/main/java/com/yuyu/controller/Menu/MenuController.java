package com.yuyu.controller.Menu;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Menu;
import com.yuyu.service.IFileService;
import com.yuyu.service.IMenuService;
import com.yuyu.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/menu/")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

    @Autowired
    private IFileService iFileService;

    /**
     * 增加菜品目录
     * @return
     */
    @RequestMapping("addmenu.do")
    @ResponseBody
    public ServerResponse<Menu> AddMenu(@RequestParam("foodname") String foodname,
                                          @RequestParam(value = "price") String price,
                                          @RequestParam(value = "tagone",required = false) String tagone,
                                          @RequestParam(value = "tagtwo",required = false) String tagtwo,
                                          @RequestParam(value = "tagthree",required = false) String tagthree,
                                          @RequestParam(value = "tagfour",required = false) String tagfour,
                                          @RequestParam(value = "tagfive",required = false) String tagfive,
                                          @RequestParam(value = "tagsix",required = false) String tagsix,
                                          @RequestParam(value = "existence") String existence,
                                          @RequestParam(value = "upload_file",required = false) MultipartFile file,
                                        HttpServletRequest request) {
        Menu menu = new Menu();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);

        //todo url添加到数据库中
        menu.setFoodname(foodname);
        menu.setPrice(Float.valueOf(price));
        menu.setTagone(tagone);
        menu.setTagtwo(tagtwo);
        menu.setTagthree(tagthree);
        menu.setTagfour(tagfour);
        menu.setTagfive(tagfive);
        menu.setTagesix(tagsix);
        menu.setPicture(url);
        menu.setExistence(Integer.valueOf(existence));
        return iMenuService.addmenu(menu);
        //return ServerResponse.createBySuccess(foodname);
    }

    /**
     * 删除菜品
     *
     * @param menu
     * @return
     */
    @RequestMapping("deletemenu.do")
    @ResponseBody
    public ServerResponse<String> DeleteMenu(Menu menu) {
        //先获取id，在删除
        return iMenuService.DeleteMenu(menu.getFoodname());
    }
    /**
     * 删除菜品
     * @param foodname
     * @return
     */
    @RequestMapping("deletemenubyfoodname.do")
    @ResponseBody
    public ServerResponse<String> DeleteMenu(String foodname) {
        //先获取id，在删除
        return iMenuService.DeleteMenu(foodname);
    }
    /**
     * 修改菜品
     * @param foodname
     * @param price
     * @param tagone
     * @param tagtwo
     * @param tagthree
     * @param tagfour
     * @param tagfive
     * @param tagsix
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("updatemenu.do")
    @ResponseBody
    public ServerResponse<Menu> UpdateMenu(@RequestParam("foodname") String foodname,
                                           @RequestParam(value = "price") String price,
                                           @RequestParam(value = "tagone",required = false) String tagone,
                                           @RequestParam(value = "tagtwo",required = false) String tagtwo,
                                           @RequestParam(value = "tagthree",required = false) String tagthree,
                                           @RequestParam(value = "tagfour",required = false) String tagfour,
                                           @RequestParam(value = "tagfive",required = false) String tagfive,
                                           @RequestParam(value = "tagsix",required = false) String tagsix,
                                           @RequestParam(value = "upload_file",required = false) MultipartFile file,
                                           HttpServletRequest request) {
        Menu menu = new Menu();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);

        menu.setFoodname(foodname);
        menu.setPrice(Float.valueOf(price));
        menu.setTagone(tagone);
        menu.setTagtwo(tagtwo);
        menu.setTagthree(tagthree);
        menu.setTagfour(tagfour);
        menu.setTagfive(tagfive);
        menu.setTagesix(tagsix);
        menu.setPicture(url);
        return iMenuService.UpdateMenu(menu);
    }

    /**
     * 根据名称查找菜品的具体信息
     * @param foodname
     * @return
     */
    @RequestMapping("selectmenubyname.do")
    @ResponseBody
    public ServerResponse<Menu> SelectMenuByname(String foodname) {
        return iMenuService.SelectMenuByname(foodname);
    }

    /**
     * 查找所有的信息
     * todo 完成
     * @return
     */
    @RequestMapping("selectall.do")
    @ResponseBody
    public ServerResponse<PageInfo> GetAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iMenuService.SelectAll(pageNum, pageSize);
    }


    @RequestMapping("uploadmenupicture.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value="upload_file",required = false) MultipartFile file, HttpServletRequest request ){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);

        return ServerResponse.createBySuccess(fileMap);
    }
}
