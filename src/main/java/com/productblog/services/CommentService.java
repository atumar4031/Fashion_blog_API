package com.productblog.services;

import com.productblog.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    void addComment(CommentDto commentDto, long postId, long userId);
    void updateComment(long id, CommentDto commentDto);
    void deleteCooment(long id);
    CommentDto fetchComment(long id);
    List<CommentDto> fetchComments();

}
