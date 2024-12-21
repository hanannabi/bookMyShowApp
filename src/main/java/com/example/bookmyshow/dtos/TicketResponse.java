package com.example.bookmyshow.dtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class TicketResponse {

    private LocalTime showTime;
    private Date showDate;
    private String movieName;
    private String theaterName;
    private String bookedSeats;
    private String location;


}
