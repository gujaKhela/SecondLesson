

public class TicketService {

    public static void main(String[] args) {
        var emptyTicket = new Ticket();
        var fullTicket = new Ticket("Id12", "Main hall", "256",1693430400L, true,'A', 25.00, 50.0);
        var limitedTicket = new Ticket("Main hall", "124", 1456789L);

        System.out.println(emptyTicket);
        System.out.println(fullTicket);
        System.out.println(limitedTicket);

    }
}
