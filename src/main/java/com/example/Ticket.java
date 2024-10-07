package com.example;

import java.sql.Timestamp;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket extends Identifiable {

    @NullableWarning
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBase user;

    @Transient
    private long time;
    @Transient
    private String concertHall;
    @Transient
    private String eventCode;
    @Transient
    private boolean isPromo;
    @Transient
    private char stadiumSector;
    @Transient
    private double maxBackpackWeight;
    @Transient
    private double price;


    public Ticket() {
        NullableWarningChecker.check(this);
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Ticket(String id, String concertHall, String eventCode, long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        this(concertHall, eventCode, time);
        setId(id);
        setPromo(isPromo);
        setStadiumSector(stadiumSector);
        setMaxBackpackWeight(maxBackpackWeight);
        setPrice(price);
    }

    public Ticket(int userId, TicketType ticketType) {
        this();
        this.ticketType = ticketType;
        if (ticketType == null) {
            throw new IllegalArgumentException("Ticket type cannot be null.");
        }
    }

    public Ticket(String concertHall, String eventCode, long time) {
        this();
        setConcertHall(concertHall);
        setEventCode(eventCode);
        setTime(time);
    }

    public int getTicketId() {
        return id;
    }

    public TicketType getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public UserBase getUser() {
        return user;
    }

    public void setUser(UserBase user) {
        this.user = user;
    }


    public String getConcertHall() {
        return this.concertHall;
    }

    private void setConcertHall(String concertHall) {
        if (concertHall.length() <= 10) {
            this.concertHall = concertHall;
        } else {
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
        if (stadiumSectorToUpper == 'A' || stadiumSectorToUpper == 'B' || stadiumSectorToUpper == 'C') {
            this.stadiumSector = stadiumSector;
        } else {
            throw new IllegalArgumentException("Stadium sector should be A, B, or C.");
        }
    }

    public long getTime() {
        return this.time;
    }

    private void setTime(long time) {
        if (time >= 0) {
            this.time = time;
        } else {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
                ", Creation Time=" + creationDate + "]";
    }

    public String getTicketDetails() {
        return this.toString();
    }


}
