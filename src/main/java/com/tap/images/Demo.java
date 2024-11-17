package com.tap.images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) {
		 Connection con =null;
		 String url = "jdbc:mysql://localhost:3306/foodapp";
		String un = "root";
		String pwd = "root";
		PreparedStatement pstmt;
		
		try {
			File file = new File("C:\\Users\\supra\\eclipse-workspace\\MVNTapFoods\\images\\rest4.jpg");
			FileInputStream fis = new FileInputStream(file);
			//byte[] image = new byte[fis.available()];
			byte[] image = new byte[(int) file.length()];
			fis.read(image);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			
			String query = "update restaurant set imgpath=? where restaurantId=?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(2, 20);
			
			pstmt.setBytes(1, image);
			int x = pstmt.executeUpdate();

			if (x > 0) {
			    System.out.println("Image successfully updated!");
			} else {
			    System.out.println("No rows were updated. Check menuId or query.");
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
