package com.yuyu.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.vo.OrderItemOrderVo;

public interface IOrderService {
    //添加订单信息
    ServerResponse<String> addorder(OrderItemOrderVo orderItemOrderVo);
    //查看订单信息
    ServerResponse<PageInfo> checkorder(int user_id,int pageNum, int pageSize);

    ServerResponse<PageInfo> checkOrderByadmin(String username,int pageNum, int pageSize);

    ServerResponse<PageInfo> checkDetailOrder(int itemId,int pageNum, int pageSize);

}
