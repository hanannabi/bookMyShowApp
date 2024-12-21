package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.SeatTpes;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "theater_seats")
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatTpes seatType;

    @ManyToOne
    @JoinColumn
    private Theater theater;
}
