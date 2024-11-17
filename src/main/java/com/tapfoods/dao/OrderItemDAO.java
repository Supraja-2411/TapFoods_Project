package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.OrderItem;

public interface OrderItemDAO {
	
	int addOrderItem(OrderItem oi);
	ArrayList<OrderItem> getAllOrders(); //incomplete
	ArrayList<OrderItem> getSpecificOrderItem(int orderId);

}
