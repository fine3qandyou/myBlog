package com.myblog.entity;

import java.util.Date;

public class Comment {
    private Integer id;
    private String userId;
    private String content;
    private Date date;
    private Integer verifyState;
    private Blog blog;

    public Comment(Integer id, String userId, String content, Date date, Integer verifyState, Blog blog) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.date = date;
        this.verifyState = verifyState;
        this.blog = blog;
    }

    public Comment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}

