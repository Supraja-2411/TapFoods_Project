package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderTable;

public class OrderTableDAOImpl implements OrderTableDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private int status;
	ArrayList<OrderTable> orderTableList = new ArrayList<OrderTable>();
	private ResultSet resultSet;
	private Statement stmt;
	private OrderTable ordertable;
	private OrderTable order;
	
	private static final String ADD_ORDER = "INSERT INTO `ordertable` (`userId`,`restaurantId`,`totalAmount`,`status`,`paymentMode`) "
			+ "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM ordertable where `userId`=?";
    private static final String SELECT_ON_RESTAURANTID = "SELECT * FROM ordertable WHERE  restaurantId = ?";

	public OrderTableDAOImpl() {
		try {
			con  = DBUtils.myDBConnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addOrder(OrderTable ot) {
		int generatedOrderId = -1;
		try {
			pstmt=con.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ot.getUserId());
			pstmt.setInt(2, ot.getRestaurantId());
			pstmt.setDouble(3, ot.getTotalAmount());
			pstmt.setString(4, ot.getStatus());
			pstmt.setString(5, ot.getPaymentMode());
			
			status =pstmt.executeUpdate();
			if(status >0) {
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedOrderId = generatedKeys.getInt(1);
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return generatedOrderId;
	}

	@Override
	public ArrayList<OrderTable> getAllOrders(int userId) {
		try {
			pstmt = con.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			
			orderTableList = extractOrderTableFromResultSet(resultSet);
			order = orderTableList.get(0);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderTableList;
	}

	@Override
	public OrderTable specificOrder(int restaurantId) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_RESTAURANTID);
			pstmt.setInt(1, restaurantId);
			
			resultSet = pstmt.executeQuery();
			orderTableList = extractOrderTableFromResultSet(resultSet);
			
			ordertable = orderTableList.get(0);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ordertable;
	}
	
	private ArrayList<OrderTable> extractOrderTableFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				orderTableList.add(new OrderTable(resultSet.getInt("orderId"),
						resultSet.getInt("userId"),
						resultSet.getInt("restaurantId"),
						resultSet.getDate("orderDate"),
						resultSet.getDouble("totalAmount"),
						resultSet.getString("status"),
						resultSet.getString("paymentMode")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderTableList;
	}

}
