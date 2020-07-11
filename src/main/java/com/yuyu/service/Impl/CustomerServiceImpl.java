package com.yuyu.service.Impl;

import com.yuyu.common.ServerResponse;
import com.yuyu.dao.CustomerMapper;
import com.yuyu.pojo.Customer;
import com.yuyu.service.ICustomerService;
import com.yuyu.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iCustomerService")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ServerResponse<Customer> addCustomer(Customer customer) {
        String phoneNumber = customer.getPhone();
        int ResultCount = customerMapper.checkPhone(phoneNumber);
        if (ResultCount > 0){
            return ServerResponse.createByErrorMessage("手机号已经存在，请更换一个手机号码再重试");
        }
        String newPassword = MD5Util.MD5EncodeUtf8(customer.getPassword());
        customer.setPassword(newPassword);
        ResultCount = customerMapper.insert(customer);
        if (ResultCount > 0){
            return ServerResponse.createBySuccess("添加成功",customer);
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse<Customer> loginCustomer(String phone, String password) {
        String MD5password = MD5Util.MD5EncodeUtf8(password);
        int ResultCount = customerMapper.checkUandP(phone, MD5password);
        int ResultId = customerMapper.checkId(phone);
        Customer customer = customerMapper.selectByPrimaryKey(ResultId);
        customer.setPassword(" ");
        if (ResultCount > 0){
            return ServerResponse.createBySuccess("添加成功",customer);
        }
        return ServerResponse.createByErrorMessage("登陆失败，请重试");
    }

    @Override
    public ServerResponse<String> deleteCustomer(Customer customer) {
        int ResultId = customerMapper.checkId(customer.getPhone());
        int ResultCount = customerMapper.deleteByPrimaryKey(ResultId);
        if (ResultCount > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<Customer> checkCustomer(String phone) {
        int ResultId = customerMapper.checkId(phone);
        return ServerResponse.createBySuccess(customerMapper.selectByPrimaryKey(ResultId));
    }

    @Override
    public ServerResponse<String> updatePassword(String phone, String password,String newPassword) {
        int ResultId = customerMapper.checkId(phone);
        if (ResultId==0){
            return ServerResponse.createByErrorMessage("此用户不存在");
        }
        String Resultpassword = customerMapper.check(phone);
        String resultPassword = MD5Util.MD5EncodeUtf8(password);
        System.out.println(Resultpassword);
        System.out.println(resultPassword);
        System.out.println(resultPassword);
        if (resultPassword != Resultpassword){
            return ServerResponse.createByErrorMessage("密码错误");
        }else{
            Customer customer = customerMapper.selectByPrimaryKey(ResultId);
            String MD5newpassword = MD5Util.MD5EncodeUtf8(newPassword);
            customer.setPassword(MD5newpassword);
            int Count = customerMapper.updateByPrimaryKeySelective(customer);
            if (Count > 0){
                return ServerResponse.createBySuccessMessage("更新成功");
            }
            return ServerResponse.createByErrorMessage("更新失败");
        }

    }
}
