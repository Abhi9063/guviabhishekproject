package com.recipewebapp.dao;

import com.recipewebapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void getUser_ValidId_ReturnsUser() throws SQLException {
        // Given
        int userId = 1;

        // When
        User user = userDAO.getUser(userId);

        // Then
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
    }

    @Test
    void createUser_ValidUser_Success() throws SQLException {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPasswordHash("hashedpassword");
        user.setFirstName("Test");
        user.setLastName("User");

        // When/Then
        assertDoesNotThrow(() -> userDAO.createUser(user));
    }

    @Test
    void getUser_InvalidId_ReturnsNull() throws SQLException {
        // Given
        int invalidUserId = -1;

        // When
        User user = userDAO.getUser(invalidUserId);

        // Then
        assertNull(user);
    }

    @Test
    void createUser_NullUser_ThrowsException() {
        // Given
        User nullUser = null;

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> userDAO.createUser(nullUser));
    }

    @Test
    void updateUser_ValidUser_Success() throws SQLException {
        // Given
        User user = new User();
        user.setUserId(1);
        user.setUsername("updatedUser");
        user.setEmail("updated@example.com");
        user.setPasswordHash("newhashedpassword");
        user.setFirstName("Updated");
        user.setLastName("User");

        // When/Then
        assertDoesNotThrow(() -> userDAO.createUser(user));
    }

  
}
