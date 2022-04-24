package com.productblog.services;

import com.productblog.dtos.PostDto;

import java.rmi.AccessException;
import java.util.List;

public interface PostService {
    PostDto createPost(long userId, long categoryId, PostDto postDto) throws AccessException;
    PostDto updatePost(long id, PostDto postDto);
    void deletePost(long id);
    List<PostDto> findAllPosts();
    PostDto findPost(long id);
}
