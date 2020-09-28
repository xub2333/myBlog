package com.example.demo.dao;

import com.example.demo.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xubin
 * @date 2020/9/7 22:27
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
