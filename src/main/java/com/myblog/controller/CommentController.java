package com.myblog.controller;

import com.myblog.entity.Comment;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
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

//    public String save(Comment comment, @RequestParam(value = "imageCode")String imagecode,//验证码
//                       HttpServletRequest request, HttpServletResponse response, Session session) throws Exception{
//
//    }
}
