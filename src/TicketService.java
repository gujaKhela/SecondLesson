import java.util.ArrayList;
import java.util.List;

public class TicketService implements Shareable {

    private Ticket[] storageOfTickets = new Ticket[10];

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Create tickets
        Ticket emptyTicket = new Ticket();
        Ticket fullTicketOne = new Ticket("Id12", "Main hall", "256", 1693430450L, true, 'A', 25.00, 50.0);
        Ticket fullTicketTwo = new Ticket("Id13", "Main hall", "257", 169343070L, false, 'A', 25.00, 50.0);
        Ticket fullTicketThree = new Ticket("Id14", "Main hall", "258", 16930400L, true, 'B', 50.00, 60.0);
        Ticket fullTicketFour = new Ticket("Id15", "Main hall", "259", 26430400L, false, 'A', 25.00, 50.0);
        Ticket fullTicketFive = new Ticket("Id16", "Main hall", "260", 369430400L, true, 'C', 75.00, 70.0);
        Ticket limitedTicketOne = new Ticket("Main hall", "123", 256789L);
        Ticket limitedTicketTwo = new Ticket("Main hall", "124", 1456789L);
        Ticket limitedTicketThree = new Ticket("Main hall", "424", 3456789L);
        Ticket limitedTicketFour = new Ticket("Main hall", "524", 4456789L);

        // Store tickets
        service.storageOfTickets[0] = emptyTicket;
        service.storageOfTickets[1] = fullTicketOne;
        service.storageOfTickets[2] = fullTicketTwo;
        service.storageOfTickets[3] = fullTicketThree;
        service.storageOfTickets[4] = fullTicketFour;
        service.storageOfTickets[5] = fullTicketFive;
        service.storageOfTickets[6] = limitedTicketOne;
        service.storageOfTickets[7] = limitedTicketTwo;
        service.storageOfTickets[8] = limitedTicketThree;
        service.storageOfTickets[9] = limitedTicketFour;


        service.share(String.valueOf(fullTicketOne), "123-456-7890");



        User client = new Client();
        User admin = new Admin();


        client.printRole();
        admin.printRole();


        ((Client) client).getTicket();
        ((Admin) admin).checkTicket();

        // Retrieve and print tickets by stadium sector
        List<Ticket> foundTickets = service.getTicketsByStadiumSector('A');
        if (!foundTickets.isEmpty()) {
            for (Ticket ticket : foundTickets) {
                System.out.println(ticket);
            }
        } else {
            System.out.println("There are no tickets by given sector code");
        }
    }

    private Ticket returnTicketById(String id) {
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
