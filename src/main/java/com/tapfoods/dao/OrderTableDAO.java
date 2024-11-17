package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.OrderTable;

public interface OrderTableDAO {
	
	int addOrder(OrderTable ot);
	ArrayList<OrderTable> getAllOrders(int userId);
	OrderTable specificOrder(int restaurantId);

}
