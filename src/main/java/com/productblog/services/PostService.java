package com.productblog.services;

import com.productblog.dtos.PostDto;

import java.util.List;

public interface PostService {
    void createPost(long id, PostDto postDto);
    void updatePost(long id, PostDto postDto);
    void deletePost(long id);
    List<PostDto> findPosts();
    PostDto findPost(long id);
    Long totalLikes(long id);
    Long totalDislikes(long id);
}
