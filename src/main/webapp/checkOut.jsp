<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.tapfoods.model.Cart,com.tapfoods.model.CartItem, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckOut Page</title>
<link rel="stylesheet" href="cartStyle.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
 
</head>
<body>


<div class="container mt-5">
        <h1 class="mb-4 " >Order Summary(<%= session.getAttribute("cart") != null ? ((Cart)session.getAttribute("cart")).getItems().size() : 0 %> items)</h1>
        
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
                            <td><%= item.getQuantity() %></td>
                            <td>₹<%= String.format("%.2f", item.getQuantity() * item.getPrice()) %></td>
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
                            <td colspan="5" class="text-center"> <a class="link-secondary link-offset-2 link-underline link-underline-opacity-0" 
                            href="cart.jsp"><i class="fas fa-angle-double-left"></i> Go back</a> </td>
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
                            
                            <dt class="col-sm-8">Coupon Code:</dt>
                            <dd class="col-sm-4 text-end"><a href="#" class="text-decoration-none">Add Coupon</a></dd>
                        </dl>
                        <hr>
                        <dl class="row">
                            <dt class="col-sm-8">Grand total:</dt>
                            <dd class="col-sm-4 text-end"><u><strong>₹<%= String.format("%.2f", cart != null ? subtotal * 1.1 : 0) %></strong></u></dd>
                        </dl>
                        
                        
                        
                        <!-- Payment Methods Section -->
                       <form action="orderTableServlet" method="POST"> 
							<label for="paymentMode" class="form-label">Payment</label>
                            <select class="form-select btn-outline-dark w-100 mb-3" id="paymentMode" name="paymentMode" required>
                           <option selected disabled value="">Payment Mode...</option>
      					 <option value="PhonePay">PhonePay</option>
  						 <option value="CashOnDelivery">CashOnDelivery</option>
                         <option value="GooglePay">GooglePay</option>
                       </select>
                       
                     
             <div class="mb-3 was-validated">
  					<label for="address" class="form-label fw-semibold">Shipping Address</label>
  					<textarea class="form-control" id="address" placeholder="Enter Your Delivery Address" rows="3"  required></textarea>
  					<div class="invalid-feedback">
                      Please enter your Current Address.
                    </div>
			</div>
			 <button type="submit" class="btn btn-primary w-100 data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        Confirm Order
    </button>
    </form>
          
         
         
      
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" tabindex="-1" data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5 text-success fw-monospace" id="staticBackdropLabel"><i class="fas fa-check-double"></i>  Order Placed!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       Thank you for Ordering!
      </div>
      <div class="modal-footer">
        <a href="orderHistory.jsp"></a><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">View Orders</button>
        <a href="home.jsp"><button type="button" class="btn btn-primary">Back to Home</button></a>
      </div>
    </div>
  </div>
</div>
                        
                    </div>
                    </div>
            </div>
        </div>
        
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
 
</body>
</html>