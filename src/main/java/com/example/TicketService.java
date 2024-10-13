package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketService implements Shareable {

    @Autowired
    private TicketServiceRepo ticketServiceRepo;

    @Autowired
    private  UserServiceRepo userServiceRepo;

    @Autowired
    private TicketServiceJSON ticketServiceJSON;

    private Ticket[] storageOfTickets = new Ticket[10];

    public void run() {
        validateBusTickets();
        initializeTickets();
        shareTicket();
        demonstrateUserFunctionality();
        handleDatabaseOperations();
        retrieveTicketsBySector('A');
        BusTicketFromJSON();
    }

    private void BusTicketFromJSON() {
        TicketServiceJSON ticketService = new TicketServiceJSON();
        List<BusTicket> tickets = ticketService.loadTicketsFromJson();
        tickets.forEach(System.out::println);
    }

    public Ticket getTicketById(int id) {
        Ticket ticket = ticketServiceRepo.fetchTicketById(id);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket with ID " + id + " not found.");
        }
        return ticket;
    }


    private void validateBusTickets() {
        TicketReader reader = new TicketReader();
        TicketValidator validator = new TicketValidator();

        List<BusTicket> tickets = reader.readTicketsFromFile("C:/Users/elguj/Desktop/SecondLesson/src/tickets.txt");
        int totalTickets = tickets.size();
        int validTickets = 0;

        for (BusTicket ticket : tickets) {
            if (validator.validateTicket(ticket)) {
                validTickets++;
            }
        }

        System.out.println("Total = " + totalTickets);
        System.out.println("Valid = " + validTickets);
        System.out.println("Most popular violation = " + validator.getMostPopularViolation());
    }

    private void initializeTickets() {
        storageOfTickets[0] = new Ticket();
        storageOfTickets[1] = new Ticket("Id12", "Main hall", "256", 1693430450L, true, 'A', 25.00, 50.0);
        storageOfTickets[2] = new Ticket("Id13", "Main hall", "257", 169343070L, false, 'A', 25.00, 50.0);
        storageOfTickets[3] = new Ticket("Id14", "Main hall", "258", 16930400L, true, 'B', 50.00, 60.0);
        storageOfTickets[4] = new Ticket("Id15", "Main hall", "259", 26430400L, false, 'A', 25.00, 50.0);
        storageOfTickets[5] = new Ticket("Id16", "Main hall", "260", 369430400L, true, 'C', 75.00, 70.0);
        storageOfTickets[6] = new Ticket("Main hall", "123", 256789L);
        storageOfTickets[7] = new Ticket("Main hall", "124", 1456789L);
        storageOfTickets[8] = new Ticket("Main hall", "424", 3456789L);
        storageOfTickets[9] = new Ticket("Main hall", "524", 4456789L);
    }

    private void shareTicket() {
        share(String.valueOf(storageOfTickets[1]), "123-456-7890");
    }

    private void demonstrateUserFunctionality() {
        User client = new Client();
        User admin = new Admin();

        client.printRole();
        admin.printRole();

        ((Client) client).getTicket();
        boolean isValid = ((Admin) admin).checkTicket(storageOfTickets[1]);
        System.out.println("Ticket valid: " + isValid);
    }

    private void handleDatabaseOperations() {

            UserBase client = new Client("Client1");
            userServiceRepo.saveUser(client);
            System.out.println("Saved new client: " + client.getName());

            userServiceRepo.activateUserAndCreateTicket(client.getId(), new Ticket(client.getId(), TicketType.YEAR));

            UserBase admin = new Admin("shesacveliliaaaaaaa");
            userServiceRepo.saveUser(admin);
            System.out.println("Saved new admin: " + admin.getName());

            userServiceRepo.updateUser(admin.getId(), new Client("someone"));

            User fetchedUser = userServiceRepo.fetchUserById(120);
            if (fetchedUser != null) {
                System.out.println("Fetched user: " + fetchedUser.getName() + " with role: " + fetchedUser.getRole());
            }

            userServiceRepo.deleteUserById(118);
            System.out.println("Deleted user with ID 118");

            Ticket ticket = new Ticket(3, TicketType.DAY);
            ticketServiceRepo.saveTicket(ticket);
            System.out.println("Saved new ticket for user ID: " + ticket.getUser());

            Ticket fetchedTicket = ticketServiceRepo.fetchTicketById(3); // Ensure you fetch by the correct ID
            if (fetchedTicket != null) {
                System.out.println("Fetched ticket for user ID: " + fetchedTicket.getUser() + " with type: " + fetchedTicket.getTicketType());
            }

            Ticket ticketll = new Ticket();
            ticket.setTicketType(TicketType.DAY);
            ticket.setUser(client);  // Associate the ticket with the user
            ticketServiceRepo.saveTicket(ticket);
            System.out.println("Saved new ticket for user: " + ticket.getUser().getName());

            Ticket fetchedTicketByIdTicket = ticketServiceRepo.fetchTicketById(ticket.getTicketId());
            if (fetchedTicket != null) {
                System.out.println("Fetched ticket for user: " + fetchedTicket.getUser().getName() + " with type: " + fetchedTicketByIdTicket.getTicketType());
            }

            ticketServiceRepo.updateTicketType(34, TicketType.MONTH);
            System.out.println("Updated ticket type for ticket ID 10");

            Ticket updatedTicket = ticketServiceRepo.fetchTicketById(34);
            if (updatedTicket != null) {
                System.out.println("Updated ticket type for user ID: " + updatedTicket.getUser() + " is now: " + updatedTicket.getTicketType());
            }

    }



    private void retrieveTicketsBySector(char stadiumSector) {
        List<Ticket> foundTickets = getTicketsByStadiumSector(stadiumSector);
        if (!foundTickets.isEmpty()) {
            foundTickets.forEach(System.out::println);
        } else {
            System.out.println("There are no tickets by given sector code");
        }
    }

    protected Ticket returnTicketById(String id) {
        for (Ticket ticket : storageOfTickets) {
            if (ticket != null && ticket.getId().equals(id)) {
                return ticket;
            }
        }
        return null;
    }

    private List<Ticket> getTicketsByStadiumSector(char stadiumSector) {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : storageOfTickets) {
            if (ticket != null && ticket.getStadiumSector() == stadiumSector) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    @Override
    public void share(String phone) {
        System.out.println("Ticket shared via phone: " + phone);
    }

    @Override
    public void share(String phone, String email) {
        System.out.println("Ticket shared via phone and email: " + phone + ", " + email);
    }
}

