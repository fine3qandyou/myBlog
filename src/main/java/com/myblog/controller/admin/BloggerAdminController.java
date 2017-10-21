package com.myblog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import com.myblog.util.ResponseUtil;
import com.sun.javafx.scene.shape.PathUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping(value = "getBloggerData")
    public void getBloggerData(HttpServletResponse response) throws Exception{
        Blogger blogger = bloggerService.getBloggerData();
        String str = JSONObject.toJSONString(blogger);
        JSONObject result = JSONObject.parseObject(str);
        ResponseUtil.write(response,str);
    }

    @RequestMapping(value = "save")
    public void getBloggerByName(@RequestParam(value = "imageFile" , required = false)MultipartFile imageFile,
                                 HttpServletResponse response , Blogger blogger) throws Exception{
        if(!imageFile.isEmpty()){

        }
    }
}
