package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceRepo {
    @Autowired
    private TicketRepository ticketRepository;

    @Value("${app.allow.ticket.create}")
    private boolean allowTicketCreate;


    public void saveTicket(Ticket ticket) {
        if(!allowTicketCreate){
            throw new UnsupportedOperationException("Ticket creation is disabled.");
        }
        ticketRepository.save(ticket);
    }

    public Ticket fetchTicketById(int id) {
        return ticketRepository.findById(id).orElse(null);

    }

    public void updateTicketType(int id, TicketType ticketType) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setTicketType(ticketType);
            ticketRepository.save(ticket);
        }
    }
}
