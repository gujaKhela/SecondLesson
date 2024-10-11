package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    private TicketServiceRepo ticketService;

    @GetMapping("/ticket")
    public Optional<Ticket> getOneTicket() {
        return Optional.ofNullable(ticketService.fetchTicketById(39));
    }

}
