import java.time.LocalDate;

public class TicketValidator {
    private int startDateViolationCount = 0;
    private int priceViolationCount = 0;
    private int ticketTypeViolationCount = 0;

    public boolean validateTicket(BusTicket ticket) {
        boolean isValid = true;

        // Validate startDate
        if (ticket.getStartDate() != null && ticket.getStartDate().isAfter(LocalDate.now())) {
            System.out.println("Error: Start date can't be in the future.");
            startDateViolationCount++;
            isValid = false;
        }

        // Validate price
        if (ticket.getPrice() <= 0 || ticket.getPrice() % 2 != 0) {
            System.out.println("Error: Price must be non-zero and even.");
            priceViolationCount++;
            isValid = false;
        }

        // Validate ticketType
        if (ticket.getTicketType() == null || !isValidTicketType(ticket.getTicketType())) {
            System.out.println("Error: Invalid ticket type.");
            ticketTypeViolationCount++;
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidTicketType(String ticketType) {
        return ticketType != null && (ticketType.equals("DAY") || ticketType.equals("WEEK") || ticketType.equals("MONTH") || ticketType.equals("YEAR"));
    }

    public String getMostPopularViolation() {
        if (startDateViolationCount >= priceViolationCount && startDateViolationCount >= ticketTypeViolationCount) {
            return "start date";
        } else if (priceViolationCount >= ticketTypeViolationCount) {
            return "price";
        } else {
            return "ticket type";
        }
    }
}
