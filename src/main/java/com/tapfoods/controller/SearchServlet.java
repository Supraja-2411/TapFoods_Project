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

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet{
	
	private Restaurant restaurant;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ArrayList<Menu> menuItemList ;
		ArrayList<Restaurant> restaurantList;
		
        String menuName = req.getParameter("menuName");
     
        if(menuName != null) {
        MenuDAO menuDAO = new MenuDAOImpl();
		menuItemList = menuDAO.getAllMenuItems(menuName);
	
			RestaurantDAO rDAO = new RestaurantDAOImpl();
			restaurantList = rDAO.getAllrestaurant();
			
			session.setAttribute("restaurantList",restaurantList);
		    session.setAttribute("menuItemList", menuItemList);
			
		}
		
		resp.sendRedirect("search.jsp");
		
		
        }

}
