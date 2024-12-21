package com.example.bookmyshow.dtos;

import lombok.Data;

@Data
public class TheaterResponse {
    private String theaterName;

    private String location;

    public TheaterResponse(String theaterName, String location) {
        this.theaterName = theaterName;
        this.location = location;
    }
}
