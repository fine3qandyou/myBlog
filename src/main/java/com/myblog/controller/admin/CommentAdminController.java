package com.myblog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.myblog.entity.Comment;
import com.myblog.entity.PageBean;
import com.myblog.service.CommentService;
import com.myblog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    @RequestMapping(value = "/list")
    public void listByPage(@RequestParam(value = "page" ,required = false) String page,
                           @RequestParam(value = "rows" ,required = false) String rows,
                           @RequestParam(value = "verifyState" ,required = false) String verifyState,
                           HttpServletResponse response){
        //pagebean属性填充
        PageBean<Comment> pageBean = new PageBean<Comment>(Integer.parseInt(page),Integer.parseInt(rows));
        pageBean.getMap().put("verifyState",verifyState);
        pageBean = commentService.listByPage(pageBean);

        JSONObject result=new JSONObject();
        //设置JSON序列化日期格式
        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        //禁止对象循环引用
        //使用默认日期格式化
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        JSONArray array = JSON.parseArray(jsonStr);

        result.put("total",pageBean.getTotal());
        result.put("rows",array);
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "/delete")
    public void deleteComment(@RequestParam(value = "ids",required = false)String ids,HttpServletResponse response){
        String[] id = ids.split(",");
        for(int i=0;i<id.length;i++){
            commentService.deleteComment(Integer.parseInt(id[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "/review")
    public void reviewComment(@RequestParam(value = "ids",required = false)String ids,
                              @RequestParam(value = "verifyState" , required = false)String verifyState,
                              HttpServletResponse response){
        String[] id = ids.split(",");
        for(int i=0;i<id.length;i++){
            Comment comment = new Comment();
            comment.setId(Integer.parseInt(id[i]));
            comment.setVerifyState(Integer.parseInt(verifyState));
            commentService.updateComment(comment);
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
    }

}
