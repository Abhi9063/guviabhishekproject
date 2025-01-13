package com.recipewebapp.servlet.recipe;

import com.recipewebapp.dao.RecipeDAO;
import com.recipewebapp.model.Recipe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipe")
public class ViewRecipeServlet extends HttpServlet {
    private final RecipeDAO recipeDAO = new RecipeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int recipeId = Integer.parseInt(request.getParameter("id"));
            Recipe recipe = recipeDAO.getRecipe(recipeId);
            
            if (recipe != null) {
                request.setAttribute("recipe", recipe);
                request.getRequestDispatcher("/WEB-INF/jsp/recipe/view.jsp")
                      .forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}