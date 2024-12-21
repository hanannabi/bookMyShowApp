package com.example.bookmyshow.ShowMapper;

import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.dtos.MovieRequest;

public class MovieMapper {
    public static Movie dtoToEntity(MovieRequest movieRequest) {
        return new Movie(movieRequest.getMovieName(),
                movieRequest.getDuration(),
                movieRequest.getRating(),
                movieRequest.getGenre(),
                movieRequest.getLanguage(),
              movieRequest.getReleaseDate());
    }
}
