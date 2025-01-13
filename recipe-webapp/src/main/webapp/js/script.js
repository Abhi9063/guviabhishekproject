/**
 * 
 */document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    // Get the input values
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    
    // Simulating login check (simple example)
    if(username === "admin" && password === "password123") {
        // Redirect to recipe page
        window.location.href = "recipe.html";
    } else {
        alert("Invalid username or password");
    }
});

document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    let newUsername = document.getElementById("newUsername").value;
    let newPassword = document.getElementById("newPassword").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if(newPassword !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }
    
    // Here you can add logic to store the registration data.
    alert("Registration successful! Please login.");
    window.location.href = "login.html"; // Redirect to login page
});
