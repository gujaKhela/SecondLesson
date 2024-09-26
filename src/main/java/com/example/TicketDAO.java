package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class TicketDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence");

    // Method to save a ticket to the database
    public void saveTicket(Ticket ticket) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ticket);
            em.getTransaction().commit();
            System.out.println("Saved new ticket for user ID: " + ticket.getUserId() + " with type: " + ticket.getTicketType());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error saving ticket: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Ticket fetchTicketById(int id) {
        EntityManager em = emf.createEntityManager();
        Ticket ticket = null;
        try {
            ticket = em.find(Ticket.class, id); // Fetch ticket by ID
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
        return ticket; // Return the fetched ticket
    }

    // Method to update the ticket type in the database
    public void updateTicketType(int ticketId, TicketType newTicketType) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Ticket ticket = em.find(Ticket.class, ticketId);

            if (ticket != null) {
                System.out.println("Current ticket type before update: " + ticket.getTicketType());
                ticket.setTicketType(newTicketType); // Update the ticket type
                em.merge(ticket); // Merge the modified ticket to persist changes
                em.getTransaction().commit(); // Commit the transaction
                System.out.println("Updated ticket type to: " + newTicketType);
            } else {
                System.out.println("No ticket found with ID: " + ticketId);
                em.getTransaction().rollback(); // Rollback if no ticket found
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback in case of error
            }
            e.printStackTrace(); // Handle exception
        } finally {
            em.close(); // Ensure EntityManager is closed
        }
    }
}