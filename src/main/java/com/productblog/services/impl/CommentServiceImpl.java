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
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public CommentDto addComment(CommentDto commentDto, long postId, long userId) throws AccessException {
        Optional.ofNullable(commentDto.getContent()) // string passes // null throw an exception
                .orElseThrow(() -> new IllegalArgumentException("Comment is required"));

        Post selectedPost = postSRepository.findById(postId)
                .orElseThrow(()-> new PostNotFound("Post is not available now"));

        User selectedUser = userRepository.findById(userId)
                .orElseThrow(()-> new PostNotFound("Post is not available now"));
        if(!selectedUser.getRole().equals("customer"))
            throw new AccessException("You are not allowed to perform this operation");

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(selectedUser)
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        Comment createdComment = commentReopitory.save(comment);
        return CommentDto.builder()
                .content(createdComment.getContent())
                .build();
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        Comment comment = commentReopitory.findById(id).orElseThrow(
                ()->new PostNotFound("Post not found"));
        comment.setContent(commentDto.getContent());
        comment.setModify_at(LocalDateTime.now());
        Comment updatedComment = commentReopitory.save(comment);
        return  CommentDto.builder()
                .content(updatedComment.getContent())
                .build();
    }

    @Override
    public void deleteCooment(long id) {
        commentReopitory.deleteById(id);
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
