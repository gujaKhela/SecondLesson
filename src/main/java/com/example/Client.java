package com.example;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("client")
public class Client extends UserBase {

    public Client() {}

    public Client(String name) {
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("I am a client");
    }

    @Override
    public String getRole() {
        return "client";
    }

    @Override
    public void addTicket(Ticket ticket) {
        super.addTicket(ticket);
    }

    public Ticket getTicket(TicketService service, String ticketId) {
        Ticket ticket = service.returnTicketById(ticketId);
        if (ticket != null) {
            System.out.println("Client: Retrieved ticket with ID " + ticketId);
        } else {
            System.out.println("Client: Ticket with ID " + ticketId + "not found.");
        }
        return ticket;
    }
}
