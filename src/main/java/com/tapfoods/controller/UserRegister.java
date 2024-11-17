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


@WebServlet("/userRegister")
public class UserRegister extends HttpServlet  {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String phonenumber = req.getParameter("phonenumber");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		String address = req.getParameter("address");
		
		if(password.equals(cpassword)) {
			User u =new User(userName, email, phonenumber, password, address);
			
			UserDAO userDAO = new UserDAOImpl();
			int status = userDAO.addUser(u);
			
			if(status==1) {
				resp.sendRedirect("home.jsp");
			}
			else {
				resp.sendRedirect("failure.jsp");
			}
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
		
		
	}
	

}
