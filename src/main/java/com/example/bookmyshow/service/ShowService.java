package com.example.bookmyshow.service;

import com.example.bookmyshow.Enums.SeatTpes;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.ShowMapper.ShowMapper;
import com.example.bookmyshow.dtos.ShowRequest;
import com.example.bookmyshow.dtos.ShowSeatRequest;
import com.example.bookmyshow.exceptions.MovieNotFoundException;
import com.example.bookmyshow.exceptions.ShowNotFound;
import com.example.bookmyshow.exceptions.TheaterNotFoundException;
import com.example.bookmyshow.repository.MovieRepository;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;




    public String addShow(ShowRequest showRequest) throws MovieNotFoundException, TheaterNotFoundException {
        Show show = ShowMapper.dtoToEntity(showRequest);

        //set movie and theater entity
        Optional<Movie> movieOptional = movieRepository.findById(showRequest.getMovieId());
        if (movieOptional.isEmpty()) {
            throw new MovieNotFoundException("Movie not present");
        }
        Optional<Theater> optionalTheater = theaterRepository.findById(showRequest.getTheaterId());
        if (optionalTheater.isEmpty()) {
            throw new TheaterNotFoundException("Theater Not Found");
        }
        Movie movie = movieOptional.get();
        Theater theater = optionalTheater.get();
//setting th foreign keys in show table
        //setting parent in child
        show.setMovie(movie);
        show.setTheater(theater);
//these two parents movie and theater will save the show twice bc show table dont have id so now we
        //wll first save show to get id
        Show savedShow = showRepository.save(show);
        //setting bidirectional or setting child entity in parent entity
        movie.getShowList().add(savedShow);
        theater.getShowList().add(savedShow);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "show added";
    }

    public String associateSeats(ShowSeatRequest showSeatRequest) throws ShowNotFound {

        Optional<Show> optionalShow = showRepository.findById(showSeatRequest.getShowId());
        if (optionalShow.isEmpty()) {
            throw new ShowNotFound("Show id not correct");
        }
        //getting the show
        Show show = optionalShow.get();

        //getting theater Seats with help of show entity all because of mapping

        Theater theater = show.getTheater();
        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeatsList();

        //now i want to get 2 attributes from theater seats into show seats
        //by mapping
        for (TheaterSeats theaterSeats : theaterSeatsList) {

            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNo(theaterSeats.getSeatNo());
            showSeat.setSeatTypes(theaterSeats.getSeatType());

            //setting price

            if (showSeat.getSeatTypes().equals(SeatTpes.CLASSIC)) {
                showSeat.setPrice(showSeatRequest.getPriceForClassicSeats());
            } else
                showSeat.setPrice(showSeatRequest.getPriceForPremiumSeats());

            //set FK
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);

            //bidirectional mapping ---.each seat needs to be added to showSeatList
            List<ShowSeat> showSeatList = show.getShowSeats();
            showSeatList.add(showSeat);


            //save parent only
            showRepository.save(show);
        }

      return "Show Seats added";

    }
}
