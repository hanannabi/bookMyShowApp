package com.example.bookmyshow.ShowMapper;

import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.dtos.UserRequest;

public class UserMapper {
    public static User dtoToEntity(UserRequest userRequest) {
       return new User(userRequest.getName(),
                userRequest.getAge(),
                userRequest.getEmailId(),
                userRequest.getMobileNo());
    }
}
