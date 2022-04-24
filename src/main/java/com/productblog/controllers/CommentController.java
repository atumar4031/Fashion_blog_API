package com.productblog.controllers;

import com.productblog.dtos.CommentDto;
import com.productblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/blog/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/{userId}/create")
    public CommentDto createComment(@PathVariable("postId") long postId,
                                                @PathVariable("userId") long userId,
                                                @RequestBody CommentDto commentDto) throws AccessException {
        return commentService.addComment(commentDto, postId, userId);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") long id, @RequestBody CommentDto commentDto){
        commentService.updateComment(id, commentDto);

        return new ResponseEntity<>(
                "comment updated",
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public List<CommentDto> getComment(){
        return  commentService.fetchComments();
    }

    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable("id") long id){
        return  commentService.fetchComment(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        commentService.deleteCooment(id);
        return new ResponseEntity<>(
                "Comment deleted",
                HttpStatus.OK
        );
    }
}
