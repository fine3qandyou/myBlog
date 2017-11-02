package com.myblog.controller;


import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import com.myblog.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/blogger")
public class BloggerController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping(value = "/login")
    public String login(Blogger blogger, HttpServletRequest request) {
        //获取登录实体
        Subject subject = SecurityUtils.getSubject();
        //String password = MD5Util.md5(blogger.getPassword(),"lalala");
        //获取用户密码登录token
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(), blogger.getPassword());
        try {
            //根据token登录 会调用MyRealm中的doGetAuthenticationInfo方法进行身份认证
            subject.login(token);
            return "redirect:/admin/main.jsp";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            //提示信息
            request.setAttribute("errorInfo", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/aboutme")
    public ModelAndView aboutMe() {
        ModelAndView modelAndView = new ModelAndView();
        Blogger blogger = bloggerService.getBloggerData();
        modelAndView.addObject("blogger", blogger);
        modelAndView.addObject("commonPage", "foreground/blogger/bloggerInfo.jsp");
        modelAndView.addObject("title", "关于博主 - 邱天的博客");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }

    @RequestMapping(value = "/myalbum")
    public ModelAndView myAlbum() {
        ModelAndView modelAndView = new ModelAndView();
        //要写一个相册的service获取相册
        //要建一个相册表
        //....
        modelAndView.addObject("commonPage", "foreground/blogger/myAlbum.jsp");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }

    @RequestMapping("/resource")
    public ModelAndView resource() {
        ModelAndView modelAndView = new ModelAndView();
        //
        //....
        modelAndView.addObject("commonPage", "foreground/blogger/resource.jsp");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }
}
