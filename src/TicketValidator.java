import java.time.LocalDate;

public class TicketValidator {
    private int startDateViolationCount = 0;
    private int priceViolationCount = 0;
    private int ticketTypeViolationCount = 0;

    public boolean validateTicket(BusTicket ticket) {
        boolean isValid = true;

        // Validate startDate
        if (!isValidStartDate(ticket.getStartDate())) {
            isValid = false;
        }

        // Validate price
        if (!isValidPrice(ticket.getPrice())) {
            isValid = false;
        }

        // Validate ticketType
        if (!isValidTicketType(ticket.getTicketType())) {
            isValid = false;
        }

        return isValid;
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

    private boolean isValidStartDate(LocalDate startDate) {
        if (startDate != null && startDate.isAfter(LocalDate.now())) {
            System.out.println("Error: Start date can't be in the future.");
            startDateViolationCount++;
            return false;
        }
        return true;
    }

    private boolean isValidPrice(double price) {
        if (price <= 0 || price % 2 != 0) {
            System.out.println("Error: Price must be non-zero and even.");
            priceViolationCount++;
            return false;
        }
        return true;
    }

    private boolean isValidTicketType(String ticketType) {
        if (ticketType == null || !(ticketType.equals("DAY") || ticketType.equals("WEEK") || ticketType.equals("MONTH") || ticketType.equals("YEAR"))) {
            System.out.println("Error: Invalid ticket type.");
            ticketTypeViolationCount++;
            return false;
        }
        return true;
    }
}
