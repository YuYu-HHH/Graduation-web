package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.AdminoneMapper;
import com.yuyu.dao.OrderItemMapper;
import com.yuyu.dao.OrderMapper;
import com.yuyu.pojo.Order;
import com.yuyu.pojo.OrderItem;
import com.yuyu.service.IOrderService;
import com.yuyu.util.DataTimeUtil;
import com.yuyu.util.DateTimeUtil;
import com.yuyu.vo.OrderItemOrderVo;
import com.yuyu.vo.OrderVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private AdminoneMapper adminoneMapper;

    @Override
    public ServerResponse<String> addorder(OrderItemOrderVo orderItemOrderVo) {
        Order order = new Order();
        int size = orderItemMapper.getsize();
        List<OrderItem> orderItems = orderItemMapper.getItem();
        OrderItem orderItem1 = orderItems.get(size-1);
        //int ItemId = orderItemMapper.getitemid(size-1);
        int ItemId = orderItem1.getOrderitemId();
        order.setPaymentTime(DateTimeUtil.strToDate(orderItemOrderVo.getPaymentTime()));
        order.setPayment(orderItemOrderVo.getPayment());
        order.setAdminid(orderItemOrderVo.getAdminid());
        order.setOrderNo(orderItemOrderVo.getOrderNo());
        order.setUserId(orderItemOrderVo.getUserId());
        order.setSituation(orderItemOrderVo.getSituation());
        order.setItemId(ItemId+1);
        order.setEvaluationId(orderItemOrderVo.getEvaluationId());

        String OrderItem_Json = orderItemOrderVo.getAllMenuJson();
        System.out.println(OrderItem_Json);
        OrderItem_Json = OrderItem_Json.replace("!" ,"[");
        OrderItem_Json = OrderItem_Json.replace("*","]");
        OrderItem_Json = OrderItem_Json.replace(")","}");
        OrderItem_Json = OrderItem_Json.replace("(","{");
        System.out.println(OrderItem_Json);
        JSONObject jsonObject1 = new JSONObject(OrderItem_Json);
        JSONArray jsonArray = jsonObject1.getJSONArray("data");
        System.out.println("data为    "+jsonArray.toString());
        for (int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            OrderItem orderItem= new OrderItem();
            String foodname = jsonObject.getString("foodname");
            String price = jsonObject.getString("price");
            String number = jsonObject.getString("number");
            orderItem.setFoodname(foodname);
            orderItem.setPrice(price);
            orderItem.setNumber(Integer.valueOf(number));
            orderItem.setOrderitemId(ItemId+1);
            System.out.println(orderItem);
            orderItemMapper.insert(orderItem);
        }
        int ResultCount = orderMapper.insert(order);
        //int ResultCount = 1;
        if (ResultCount > 0 ){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse<PageInfo> checkorder(int user_id,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectall(user_id);
        List<OrderVo> orderList = Lists.newArrayList();
        for (Order orderItem : orders) {
            OrderVo order = assembleOrderDtailVo(orderItem);
            orderList.add(order);
        }
        PageInfo pageResult = new PageInfo(orders);
        pageResult.setList(orderList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> checkOrderByadmin(String username, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        int store_id = adminoneMapper.getstoreidbyusername(username);
        List<Order> orders = orderMapper.selectalBystore(store_id);
        List<OrderVo> orderList = Lists.newArrayList();
        for (Order orderItem : orders) {
            OrderVo order = assembleOrderDtailVo(orderItem);
            orderList.add(order);
        }
        PageInfo pageResult = new PageInfo(orders);
        pageResult.setList(orderList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> checkDetailOrder(int itemId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderItem> orderitems = orderItemMapper.getItemList(itemId);
        List<OrderItem> orderList = Lists.newArrayList();
        for (OrderItem orderItem : orderitems) {
            OrderItem orderitem = assembleOrderItemDtailVo(orderItem);
            orderList.add(orderitem);
        }
        PageInfo pageResult = new PageInfo(orderitems);
        pageResult.setList(orderList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private OrderVo assembleOrderDtailVo(Order order) {
        OrderVo ordernew = new OrderVo();

        ordernew.setId(order.getId());
        ordernew.setAdminid(order.getAdminid());
        ordernew.setItemId(order.getItemId());
        ordernew.setUserId(order.getUserId());
        ordernew.setOrderNo(order.getOrderNo());
        ordernew.setPayment(order.getPayment());
        ordernew.setPaymentTime(DataTimeUtil.dateToStr(order.getPaymentTime()));
        ordernew.setEvaluationId(order.getEvaluationId());
        ordernew.setSituation(order.getSituation());
        return ordernew;
    }

    private OrderItem assembleOrderItemDtailVo(OrderItem orderitem) {
        OrderItem ordernew = new OrderItem();

        ordernew.setId(orderitem.getId());
        ordernew.setOrderitemId(orderitem.getOrderitemId());
        ordernew.setFoodname(orderitem.getFoodname());
        ordernew.setNumber(orderitem.getNumber());
        ordernew.setPrice(orderitem.getPrice());

        return ordernew;
    }
}
