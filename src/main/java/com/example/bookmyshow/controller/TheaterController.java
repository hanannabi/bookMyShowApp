package com.example.bookmyshow.controller;

import com.example.bookmyshow.dtos.TheaterRequest;
import com.example.bookmyshow.dtos.TheaterResponse;
import com.example.bookmyshow.dtos.TheaterSeatRequest;
import com.example.bookmyshow.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addTheater")
    public TheaterResponse addTheater(@RequestBody TheaterRequest theaterRequest) {
        return theaterService.addTheater(theaterRequest);
    }

    @PostMapping("/addTheaterSeats")
    private String addTheaterSeats(@RequestBody TheaterSeatRequest theaterSeatRequest) {
        return theaterService.addTheaterSeats(theaterSeatRequest);
    }
}
