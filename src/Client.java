public class Client implements User {
    @Override
    public void printRole() {
        System.out.println("I am a client");
    }

    public void getTicket() {
        System.out.println("Client: Getting ticket.");
    }

    public Ticket getTicket(TicketService service, String ticketId ) {
        Ticket ticket = service.returnTicketById(ticketId);
        if (ticket != null) {
            System.out.println("Client: Retrieved ticket with ID " + ticketId);
        } else {
            System.out.println("Client: Ticket with ID " + ticketId + " not found.");
        }
        return ticket;
    }
}
