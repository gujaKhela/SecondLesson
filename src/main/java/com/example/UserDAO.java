package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence");

    // Method to save a new user
    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error occurred during the transaction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Method to fetch a user by ID
    public User fetchUserById(int id) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            user = em.find(UserBase.class, id); // Fetch user by ID
        } finally {
            em.close();
        }
        return user;
    }

    // Method to delete a user by ID
    public void deleteUserById(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(UserBase.class, id); // Fetch user to delete
            if (user != null) {
                em.remove(user); // Use remove to delete the user object
                em.getTransaction().commit();
                System.out.println("User with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("No user found with ID " + id);
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback if something goes wrong
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
