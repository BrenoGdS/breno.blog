package com.example.breno.blog.controllers;

import com.example.breno.blog.exceptions.UserNotFoundException;
import com.example.breno.blog.models.UserModel;
import com.example.breno.blog.models.dtos.NewUserRecordDto;
import com.example.breno.blog.models.dtos.UserRecordDto;
import com.example.breno.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRecordDto> saveUser(@RequestBody @Valid NewUserRecordDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable UUID userId){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.getUser(userId));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with passed id.");
        }
    }
    @GetMapping
    ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getAllUsers());
    }

}
