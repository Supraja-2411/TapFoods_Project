<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.User,java.util.ArrayList,com.tapfoods.model.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tap Foods</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<link rel="stylesheet" href="home.css?">
<link rel="stylesheet" href="footerStyle.css?">
</head>
<body>

  <!-- Header -->
  <%@ include file="header.jsp" %>


    <% ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>) session.getAttribute("restaurantList"); 
    if (restaurantList == null) { 
        response.sendRedirect("getAllRestaurant");
        return;
    }	
	%>


<div class= "row" id ="hero">
	<div class="col-md-5" id="hero-items">
		<h2>Are you Starving?</h2>
		<h4>Choose,Order, TakeOut</h4>
		<p>Get your Cravings delivered...</p>
		<button>Just a click away</button> 
	</div>
	
</div>
				<!-- Restaurant details -->
	  <h5 class="text-center md fw-medium" id="welcome">Welcome to TapFoods!<i class="fas fa-hamburger fa-sm" style="color: #eb7070;"></i> Enjoy Delicious Meals Delivered Fast! </h5>
	  
		<h1 id="restaurant-head">Top Restaurants <i class="fas fa-utensils fa-xs" ></i></h1>	
		
    
    <div class="container">
  <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
    <%  if(restaurantList != null){ 
         for (Restaurant restaurant : restaurantList) { %>
    <div class="col">
      <div class="card h-100" id="restaurant-container">
        <img src="imageServlet?restaurantId=<%= restaurant.getRestaurantId() %>" class="card-img-top" alt="<%= restaurant.getRestaurantName() %>">
        <div class="card-body" id="restaurant-card">
          <div id="restaurant-details">
            <h5 class="card-title"><%= restaurant.getRestaurantName() %></h5>
            <p class="card-text"><%= restaurant.getFoodName() %></p>
            <p class="card-text"><strong>Delivery Time: </strong><%= restaurant.getDeliveryTime() %> mins</p>
            <p class="card-text"><strong>Address: </strong><%= restaurant.getAddress() %></p>
            <p class="card-text" id="rating"><i class="fas fa-star-half-alt"></i> <%= restaurant.getRating() %> stars</p>
            
            <% if (loggedInUser != null) { %>
              <a href="menuServlet?restaurantId=<%=restaurant.getRestaurantId()%>">View Menu</a>
            <% } else { %>
              <a href="login.jsp" >View Menu</a>  
            <% } %>
          </div>
        </div>
      </div>
    </div>
    <% }
       } else { %>
    <div class="col-12">
      <p class="fw-semi-bold">Restaurants are not available at the moment.</p>
    </div>
    <% } %>
  </div>
</div>

<!-- Footer -->
<%@ include file="footer.html" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> 
</body>
</html>