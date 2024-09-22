package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDAO {

    // Method to save a ticket to the database
    public void saveTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Ticket (user_id, ticket_type) VALUES (?, ?::ticket_type)";  // Cast to enum type
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getUserId());
            stmt.setString(2, ticket.getTicketType().name());  // Make sure to match enum name

            stmt.executeUpdate();
        }
    }


    // Method to fetch a ticket by its ID
    public Ticket fetchTicketById(int id) throws SQLException {
        String sql = "SELECT * FROM Ticket WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Set the ID to fetch
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Assuming your Ticket class constructor only needs user_id and ticket_type
                return new Ticket(
                        rs.getInt("user_id"),  // Get user_id from result set
                        TicketType.valueOf(rs.getString("ticket_type"))  // Get ticket_type and convert to enum
                );
            }
            return null;  // If no ticket is found, return null
        }
    }

    // Method to update the ticket type in the database
    public void updateTicketType(int ticketId, TicketType newTicketType) throws SQLException {
        String sql = "UPDATE Ticket SET ticket_type = ?::ticket_type WHERE id = ?"; // Casting to ticket_type
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTicketType.name());  // Set the new ticket type
            stmt.setInt(2, ticketId);  // Set the ID of the ticket to update

            stmt.executeUpdate();  // Execute the update
        }
    }
}
