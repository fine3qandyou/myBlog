package com.myblog.test;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.Blogger;
import com.myblog.service.BlogService;
import com.myblog.util.MD5Util;
import com.myblog.util.ResponseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ShiroTest {
    @Resource
    private BlogService blogService;

    @Test
    public void md5(){
        System.out.print(MD5Util.md5("qiutian","lalala"));
    }

}
