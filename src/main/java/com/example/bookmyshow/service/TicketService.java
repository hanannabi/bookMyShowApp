package com.example.bookmyshow.service;

import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeat;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.dtos.TicketRequest;
import com.example.bookmyshow.dtos.TicketResponse;
import com.example.bookmyshow.exceptions.ShowNotFound;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.repository.TicketRepository;
import com.example.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;

    public TicketResponse bookTicket(TicketRequest ticketRequest) throws UserNotFoundException, ShowNotFound, Exception {
        //validation for userId,showId
        Optional<User> optionalUser = userRepository.findById(ticketRequest.getUserId());
        Optional<Show> optionalShow = showRepository.findById(ticketRequest.getShowId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("user Not found");
        }
        if (!optionalShow.isPresent()) {
            throw new ShowNotFound("Show not found");
        }
        //get user and show

        Show show = optionalShow.get();
        User user = optionalUser.get();

        //validation for requested seats
        //we will do it in a method

        boolean isValid = validateRequestedAvailability(show, ticketRequest.getRequestedSeats());
        if (!isValid) {
            throw new Exception("Requested seats are not available");
        }

        Ticket ticket = new Ticket();
        //calcuate total price of requested tickets

        int totalPrice = calculateTotalPrice(show, ticketRequest.getRequestedSeats());
        ticket.setTotalTicketPrice(totalPrice);
        //convert the list of booked seats into string from list
        String bookedSeats = convertListIntoString(ticketRequest.getRequestedSeats());
        ticket.setBookedSeats(bookedSeats);

        //birectional
        user.getTicketList().add(ticket);

        show.getTicketList().add(ticket);

        //save ticket repository to avoid saving ticket twice bc of two parents
        ticketRepository.save(ticket);

        //save repository
        userRepository.save(user);
        showRepository.save(show);
        return createTicketResponse(show,ticket);

    }

    private TicketResponse createTicketResponse(Show show, Ticket ticket) {
       TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setBookedSeats(ticket.getBookedSeats());
        ticketResponse.setLocation(show.getTheater().getLocation());
        ticketResponse.setTheaterName(show.getTheater().getTheaterName());
        ticketResponse.setMovieName(show.getMovie().getMovieName());
        ticketResponse.setShowTime(show.getShowStartTime());
        ticketResponse.setShowDate(show.getShowDate());
        return ticketResponse;
    }

    private String convertListIntoString(List<String> requestedSeats) {

        return requestedSeats.toString();
//        String result = "";
//        for (String seatNo : requestedSeats) {
//            result = result + seatNo + ",";
//        }
//        return result;
    }

    private int calculateTotalPrice(Show show, List<String> requestedSeats) {
        int totalPrice = 0;
        List<ShowSeat> showSeatList = show.getShowSeats();
        for (ShowSeat showSeat : showSeatList) {
            if (requestedSeats.contains(showSeat.getSeatNo())) {
                totalPrice = totalPrice + showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }

    private boolean validateRequestedAvailability(Show show, List<String> requestedSeats) {
        List<ShowSeat> showSeatList = show.getShowSeats();
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();
            if (requestedSeats.contains(seatNo) && !showSeat.isAvailable()) {
                return false;
            }
        }
        return true;
    }


}
