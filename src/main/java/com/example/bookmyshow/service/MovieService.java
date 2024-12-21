package com.example.bookmyshow.service;

import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.ShowMapper.MovieMapper;
import com.example.bookmyshow.dtos.MovieRequest;
import com.example.bookmyshow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieRequest movieRequest) {
        Movie movie = MovieMapper.dtoToEntity(movieRequest);
        movieRepository.save(movie);
        return "movie added successfully!";
    }
}
