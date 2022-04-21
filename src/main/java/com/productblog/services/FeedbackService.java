package com.productblog.services;

import com.productblog.dtos.CommentDto;
import com.productblog.dtos.LikesDto;

public interface FeedbackService {
    void addLikes(int customerId, LikesDto likesDto);
    void addDislikes(int customerId, LikesDto likesDto);
    void addComment(int customerId,CommentDto commentDto);
    void deleteComment(int customerId, int commentId);
}
