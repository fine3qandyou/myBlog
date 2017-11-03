package com.myblog.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.myblog.entity.Blog;
import com.myblog.entity.BlogType;
import com.myblog.entity.PageBean;
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
    public void getById(){
        Blog blog=blogService.getById(4);
        String jsonStr = JSONObject.toJSONString(blog);
        JSONObject result = JSONObject.parseObject(jsonStr);
        System.out.print(result.toString());
    }

    @Test
    public void listBlog(){
        JSONObject result = new JSONObject();
        List<Blog> list = blogService.showAll();
        result.put("list",list);
        System.out.print(result.toString());
    }

}
