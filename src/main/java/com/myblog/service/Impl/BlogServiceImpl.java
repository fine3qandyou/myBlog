package com.myblog.service.Impl;


import com.myblog.dao.BlogDao;
import com.myblog.entity.Blog;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{

    @Resource
    private BlogDao blogDao;

    // 分页查询博客
    public List<Blog> listBlog(Map<String, Object> map){
        return blogDao.listBlog(map);
    }

    // 获取总记录数
    public Long getTotal(Map<String ,Object> map){
        return blogDao.getTotal(map);
    }

    // 根据博客类型的id查询该类型下的博客数量
    public Integer getBlogByTypeId(Integer typeId){
        return blogDao.getBlogByTypeId(typeId);
    }

    //添加博客
    public Integer saveBlog(Blog blog){
        return blogDao.saveBlog(blog);
    }

    //更新博客
    public Integer updateBlog(Blog blog){
        return blogDao.updateBlog(blog);
    }

    //删除博客
    public Integer deleteBlog(Integer id){
        return blogDao.deleteBlog(id);
    }

    //通过id获取博客
    public Blog getById(Integer id){
        return blogDao.getById(id);
    }

    // 分页查询博客
    public PageBean<Blog> listBlog(String title, PageBean<Blog> pageBean){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("title",title);
        pageBean.setTotal(blogDao.getTotal(map));
        map.put("start",pageBean.getStart());
        map.put("end",pageBean.getEnd());
        pageBean.setResult(listBlog(map));
        return pageBean;
    }

}
