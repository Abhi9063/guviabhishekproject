package com.recipewebapp.dao;

import com.recipewebapp.dao.recipe.RecipeQueryDAO;
import com.recipewebapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

class RecipeQueryDAOTest {
    private RecipeQueryDAO recipeDAO;

    @BeforeEach
    void setUp() {
        recipeDAO = new RecipeQueryDAO();
    }

    @Test
    void getRecipe_ValidId_ReturnsRecipe() throws SQLException {
        // Given
        int recipeId = 1;

        // When
        Recipe recipe = recipeDAO.getRecipe(recipeId);

        // Then
        assertNotNull(recipe);
        assertEquals(recipeId, recipe.getRecipeId());
    }

    @Test
    void getRecipe_InvalidId_ReturnsNull() throws SQLException {
        // Given
        int invalidRecipeId = -1;

        // When
        Recipe recipe = recipeDAO.getRecipe(invalidRecipeId);

        // Then
        assertNull(recipe);
    }

    @Test
    void getAllRecipes_ReturnsNonEmptyList() throws SQLException {
        // When
        List<Recipe> recipes = recipeDAO.getAllRecipes();

        // Then
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
    }

    @Test
    void getAllRecipes_EmptyDatabase_ReturnsEmptyList() throws SQLException {
        // This test assumes that the database has been cleared or is empty for the test.
        // When
        List<Recipe> recipes = recipeDAO.getAllRecipes();

        // Then
        assertNotNull(recipes);
        assertTrue(recipes.isEmpty());
    }
}

   