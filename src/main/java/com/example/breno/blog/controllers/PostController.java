package com.example.breno.blog.controllers;

import com.example.breno.blog.exceptions.PostNotFoundException;
import com.example.breno.blog.exceptions.UserNotFoundException;
import com.example.breno.blog.models.PostModel;
import com.example.breno.blog.models.dtos.NewPostRecordDto;
import com.example.breno.blog.services.PostService;
import com.example.breno.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Object> savePost(@PathVariable(name = "userId") UUID userID, @RequestBody @Valid NewPostRecordDto newPostRecordDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(userID, newPostRecordDto));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with passed ID");
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Object> getOnePost(@PathVariable(name = "postId") UUID postId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getOnePost(postId));
        } catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found with passed ID");
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getAllPostsByUserId(@PathVariable(name = "userId") UUID userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPostsByUserId(userId));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with passed ID");
        }
    }
}
