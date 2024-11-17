package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.OrderItem;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	
	private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet;
    int status;
	ArrayList<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>() ;
    
    private final static String ADD_ITEM = "insert into `orderhistory` (`orderId`,`userId`,`restaurantName`,`totalAmount`,`status`) values (?,?,?,?,?) ";
	private final static String SELECT_ALL = "select * from `orderhistory` where `userId`=?";
	private final static String GET_ITEM_BY_ORDERID = "select * from `orderitem` where `orderId`=?";
	
    

	public OrderHistoryDAOImpl() {
        try {
            con = DBUtils.myDBConnect();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public  int addOrderHistory(OrderHistory oh) {

		try {
			pstmt = con.prepareStatement(ADD_ITEM);
			pstmt.setInt(1, oh.getOrderId());
			pstmt.setInt(2, oh.getUserId());
			pstmt.setString(3, oh.getRestaurantName());
			pstmt.setDouble(4, oh.getTotalAmount());
			pstmt.setString(5, oh.getStatus());
			
			status=pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public ArrayList<OrderHistory> getAllHistoryItems(int userId) {
		try {
			pstmt =con.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			
			orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderHistoryList;
	}

	
	private ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next() == true) {
				orderHistoryList.add(new OrderHistory(resultSet.getInt("orderHistoryId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getString("restaurantName"),
						resultSet.getDate("orderDate"),
						resultSet.getDouble("totalAmount"),
						resultSet.getString("status")
						));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderHistoryList;
	}



}
