public class Admin implements User {
    @Override
    public void printRole() {
        System.out.println("I am an admin");
    }

    public boolean checkTicket(Ticket ticket) {
        if(ticket.getEventCode() != null && !ticket.getEventCode().isEmpty()) {
            System.out.println("Admin: Ticket is valid.");
            return true;
        }else {
            System.out.println("Admin: Ticket is invalid.");
            return false;
        }
    }
}
