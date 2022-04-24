package com.productblog.controllers;

import com.productblog.dtos.LikesDto;
import com.productblog.services.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog/api/reaction")
public class LikeController {

    private final LikeServices likeServices;

    @Autowired
    public LikeController(LikeServices likeServices) {
        this.likeServices = likeServices;
    }

    @PostMapping("/{userId}/{postId}/{like}")
    public ResponseEntity<String> addLkes(
            @RequestBody LikesDto likesDto,
            @PathVariable("userId") long userId,
            @PathVariable("postId") long postId,
            @PathVariable("like") boolean isLike
    ) {
        if (isLike)
            likeServices.addLike(likesDto, userId, postId);
        else likeServices.disLike(likesDto, userId, postId);
        return new ResponseEntity<>(
                isLike ? "you just liked": "you just dislike", HttpStatus.OK
        );
    }
}
