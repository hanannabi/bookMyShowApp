package com.example.bookmyshow.ShowMapper;

import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.dtos.ShowRequest;

public class ShowMapper {
    public static Show dtoToEntity(ShowRequest showRequest) {
       return new Show(showRequest.getShowDate(),showRequest.getShowStartTime());

    }
}
