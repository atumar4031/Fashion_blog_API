package com.productblog.services.impl;

import com.productblog.dtos.PostDto;
import com.productblog.repositories.PostRepository;
import com.productblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto createPost(PostDto product) {

        return null;
    }

    @Override
    public PostDto updatePost(int id, PostDto productDto) {
        return null;
    }

    @Override
    public List<PostDto> fetchPosts() {
        return null;
    }

    @Override
    public PostDto findPost(int id) {
        return null;
    }

    @Override
    public void deletePost(int id) {

    }

    @Override
    public Long totalLikes(PostDto productDto) {
        return null;
    }

    @Override
    public Long totalDislikes(PostDto productDto) {
        return null;
    }
}
