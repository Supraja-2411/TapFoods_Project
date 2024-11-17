<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tapfoods.model.Menu,com.tapfoods.model.Restaurant,com.tapfoods.model.Cart,com.tapfoods.model.CartItem, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Restaurant Menu</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<link rel="stylesheet" href="menuCardStyle.css">
<link rel="stylesheet" href="footerStyle.css">


</head>
<body>
<!-- Header -->


<%
    ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");
    String restaurantName = (String) session.getAttribute("restaurantName");
    Cart cart = (Cart) session.getAttribute("cart");
   
    int cartCount = 0;
    if(cart != null && cart.getItems() != null) {
        for(CartItem item : cart.getItems().values()) {
            cartCount += item.getQuantity();
        }
    }
   %>

<!-- Cart Icon with Notification -->
<div class="cart-wrapper" onclick="window.location.href='cart.jsp'">
    <i class="fas fa-shopping-cart fa-lg"></i>
    <span id="cart-count" class="cart-notification" data-count="<%= cartCount %>"><%= cartCount %></span>
</div>

<div class="menu-container">
    <h2 class="order-summary text-center"><%= restaurantName %></h2>
    
    <% if(menuList != null && !menuList.isEmpty()) {
        for(Menu menu : menuList) { %>
    <div class="menu-item">
        <img src="itemImageServlet?menuId=<%= menu.getMenuId() %>" class="item-image border border-4" alt="<%= menu.getMenuName() %>">
        <div class="item-details">
            <div class="item-name"><%= menu.getMenuName() %></div>
            <div class="item-price"><span class="price-info">• </span><%= menu.getPrice() %> ₹</div>
            <div class="item-description"><%= menu.getDescription() %></div>
            
            <form class="add-to-cart-form" data-menu-id="<%= menu.getMenuId() %>" 
                  data-menu-name="<%= menu.getMenuName() %>" 
                  data-menu-price="<%= menu.getPrice() %>">
                <input type="number" name="quantity" value="1" min="1" class="quantity-input" style="width: 50px;">
                <button type="button" class="add-to-cart">Add to Cart</button>  
            </form>
        </div>
    </div>
    <% }
    } else { %>
        <div class="no-items">Menu items are not available at the moment.</div>
    <% } %>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const cartCount = document.getElementById('cart-count');
    const buttons = document.querySelectorAll('.add-to-cart');
    
    buttons.forEach(button => {
        button.addEventListener('click', function(e) {
            const form = e.target.closest('.add-to-cart-form');
            const menuId = form.getAttribute('data-menu-id');
            const quantity = form.querySelector('.quantity-input').value;
            
            addToCart(menuId, quantity, e.target);
        });
    });
    
    function addToCart(menuId, quantity, buttonElement) {
        // Disable button while processing
        buttonElement.disabled = true;
        
        fetch('cartServlet?action=add&menuId=' + menuId + '&quantity=' + quantity, {
            method: 'POST',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                // Update cart count
                cartCount.setAttribute('data-count', data.cartCount);
                cartCount.textContent = data.cartCount;
                
                // Show success message
                showNotification(data.message, 'success');
                
                // Animate item to cart
                const menuItem = buttonElement.closest('.menu-item');
                const img = menuItem.querySelector('.item-image');
                animateAddToCart(img);
            } else {
                // Show error message
                showNotification(data.message, 'error');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Failed to add item to cart', 'error');
        })
        .finally(() => {
            // Re-enable button
            buttonElement.disabled = false;
        });
    }
    
    function showNotification(message, type = 'success') {
        // Remove any existing notifications
        const existingNotifications = document.querySelectorAll('.notification');
        existingNotifications.forEach(notification => notification.remove());
        
        const notification = document.createElement('div');
        notification.className = `notification ${type}`;
        notification.textContent = message;
        
        // Style the notification
        Object.assign(notification.style, {
            position: 'fixed',
            top: '20px',
            left: '50%',
            transform: 'translateX(-50%)',
            padding: '12px 24px',
            borderRadius: '4px',
            backgroundColor: type === 'success' ? '#4CAF50' : '#ff4444',
            color: 'white',
            zIndex: '1001',
            boxShadow: '0 2px 5px rgba(0,0,0,0.2)',
            transition: 'opacity 0.3s ease-in-out',
            textAlign: 'center',
            maxWidth: '80%',
            wordWrap: 'break-word'
        });
        
        document.body.appendChild(notification);
        
        // Fade out and remove after 3 seconds
        setTimeout(() => {
            notification.style.opacity = '0';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }
    
    function animateAddToCart(img) {
        const clone = img.cloneNode(true);
        const cart = document.querySelector('.cart-wrapper');
        
        // Get positions
        const startPos = img.getBoundingClientRect();
        const endPos = cart.getBoundingClientRect();
        
        // Style the clone
        Object.assign(clone.style, {
            position: 'fixed',
            height: '50px',
            width: '50px',
            top: startPos.top + 'px',
            left: startPos.left + 'px',
            transition: 'all 0.8s cubic-bezier(0.25, 0.46, 0.45, 0.94)',
            zIndex: '1000',
            borderRadius: '50%',
            boxShadow: '0 2px 5px rgba(0,0,0,0.2)'
        });
        
        document.body.appendChild(clone);
        
        // Trigger animation
        requestAnimationFrame(() => {
            Object.assign(clone.style, {
                top: endPos.top + 'px',
                left: endPos.left + 'px',
                opacity: '0',
                transform: 'scale(0.1)'
            });
        });
        
        // Remove clone after animation
        setTimeout(() => clone.remove(), 800);
    }
});
</script>

  
 

	
<%@ include file="footer.html" %>	  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> 

</body>
</html>



