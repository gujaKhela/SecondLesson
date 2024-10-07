package com.example;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceRepo {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Value("${app.allow.user.update}")
    private boolean allowUserUpdate;


    public void saveUser(UserBase user) {
        userRepository.save(user);
    }

    public void addTicketToUser(int userId, Ticket ticket) {
        UserBase user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.addTicket(ticket); // Add the ticket to the user
            userRepository.save(user); // Save the updated user with the new ticket
        }
    }

    public UserBase fetchUserById(int id) {
        Optional<UserBase> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();  // This might return a Client or Admin instance
        } else {
            throw new RuntimeException("User with ID " + id + " not found.");
        }
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<UserBase> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(int id, UserBase updatedUser) {
        if (!allowUserUpdate) {
            throw new UnsupportedOperationException("User update is disabled.");
        }
        UserBase user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            userRepository.save(user);
        }
    }

    // New method to activate user and create a ticket
    @Transactional
    public void activateUserAndCreateTicket(int userId, Ticket ticket) {
        UserBase user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // Optional: throw an exception if not found

        user.setStatus("ACTIVATED"); // Update the user status
        user.addTicket(ticket); // Add ticket to user and set user for the ticket
        userRepository.save(user); // Save the user, tickets are automatically persisted due to Cascade.ALL
    }
}
