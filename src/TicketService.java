

public class TicketService {

    private Ticket[] storageOfTickets = new Ticket[10];

    public static void main(String[] args) {
        TicketService service = new TicketService();

        var emptyTicket = new Ticket();
        var fullTicketOne = new Ticket("Id12", "Main hall", "256",1693430450L, true,'A', 25.00, 50.0);
        var fullTicketTwo = new Ticket("Id13", "Main hall", "257",169343070L, false,'A', 25.00, 50.0);
        var fullTicketThree = new Ticket("Id14", "Main hall", "258",16930400L, true,'B', 50.00, 60.0);
        var fullTicketFour = new Ticket("Id15", "Main hall", "259",26430400L, false,'A', 25.00, 50.0);
        var fullTicketFive = new Ticket("Id16", "Main hall", "260",369430400L, true,'C', 75.00, 70.0);
        var limitedTicketOne = new Ticket("Main hall", "123", 256789L);
        var limitedTicketTwo = new Ticket("Main hall", "124", 1456789L);
        var limitedTicketThree = new Ticket("Main hall", "424", 3456789L);
        var limitedTicketFour = new Ticket("Main hall", "524", 4456789L);



        service.storageOfTickets[0]= emptyTicket;
        service.storageOfTickets[1] = fullTicketOne;
        service.storageOfTickets[2] = fullTicketTwo;
        service.storageOfTickets[3] = fullTicketThree;
        service.storageOfTickets[4] = fullTicketFour;
        service.storageOfTickets[5] = fullTicketFive;
        service.storageOfTickets[6]= limitedTicketOne;
        service.storageOfTickets[7]= limitedTicketTwo;
        service.storageOfTickets[8]= limitedTicketThree;
        service.storageOfTickets[9]= limitedTicketFour;
    }

    private Ticket returnTicketById(String id) {
        for (Ticket ticket : storageOfTickets) {
            if (ticket != null && ticket.getID().equals(id)) {
                return ticket;
            }
        }
        return null;
    }
}
