package com.myblog.test;

import com.myblog.dao.BloggerDao;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import com.myblog.entity.Blogger;
import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class BloggerTest {

    @Resource
    private BloggerDao bloggerDao;

    @Test
    public void test() throws Exception {
            Blogger blogger = bloggerDao.getBloggerData();
            System.out.println(blogger);
    }
}
