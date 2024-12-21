package com.example.bookmyshow.repository;

import com.example.bookmyshow.Models.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatRepository extends JpaRepository<TheaterSeats,Integer> {
}
