package com.recipewebapp.dao;

import com.recipewebapp.model.Recipe;
import com.recipewebapp.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    public Recipe getRecipe(int recipeId) throws SQLException {
        String sql = "SELECT * FROM Recipes WHERE recipe_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
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
        return null;
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM Recipes";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
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
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}