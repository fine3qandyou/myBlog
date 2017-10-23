package com.myblog.test;


import com.myblog.entity.Blog;
import com.myblog.entity.BlogType;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
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
public class CommentTest {

    @Resource
    private CommentService commentService;

    @Test
    public void getTotal() {
        Map<String,Object> map=new HashMap<String, Object>();
        System.out.print(commentService.getTotal(map));
    }

}
