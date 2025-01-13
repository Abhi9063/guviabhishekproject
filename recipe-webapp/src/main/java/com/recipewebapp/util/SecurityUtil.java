package com.recipewebapp.util;

import javax.servlet.http.HttpServletRequest;
import com.recipewebapp.model.User;

public final class SecurityUtil {
    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }
    
    public static boolean isAuthorized(HttpServletRequest request, int resourceUserId) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null && (user.getUserId() == resourceUserId || "ADMIN".equals(user.getRole()));
    }
    
    private SecurityUtil() {} // Prevent instantiation
}