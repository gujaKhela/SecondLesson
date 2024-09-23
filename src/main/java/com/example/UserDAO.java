package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SAVE_USER_SQL= "INSERT INTO app_user(name, role) VALUES (?, ?)";
    private static final String FETCH_USER_SQL= "SELECT * FROM app_user WHERE id=?";
    private static final String DELETE_USER_SQL = "DELETE FROM app_user WHERE id = ?";

    public void saveUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SAVE_USER_SQL)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole());  // Save role (admin/client)
            stmt.executeUpdate();
        }
    }

    public User fetchUserById(int id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FETCH_USER_SQL)) {
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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER_SQL)) {
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
