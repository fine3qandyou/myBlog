package com.myblog.util;

//获取服务器根目录的方法
public class pathUtil {
    public static void main(String args[]){
        String path = pathUtil.class.getResource("/").toString();
        for (int i = 0; i < 5; i++) {
            int end = path.lastIndexOf("/");
            path = path.substring(0, end);
        }
        int index = path.indexOf(":");
        System.out.print(path.substring(index+1));
    }
}
