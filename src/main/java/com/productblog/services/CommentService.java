package com.productblog.services;

import com.productblog.dtos.CommentDto;
import org.springframework.expression.AccessException;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto, long postId, long userId) throws AccessException;
    CommentDto updateComment(long id, CommentDto commentDto);
    void deleteCooment(long id);
    CommentDto fetchComment(long id);
    List<CommentDto> fetchComments();

}
