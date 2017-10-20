package com.myblog.service.Impl;

import com.myblog.dao.BlogTypeDao;
import com.myblog.entity.BlogType;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogTypeServiceImpl implements BlogTypeService{
    @Resource
    private BlogTypeDao blogTypeDao;

    public PageBean<BlogType> listByPage(PageBean<BlogType> pageBean){
        //查询分页结果
        pageBean.setResult(blogTypeDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(blogTypeDao.getTotal());
        return pageBean;
    }
    // 添加博客类别
    public Integer addBlogType(BlogType blogType){
        return blogTypeDao.addBlogType(blogType);
    }

    // 更新博客类别
    public Integer updateBlogType(BlogType blogType){
        return blogTypeDao.updateBlogType(blogType);
    }

    // 删除博客类别
    public Integer deleteBlogType(Integer id){
        return blogTypeDao.deleteBlogType(id);
    }

    public Long getTotal(){return blogTypeDao.getTotal();}

    public List<BlogType> getBlogTypeData(){return blogTypeDao.getBlogTypeData();}
}
