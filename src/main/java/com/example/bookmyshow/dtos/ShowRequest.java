package com.example.bookmyshow.dtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowRequest {

    private Date showDate;

    private LocalTime showStartTime;

    private int theaterId;

    private int movieId;
}
