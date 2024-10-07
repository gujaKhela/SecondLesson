package com.example;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("admin")
public class Admin extends UserBase {

    public Admin() {}

    public Admin(String name) {
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("I am an admin");
    }

    @Override
    public String getRole() {
        return "admin";
    }

    @Override
    public void addTicket(Ticket ticket) {
        super.addTicket(ticket); // Use the superclass method to add the ticket to the user
    }

    public Ticket getTicket(TicketService service, String ticketId) {
        if (service == null) {
            System.out.println("Admin: Ticket service is not available.");
            return null;
        }
        Ticket ticket = service.returnTicketById(ticketId);
        if (ticket != null) {
            System.out.println("Admin: Retrieved ticket with ID " + ticketId);
        } else {
            System.out.println("Admin: Ticket with ID " + ticketId + " not found.");
        }
        return ticket;
    }

    public boolean checkTicket(Ticket ticket) {
        if (ticket.getEventCode() != null && !ticket.getEventCode().isEmpty()) {
            System.out.println("Admin: Ticket is valid.");
            return true;
        } else {
            System.out.println("Admin: Ticket is invalid.");
            return false;
        }
    }
}
