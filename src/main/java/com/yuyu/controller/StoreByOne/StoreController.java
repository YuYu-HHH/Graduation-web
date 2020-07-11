package com.yuyu.controller.StoreByOne;

import com.google.common.collect.Maps;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.StoreMapper;
import com.yuyu.pojo.Store;
import com.yuyu.service.IFileService;
import com.yuyu.service.IStoreService;
import com.yuyu.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * 店面部分
 * 增加店面，修改店面，修改店面
 */
@Controller
@RequestMapping("storeByAdmin")
public class StoreController {

    @Autowired
    private IStoreService iStoreService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private AdminoneMapper adminoneMapper;

    /**
     * 增加店铺信息
     * @param store
     * @return
     */
    @RequestMapping(value = "addstore.do")
    @ResponseBody
    public ServerResponse<Store> AddStore(Store store, String username) {
        if (username == null) {
            return ServerResponse.createByErrorMessage("此用户没有此权限");
        }
        return iStoreService.add_Store(store, username);
    }

    /**
     * 根据管理员id查询店面信息
     * @param username
     * @return
     */
    @RequestMapping(value = "showstorebyadmin.do")
    @ResponseBody
    public ServerResponse<Store> ShowStore_ByAdminId(String username) {
        if (username == null) {
            return ServerResponse.createByErrorMessage("此用户没有此权限");
        }
        return iStoreService.show_Store_byadmin(username);
    }

    /**
     * 根据店面id查询店面信息
     * @param id
     * @return
     */
    @RequestMapping(value = "showstorebyid.do")
    @ResponseBody
    public ServerResponse<Store> ShowStore_Bystoreid(int id) {
        if (id < 1) {
            return ServerResponse.createByErrorMessage("查询错误");
        }
        return iStoreService.show_Store_bystore(id);
    }

    /**
     * 更新店面信息
     *
     * @param store
     * @return
     */
    @RequestMapping(value = "updatestore.do")
    @ResponseBody
    public ServerResponse<String> update_Store(Store store) {
        return iStoreService.update_Store(store);
    }

    /**
     * 查看店铺开闭
     * @param username
     * @return
     */
    @RequestMapping(value = "showopen.do")
    @ResponseBody
    public ServerResponse<String> Show_OpenClose(String username) {
        return iStoreService.show_openstore(username);
    }

    /**
     * 修改店铺开闭
     * @param username
     * @return
     */
    @RequestMapping(value = "update_store_open.do")
    @ResponseBody
    public ServerResponse<String> Update_OpenClose(String username,String sit) {
        if (sit.equals("open")){
            return iStoreService.update_openstore(username);
        }else{
            return iStoreService.update_closestore(username);
        }

    }

    /**
     * 添加座位信息
     * @param store
     * @return
     */
    @RequestMapping(value = "addsit.do")
    @ResponseBody
    public ServerResponse<String> Show_AddSit(Store store) {
        return null;
    }

    /**
     * 更新座位信息
     * @return
     */
    @RequestMapping(value = "updatesit.do")
    @ResponseBody
    public ServerResponse<String> Update_sit(){
        return null;
    }

    /**
     * todo 添加法律信息
     * @return
     */
    @RequestMapping(value = "uploadlicense.do")
    @ResponseBody
    public ServerResponse uploadlicense(@RequestParam(value="upload_file") MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);

        //todo url添加到数据库中 还需要提供管理员的信息，来添加到数据库中
        int storeid = adminoneMapper.getstoreidbyusername("yuyu");
        storeMapper.updatelicesepicture(storeid,url);
        return ServerResponse.createBySuccess(fileMap);
    }
    /**
     * 更新法律信息
     * @return
     */
    @RequestMapping(value = "updatelicense.do")
    @ResponseBody
    public ServerResponse<String> updatelicense(){
        return null;
    }
}
