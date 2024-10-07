package com.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "app_user")
public abstract class UserBase implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private String status;

    public UserBase() {}

    public UserBase(String name) {
        this.name = name;
        this.status = "DEACTIVATED";
    }

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Ticket> getTicket() {
        return this.tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setUser(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket); // Remove ticket from the list
        ticket.setUser(null); // Remove the user association
    }
}
