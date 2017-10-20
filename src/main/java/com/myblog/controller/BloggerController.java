package com.myblog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/blog")
public class BloggerController {

    @ResponseBody
    @RequestMapping(value="/hello")
    public String hello(){
        return "hello";
    }

}
