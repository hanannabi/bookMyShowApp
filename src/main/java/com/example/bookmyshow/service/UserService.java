package com.example.bookmyshow.service;

import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.ShowMapper.UserMapper;
import com.example.bookmyshow.dtos.UserRequest;
import com.example.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(UserRequest userRequest) {
        User user = UserMapper.dtoToEntity(userRequest);
       return userRepository.save(user);

    }
}
