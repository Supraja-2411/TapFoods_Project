<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.tapfoods.model.Menu,com.tapfoods.model.Restaurant, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<link rel="stylesheet" href="searchstyle.css?">



</head>
<body>
<%@ include file="header.jsp" %>
<% ArrayList<Menu> menuItemList = (ArrayList<Menu>)session.getAttribute("menuItemList");
ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>)session.getAttribute("restaurantList");
   %>
<div class="menu-container">
        <% if(menuItemList != null) {
            for(Menu menuItem : menuItemList) { %>
                <div class="menu-item">
                    <% if(restaurantList != null) {
                        for (Restaurant restaurant : restaurantList) { 
                            if(restaurant.getRestaurantId() == menuItem.getRestaurantId()) { %>
                                <div class="d-flex justify-content-between align-items-center mb-2">
                                    <p class="mb-0">By <%= restaurant.getRestaurantName() %></p>
                                    <div class="rating">
                                        <i class="fas fa-star text-warning"></i>
                                        <span>4.2</span>
                                        <span class="delivery-time">20-25 MINS</span>
                                    </div>
                                </div>
                            <% }
                        }
                    } %>
                    
                    <img src="itemImageServlet?menuId=<%= menuItem.getMenuId() %>" 
                         class="item-image border border-4" 
                         alt="<%= menuItem.getMenuName() %>">
                         
                    <div class="item-details">
                        <div class="item-name"><%= menuItem.getMenuName() %></div>
                        <div class="item-price">
                            <span class="price-info">•</span> ₹<%= menuItem.getPrice() %>
                        </div>
                        <div class="item-description"><%= menuItem.getDescription() %></div>
                        <form action="cartServlet?menuId=<%= menuItem.getMenuId() %>" method="post" >
                             
                            <input type="hidden" name="menuId" value="<%= menuItem.getMenuId() %>">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <label for="quantity">Quantity: </label>
                                    <input type="number" name="quantity" value="1" min="1" style="width: 60px;">
                                </div>
                                <input type="hidden" name="action" value="add">
                                <button type="submit" class="add-to-cart">ADD</button>
                            </div>
                        </form>
                        
                    </div>
                </div>
            <% }
        } else { %>
            <div class="no-items">Menu items are not available at the moment.</div>
        <% } %>
    </div>
 

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> 

</body>
</html>