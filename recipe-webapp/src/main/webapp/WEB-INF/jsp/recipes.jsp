<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Recipes" scope="request"/>
<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Recipes</h2>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/recipe/new" class="btn btn-primary">
                <i class="bi bi-plus-lg"></i> Add Recipe
            </a>
        </c:if>
    </div>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach items="${recipes}" var="recipe">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <c:if test="${not empty recipe.imageUrl}">
                        <img src="${recipe.imageUrl}" class="card-img-top" alt="${recipe.title}" 
                             style="height: 200px; object-fit: cover;">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title">${recipe.title}</h5>
                        <p class="card-text">${recipe.description}</p>
                        <div class="d-flex justify-content-between align-items-center text-muted small mb-3">
                            <span><i class="bi bi-clock"></i> Prep: ${recipe.prepTime}m</span>
                            <span><i class="bi bi-fire"></i> Cook: ${recipe.cookTime}m</span>
                            <span><i class="bi bi-people"></i> Serves: ${recipe.servings}</span>
                        </div>
                        <div class="d-grid">
                            <a href="${pageContext.request.contextPath}/recipe?id=${recipe.recipeId}" 
                               class="btn btn-outline-primary">View Recipe</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="common/footer.jsp" %>