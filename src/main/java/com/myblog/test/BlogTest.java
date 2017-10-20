package com.myblog.test;


import com.myblog.entity.Blog;
import com.myblog.entity.BlogType;
import com.myblog.service.BlogService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class BlogTest {
    @Resource
    private BlogService blogService;

    @Test
    public void saveBlog(){
        Blog blog=new Blog();
        blog.setId(null);
        blog.setTitle("das");
        System.out.println(blogService.saveBlog(blog));
        Blog blog2=new Blog();
        blog.setId(null);
        blog.setTitle("aaa");
        blogService.saveBlog(blog);
    }

    @Test
    public void listBlog() throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("title","das");
        List<Blog> list=blogService.listBlog(map);
        System.out.println(list.toArray());
    }

    @Test
    public void updateBlog() throws Exception{
        Blog blog=new Blog();
        blog.setId(1);
        blog.setTitle("aaaaa");
        BlogType blogType=new BlogType(7,"1",321);
        blog.setBlogType(blogType);
        System.out.println(blogService.updateBlog(blog));
    }

    @Test
    public void deleteBlog() throws Exception{
        System.out.println(blogService.deleteBlog(1));
    }

}
