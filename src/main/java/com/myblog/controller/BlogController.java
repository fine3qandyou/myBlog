package com.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blog;
import com.myblog.entity.BlogType;
import com.myblog.entity.Comment;
import com.myblog.lucence.BlogIndex;
import com.myblog.service.BlogService;
import com.myblog.service.BlogTypeService;
import com.myblog.service.CommentService;
import com.myblog.util.DateUtil;
import com.myblog.util.PageUtil;
import com.myblog.util.ResponseUtil;
import com.myblog.util.StringUtil;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @Resource
    private BlogIndex blogIndex;

    @Resource
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "/articles/{id}")
    public ModelAndView details(@PathVariable(value = "id")String id, HttpServletRequest request){

        int blogId = Integer.parseInt(id);
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogService.getById(blogId);

        //获取关键字
        String keyWords = blog.getKeyWord();
        if(StringUtil.isNotEmpty(keyWords)){
            String[] keyWordsArray = keyWords.split(" ");
            List<String> keyWordsList = StringUtil.blankFilter(Arrays.asList(keyWordsArray));//去掉空格
            modelAndView.addObject("keyWord",keyWordsList);
        }else {
            modelAndView.addObject("keyWord",null);
        }

        //添加博客
        modelAndView.addObject("blog",blog);

        //访问量+1
        blog.setClickHit(blog.getClickHit()+1);
        blogService.updateBlog(blog);
        //获取博客类型信息
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        // 查询评论信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blogId", blog.getId());
        List<Comment> commentList = commentService.getCommentData(map);
        //把楼层对应评论翻转一下
        Collections.reverse(commentList);
        //加到响应里去
        modelAndView.addObject("commentList",commentList);
        modelAndView.addObject("blogTypeList", blogTypeList);
        modelAndView.addObject("commonPage", "foreground/blog/blogDetail.jsp");
        modelAndView.addObject("title", blog.getTitle() + " - 邱天的博客");

        // 存入上一篇和下一篇的显示代码
        modelAndView.addObject("pageCode", PageUtil.getPrevAndNextPageCode(
                blogService.getPrevBlog(blogId), blogService.getNextBlog(blogId),
                request.getServletContext().getContextPath()));

        modelAndView.setViewName("mainTemp");

        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "data" , required = false)String data,
                               @RequestParam(value = "page" , required = false)String page,
                               HttpServletRequest request) throws Exception{
        int pageSize = 10;
        ModelAndView modelAndView = new ModelAndView();
        List<Blog> blogIndexList = blogIndex.searchBlog(data);
        if(page == null) { //page为空表示第一次搜索
            page = "1";
        }
        if(blogIndexList!=null){
            int fromIndex = (Integer.parseInt(page) - 1) * pageSize; // 开始索引
            int toIndex = blogIndexList.size() >= Integer.parseInt(page) * pageSize ? Integer
                    .parseInt(page) * pageSize
                    : blogIndexList.size();

            List<Blog> tempList =  blogIndexList.subList(fromIndex, toIndex);
            List<Blog> returnList = new ArrayList<>();
            for(int i = 0 ; i< tempList.size() ;i++){
                Blog returnBlog = tempList.get(i);
                Blog blog = blogService.getById(returnBlog.getId());
                returnBlog.setReleaseDate(blog.getReleaseDate());
                returnBlog.setSummary(blog.getSummary());
                returnBlog.setKeyWord(blog.getKeyWord());
                returnList.add(returnBlog);
            }

            modelAndView.addObject("blogIndexList",returnList );
            modelAndView.addObject("pageCode", PageUtil.getUpAndDownPageCode(
                    Integer.parseInt(page), blogIndexList.size(), data, pageSize,
                    request.getServletContext().getContextPath()));
            modelAndView.addObject("resultTotal", blogIndexList.size()); // 查询到的总记录数
        }else{
            modelAndView.addObject("blogIndexList", blogIndexList);
            modelAndView.addObject("resultTotal", 0); // 查询到的总记录数
        }
        //获取博客类型信息
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        modelAndView.addObject("blogTypeList", blogTypeList);
        modelAndView.addObject("data", data); // 用于数据的回显
        modelAndView.addObject("commonPage", "foreground/blog/searchResult.jsp");
        modelAndView.addObject("title", "搜索'" + data + "'的结果 - 邱天的博客");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }

//    @RequestMapping(value = "/show")
//    public void show(HttpServletRequest request){
//        request.setAttribute("list",blogService.showAll());
//    }
}
