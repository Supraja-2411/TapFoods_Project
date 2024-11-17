package com.tapfoods.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.daoimpl.OrderTableDAOImpl;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.OrderItem;
import com.tapfoods.model.OrderTable;
import com.tapfoods.model.Restaurant;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/orderTableServlet")
public class OrderTableSevlet extends HttpServlet {
	
	HttpSession session = null;
	private int restaurantId;
	private int userId;
	private double totalAmount;
	private String status;
	private String paymentMode = null;
	private Connection con;
	int itemStatus;
	private String restaurantName;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		con = DBUtils.myDBConnect();
		con.setAutoCommit(false);
		
		session = req.getSession();
		restaurantId =(int)session.getAttribute("restaurantId");
		if(restaurantId != 0) {
			RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
	        Restaurant restaurantList = restaurantDAO.getSpecificRestaurant(restaurantId);
	        if(restaurantList != null) {
	        	
	        	restaurantName =restaurantList.getRestaurantName();
	        }
	        System.out.println("Restaurant fetched for resid="+ restaurantList.getRestaurantName());
		}
		
		User user = (User)session.getAttribute("UserLoginDetails");
		if(user != null) {
			userId = user.getUserId();
		}
		else {
			resp.sendRedirect("login.jsp");
		}
		double subTotal =0.0;
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) {
            	double itemTotal = item.getPrice() * item.getQuantity();
                subTotal += itemTotal; 
            }
		}
		else {
			req.setAttribute("errorMessage", "Your cart is empty. Please add items before confirming the order.");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
		}
		totalAmount  =subTotal * 1.1 ;
                	
	    status ="pending";
		
		paymentMode = req.getParameter("paymentMode");
		System.out.println(paymentMode+" "+status+" "+restaurantId+" "+restaurantName+" "+userId+" "+ totalAmount);
		
		//OrderTable
		OrderTable ot = new OrderTable(restaurantId, userId, totalAmount, status, paymentMode);
		
		OrderTableDAO orderTableDAO = new OrderTableDAOImpl();
		int orderId = orderTableDAO.addOrder(ot);
		System.out.println("Data added"+" "+orderId);
		session.setAttribute("orderId", orderId);
		
		//OrderItem table
		OrderItem oi;
		if(orderId >0) {
			OrderItemDAO oiDAO = new OrderItemDAOImpl();
			for(CartItem item : cart.getItems().values()) {
				oi = new OrderItem(orderId,
									item.getMenuId(),
									item.getQuantity(),
									item.getPrice()*item.getQuantity(),
									item.getMenuName()
									);
				itemStatus = oiDAO.addOrderItem(oi);
				System.out.println("Items inserted SuccessFully");
			}	
		}
		//OrderHistrory table
		if(itemStatus > 0) {
			OrderHistory oh = new OrderHistory(orderId,userId,restaurantName,totalAmount,"Delivered");
			OrderHistoryDAO ohDAO = new OrderHistoryDAOImpl();
			ohDAO.addOrderHistory(oh);
			
			System.out.println("OrderHistory Updated");
			
			// Clear the cart after successful order placement
			session.removeAttribute("cart");
//			
//			// Redirect to order history page
           resp.sendRedirect("orderHistoryServlet");
		}
		con.commit();
		}
		catch(Exception e) {
			if (con != null) {
	            try {
	                con.rollback();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
		e.printStackTrace();
		}
	
		
		
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//		
//		if(status >0) {
//			//insert into OrderItem table
//			OrderItemDAO oiDAO = new OrderItemDAOImpl();
//			
//			for(CartItem item : cart.getItems().values()) {
//				OrderItem oi = new OrderItem(ot.getOrderId(), 
//						        item.getMenuId(),
//						        item.getQuantity(), 
//						        item.getQuantity()*item.getPrice()
//						        );
//				int itemStatus = oiDAO.addOrderItem(oi);
//				System.out.println("OrderItem Insertion Status: " + itemStatus);
//			}
//			// Clear the cart after successful order placement
//			session.removeAttribute("cart");
//			
//			// Redirect to order history page
//            resp.sendRedirect("orderHistoryServlet");
//		}
//		else {
//			System.out.println("Failed to Place the Order!");
//		}
//	}

