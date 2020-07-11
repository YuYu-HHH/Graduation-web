package com.yuyu.controller.Find;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Find;
import com.yuyu.service.IFileService;
import com.yuyu.service.IFindService;
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
@RequestMapping("find")
public class FindController {

    @Autowired
    private IFindService iFindService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping("selectallfind.do")
    @ResponseBody
    public ServerResponse<PageInfo> GetAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iFindService.SelectAll(pageNum, pageSize);
    }

    @RequestMapping("selectfindByid.do")
    @ResponseBody
    public ServerResponse<Find> selectfindByid(int id) {
        return iFindService.selectfindByid(id);
    }

    @RequestMapping("addfind.do")
    @ResponseBody
    public ServerResponse<Find> Addfind(@RequestParam("title") String title,
                                        @RequestParam("text") String text,
                                        @RequestParam(value = "upload_file",required = false) MultipartFile[] file,
                                        HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        Find find = new Find();
        find.setText(text);
        find.setTitle(title);
        for (int i=0;i<file.length;i++) {
            String targetFileName = iFileService.upload(file[i], path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            if (i==0){
                find.setPictureone(url);
            }
            if (i==1){
                find.setPicturetwo(url);
            }
            if (i==2){
                find.setPicturethree(url);
            }
        }
        return iFindService.Addfind(find);
    }
}
