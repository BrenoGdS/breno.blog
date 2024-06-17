package com.example.breno.blog.services;

import com.example.breno.blog.exceptions.UserNotFoundException;
import com.example.breno.blog.models.PostModel;
import com.example.breno.blog.models.UserModel;
import com.example.breno.blog.models.dtos.NewPostRecordDto;
import com.example.breno.blog.models.dtos.PostRecordDto;
import com.example.breno.blog.models.dtos.UserRecordDto;
import com.example.breno.blog.repositories.PostRepository;
import com.example.breno.blog.repositories.UserRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostModel savePost(UUID userID, NewPostRecordDto newPostDto){
        if(!userRepository.existsById(userID)){
            throw new UserNotFoundException();
        }
        PostModel postModel = new PostModel((new UserModel(userID)), LocalDateTime.now());
        BeanUtils.copyProperties(newPostDto, postModel);
        return postRepository.save(postModel);
    }

    public PostModel getOnePost(UUID postID){
        if(!postRepository.existsById(postID)){
            throw new UserNotFoundException();
        }
        return postRepository.findById(postID).get();
    }
    public List<PostRecordDto> getAllPostsByUserId(UUID userID){
        if(!userRepository.existsById(userID)){
            throw new UserNotFoundException();
        }
        return postRepository.findByAuthorOrderByCreationDateDesc(new UserModel(userID)).stream()
                .map(p -> new PostRecordDto(p.getPostId(),p.getAuthor().getUserId(), p.getTitle(),p.getContent(),p.getCreationDate()))
                .collect(Collectors.toList());
    }
}
