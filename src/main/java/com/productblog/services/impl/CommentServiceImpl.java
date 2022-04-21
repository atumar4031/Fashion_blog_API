package com.productblog.services.impl;

import com.productblog.dtos.CommentDto;
import com.productblog.exception.CommentNotFound;
import com.productblog.exception.PostNotFound;
import com.productblog.models.Comment;
import com.productblog.models.Post;
import com.productblog.models.User;
import com.productblog.repositories.CommentReopitory;
import com.productblog.repositories.PostRepository;
import com.productblog.repositories.UserRepository;
import com.productblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private final CommentReopitory commentReopitory;
    private final PostRepository postSRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentReopitory commentReopitory, PostRepository postSRepository, UserRepository userRepository) {
        this.commentReopitory = commentReopitory;
        this.postSRepository = postSRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addComment(CommentDto commentDto, long postId, long userId) {
        Optional.ofNullable(commentDto.getContent()) // string passes // null throw an exception
                .orElseThrow(() -> new IllegalArgumentException("Comment is required"));

        Post selectedPost = postSRepository.findById(postId)
                .orElseThrow(()-> new PostNotFound("Post is not available now"));

        User selectedUser = userRepository.findById(userId)
                .orElseThrow(()-> new PostNotFound("Post is not available now"));

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .post(selectedPost)
                .user(selectedUser)
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        commentReopitory.save(comment);
    }

    @Override
    public void updateComment(long id, CommentDto commentDto) {
        Comment comment = commentReopitory.findById(id).orElseThrow(
                ()->new PostNotFound("Post not found"));
        comment.setContent(commentDto.getContent());
        comment.setModify_at(LocalDateTime.now());
        commentReopitory.save(comment);
    }

    @Override
    public void deleteCooment(long id) {

    }

    @Override
    public CommentDto fetchComment(long id) {
            Comment comment = commentReopitory.findById(id)
                    .orElseThrow(() -> new CommentNotFound("comment not found"));

        return CommentDto.builder()
                .content(comment.getContent())
                .created_at(comment.getCreated_at())
                .modify_at(comment.getModify_at())
                .build();
    }

    @Override
    public List<CommentDto> fetchComments() {
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = commentReopitory.findAll();
        for (Comment comment: comments)
            commentDtos.add(
                    CommentDto.builder()
                            .content(comment.getContent())
                            .created_at(comment.getCreated_at())
                            .modify_at(comment.getModify_at())
                            .build()
            );
        return commentDtos;
    }
}