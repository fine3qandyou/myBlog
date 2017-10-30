package com.myblog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.myblog.entity.Blog;
import com.myblog.entity.PageBean;
import com.myblog.lucence.BlogIndex;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin/blog" )
public class BlogAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private CommentService commentService;
    @Resource
    private BlogIndex blogIndex;

    @RequestMapping(value = "/listBlog")
    public void listBlog(@RequestParam(value = "page", required = false) String page,
                         @RequestParam(value = "rows", required = false) String rows,
                         Blog blog, HttpServletResponse response){

        PageBean<Blog> pageBean=new PageBean<Blog>(Integer.parseInt(page),Integer.parseInt(rows));
        pageBean=blogService.listBlog(blog.getTitle(),pageBean);

        //创建json对象
        JSONObject result=new JSONObject();
        //设置JSON序列化日期格式
        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        //禁止对象循环引用
        //使用默认日期格式化
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        JSONArray array = JSON.parseArray(jsonStr);
        //把结果放入json
        result.put("rows", array);
        result.put("total", pageBean.getTotal());
        //返回
        ResponseUtil.write(response, result);
    }

    @RequestMapping(value = "/saveBlog")
    public void saveBlog(Blog blog,HttpServletResponse response)throws Exception{
        int saveOrUpdate=0;
        if(blog.getId()==null){
            saveOrUpdate=blogService.saveBlog(blog);
            blogIndex.addIndex(blog);
        }else{
            saveOrUpdate=blogService.updateBlog(blog);
            blogIndex.updateIndex(blog);
        }
        JSONObject result=new JSONObject();
        if(saveOrUpdate>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "/deleteBlog")
    public void deleteBlog(@RequestParam(value = "ids")String ids,HttpServletResponse response)throws Exception{
        String[] target=ids.split(",");
        for(int i=0;i<target.length;i++){
            commentService.deleteCommentByBlogId(Integer.parseInt(target[i]));
            blogService.deleteBlog(Integer.parseInt(target[i]));
            blogIndex.deleteIndex(target[i]);
        }
        JSONObject result=new JSONObject();
        result.put("success",true);
        ResponseUtil.write(response,result);
    }

    @RequestMapping(value = "/getById")
    public void getById(@RequestParam(value = "id")String id,HttpServletResponse response){
        int getId=Integer.parseInt(id);
        Blog blog=blogService.getById(getId);
        String jsonStr = JSONObject.toJSONString(blog);
        JSONObject result=JSONObject.parseObject(jsonStr);
        result.put("success",true);
        ResponseUtil.write(response,result);
    }

}
