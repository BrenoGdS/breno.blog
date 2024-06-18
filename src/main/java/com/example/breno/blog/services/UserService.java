package com.example.breno.blog.services;

import com.example.breno.blog.exceptions.UserNotFoundException;
import com.example.breno.blog.models.UserModel;
import com.example.breno.blog.models.dtos.NewUserRecordDto;
import com.example.breno.blog.models.dtos.UserRecordDto;
import com.example.breno.blog.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserRecordDto saveUser(NewUserRecordDto newUserRecordDto){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(newUserRecordDto, userModel);
        userModel.setPassword(new BCryptPasswordEncoder().encode(newUserRecordDto.password()));
        userModel = userRepository.save(userModel);
        return new UserRecordDto(userModel.getUserId(), userModel.getUserName(), userModel.getPassword());
    }

    public UserModel getUser(UUID userID){
        Optional<UserModel> userO = userRepository.findById(userID);
        if(userO.isEmpty()){
            throw new UserNotFoundException();
        }
        return userO.get();
    }
    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

}
