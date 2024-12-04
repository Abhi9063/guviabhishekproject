package abhishekgroup.dao;

import abhishekgroup.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connection;

    // Constructor with JDBC connection setup
    public OrderDAO() throws SQLException {
        // Initialize the connection here using your database credentials
        String url = "jdbc:mysql://localhost:3306/your_database";  // Replace with your database URL
        String username = "your_username";  // Replace with your DB username
        String password = "your_password";  // Replace with your DB password
        this.connection = DriverManager.getConnection(url, username, password);
    }

    // Method to save a new order
    public int saveOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (user_id, total_amount, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getUserId());
            statement.setDouble(2, order.getTotalAmount());
            statement.setString(3, order.getStatus());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  // Return the order ID of the newly created order
                }
            }
        }
        return -1;  // Return -1 if insertion fails
    }

    // Method to update the status of an order
    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
        String query = "UPDATE Orders SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, orderId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Return true if update was successful
        }
    }

    // Method to retrieve an order by its ID
    public Order getOrderById(int orderId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setCreatedAt(resultSet.getTimestamp("created_at"));
                order.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                return order;
            }
        }
        return null;  // Return null if order not found
    }

    // Method to retrieve all orders for a specific user
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE user_id = ? ORDER BY created_at DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setCreatedAt(resultSet.getTimestamp("created_at"));
                order.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                orders.add(order);
            }
        }
        return orders;  // Return the list of orders for the user
    }

    // Method to retrieve all orders (can be useful for admins)
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders ORDER BY created_at DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setCreatedAt(resultSet.getTimestamp("created_at"));
                order.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                orders.add(order);
            }
        }
        return orders;  // Return the list of all orders
    }

    // Method to delete an order (if needed)
    public boolean deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Return true if deletion was successful
        }
    }

    // Method to close the database connection (important to avoid memory leaks)
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
