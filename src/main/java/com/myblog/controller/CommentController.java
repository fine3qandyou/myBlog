package com.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blog;
import com.myblog.entity.Comment;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.util.ResponseUtil;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    public void save(Comment comment, @RequestParam(value = "imageCode")String imageCode,//验证码
                       HttpServletRequest request,//获取评论者ip
                       HttpServletResponse response, //响应
                       Session session) throws Exception{

        //获取session中正确的验证码，验证码产生后会存到session中的
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0; //执行记录数
        if(!imageCode.equals(sRand)){
            result.put("success",false);
            result.put("errorInfo","验证码有误");
        }else{
            //获取评论者ip
            String userId = request.getRemoteAddr();
            comment.setUserId(userId);
            if(comment.getId() == null){
                resultTotal = commentService.saveComment(comment); //添加评论
                Blog blog = blogService.getById(comment.getBlog().getId()); //更新一下博客的评论次数
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.updateBlog(blog);
            }else{
                result.put("success",false);
                result.put("errorInfo","验证码有误");
            }
        }
        if(resultTotal > 0) {
            result.put("success", true);
        }
        ResponseUtil.write(response, result);
    }
}
