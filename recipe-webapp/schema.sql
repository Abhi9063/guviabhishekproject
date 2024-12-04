CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    profile_picture VARCHAR(255),
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',  -- User roles (e.g., ADMIN or regular USER)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL
);
CREATE TABLE Recipes (
    recipe_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,  -- Foreign key referencing the user who created the recipe
    title VARCHAR(255) NOT NULL,
    description TEXT,
    instructions TEXT NOT NULL,
    category_id INT,  -- Foreign key referencing category
    prep_time INT,  -- Preparation time in minutes
    cook_time INT,  -- Cooking time in minutes
    servings INT,  -- Number of servings
    image_url VARCHAR(255),  -- Optional image URL for the recipe
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);
CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,  -- Foreign key referencing the user who placed the order
    total_amount DECIMAL(10, 2) NOT NULL,  -- Total price for the order
    status ENUM('PENDING', 'COMPLETED', 'CANCELLED', 'SHIPPED') DEFAULT 'PENDING',  -- Status of the order
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp of order creation
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- Timestamp of order update
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
CREATE TABLE Order_Items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,  -- Foreign key referencing the order
    recipe_id INT,  -- Foreign key referencing the recipe if a complete recipe is ordered
    ingredient_id INT,  -- Foreign key referencing the ingredient if ingredients are ordered
    quantity INT NOT NULL,  -- Quantity of the ingredient/recipe ordered
    price DECIMAL(10, 2) NOT NULL,  -- Price of the item at the time of order
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (recipe_id) REFERENCES Recipes(recipe_id),
    FOREIGN KEY (ingredient_id) REFERENCES Ingredients(ingredient_id)
);
