package com.myblog.dao;

import com.myblog.entity.Blogger;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerDao {

    /*查询博主信息*/
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

    Blogger getBloggerById(Integer id);

}
