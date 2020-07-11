package com.yuyu.controller.sit;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Sit;
import com.yuyu.service.ISitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sit")
public class SitController {

    @Autowired
    private ISitService iSitService;

    @RequestMapping("addsit.do")
    @ResponseBody
    public ServerResponse<Sit> addSit(Sit sit) {
        return iSitService.addsit(sit);
    }
    @RequestMapping("updatesit.do")
    @ResponseBody
    public ServerResponse<Sit> updatesit(Sit sit) {
        return iSitService.updatesit(sit);
    }
    @RequestMapping("deletesit.do")
    @ResponseBody
    public ServerResponse<Sit> deletesit(Sit sit) {
        return iSitService.deletesit(sit);
    }

    @RequestMapping("sit.do")
    @ResponseBody
    public ServerResponse<PageInfo> GetAll(String username,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iSitService.checkSit(username,pageNum, pageSize);
    }
    @RequestMapping("allsit.do")
    @ResponseBody
    public ServerResponse<PageInfo> GetAllSit(String storeid,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iSitService.getAllSit(storeid,pageNum, pageSize);
    }
}
