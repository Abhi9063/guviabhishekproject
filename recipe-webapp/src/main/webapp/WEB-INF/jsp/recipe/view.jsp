<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="${recipe.title}" scope="request"/>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/navigation.jsp" %>

<div class="container py-5">
    <div class="row">
        <div class="col-md-8">
            <h1 class="mb-4">${recipe.title}</h1>
            <div class="mb-4">
                <c:if test="${not empty recipe.imageUrl}">
                    <img src="${recipe.imageUrl}" alt="${recipe.title}" class="img-fluid rounded">
                </c:if>
            </div>
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Description</h5>
                    <p class="card-text">${recipe.description}</p>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Instructions</h5>
                    <p class="card-text">${recipe.instructions}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title">Recipe Details</h5>
                    <ul class="list-unstyled">
                        <li><strong>Prep Time:</strong> ${recipe.prepTime} minutes</li>
                        <li><strong>Cook Time:</strong> ${recipe.cookTime} minutes</li>
                        <li><strong>Servings:</strong> ${recipe.servings}</li>
                        <li><strong>Category:</strong> ${recipe.categoryName}</li>
                    </ul>
                </div>
            </div>
            <c:if test="${sessionScope.user.userId eq recipe.userId}">
                <div class="d-grid gap-2">
                    <a href="${pageContext.request.contextPath}/recipe/edit?id=${recipe.recipeId}" 
                       class="btn btn-primary">Edit Recipe</a>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" 
                            data-bs-target="#deleteModal">Delete Recipe</button>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this recipe?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <form action="${pageContext.request.contextPath}/recipe/delete" method="post">
                    <input type="hidden" name="recipeId" value="${recipe.recipeId}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>