package com.example.bookmyshow.dtos;

import com.example.bookmyshow.Models.Show;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ShowSeatRequest {


    private int showId;

    private int priceForClassicSeats;

    private int priceForPremiumSeats;

    @ManyToOne
    @JoinColumn
    private Show show;
}

