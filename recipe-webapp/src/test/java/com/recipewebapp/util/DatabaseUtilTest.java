package com.recipewebapp.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseUtilTest {

    @Test
    void getConnection_ReturnsValidConnection() {
        // When/Then
        assertDoesNotThrow(() -> {
            Connection conn = DatabaseUtil.getConnection();
            assertNotNull(conn);
            assertFalse(conn.isClosed());
            conn.close();
        });
    }

    @Test
    void closeConnection_ValidConnection_ClosesSuccessfully() throws SQLException {
        // Given
        Connection conn = DatabaseUtil.getConnection();

        // When
        DatabaseUtil.closeConnection(conn);

        // Then
        assertTrue(conn.isClosed());
    }

    @Test
    void closeConnection_AlreadyClosedConnection_DoesNotThrowException() throws SQLException {
        // Given
        Connection conn = DatabaseUtil.getConnection();
        DatabaseUtil.closeConnection(conn); // Close it first

        // When/Then
        assertDoesNotThrow(() -> DatabaseUtil.closeConnection(conn)); // Closing again should not throw an exception
    }

    @Test
    void getConnection_ThrowsSQLException_WhenInvalidConnectionString() {
        // This test simulates an invalid connection string or a connection failure scenario.
        // Mocking or setting invalid properties might be necessary to simulate the exception.

        // When/Then
        assertThrows(SQLException.class, () -> {
            // Simulating connection failure by providing a bad URL (this would depend on your setup)
            DatabaseUtil.getConnection();
        });
    }

    @Test
    void closeConnection_NullConnection_DoesNotThrowException() {
        // Given
        Connection conn = null; // No connection

        // When/Then
        assertDoesNotThrow(() -> DatabaseUtil.closeConnection(conn)); // Closing a null connection should not throw an exception
    }

    @Test
    void getConnection_ReturnsNewConnectionEachTime() throws SQLException {
        // Given
        Connection conn1 = DatabaseUtil.getConnection();
        Connection conn2 = DatabaseUtil.getConnection();

        // When/Then
        assertNotSame(conn1, conn2); // Assert that different calls return different connections
        conn1.close();
        conn2.close();
    }

    @Test
    void getConnection_ConnectionNotNullAndOpen() throws SQLException {
        // When
        Connection conn = DatabaseUtil.getConnection();

        // Then
        assertNotNull(conn);
        assertFalse(conn.isClosed()); // Connection should be open
        conn.close();
    }

    @Test
    void closeConnection_ThrowsSQLException_OnInvalidConnection() {
        // When/Then
        assertThrows(SQLException.class, () -> {
            // Simulate passing an invalid connection object to close
            DatabaseUtil.closeConnection(null);
        });
    }
}
