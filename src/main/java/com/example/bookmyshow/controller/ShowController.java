package com.example.bookmyshow.controller;

import com.example.bookmyshow.dtos.ShowRequest;
import com.example.bookmyshow.dtos.ShowSeatRequest;
import com.example.bookmyshow.exceptions.ShowNotFound;
import com.example.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowService showService;


    @PostMapping("/add")
    public String addShow(@RequestBody ShowRequest showRequest) {
        try {
            return showService.addShow(showRequest);
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody ShowSeatRequest showSeatRequest) throws ShowNotFound {
        try {
            return showService.associateSeats(showSeatRequest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
