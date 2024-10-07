package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusTicket {
    private String ticketClass;
    private String ticketType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate startDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double price;

    public  BusTicket(){};

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

    @Override
    public String toString() {
        return "BusTicket{" +
                "ticketClass='" + ticketClass + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", startDate=" + startDate +
                ", price=" + price +
                '}';
    }
}
