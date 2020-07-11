package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ReservationTwo;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.ReservationMapper;
import com.yuyu.pojo.Adminone;
import com.yuyu.pojo.Menu;
import com.yuyu.pojo.Reservation;
import com.yuyu.service.IReservationService;
import com.yuyu.util.DataTimeUtil;
import com.yuyu.util.PropertiesUtil;
import com.yuyu.vo.MenuListvo;
import com.yuyu.vo.menuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iReservationService")
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private AdminoneMapper adminoneMapper;

    /**
     * 添加预定信息，先根据storeid获取最大的number，将number+1之后，加入到数据库中
     *先判断用户是否已经预约过，预约过则返回已经“预约过”的信息，并且返回现在已经预约过的信息
     * @param storeid
     * @param customerid
     * @return
     */
    @Override
    public ServerResponse<ReservationTwo> addReservation(String storeid, String customerid) {
        int ConfirmCount = reservationMapper.confirmcustomer(Integer.valueOf(customerid));
        if (ConfirmCount > 0){
            ReservationTwo reservationTwo1 = new ReservationTwo();
            int RNUMBER = reservationMapper.getrnumber(Integer.valueOf(customerid));
            String waitnumber = String.valueOf(reservationMapper.countBystoreid(Integer.valueOf(storeid)));
            reservationTwo1.setRnumber(RNUMBER);
            reservationTwo1.setWaitnumber(waitnumber);
            return ServerResponse.createBySuccess("您已经预约过了",reservationTwo1);
        }
        //获取店面的所有预约的数量
        int ResultNumber = reservationMapper.getcountBystoreid(Integer.valueOf(storeid));
        //List<Reservation> reservations = reservationMapper.getidBycount(Integer.valueOf(storeid));
        //int ResultNumber = reservationMapper.getnumberBystoreid(ResultCOUNT);
        ResultNumber = ResultNumber+1;
        Reservation reservation = new Reservation();
        reservation.setRnumber(ResultNumber);
        reservation.setStoreid(Integer.valueOf(storeid));
        reservation.setCustomerid(Integer.valueOf(customerid));
        int ResultCount = reservationMapper.insert(reservation);
        ReservationTwo reservationTwo = new ReservationTwo();
        reservationTwo.setRnumber(ResultNumber);
        reservationTwo.setWaitnumber(String.valueOf(reservationMapper.countBystoreid(Integer.valueOf(storeid))));
        if (ResultCount > 0){
            return ServerResponse.createBySuccess("预定成功",reservationTwo);
        }
        else
        {
            return ServerResponse.createByErrorMessage("预约失败");
        }
    }

    @Override
    public ServerResponse<PageInfo> showReservation(String username,int pageNum, int pageSize) {
        int ResultStoreId = adminoneMapper.getstoreidbyusername(username);
        PageHelper.startPage(pageNum, pageSize);
        List<Reservation> Reservations = reservationMapper.reservationBystoreid(ResultStoreId);
        List<Reservation> ReservationList = Lists.newArrayList();
        for (Reservation ReservationItem : Reservations) {
            Reservation ReservationListvo = assembleMReservationVo(ReservationItem);
            ReservationList.add(ReservationListvo);
        }
        PageInfo pageResult = new PageInfo(Reservations);
        pageResult.setList(ReservationList);
        return ServerResponse.createBySuccess(pageResult);

    }

    private Reservation assembleMReservationVo(Reservation reservation) {
        Reservation reservation1 = new Reservation();
        reservation1.setId(reservation.getId());
        reservation1.setRnumber(reservation.getRnumber());
        reservation1.setStoreid(reservation.getStoreid());
        reservation1.setCustomerid(reservation.getCustomerid());

        return reservation1;
    }
    @Override
    public ServerResponse<String> deleteReservation(String storeid, String number) {
        int CountId = reservationMapper.getIdBystoreid(Integer.valueOf(storeid),Integer.valueOf(number));
        int Count = reservationMapper.deleteByPrimaryKey(CountId);
        if (Count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> showreservationbyuser(String customerid) {
        int rnumber = reservationMapper.getrnumber(Integer.valueOf(customerid));
        return null;
    }
}
