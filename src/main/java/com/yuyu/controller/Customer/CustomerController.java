package com.yuyu.controller.Customer;

import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Customer;
import com.yuyu.service.ICustomerService;
import com.yuyu.service.IFileService;
import com.yuyu.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("customer/")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IFileService iFileService;

    //todo 新加：注册用户时添加用户头像
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<Customer> addCustomer(@RequestParam("phone") String phone,
                                                @RequestParam("password") String password,
                                                @RequestParam(value = "upload_file",required = false) MultipartFile file,
                                                HttpServletRequest request){
        //上传图片
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        System.out.println(phone);
        Customer customer = new Customer();
        customer.setPhone(phone);
        customer.setPassword(password);
        customer.setLove(url);
        return iCustomerService.addCustomer(customer);
    }

    @RequestMapping("login.do")
    @ResponseBody
    public ServerResponse<Customer> loginCustomer(@RequestParam("phone") String phone,
                                                  @RequestParam("password") String password){
        if (StringUtils.isBlank(phone)) {
            return ServerResponse.createByErrorMessage("电话号码为空，请输入用户名");
        }
        return iCustomerService.loginCustomer(phone,password);
    }
    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse<String> deleteCustomer(Customer customer){
        return iCustomerService.deleteCustomer(customer);
    }
    @RequestMapping("check.do")
    @ResponseBody
    public ServerResponse<Customer> checkCustomer(String phone){
        return iCustomerService.checkCustomer(phone);
    }
    @RequestMapping("updatecustomerpassword.do")
    @ResponseBody
    public ServerResponse<String> updatePassword(String phone,String password,String newPassword){
        return iCustomerService.updatePassword(phone,password,newPassword);
    }
}
