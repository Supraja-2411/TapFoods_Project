package com.tapfoods.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tapfoods.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Connection con;
		
		 String query = "SELECT imgpath FROM restaurant WHERE restaurantId = ?";
		
		String restaurantId1 = req.getParameter("restaurantId");
		int restaurantId = Integer.parseInt(restaurantId1); 
		 
		 try {

			 Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "root");

	           
		         if(restaurantId != 0) { 
	       
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, restaurantId);

	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                byte[] imgData = rs.getBytes("imgpath");
	                resp.setContentType("image/jpeg");
	                OutputStream out = resp.getOutputStream();
	                out.write(imgData);
	                out.close(); 
	            } else {
	                resp.sendError(HttpServletResponse.SC_NOT_FOUND); // Image not found
	            }
		       }       
		 }
	        catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	}
}

