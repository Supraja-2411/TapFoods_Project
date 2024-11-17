<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tapfoods.model.Cart,com.tapfoods.model.CartItem, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="cartStyle.css?">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
 <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
 
 
    <div class="container mt-5">
        <h1 class="mb-4 " >Cart Items(<%= session.getAttribute("cart") != null ? ((Cart)session.getAttribute("cart")).getItems().size() : 0 %> items)</h1>
        
        <!-- Error Message Display -->
    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { 
    %>
        <div class="alert alert-danger text-center" role="alert">
            <%= errorMessage %>
        </div>
    <% } %>
        
        <div class="row ">
            <div class="col-md-8 ">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>SubTotal</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        Cart cart = (Cart)session.getAttribute("cart");
                        double subtotal = 0.0;
                        if(cart != null && !cart.getItems().isEmpty()) {
                            for (CartItem item : cart.getItems().values()) {
                            	double itemTotal = item.getPrice() * item.getQuantity();
                                subtotal += itemTotal; 
                        %>
                       
    
                        
                        <tr>
                            <td>
                                <div class="d-flex align-items-center ">
                                    <div>
                                        <h5 class="mb-0"><%= item.getMenuName() %></h5>
                                    </div>
                                </div>
                            </td>
                            <td>₹<%= String.format("%.2f", item.getPrice()) %></td>
                            <td>
                                <form action="cartServlet" method="post" class="d-flex align-items-center">
                                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" class="form-control form-control-sm mx-2" style="width: 60px;">
                                    <button type="submit" name="action" value="update" class="btn btn-sm btn-outline-secondary">Update</button>
                                </form>
                            </td>
                            <td>₹<%= String.format("%.2f", item.getQuantity() * item.getPrice()) %></td>
                            <td>
                                <form action="cartServlet" method="post">
                                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                                    <button type="submit" name="action" value="remove" class="btn btn-sm btn-outline-danger"><i class="fas fa-trash-alt"></i></button>
                                </form>
                            </td>
                        </tr>
                         
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">Your cart is empty!</td>
                        </tr>
                        <% } %>
                         <tr>
                            <td colspan="5" class="text-center">
                            <a href="menu.jsp">Add More</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
 
           <div class="col-md-4">
                	<div class="card ">
                    <div class="card-body bs-danger-border-subtle">
                        <h5 class="card-title">Order Summary</h5>
                        <dl class="row">
                            <dt class="col-sm-8">SubTotal:</dt>
                            <dd class="col-sm-4 text-end">₹<%= String.format("%.2f", cart != null ? subtotal : 0) %></dd>
                            
                            <dt class="col-sm-8">GST:</dt>
                            <dd class="col-sm-4 text-end">₹<%= String.format("%.2f", cart != null ? subtotal * 0.1 : 0) %></dd>
                        </dl>
                        <hr>
                        <dl class="row">
                            <dt class="col-sm-8">Grand total:</dt>
                            <dd class="col-sm-4 text-end"><u><strong>₹<%= String.format("%.2f", cart != null ? subtotal * 1.1 : 0) %></strong></u></dd>
                        </dl>
                        
                        <a href="checkOut.jsp"><button class="btn btn-primary w-100">Continue</button></a>
                        
                    </div>
                </div>
            </div>
        
        </div>
    </div>
 
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  
</body>
</head>
</html>