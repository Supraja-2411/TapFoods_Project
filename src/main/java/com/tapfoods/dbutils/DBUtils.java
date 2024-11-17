package com.tapfoods.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection con =null;
	private static String url = "jdbc:mysql://localhost:3306/foodapp";
	private static String dbun = "root";
	private static String dbpwd = "root";
	
	public static Connection myDBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbun, dbpwd);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;     
	}

}
