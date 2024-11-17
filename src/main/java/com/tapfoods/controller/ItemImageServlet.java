package com.tapfoods.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/itemImageServlet")
public class ItemImageServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Connection con;
		
		 String query = "SELECT imgpath FROM `menu` WHERE `menuId` = ?";
		
		//String menuId1 = req.getParameter("menuId");
		
		int menuId =Integer.parseInt(req.getParameter("menuId")); 
		
		//System.out.println("Received menuId: " + menuId);  // Log the menuId value
		 
		 try {

			 Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp", "root", "root");

				//System.out.println("Received menuId: " + menuId);  // Log the menuId value

		         if(menuId != 0) { 
	       
	            PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, menuId);

	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                byte[] imgData = rs.getBytes("imgpath");
	               // resp.setContentType("image/jpeg");
	                resp.setContentType("image/avif");

	               
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
