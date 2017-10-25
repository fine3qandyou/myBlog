package com.myblog.controller;

import com.myblog.entity.Blog;
import com.myblog.entity.Comment;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @RequestMapping(value = "/articles/{id}")
    public ModelAndView details(@PathVariable(value = "id")String id, HttpServletRequest){

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

        // 查询评论信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blogId", blog.getId());
        map.put("state", 1);
        List<Comment> commentList = commentService.getCommentData(map);
        modelAndView.addObject("commentList",commentList);

        modelAndView.addObject("commonPage", "foreground/blog/blogDetail.jsp");
        modelAndView.addObject("title", blog.getTitle() + " - 熊平的博客");

        // 存入上一篇和下一篇的显示代码
        modelAndView.addObject("pageCode", PageUtil.getPrevAndNextPageCode(
                blogService.getPrevBlog(blogId), blogService.getNextBlog(blogId),
                request.getServletContext().getContextPath()));

        modelAndView.setViewName("mainTemp");

        return modelAndView;
    }
}
