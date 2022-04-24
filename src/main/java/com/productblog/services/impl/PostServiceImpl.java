package com.productblog.services.impl;

import com.productblog.dtos.CommentDto;
import com.productblog.dtos.PostDto;
import com.productblog.dtos.UserDto;
import com.productblog.exception.CategoryNotFound;
import com.productblog.exception.PostNotFound;
import com.productblog.exception.UserNotFound;
import com.productblog.models.Category;
import com.productblog.models.Post;
import com.productblog.models.User;
import com.productblog.repositories.CategoryRepository;
import com.productblog.repositories.PostRepository;
import com.productblog.repositories.UserRepository;
import com.productblog.services.FeedbackService;
import com.productblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService, FeedbackService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    @Override
    public PostDto createPost(long userId, long categoryId, PostDto postDto){

        Optional<Category> selected = categoryRepository.findById(categoryId);
        if(selected.isEmpty())
            throw new CategoryNotFound("category not found");
            Category category = selected.get();
        User selectedUser = userRepository.findById(userId).get();

        if(!selectedUser.getRole().equals("admin"))
            throw new UserNotFound("You are not allowed to perform this operation");

        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(category)
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();

        Post createdPost = postRepository.save(post);
        return PostDto.builder()
                .id(createdPost.getId())
                .title(createdPost.getTitle())
                .description(createdPost.getDescription())
                .build();
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new PostNotFound("Post not found"));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setModify_at(LocalDateTime.now());
        Post updatedPost = postRepository.save(post);

        return PostDto.builder()
                .title(updatedPost.getTitle())
                .description(updatedPost.getDescription())
                .build();
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<PostDto> postDtos = new ArrayList<>();
        List<Post> posts =  postRepository.findAll();
        for (Post post: posts)
            postDtos.add(new PostDto(post.getId(), post.getTitle(), post.getDescription(),post.getCategory()));
        return postDtos;
    }

    @Override
    public PostDto findPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFound("post not found"));
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .category(post.getCategory())
                .description(post.getDescription())
                .build();
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFound("post not found"));
        postRepository.delete(post);
    }

    @Override
    public List<CommentDto> getUserComments(UserDto userDto) {

        return null;
    }

    @Override
    public List<CommentDto> getPostComments(PostDto postDto) {
        return null;
    }

    @Override
    public long getPostLikes(PostDto postDto) {
        return 0;
    }

    @Override
    public long getPostDislikes(PostDto postDto) {
        return 0;
    }
}
