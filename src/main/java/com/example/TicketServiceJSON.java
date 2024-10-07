package com.example;


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

            Resource resource = new ClassPathResource("tickets.json");

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                tickets = mapper.readValue(resource.getInputStream(), new TypeReference<List<BusTicket>>() {});


            } catch (IOException e) {
                e.printStackTrace();
            }

            return tickets;
        }

}
