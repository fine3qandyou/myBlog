package com.myblog.service;


import com.myblog.entity.Blogger;

public interface BloggerService {

    /**
     * 查询博主信息
     * @return
     */
    Blogger getBloggerData();

    /**
     * 通过用户名查询博主信息
     * @param username
     * @return
     */
    Blogger getBloggerByName(String username);

    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    Integer updateBlogger(Blogger blogger);
}
