package com.myblog.controller;

import com.myblog.entity.Blog;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


//用于主页跳转
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "typeId", required = false) String typeId,
                              @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                              HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        if (page ==  null || page.length() == 0) {
            page = "1";
        }

        // 获取分页的bean
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10); //每页显示10条数据

        // map中封装起始页和每页的记录
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("end", pageBean.getEnd());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);

        // 获取博客信息
        List<Blog> blogList = blogService.listBlog(map);

        return modelAndView;
    }
}
