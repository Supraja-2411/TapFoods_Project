<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tapfoods.model.OrderHistory,com.tapfoods.model.Restaurant,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<link rel="stylesheet" href="orderHistoryStyle.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
 
</head>
<body>


<div class="container mt-5 ">
        <h1 class="mb-4 text-decoration-underline" >Order History</h1>
        
        <div class="vstack gap-3 border border-secondary border-4 ">
      
                <table class="table">
                    <thead>
                        <tr>
                          <th>RestaurantName</th>
                          <th>Order Id</th>
                          <th>Ordered Date</th>
                          <th>Total Amount</th>
                          <th>Status</th>
                       </tr>
                    </thead>
                    <tbody>
	 <% 
        ArrayList<OrderHistory> orderHistory = (ArrayList<OrderHistory>)session.getAttribute("orderHistoryList");
            if (orderHistory != null ) {
                for (OrderHistory order : orderHistory) { 
     %>
       
                    <tr>
                    <td>
                     <div class="d-flex align-items-center ">
                           <div>
                               <h5 class="mb-0"><%= order.getRestaurantName() %></h5>
                           </div>
                        </div>
                    </td>
                        <td><%= order.getOrderId() %></td> 
                        <td><%= order.getOrderDate() %></td>
                       <td>₹<%= String.format("%.2f", order.getTotalAmount()) %></td>
                       <td><%= order.getStatus() %></td>
                       <td><a href="orderItemServlet?orderId=<%= order.getOrderId() %>">View Details</a></td>
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
          
           <p class="fs-4 text-center"><a href="home.jsp" class="link-secondary link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover">
              <i class="fas fa-angle-double-left"></i> Go back</a></p>
           </div>
              
    
           
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 
</body>
</html>