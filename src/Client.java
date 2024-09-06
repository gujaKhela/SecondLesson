public class Client implements User {
    @Override
    public void printRole() {
        System.out.println("I am a client");
    }

    public void getTicket() {
        System.out.println("Client: Getting ticket.");
    }
}
