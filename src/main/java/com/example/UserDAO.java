package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence");

    // Method to save a new user
    public void saveUser(User user) {

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {

            System.err.println("Error occurred during the transaction: " + e.getMessage());
            e.printStackTrace();
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

        try(EntityManager em = emf.createEntityManager()) {
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

            e.printStackTrace();
        }
    }
}
