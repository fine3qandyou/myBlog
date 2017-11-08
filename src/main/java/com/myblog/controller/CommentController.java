package com.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blog;
import com.myblog.entity.Comment;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    @RequestMapping(value = "/save")
    public void save(@RequestParam(value = "content",required = false)String content,//内容
                     @RequestParam(value = "imageCode",required = false)String imageCode,//验证码
                     @RequestParam(value = "blogId",required = false)String blogId,//对应博客
                     HttpServletRequest request,//获取评论者ip
                     HttpServletResponse response, //响应
                     HttpSession session) throws Exception{

        //获取session中正确的验证码，验证码产生后会存到session中的
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0; //执行记录数
        if(!imageCode.equals(sRand)){
            result.put("success",false);
            result.put("errorInfo","验证码有误");
        }else{
            //获取评论者ip
            String userIp = request.getRemoteAddr();
            //初始化设置comment，只需要三个参数
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUserIp(userIp);
            comment.setBlogId(Integer.parseInt(blogId));
            if(comment.getId() == null){
                resultTotal = commentService.saveComment(comment); //添加评论
                Blog blog = blogService.getById(Integer.parseInt(blogId)); //更新一下博客的评论次数
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.updateBlog(blog);
            }else{
                result.put("success",false);
                result.put("errorInfo","验证码有误！");
            }
        }
        if(resultTotal > 0) {
            result.put("success", true);
        }
        ResponseUtil.write(response, result);
    }
}
