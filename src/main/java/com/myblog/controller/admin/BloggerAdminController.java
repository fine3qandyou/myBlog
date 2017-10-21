package com.myblog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import com.myblog.util.DateUtil;
import com.myblog.util.ResponseUtil;
import com.sun.javafx.scene.shape.PathUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping(value = "admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping(value = "/getBloggerData")
    public void getBloggerData(HttpServletResponse response) throws Exception{
        Blogger blogger = bloggerService.getBloggerData();
        String str = JSONObject.toJSONString(blogger);
        JSONObject result = JSONObject.parseObject(str);
        ResponseUtil.write(response,str);
    }

    @RequestMapping(value = "/save")
    public void getBloggerByName(@RequestParam(value = "imageFile" , required = false)MultipartFile imageFile,
                                 HttpServletResponse response , Blogger blogger) throws Exception{
        if(!imageFile.isEmpty()){
            String path="J:/resourcecode/myBlog/src/main/webapp/static/userImages";
            String imagename= DateUtil.getCurrentData()+"."+imageFile.getOriginalFilename();
            imageFile.transferTo(new File(path+imagename));
            blogger.setImageName(imagename);
        }
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result=new JSONObject();
        if(resultTotal > 0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "modifyPassword")
    public void modifyPassword(Blogger blogger, HttpServletResponse response) throws Exception{
        //TODO 添加shiro之后再做
    }
}
