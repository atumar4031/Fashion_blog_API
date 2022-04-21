package com.productblog.controllers;

import com.productblog.dtos.PostDto;
import com.productblog.repositories.PostRepository;
import com.productblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/api")
public class PostController {


    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/post/{categoryId}/create")
    public ResponseEntity<String> createPost(@PathVariable("categoryId") long id, @RequestBody PostDto postDto){
        postService.createPost(id, postDto);
        return new ResponseEntity<>(
                "post added",
                HttpStatus.OK
        );
    }
    @PutMapping("/post/update/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
        postService.updatePost(id, postDto);

        return new ResponseEntity<>(
                "post updated",
                HttpStatus.OK
        );
    }

    @GetMapping("/post/all")
    public List<PostDto> getPosts(){
        return  postService.findPosts();
    }

    @GetMapping("/post/{id}")
    public PostDto getPosts(@PathVariable("id") long id){
        return  postService.findPost(id);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
            postService.deletePost(id);
        return new ResponseEntity<>(
                "post deleted",
                HttpStatus.OK
        );
    }

}
