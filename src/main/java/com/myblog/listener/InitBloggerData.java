package com.myblog.listener;

import com.myblog.entity.Blog;
import com.myblog.entity.BlogType;
import com.myblog.entity.Blogger;
import com.myblog.service.BlogService;
import com.myblog.service.BlogTypeService;
import com.myblog.service.BloggerService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class InitBloggerData implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void contextInitialized(ServletContextEvent sce) {
        //先获取servlet上下文
        ServletContext application = sce.getServletContext();
        //同上，获取博客类别信息service
        BlogTypeService blogTypeService = applicationContext.getBean(BlogTypeService.class);
        //获取博主信息service
        BloggerService bloggerService = applicationContext.getBean(BloggerService.class);
        //获取博客service
        //BlogService blogService = applicationContext.getBean(BlogService.class);
        //获取博客类型信息
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        application.setAttribute("blogTypeList", blogTypeList);
        //获取博主信息
        Blogger blogger = bloggerService.getBloggerData();
        //隐藏密码
        blogger.setPassword(null);
        application.setAttribute("blogger",blogger);
        //获取博客信息
        //List<Blog> blogList = blogService.listBlog();
        //application.setAttribute("blogList",blogService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        InitBloggerData.applicationContext = applicationContext;
    }

}
