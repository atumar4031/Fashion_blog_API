package com.productblog.services.impl;

import com.productblog.dtos.PostDto;
import com.productblog.exception.PostNotFound;
import com.productblog.models.Post;
import com.productblog.repositories.PostRepository;
import com.productblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public void createPost(PostDto postDto) {
        //Todo Category category = new Category("category 2", LocalDateTime.now(), LocalDateTime.now());
        Optional.ofNullable(postDto.getTitle())
                .orElseThrow(() -> new IllegalArgumentException("Post is required"));

        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        postRepository.save(post);
    }

    @Override
    public void updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new PostNotFound("Post not found"));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setModify_at(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public List<PostDto> findPosts() {
        List<PostDto> postDtos = new ArrayList<>();
        List<Post> posts =  postRepository.findAll();
        for (Post post: posts)
            postDtos.add(new PostDto(post.getId(), post.getTitle(), post.getDescription()));
        return postDtos;
    }

    @Override
    public PostDto findPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFound("post not found"));
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .build();
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFound("post not found"));
        postRepository.delete(post);
    }

    @Override
    public Long totalLikes(long id) {
        //Todo total like
        return null;
    }

    @Override
    public Long totalDislikes(long id) {
        // Todo total dislikes
        return null;
    }
}
