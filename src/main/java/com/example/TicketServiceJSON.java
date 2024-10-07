package com.example;

import com.example.BusTicket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class TicketServiceJSON {

        public List<BusTicket> loadTicketsFromJson() {
            List<BusTicket> tickets = new ArrayList<>();

            // Step 1: Load the JSON file using Spring's Resource interface
            Resource resource = new ClassPathResource("tickets.json");

            try {
                // Step 2: Use Jackson's ObjectMapper to map the JSON to a List of Ticket objects

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());


                tickets = mapper.readValue(resource.getInputStream(), new TypeReference<List<BusTicket>>() {});


            } catch (IOException e) {
                e.printStackTrace(); // Handle exception (could also throw a custom exception)
            }

            return tickets;
        }

}
