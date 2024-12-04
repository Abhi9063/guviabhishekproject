// Base URL for your API (replace with your actual API endpoint)
const apiUrl = 'http://localhost:8080/api/orders'; 

// Function to create a new order
async function createOrder(event) {
    event.preventDefault(); // Prevent the form from submitting the default way

    const userId = document.getElementById('userId').value;
    const totalAmount = document.getElementById('totalAmount').value;
    const status = document.getElementById('status').value;

    const order = {
        userId: userId,
        totalAmount: totalAmount,
        status: status
    };

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(order)
        });

        const result = await response.json();
        if (response.ok) {
            alert(`Order created with ID: ${result.orderId}`);
            loadOrders(); // Reload the orders after creation
        } else {
            alert('Failed to create order.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error creating order.');
    }
}

// Function to load all orders for a user
async function loadOrders() {
    const userId = document.getElementById('userId').value; // Assume we use userId to fetch orders

    try {
        const response = await fetch(`${apiUrl}?userId=${userId}`);
        const orders = await response.json();
        const ordersList = document.getElementById('ordersList');
        ordersList.innerHTML = ''; // Clear existing orders

        if (orders.length === 0) {
            ordersList.innerHTML = '<p>No orders found.</p>';
        } else {
            orders.forEach(order => {
                const orderItem = document.createElement('div');
                orderItem.classList.add('order-item');
                orderItem.innerHTML = `
                    <p>Order ID: ${order.orderId}</p>
                    <p>Total Amount: $${order.totalAmount}</p>
                    <p>Status: ${order.status}</p>
                    <button onclick="deleteOrder(${order.orderId})">Delete Order</button>
                `;
                ordersList.appendChild(orderItem);
            });
        }
    } catch (error) {
        console.error('Error loading orders:', error);
        alert('Error loading orders.');
    }
}

// Function to delete an order
async function deleteOrder(orderId) {
    try {
        const response = await fetch(`${apiUrl}/${orderId}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Order deleted');
            loadOrders(); // Reload the orders after deletion
        } else {
            alert('Failed to delete order');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error deleting order.');
    }
}

// Load orders when the page loads
document.addEventListener('DOMContentLoaded', () => {
    loadOrders();
});
