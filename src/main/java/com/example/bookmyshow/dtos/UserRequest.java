package com.example.bookmyshow.dtos;

import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private Integer age;
    private String emailId;
    private String mobileNo;
}
