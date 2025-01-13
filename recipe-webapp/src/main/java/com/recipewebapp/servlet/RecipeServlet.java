package com.recipewebapp.servlet;

import com.recipewebapp.dao.RecipeDAO;
import com.recipewebapp.model.Recipe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recipes")
public class RecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO = new RecipeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeDAO.getAllRecipes();
            request.setAttribute("recipes", recipes);
            request.getRequestDispatcher("/WEB-INF/jsp/recipes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}