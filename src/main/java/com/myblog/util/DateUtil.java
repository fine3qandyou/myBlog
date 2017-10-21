package com.myblog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//日期格式化
public class DateUtil {
    public static String getCurrentData(){
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sf.format(date);
    }
}
