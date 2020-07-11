package com.yuyu.service.Impl;

import com.yuyu.common.ReservationNowTwo;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.ReservationMapper;
import com.yuyu.dao.ReservationNowMapper;
import com.yuyu.pojo.ReservationNow;
import com.yuyu.service.IReservationNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iReservationNowService")
public class ReservationNowServiceImpl implements IReservationNowService {

    @Autowired
    private ReservationNowMapper reservationNowMapper;

    @Autowired
    private ReservationMapper reservationMapper;
    /**
     * 先删除storeid下的那一列，然后再田间
     * @param number
     * @param storeid
     * @return
     */
    @Override
    public ServerResponse<ReservationNow> addReservationNow(String number, String storeid) {
        ReservationNow reservationNow = new ReservationNow();
        reservationNow.setNumberNow(Integer.valueOf(number));
        reservationNow.setStoreid(Integer.valueOf(storeid));
        int Count = reservationNowMapper.insert(reservationNow);
        if (Count > 0){
            return ServerResponse.createBySuccess("添加成功",reservationNow);
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    /**
     * 显示现在的排队信息,显示排队人数，最新的人，自己的编号
     * @param storeid
     * @return
     */
    public ServerResponse<ReservationNowTwo> showReservationNow(String storeid){
        int Countid = reservationNowMapper.getidBystoreid(Integer.valueOf(storeid));
        ReservationNow reservationNow = reservationNowMapper.selectByPrimaryKey(Countid);
        ReservationNowTwo reservationNowTwo = new ReservationNowTwo();
        reservationNowTwo.setNumber(reservationNow.getNumberNow());
        reservationNowTwo.setWaitnumber(reservationMapper.countBystoreid(Integer.valueOf(storeid)));
        if (Countid != 0){
            return ServerResponse.createBySuccess("成功",reservationNowTwo);
        }
        return ServerResponse.createByErrorMessage("错误");
    }

    /**
     * 直接删除
     * @param storeid
     * @return
     */
    @Override
    public ServerResponse<ReservationNow> deleteReservationNow(String storeid) {
        return null;
    }
}
