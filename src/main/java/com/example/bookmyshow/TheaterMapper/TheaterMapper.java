package com.example.bookmyshow.TheaterMapper;

import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.dtos.TheaterRequest;
import com.example.bookmyshow.dtos.TheaterResponse;
import com.example.bookmyshow.repository.TheaterRepository;

public class TheaterMapper {
    public static Theater dtoToEntity(TheaterRequest theaterRequest) {
        
      return new Theater(theaterRequest.getTheaterName(),
                theaterRequest.getLocation());
    }

    public static TheaterResponse entityToDto(Theater savedTheater) {
        return new TheaterResponse(savedTheater.getTheaterName(),
                savedTheater.getLocation());
    }
}
