package com.myblog.dao;

import com.myblog.entity.Blogger;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerDao {

    /*查询博主信息*/
    Blogger getBloggerData();

}
