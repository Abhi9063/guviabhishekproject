package com.recipewebapp.dao.recipe;

import com.recipewebapp.model.Recipe;
import com.recipewebapp.util.DatabaseUtil;
import com.recipewebapp.util.SQLQueries;
import java.sql.*;

public class RecipeCommandDAO {
    public boolean deleteRecipe(int recipeId, int userId) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.DELETE_RECIPE)) {
            
            stmt.setInt(1, recipeId);
            stmt.setInt(2, userId);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public void createRecipe(Recipe recipe) throws SQLException {
        // Implementation for creating a recipe
    }

    public void updateRecipe(Recipe recipe) throws SQLException {
        // Implementation for updating a recipe
    }
}