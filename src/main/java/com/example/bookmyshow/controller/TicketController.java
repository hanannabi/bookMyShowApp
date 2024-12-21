package com.example.bookmyshow.controller;

import com.example.bookmyshow.dtos.TicketRequest;
import com.example.bookmyshow.dtos.TicketResponse;
import com.example.bookmyshow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public TicketResponse bookTicket(@RequestBody TicketRequest ticketRequest) {
        try {
           return ticketService.bookTicket(ticketRequest);
        }catch (Exception e){
            return new TicketResponse();
        }
    }
}
