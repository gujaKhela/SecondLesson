public class Admin implements User {
    @Override
    public void printRole() {
        System.out.println("I am an admin");
    }

    public void checkTicket() {
        System.out.println("Admin: Checking ticket.");
    }
}
