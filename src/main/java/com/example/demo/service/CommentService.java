package com.example.demo.service;

import com.example.demo.po.Comment;

import java.util.List;

/**
 * @author xubin
 * @date 2020/9/7 22:18
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
