package com.tapfoods.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.daoimpl.OrderTableDAOImpl;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Cart;
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


@WebServlet("/orderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	
	HttpSession session;
	private int userId;
	ArrayList<OrderHistory> orderHistoryList;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	// Get the user session
    session = req.getSession();
    User user = (User) session.getAttribute("UserLoginDetails");// Assuming user is stored in session
    
    if(user != null) {
    userId = user.getUserId();
    
    	// Fetch the order history for specific user from the database
        OrderHistoryDAO ohDAO = new OrderHistoryDAOImpl();
        orderHistoryList = ohDAO.getAllHistoryItems(userId);
        //System.out.println("Order history fetched for userid="+userId);
       
        
        session.setAttribute("orderHistoryList", orderHistoryList);
        resp.sendRedirect("orderHistory.jsp");
   
    }
    else {
    	resp.sendRedirect("login.jsp");
    }
   
	}
}
