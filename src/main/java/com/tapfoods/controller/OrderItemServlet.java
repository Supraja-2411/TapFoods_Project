package com.tapfoods.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/orderItemServlet")
public class OrderItemServlet extends HttpServlet {
	
	HttpSession session;	
	ArrayList<OrderItem> orderItemList;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 session = req.getSession();
		
		String oId = req.getParameter("orderId");
		int orderId =Integer.parseInt(oId);
		//System.out.println("OrderId is ="+orderId);
		
		OrderItemDAO oiDAO = new OrderItemDAOImpl();
		orderItemList = oiDAO.getSpecificOrderItem(orderId);
		//System.out.println("Orderitems fetched successfully...");
		
		
		
		
		 session.setAttribute("orderItemList", orderItemList);
	        resp.sendRedirect("orderItem.jsp");
		
		
	}

}
