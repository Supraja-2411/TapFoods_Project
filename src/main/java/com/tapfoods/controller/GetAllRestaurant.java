package com.tapfoods.controller;

import java.io.IOException;

import java.util.ArrayList;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/getAllRestaurant")
public class GetAllRestaurant extends HttpServlet
{

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
			ArrayList<Restaurant> restaurantList = restaurantDAO.getAllrestaurant();
			
			HttpSession session = req.getSession();
			session.setAttribute("restaurantList", restaurantList);
			resp.sendRedirect("home.jsp");
			
//			 req.setAttribute("restaurantList", restaurantList);
//			 req.getRequestDispatcher("home.jsp").forward(req, resp); 
			
		}
}
