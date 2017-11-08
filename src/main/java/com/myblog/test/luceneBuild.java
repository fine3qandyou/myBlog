package com.myblog.test;

import com.myblog.dao.BlogDao;
import com.myblog.entity.Blog;
import com.myblog.lucence.BlogIndex;
import com.myblog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class luceneBuild {

    @Resource
    private BlogIndex blogIndex;
    @Resource
    private BlogService blogService;

    @Test
    public void addIndex(){
        for(int i=4;i<9;i++){
            Blog blog = blogService.getById(i);
            try {
                blogIndex.addIndex(blog);
                System.out.print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void highlightTest(){
        try {
            List<Blog> list = blogIndex.searchBlog("test");
            for (Blog blog : list) {
                System.out.println(blog.getTitle()+"   "+blog.getContent()+"     ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
