package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class TicketDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence");

    // Method to save a ticket to the database
    public void saveTicket(Ticket ticket) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(ticket);
            em.getTransaction().commit();
            System.out.println("Saved new ticket for user ID: " + ticket.getUserId() + " with type: " + ticket.getTicketType());
        } catch (Exception e) {
                System.err.println("Error saving ticket: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public Ticket fetchTicketById(int id) {

        Ticket ticket = null;
        try(EntityManager em = emf.createEntityManager()) {
            ticket = em.find(Ticket.class, id);
        }
        return ticket;
    }

    // Method to update the ticket type in the database
    public void updateTicketType(int ticketId, TicketType newTicketType) {

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Ticket ticket = em.find(Ticket.class, ticketId);

            if (ticket != null) {
                System.out.println("Current ticket type before update: " + ticket.getTicketType());
                ticket.setTicketType(newTicketType);
                em.merge(ticket);
                em.getTransaction().commit();
                System.out.println("Updated ticket type to: " + newTicketType);
            } else {
                System.out.println("No ticket found with ID: " + ticketId);
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}