package com.myblog.test;

import com.myblog.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ShiroTest {
    @Test
    public void md5(){
        System.out.print(MD5Util.md5("123","asd"));
    }
}
