package com.recipewebapp.util;

public final class SQLQueries {
    // Recipe queries
    public static final String GET_RECIPE = "SELECT * FROM Recipes WHERE recipe_id = ?";
    public static final String GET_ALL_RECIPES = "SELECT * FROM Recipes ORDER BY created_at DESC";
    public static final String DELETE_RECIPE = "DELETE FROM Recipes WHERE recipe_id = ? AND user_id = ?";
    
    // Category queries
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM Categories ORDER BY category_name";
    public static final String GET_CATEGORY = "SELECT * FROM Categories WHERE category_id = ?";
    
    // User queries
    public static final String GET_USER = "SELECT * FROM Users WHERE user_id = ?";
    public static final String CREATE_USER = "INSERT INTO Users (username, email, password_hash, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
    
    private SQLQueries() {} // Prevent instantiation
}