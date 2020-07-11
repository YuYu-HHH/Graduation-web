package com.yuyu.controller.Order;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.service.IOrderService;
import com.yuyu.vo.OrderItemOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;
    /**
     * 添加订单
     * @return
     */
    @RequestMapping("addorder.do")
    @ResponseBody
    public ServerResponse<String> addOrder(OrderItemOrderVo ordervo){
        return iOrderService.addorder(ordervo);
    }


    /**
     * 查看订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("checkorder.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkOrder(@RequestParam("user_id") int user_id,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iOrderService.checkorder(user_id,pageNum,pageSize);
    }

    /**
     * 查看通过管理员查看订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("checkorderBYAdmin.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkorderBYAdmin(@RequestParam("username") String username,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iOrderService.checkOrderByadmin(username,pageNum,pageSize);
    }
    /**
     * 查看订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("checkorderbyadmin.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkOrderByadmin(@RequestParam("username") String username,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iOrderService.checkOrderByadmin(username,pageNum,pageSize);
    }

    /**
     * 查看详细订单
     * @param itemId
     * @return
     */
    @RequestMapping("checkdetailorder.do")
    @ResponseBody
    public ServerResponse<PageInfo> checkDetailOrder(@RequestParam(value = "itemId") int itemId,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "20" )int pageSize){
        return iOrderService.checkDetailOrder(itemId,pageNum,pageSize);
    }
}
