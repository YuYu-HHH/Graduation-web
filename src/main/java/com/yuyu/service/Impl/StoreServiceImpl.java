package com.yuyu.service.Impl;

import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.StoreMapper;
import com.yuyu.pojo.Store;
import com.yuyu.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iStoreService")
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private AdminoneMapper adminoneMapper;

    @Autowired
    private StoreMapper storeMapper;


    /**
     * 增加
     *
     * @param store
     * @param username
     * @return
     */
    @Override
    public ServerResponse<Store> add_Store(Store store, String username) {
        int resultCount = adminoneMapper.checkUsername(username);
        if (resultCount == 0) {
            ServerResponse.createByErrorMessage("此用户没有此项权利");
        }
        resultCount = storeMapper.insert(store);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("出现错误，请查看后再试一次");
        }
        return ServerResponse.createBySuccess("添加成功", store);
    }

    /**
     * 查看信息，通过管理员id
     *
     * @param username
     * @return
     */
    @Override
    public ServerResponse<Store> show_Store_byadmin(String username) {
        int resultCount = adminoneMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("查看失败");
        }
        int adminid = adminoneMapper.getAdminoneIdByusernmae(username);
        Store store = storeMapper.selectByAdminId(adminid);
        return ServerResponse.createBySuccess("查询成功", store);
    }

    /**
     * 查看信息，通过店面id
     *
     * @param storeid
     * @return
     */
    @Override
    public ServerResponse<Store> show_Store_bystore(int storeid) {
        return ServerResponse.createBySuccess("查询成功", storeMapper.selectByPrimaryKey(storeid));
    }

    /**
     * 修改信息
     *
     * @param store
     * @return
     */
    @Override
    public ServerResponse<String> update_Store(Store store) {
        Store updatestore = new Store();

        storeMapper.updateByPrimaryKeySelective(store);
        return ServerResponse.createBySuccess("更新信息成功");
    }

    /**
     * 通过username->storeid查看商店的开闭情况
     * @param username
     * @return
     */
    @Override
    public ServerResponse<String> show_openstore(String username) {
        int newstoreid =adminoneMapper.getstoreidbyusername(username);
        if ((storeMapper.checkopen(newstoreid)).equals("1"))
        {
            return ServerResponse.createBySuccessMessage("1");
        }
        else{
            return ServerResponse.createBySuccessMessage("0");
        }
    }

    @Override
    public ServerResponse<String> update_openstore(String username) {
        int newstoreid =adminoneMapper.getstoreidbyusername(username);
        if (storeMapper.updateopen(newstoreid) > 0){
            return ServerResponse.createBySuccessMessage("success");
        }
        return ServerResponse.createBySuccess("fail");
    }

    @Override
    public ServerResponse<String> update_closestore(String username) {
        int newstoreid =adminoneMapper.getstoreidbyusername(username);
        if (storeMapper.updateclose(newstoreid) > 0){
            return ServerResponse.createBySuccessMessage("success");
        }
        return ServerResponse.createBySuccess("fail");
    }
}
