package com.example;

import java.time.LocalDate;

public class BusTicket {
    private String ticketClass;
    private String ticketType;
    private LocalDate startDate;
    private double price;

    public BusTicket(String ticketClass, String ticketType, LocalDate startDate, Double price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.price = (price != null) ? price : 0.0;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getPrice() {
        return price;
    }
}
