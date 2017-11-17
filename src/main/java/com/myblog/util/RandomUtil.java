package com.myblog.util;

import com.myblog.entity.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    public static List<Blog> randomList(List<Blog> blogList){
        List<Blog> blogRandomList = new ArrayList<>();
        Random random = new Random();
        //用来产生不重复随机数
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < blogList.size(); i++){
            randomList.add(i);
        }
        if (blogList.size() < 5) {
            blogRandomList = blogList;
        }else {
            for (int i = 1; i < 5; i++){
                int a = random.nextInt(randomList.size()-1);
                blogRandomList.add(blogList.get(randomList.get(a)));
                randomList.remove(a);
            }
            blogRandomList.add(blogList.get(0));
        }
        return blogRandomList;
    }
}
