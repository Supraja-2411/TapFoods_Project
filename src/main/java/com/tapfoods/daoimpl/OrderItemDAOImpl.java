package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt;
	int status=0;
	private ResultSet resultSet;
	private Statement stmt;
	
	ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
	OrderItem orderItem;
	
	private final static String ADD_ITEM = "insert into `orderitem` (`orderId`,`menuId`,`quantity`,`subTotal`,`menuName`) values (?,?,?,?,?) ";
	private final static String SELECT_ALL = "select * from `orderItem`";
	private final static String GET_ITEM_BY_ORDERID = "select * from `orderitem` where `orderId`=?";
	

	public OrderItemDAOImpl() {
		try {
			con  = DBUtils.myDBConnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addOrderItem(OrderItem oi) {
		try {
			pstmt = con.prepareStatement(ADD_ITEM);
			pstmt.setInt(1, oi.getOrderId());
			pstmt.setInt(2, oi.getMenuId());
			pstmt.setInt(3, oi.getQuantity());
			pstmt.setDouble(4, oi.getSubTotal());
			pstmt.setString(5, oi.getMenuName());
			
			status=pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override                                          //incomplete
	public ArrayList<OrderItem> getAllOrders() {
		try {
			stmt =con.createStatement();
			resultSet =stmt.executeQuery(SELECT_ALL);
			orderItemList = extractOrderItemFromResultSet(resultSet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderItemList;
	}

	@Override
	public ArrayList<OrderItem> getSpecificOrderItem(int orderId) {
		try {
			pstmt = con.prepareStatement(GET_ITEM_BY_ORDERID);
			pstmt.setInt(1,orderId);
			resultSet = pstmt.executeQuery();
			
			orderItemList = extractOrderItemFromResultSet(resultSet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderItemList;
	}
	
	private ArrayList<OrderItem> extractOrderItemFromResultSet(ResultSet resultSet) {
		try {
			while(resultSet.next()) {
				orderItemList.add(new OrderItem(resultSet.getInt("orderItemId"),
						resultSet.getInt("orderId"),
						resultSet.getInt("menuId"),
						resultSet.getInt("quantity"),
						resultSet.getDouble("subTotal"),
						resultSet.getString("menuName")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return orderItemList;
	}

}
