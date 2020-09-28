package com.example.demo.service;

import com.example.demo.dao.CommentRepository;
import com.example.demo.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xubin
 * @date 2020/9/7 22:21
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComent(comments);
    }


    //存放迭代找出所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 循环每个顶级的评论
     * @param comments
     * @return
     */
    private List<Comment> eachComent(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for(Comment comment:comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment: comments){
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1:replys1){
                recursivery(reply1);
            }
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();

        }
    }


  /**
     *
     * @param comment
     */
    private void recursivery(Comment comment) {
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply: replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursivery(reply);
                }
            }
        }

    }


    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
