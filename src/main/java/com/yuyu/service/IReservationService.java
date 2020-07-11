package com.yuyu.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ReservationTwo;
import com.yuyu.common.ServerResponse;

public interface IReservationService {
    ServerResponse<ReservationTwo> addReservation(String storeid, String customerid);
    ServerResponse<PageInfo> showReservation(String username,int pageNum, int pageSize);
    ServerResponse<String> deleteReservation(String storeid, String customerid);
    ServerResponse<String> showreservationbyuser(String customer);
}
