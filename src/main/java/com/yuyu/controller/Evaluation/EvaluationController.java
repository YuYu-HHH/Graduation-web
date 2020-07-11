package com.yuyu.controller.Evaluation;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Evaluation;
import com.yuyu.service.IEvaluationService;
import com.yuyu.service.IFileService;
import com.yuyu.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("evaluation/")
public class EvaluationController {

    @Autowired
    private IEvaluationService iEvaluationService;

    @Autowired
    private IFileService iFileService;

    /**
     * 通过订单的id获取评价信息
     * 没有用到此
     * menu_id为order_table 里面的item_id ，即为两者的联系
     * @return
     */
    @RequestMapping("selectallBymenuId.do")
    @ResponseBody
    public ServerResponse<PageInfo> SelectAllByMenuId(int menu_id,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iEvaluationService.SelectAllByMenuId(menu_id,pageNum,pageSize);
    }

    /**
     * 通过店家id获取评价信息，在用户的功能需要
     * @param store_id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("selectallByStoreId.do")
    @ResponseBody
    public ServerResponse<PageInfo> SelectAllByStoreId(int store_id,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iEvaluationService.SelectAllByStoreId(store_id,pageNum,pageSize);
    }

    /**
     * 通过评价id获取评价信息
     * @return
     */
    @RequestMapping("selectAllByEvaluationId.do")
    @ResponseBody
    public ServerResponse<Evaluation> SelectAllByEvaluationId(int evaluationId ) {
        return iEvaluationService.SelectAllByEvaluationId(evaluationId);
    }

    /**
     *
     * @return
     */
    @RequestMapping("addEvaluation.do")
    @ResponseBody
    public ServerResponse<Evaluation> InsertEvaluation(@RequestParam("text") String text,
                                                       @RequestParam("level") String level,
                                                       @RequestParam("userid") String userid,
                                                       @RequestParam("menuid") String menuid,
                                                       @RequestParam(value = "upload_file",required = false) MultipartFile[] file,
                                                       HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        Evaluation evaluation = new Evaluation();
        evaluation.setText(text);
        evaluation.setLevel(level);
        evaluation.setUserId(Integer.valueOf(userid));
        evaluation.setMenuId(Integer.valueOf(menuid));
        evaluation.setStoreId(1);
        for (int i=0;i<file.length;i++) {
            String targetFileName = iFileService.upload(file[i], path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            if (i==0){
                evaluation.setPictureone(url);
            }
            if (i==1){
                evaluation.setPicturetwo(url);
            }
            if (i==2){
                evaluation.setPicturethree(url);
            }
        }
        return iEvaluationService.InsertEvaluation(evaluation);
    }
}
