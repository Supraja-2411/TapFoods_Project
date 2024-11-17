package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.User;


public class UserDAOImpl implements UserDAO {
	
	private Connection con = null;;
	private PreparedStatement pstmt = null;
	private Statement stmt;
	private int status;
	private ResultSet resultSet;
	ArrayList<User> userList =new ArrayList<User>();
	private User user;
	
	private static final String ADD_USER = "insert into `user`(`userName`,`email`,`phonenumber`,`password`,`address`) values(?,?,?,?,?)";
	private static final String SELECT_ALL_USERS = "select * from `user` ";
	private static final String SELECT_USER_BY_EMAIL = "select * from `user` where `email` =? ";
	private static final String UPDATE_USER = "update `user` set `userName`=?, `phonenumber`=?, `password`=?, `address`=? where `email` =?";
	private static final String DELETE_USER = "delete from `user` where `email`=? "; 
	
	//constructor
	public UserDAOImpl() {
		try {
			con = DBUtils.myDBConnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addUser(User u) {
		try {
			pstmt = con.prepareStatement(ADD_USER);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhonenumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			
			status = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL_USERS);
			
			userList = extractDataFromResultSet(resultSet);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
		
	}

	@Override
	public User getUser(String email) {
		try {
			pstmt = con.prepareStatement(SELECT_USER_BY_EMAIL);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			
			userList = extractDataFromResultSet(resultSet);
			user = userList.get(0);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUser(User u) {
		try {
			pstmt = con.prepareStatement(UPDATE_USER);
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getPhonenumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getEmail());
			
			status = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public int deleteUser(String email) {
		try {
			pstmt=con.prepareStatement(DELETE_USER);
			pstmt.setString(1, email);
			status = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<User> extractDataFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next() == true) {
				userList.add(new User(resultSet.getInt("userId"),
				resultSet.getString("userName"),
				resultSet.getString("email"),
				resultSet.getString("phonenumber"),
				resultSet.getString("password"),
				resultSet.getString("address")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
