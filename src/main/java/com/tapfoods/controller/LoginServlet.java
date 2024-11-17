package com.tapfoods.controller;

import java.io.IOException;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email =req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUser(email);
		
		if(user!= null && user.getPassword().equals(password)) {
			
			HttpSession session = req.getSession();
			session.setAttribute("UserLoginDetails", user);
			
			resp.sendRedirect("getAllRestaurant");
		}
		else {
			req.setAttribute("errorMessage","Invalid Email or Password");														
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
