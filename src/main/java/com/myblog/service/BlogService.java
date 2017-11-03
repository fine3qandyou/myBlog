package com.myblog.service;

import com.myblog.entity.Blog;
import com.myblog.entity.PageBean;
import java.util.List;
import java.util.Map;

public interface BlogService {
    // 分页查询博客
    public List<Blog> listBlog(Map<String, Object> map);

    // 获取总记录数
    public Long getTotal(Map<String ,Object> map);

    // 根据博客类型的id查询该类型下的博客数量
    public Integer getBlogByTypeId(Integer typeId);

    //添加博客
    public Integer saveBlog(Blog blog);

    //更新博客
    public Integer updateBlog(Blog blog);

    //删除博客
    public Integer deleteBlog(Integer id);

    //通过id获取博客
    public Blog getById(Integer id);

    // 分页查询博客
    public PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean);

    public Blog getPrevBlog(Integer id);

    public Blog getNextBlog(Integer id);

    public List<Blog> showAll();
}
