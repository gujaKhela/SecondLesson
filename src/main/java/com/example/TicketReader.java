package com.example;
import com.example.BusTicket;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TicketReader {

    public List<BusTicket> readTicketsFromFile(String fileName) {
        List<BusTicket> tickets = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                String[] parts = line.replace("{", "").replace("}", "").replace("\"", "").split(",");

                if (parts.length >= 4) {
                    String ticketClass = null;
                    String ticketType = null;
                    String startDateStr = null;
                    Double price = null;

                    for (String part : parts) {
                        String[] keyValue = part.split(":");

                        if (keyValue.length == 2) {
                            String key = keyValue[0].trim();
                            String value = keyValue[1].trim();

                            switch (key) {
                                case "ticketClass" -> ticketClass = !value.equals("null") ? value : null;
                                case "ticketType" -> ticketType = !value.equals("null") ? value : null;
                                case "startDate" -> startDateStr = !value.equals("null") ? value : null;
                                case "price" -> {
                                    try {
                                        price = !value.equals("null") && !value.isEmpty() ? Double.parseDouble(value) : null;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid price format: " + value);
                                    }
                                }
                            }
                        }
                    }

                    // Parse startDate only if it's not null or empty
                    LocalDate startDate = null;
                    if (startDateStr != null && !startDateStr.isEmpty()) {
                        try {
                            startDate = LocalDate.parse(startDateStr, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format: " + startDateStr);
                        }
                    }

                    // Create and add the BusTicket object
                    BusTicket ticket = new BusTicket(ticketClass, ticketType, startDate, price);
                    tickets.add(ticket);
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
