package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.OrderItem;

public interface OrderHistoryDAO {
	
	int addOrderHistory(OrderHistory oh);
	ArrayList<OrderHistory>  getAllHistoryItems(int userId);
	

}
