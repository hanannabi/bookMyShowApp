package com.example.bookmyshow.dtos;

import lombok.Data;

@Data
public class TheaterSeatRequest {

    private int noOfSeatsIn1Row;

    private int noOfClassicSeats;

    private int noOfPremiumSeats;

    private String location;
}
