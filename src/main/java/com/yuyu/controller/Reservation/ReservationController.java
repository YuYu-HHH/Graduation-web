package com.yuyu.controller.Reservation;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ReservationTwo;
import com.yuyu.common.ServerResponse;
import com.yuyu.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    @RequestMapping("addreservation.do")
    @ResponseBody
    public ServerResponse<ReservationTwo> addreservation(String storeid, String customerid) {
        return iReservationService.addReservation(storeid, customerid);
    }
    @RequestMapping("showreservation.do")
    @ResponseBody
    public ServerResponse<PageInfo> showreservation(String username, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iReservationService.showReservation(username, pageNum, pageSize);
    }
    @RequestMapping("deletereservation.do")
    @ResponseBody
    public ServerResponse<String> deletereservation(String storeid, String number) {
        return iReservationService.deleteReservation(storeid, number);
    }
    @RequestMapping("showreservationbyuser.do")
    @ResponseBody
    public ServerResponse<String> showreservationbyuser(String customer ){
        return iReservationService.showreservationbyuser(customer);
    }
}
