package com.example.bookmyshow.service;

import com.example.bookmyshow.Enums.SeatTpes;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Models.TheaterSeats;
import com.example.bookmyshow.TheaterMapper.TheaterMapper;
import com.example.bookmyshow.dtos.TheaterRequest;
import com.example.bookmyshow.dtos.TheaterResponse;
import com.example.bookmyshow.dtos.TheaterSeatRequest;
import com.example.bookmyshow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public TheaterResponse addTheater(TheaterRequest theaterRequest) {
        //only entity saves inside the db
        //convert the entry dto into entity and then save it
        Theater theater = TheaterMapper.dtoToEntity(theaterRequest);
        Theater savedTheater = theaterRepository.save(theater);
        return TheaterMapper.entityToDto(savedTheater);


    }

    public String addTheaterSeats(TheaterSeatRequest theaterSeatRequest) {
        //need to create and save theaterSeat Entity in theaterSeat repository

        int noOfClassicSeats = theaterSeatRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatRequest.getNoOfPremiumSeats();
        int columns = theaterSeatRequest.getNoOfSeatsIn1Row();
        String location = theaterSeatRequest.getLocation();
        Theater theater = theaterRepository.findByLocation(location);//Parent Entity
        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeatsList();

        int counter = 1;
        char ch = 'A';

        //done for classic seats
        for (int count = 1; count <= noOfClassicSeats; count++) {

            String seatNo = counter + "";
            seatNo = seatNo + ch;
            ch = (char) (ch + 1);//
            ch++;

            if ((ch - 'A' + 1) == columns) {
                ch = 'A';
                count++;
            }

            TheaterSeats theaterSeats = new TheaterSeats();//Child Entity
            theaterSeats.setSeatNo(seatNo);
            theaterSeats.setSeatType(SeatTpes.CLASSIC);
            //we need to set the theater entity as well
            //so for that we need to first get the theater entity
            //we will trying to find theater by its unique location inside theater repository line 39
            theaterSeats.setTheater(theater);//storing parent info in child

            //this is bidirectional mapping storing child entity
            //in the parent entity
            theaterSeatsList.add(theaterSeats);
        }

        //done for premium seates
        for (int count = 1; count <= noOfPremiumSeats; count++) {

            String seatNo = counter + "";
            seatNo = seatNo + ch;
            ch = (char) (ch + 1);//
            ch++;

            if ((ch - 'A') == columns) {
                ch = 'A';
                count++;
            }
            TheaterSeats theaterSeats = new TheaterSeats();
            theaterSeats.setSeatNo(seatNo);
            theaterSeats.setSeatType(SeatTpes.PREMIUM);
            theaterSeats.setTheater(theater);//storing parent info in child
            //storing child in parent ---bidirectional
            theaterSeatsList.add(theaterSeats);
        }
        theaterRepository.save(theater);
        return "Theater seats have been successfully added";
    }
}
