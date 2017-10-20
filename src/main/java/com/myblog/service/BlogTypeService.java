package com.myblog.service;

import com.myblog.entity.BlogType;
import com.myblog.entity.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogTypeService {

    //分页
    PageBean<BlogType> listByPage(PageBean<BlogType> pageBean);

    // 添加博客类别
    public Integer addBlogType(BlogType blogType);

    // 更新博客类别
    public Integer updateBlogType(BlogType blogType);

    // 删除博客类别
    public Integer deleteBlogType(Integer id);

    // 获取总记录数
    public Long getTotal();

    public List<BlogType> getBlogTypeData();

}
