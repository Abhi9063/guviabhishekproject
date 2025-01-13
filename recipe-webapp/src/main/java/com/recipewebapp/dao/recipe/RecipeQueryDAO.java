package com.recipewebapp.dao.recipe;

import com.recipewebapp.model.Recipe;
import com.recipewebapp.util.DatabaseUtil;
import com.recipewebapp.util.SQLQueries;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeQueryDAO {
    public Recipe getRecipe(int recipeId) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_RECIPE)) {
            
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapRecipeFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_RECIPES)) {
            
            while (rs.next()) {
                recipes.add(mapRecipeFromResultSet(rs));
            }
        }
        return recipes;
    }

    private Recipe mapRecipeFromResultSet(ResultSet rs) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setUserId(rs.getInt("user_id"));
        recipe.setTitle(rs.getString("title"));
        recipe.setDescription(rs.getString("description"));
        recipe.setInstructions(rs.getString("instructions"));
        recipe.setCategoryId(rs.getInt("category_id"));
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setCookTime(rs.getInt("cook_time"));
        recipe.setServings(rs.getInt("servings"));
        recipe.setImageUrl(rs.getString("image_url"));
        recipe.setCreatedAt(rs.getTimestamp("created_at"));
        recipe.setUpdatedAt(rs.getTimestamp("updated_at"));
        return recipe;
    }
}