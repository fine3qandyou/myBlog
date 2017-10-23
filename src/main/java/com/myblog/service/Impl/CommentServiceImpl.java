package com.myblog.service.Impl;

import com.myblog.dao.CommentDao;
import com.myblog.entity.Comment;
import com.myblog.entity.PageBean;
import com.myblog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentDao commentDao;
    /**
     * 分页查询评论信息
     * @param pageBean
     * @return
     */
    public PageBean<Comment> listByPage(PageBean<Comment> pageBean){
        pageBean.getMap().put("start",pageBean.getStart());
        pageBean.getMap().put("end",pageBean.getEnd());
        pageBean.setResult(commentDao.listByPage(pageBean.getMap()));
        pageBean.setTotal(commentDao.getTotal(pageBean.getMap()));
        return pageBean;
    }

    /**
     * 获取总记录数目
     * @param map
     * @return
     */
    public Long getTotal(Map<String ,Object> map){
        return commentDao.getTotal(map);
    }

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    public Comment getById(Integer id){
        return commentDao.getById(id);
    }

    /**
     * 添加评论信息
     * @param comment
     * @return
     */
    public Integer saveComment(Comment comment){
        return commentDao.saveComment(comment);
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    public Integer deleteComment(Integer id){
        return commentDao.deleteComment(id);
    }

    /**
     * 更改评论审核状态
     * @param comment
     * @return
     */
    public Integer updateComment(Comment comment){
        return commentDao.updateComment(comment);
    }

    /**
     * 删除评论信息通过博客id
     * @param blogId
     * @return
     */
    public Long deleteCommentByBlogId(Integer blogId){
        return commentDao.deleteCommentByBlogId(blogId);
    }

    /**
     * 查询所有评论消息
     * @param map
     * @return
     */
    public List<Comment> getCommentData(Map<String,Object> map){
        return commentDao.listByPage(map);
    }
}
