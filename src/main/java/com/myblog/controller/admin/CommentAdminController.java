package com.myblog.controller.admin;

import com.myblog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    //TODO:分页
    @RequestMapping(value = "/list")
    public void listByPage(){}

    //TODO:删除评论
    @RequestMapping(value = "/delete")
    public void deleteComment(){}

     //TODO：管理是否可以发出
    @RequestMapping(value = "/review")
    public void reviewComment(){}

}
