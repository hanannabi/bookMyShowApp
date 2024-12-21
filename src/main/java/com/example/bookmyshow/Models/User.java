package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private Integer age;

    private String emailId;

    private String mobileNo;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();

    public User(String name, Integer age, String emailId, String mobileNo) {
        this.name = name;
        this.age = age;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }
}
