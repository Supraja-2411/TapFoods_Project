package com.tapfoods.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Menu;
import com.tapfoods.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String restaurantId1 = req.getParameter("restaurantId");
		int restaurantId = Integer.parseInt(restaurantId1);
		
		RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
		Restaurant restaurant = restaurantDAO.getSpecificRestaurant(restaurantId);
		if(restaurant != null) {
			session.setAttribute("restaurantName", restaurant.getRestaurantName());
			
		}
		
		MenuDAO menuDAO = new MenuDAOImpl();
		ArrayList<Menu> menuList = menuDAO.getAllMenu(restaurantId);
		
		
		session.setAttribute("restaurantId", restaurantId);
		session.setAttribute("menuList", menuList);
		resp.sendRedirect("menu.jsp");
		
	}
	

}
