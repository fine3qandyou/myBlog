package com.myblog.service.Impl;

import com.myblog.dao.BloggerDao;
import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerDao bloggerDao;

    public Blogger getBloggerData(){return bloggerDao.getBloggerData();}

    /**
     * 通过用户名查询博主信息
     * @param username
     * @return
     */
    public Blogger getBloggerByName(String username){return bloggerDao.getBloggerByName(username);}

    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    public Integer updateBlogger(Blogger blogger){return bloggerDao.updateBlogger(blogger);}
}
