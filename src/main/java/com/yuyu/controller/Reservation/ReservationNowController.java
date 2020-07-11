package com.yuyu.controller.Reservation;

import com.yuyu.common.ReservationNowTwo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.ReservationNow;
import com.yuyu.service.IReservationNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("reservationnow")
public class ReservationNowController {

    @Autowired
    private IReservationNowService iReservationNowService;

    @RequestMapping("addreservationnew.do")
    @ResponseBody
    public ServerResponse<ReservationNow> addreservationnow(String number,String storeid){
        return iReservationNowService.addReservationNow(number,storeid);
    }

    //todo 显示等待的人数以及最新的人，和自己的排队号码
    @RequestMapping("showReservationNow.do")
    @ResponseBody
    public ServerResponse<ReservationNowTwo> showReservationNow(String storeid){
        return iReservationNowService.showReservationNow(storeid);
    }
}
