package com.yuyu.service;

import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Customer;

public interface ICustomerService {

    ServerResponse<Customer> addCustomer(Customer customer);
    ServerResponse<Customer> loginCustomer(String phone, String password);
    ServerResponse<String> deleteCustomer(Customer customer);
    ServerResponse<Customer> checkCustomer(String phone);
    ServerResponse<String> updatePassword(String phone,String password,String newPassword);

}
