package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Restaurant;


public class RestaurantDAOImpl implements RestaurantDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet resultSet;
	ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	private int status;
	private Restaurant restaurant;
	
	
	private static final String ADD_RESTAURANT = "insert into `restaurant`(`restaurantName`,`foodName`,`deliveryTime`,`address`,`imgpath`,`isActive`,`rating`)"
			+ "values(?,?,?,?,?,?)";
	private static final String SELECT_ALL_RESTAURANT = "select * from `restaurant`";
	private static final String SELECT_ON_RESTAURANTID = "select * from `restaurant` where `restaurantId`=?";
	private static final String UPDATE_ON_RESTAURANTNAME = "update `restaurant` set `restaurantName`=?,'foodType'=?,'deliveryTime'=?,'address'=?,"
			+ "'rating'=?,'isActive'=? where `restaurantId`=?";        
	private static final String DELETE_ON_RESTURANT_NAME = "delete from `restaurant` where `resturantName`=?"; 
	
	public RestaurantDAOImpl() {
		try {
			con = DBUtils.myDBConnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addRestaurant(Restaurant r) {
		try {
			pstmt = con.prepareStatement(ADD_RESTAURANT);
			pstmt.setString(1, r.getRestaurantName());
			pstmt.setString(2, r.getFoodName());
			pstmt.setInt(3, r.getDeliveryTime());
			pstmt.setString(4, r.getAddress());
			pstmt.setString(5, r.getImgpath());
			pstmt.setBoolean(6, r.isActive());
			pstmt.setDouble(7, r.getRating());
			
			status = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Restaurant> getAllrestaurant() {
		try {
			stmt  = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL_RESTAURANT);
			restaurantList = extractDataFromResultSet(resultSet);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

	@Override
	public Restaurant getSpecificRestaurant(int restaurantId) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_RESTAURANTID);
			pstmt.setInt(1, restaurantId); 
			
			resultSet = pstmt.executeQuery();    
			restaurantList = extractDataFromResultSet(resultSet);
			
			restaurant = restaurantList.get(0);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public int updateRestaurant(Restaurant r) {
		try {
			pstmt = con.prepareStatement(UPDATE_ON_RESTAURANTNAME);
			pstmt.setString(2, r.getFoodName());
			pstmt.setInt(3,r.getDeliveryTime());
			pstmt.setString(4,r.getAddress());
			pstmt.setDouble(5, r.getRating());
			pstmt.setBoolean(6, r.isActive());
			pstmt.setString(1,r.getRestaurantName());
			pstmt.setDouble(7, r.getRestaurantId());
			
			status = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteRestaurant(String restaurantName) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_RESTURANT_NAME);
			pstmt.setString(1, restaurantName);
			
			status = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<Restaurant> extractDataFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next() == true) {
				restaurantList.add(new Restaurant(resultSet.getInt("restaurantId"),
						resultSet.getString("restaurantName"),
						resultSet.getString("foodName"),
						resultSet.getInt("deliveryTime"),
						resultSet.getString("address"),
						resultSet.getDouble("rating"),
						resultSet.getBoolean("isActive"),
						resultSet.getInt("adminID"),
						resultSet.getString("imgpath")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

}
