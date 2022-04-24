package com.productblog.services;

import com.productblog.dtos.LikesDto;

public interface LikeServices {
    void addLike(LikesDto likesDto,long userId, long postId);
    void disLike(LikesDto likesDto,long userId, long postId);
}
