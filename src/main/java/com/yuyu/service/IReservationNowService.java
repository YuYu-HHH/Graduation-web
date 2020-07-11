package com.yuyu.service;

import com.yuyu.common.ReservationNowTwo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.ReservationNow;

public interface IReservationNowService {
    ServerResponse<ReservationNow> addReservationNow(String number,String storeid);
    ServerResponse<ReservationNowTwo> showReservationNow(String storeid);
    ServerResponse<ReservationNow> deleteReservationNow(String storeid);
}
