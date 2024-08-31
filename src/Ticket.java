import java.time.Instant;

public class Ticket {

    private String id;
    private String concertHall;
    private String eventCode;
    private long time;
    private boolean isPromo;
    private char stadiumSector;
    private double maxBackpackWeight;

    private double price;
    private final long creationTime;



    public  Ticket() {
        this.creationTime = Instant.now().getEpochSecond();
    }

    public Ticket(String id, String concertHall, String eventCode, long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        this(concertHall, eventCode, time);

        setId(id);
        setPromo(isPromo);
        setStadiumSector(stadiumSector);
        setMaxBackpackWeight(maxBackpackWeight) ;
        setPrice(price);
    }

    public Ticket(String concertHall, String eventCode, long time) {
        this();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        setTime(time);
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public String getID() {
        return this.id;
    }

    private void setId(String id) {
        if (id != null && !id.isBlank() && id.length() <= 4) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID should not be empty and must be 4 digits or fewer.");
        }
    }

    public String getConcertHall() {
        return this.concertHall;
    }

    private void setConcertHall(String concertHall) {
        if(concertHall.length() <= 10) {
            this.concertHall = concertHall;
        }else {
            throw new IllegalArgumentException("Concert hall name should be 10 characters or fewer.");
        }
    }


    public String getEventCode() {
        return this.eventCode;
    }

    private void setEventCode(String eventCode) {

            if (eventCode.length() == 3) {
                this.eventCode = eventCode;
            } else {
                throw new IllegalArgumentException("Event code should be 3 characters long");
            }
        }

    public char getStadiumSector() {
        return this.stadiumSector;
    }

    private void setStadiumSector(char stadiumSector) {
        char stadiumSectorToUpper = Character.toUpperCase(stadiumSector);
        if(stadiumSectorToUpper == 'A' || stadiumSectorToUpper == 'B' || stadiumSectorToUpper == 'C') {
            this.stadiumSector = stadiumSector;
        } else {
            throw new IllegalArgumentException("Stadium sector should be A, B, or C.");
        }
    }

    public long getTime() {
        return this.time;
    }

    private void setTime(long time) {
        if(time >= 0) {
            this.time = time;
        }else {
            throw new IllegalArgumentException("Time must be a non-negative Unix timestamp.");
        }
    }

    public boolean getPromo() {
        return this.isPromo;
    }

    private void setPromo(boolean isPromo) {
        this.isPromo = isPromo;
    }

    public double getMaxBackpackWeight() {
        return this.maxBackpackWeight;
    }
    private void setMaxBackpackWeight(double maxBackpackWeight) {
        if (maxBackpackWeight >= 0) {
            this.maxBackpackWeight = maxBackpackWeight;
        } else {
            throw new IllegalArgumentException("Max backpack weight must be a non-negative value.");
        }
    }

    public double getPrice() {
        return this.price;
    }
    private void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be a non-negative value.");
        }
    }
    @Override
    public String toString() {
        return "Ticket [ID=" + id +
                ", Concert Hall=" + concertHall +
                ", Event Code=" + eventCode +
                ", Time=" + time +
                ", Is Promo=" + isPromo +
                ", Stadium Sector=" + stadiumSector +
                ", Max Backpack Weight=" + maxBackpackWeight + " kg" +
                ", Price=" + price +
                ", Creation Time=" + creationTime + "]";
    }

}

