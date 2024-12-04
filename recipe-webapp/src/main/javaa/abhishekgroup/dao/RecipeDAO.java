package abhishekgroup.dao;

import abhishekgroup.model.Recipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RecipeDAO {
    private Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    public int saveRecipe(Recipe recipe) throws SQLException {
        String query = "INSERT INTO Recipes (user_id, title, description, instructions, category_id, prep_time, cook_time, servings, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, recipe.getUserId());
            statement.setString(2, recipe.getTitle());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getInstructions());
            statement.setInt(5, recipe.getCategoryId());
            statement.setInt(6, recipe.getPrepTime());
            statement.setInt(7, recipe.getCookTime());
            statement.setInt(8, recipe.getServings());
            statement.setString(9, recipe.getImageUrl());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;  // If insertion fails
    }

    // Additional methods to interact with the Recipes table (update, delete, etc.)
}
