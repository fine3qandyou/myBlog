package com.myblog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import com.myblog.util.DateUtil;
import com.myblog.util.MD5Util;
import com.myblog.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "/save")
    public void getBloggerByName(@RequestParam(value = "imageFile" , required = false)MultipartFile imageFile,
                                 HttpServletResponse response , Blogger blogger) throws Exception{
        if(!imageFile.isEmpty()){
            String path="J:/resourcecode/myBlog/src/main/webapp/static/userImages/";
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

    @RequestMapping(value = "/modifyPassword")
    public void modifyPassword(String oldPassword,String newPassword, HttpServletResponse response) throws Exception{
        Blogger blogger = bloggerService.getBloggerById(1);
        String password = MD5Util.md5(oldPassword,"lalala");
        JSONObject result = new JSONObject();
        if(!blogger.getPassword().equals(password)){
            result.put("success", false);
        }else {
            blogger.setPassword(MD5Util.md5(newPassword,"lalala"));
            int resultTotal = bloggerService.updateBlogger(blogger);
            if(resultTotal > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletResponse response) throws Exception{
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        JSONObject result = new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
    }
}
