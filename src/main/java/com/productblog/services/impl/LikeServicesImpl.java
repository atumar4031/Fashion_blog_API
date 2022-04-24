package com.productblog.services.impl;

import com.productblog.dtos.LikesDto;
import com.productblog.dtos.PostDto;
import com.productblog.dtos.UserDto;
import com.productblog.exception.LikeAlreadyExist;
import com.productblog.exception.PostNotFound;
import com.productblog.exception.UserNotFound;
import com.productblog.models.Like;
import com.productblog.models.Post;
import com.productblog.models.User;
import com.productblog.repositories.LikesRepository;
import com.productblog.repositories.PostRepository;
import com.productblog.repositories.UserRepository;
import com.productblog.services.LikeServices;
import com.productblog.services.PostService;
import com.productblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeServicesImpl implements LikeServices {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeServicesImpl(LikesRepository likesRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    @Override
    public void addLike(LikesDto likesDto, long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("user not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFound("post not found"));
        Like like = Like.builder()
                .user(user)
                .post(post)
                .likes(1L)
                .dislikes(0L)
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        Optional<Like> selected = likesRepository.findByUser(user).stream().findFirst();
        if (selected.isPresent())
            throw new LikeAlreadyExist("like already exist");

        likesRepository.save(like);
    }

    @Override
    public void disLike(LikesDto likesDto, long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("user not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFound("post not found"));
        Like like = Like.builder()
                .user(user)
                .post(post)
                .likes(0L)
                .dislikes(1L)
                .created_at(LocalDateTime.now())
                .modify_at(LocalDateTime.now())
                .build();
        Optional<Like> selected = likesRepository.findByUser(user).stream().findFirst();
        if (selected.isPresent())
            throw new LikeAlreadyExist("like already exist");

        likesRepository.save(like);
    }

}
