

public class TicketService {

    private Ticket[] storageOfTickets = new Ticket[10];

    public static void main(String[] args) {
        TicketService service = new TicketService();

        var emptyTicket = new Ticket();
        var fullTicket = new Ticket("Id12", "Main hall", "256",1693430400L, true,'A', 25.00, 50.0);
        var limitedTicket = new Ticket("Main hall", "124", 1456789L);

        service.storageOfTickets[0]= emptyTicket;
        service.storageOfTickets[1] = fullTicket;
        service.storageOfTickets[2]= limitedTicket;
    }

    public Ticket returnTicketById(String id) {
        for (Ticket ticket : storageOfTickets) {
            if (ticket != null && ticket.getID().equals(id)) {
                return ticket;
            }
        }
        return null;
    }
}
