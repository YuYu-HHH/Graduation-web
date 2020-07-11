package com.yuyu.service;

import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Store;

public interface IStoreService {
    ServerResponse<Store> add_Store(Store store, String username);

    ServerResponse<Store> show_Store_byadmin(String usernmae);

    ServerResponse<Store> show_Store_bystore(int storeid);

    ServerResponse<String> update_Store(Store store);

    ServerResponse<String> show_openstore(String username);

    ServerResponse<String> update_openstore(String username);

    ServerResponse<String> update_closestore(String username);


}
