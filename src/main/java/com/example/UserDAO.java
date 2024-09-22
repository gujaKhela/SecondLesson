package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO app_user(name, role) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole());  // Save role (admin/client)
            stmt.executeUpdate();
        }
    }

    public User fetchUserById(int id) throws SQLException {
        String sql = "SELECT * FROM app_user WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");
                int userId = rs.getInt("id");

                if ("admin".equals(role)) {
                    return new Admin(name, userId);
                } else if ("client".equals(role)) {
                    return new Client(name, userId);
                }
            }
        }
        return null;
    }
    public void deleteUserById(int id) throws SQLException {
        String sql = "DELETE FROM app_user WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("No user found with ID " + id);
            }
        }
    }


}
