package com.productblog.services;

import com.productblog.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto product);
    PostDto updatePost(int id, PostDto productDto);
    List<PostDto> fetchPosts();
    PostDto findPost(int id);
    void deletePost(int id);
    Long totalLikes(PostDto productDto);
    Long totalDislikes(PostDto productDto);
}
