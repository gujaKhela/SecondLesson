package com.example;


public class Admin implements User {

    private String name;
    private int id;


    public Admin(String name, int id) {
        this.name=name;
        this.id=id;
    }

    public Admin() {};

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return "admin";
    }

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
