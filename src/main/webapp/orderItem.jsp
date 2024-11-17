<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.tapfoods.model.OrderItem,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Items</title>
<link rel="stylesheet" href="orderItemStyle.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
<div class="container mt-5 ">
        <h1 class="mb-4 text-decoration-underline" >Order Items</h1>
        
        <div class="vstack gap-3 border border-secondary border-4 ">
      
                <table class="table">
                    <thead>
                        <tr>
                          <th>Order Id</th>
                          <th>Menu Id</th>
                          <th>Menu Name</th>
                          <th>Quantity</th>
                          <th>Sub total</th>
                       </tr>
                    </thead>
                    <tbody>

     <% ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>)session.getAttribute("orderItemList");  
     if (orderItems != null ) {
         for (OrderItem orderitem : orderItems) { 
     %>
     	<tr>
             <td>
                <div class="d-flex align-items-center ">
                <div> <h5 class="mb-0"><%= orderitem.getOrderId() %></h5> </div>
                        </div>
                    </td>
                        
                        <td><%= orderitem.getMenuId()%></td>
                        <td><%= orderitem.getMenuName()%></td>
                       <td><%= orderitem.getQuantity() %></td>
                       <td>₹<%= String.format("%.2f", orderitem.getSubTotal()) %></td>
                       </tr>
        <%
            }} else { %>
               <tr>
                   <td colspan="5" class="text-center">No Orders found!</td>
               </tr>
           <% }%>
     </tbody>
     </table>
     </div>
     </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 
</body>
</html>