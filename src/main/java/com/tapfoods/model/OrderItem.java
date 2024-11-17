package com.tapfoods.model;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double subTotal;
	private String menuName;
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int orderId, int menuId, int quantity, double subTotal ,String menuName) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.menuName = menuName;
	}

	public OrderItem(int orderItemId, int orderId, int menuId, int quantity, double subTotal,String menuName) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.menuName = menuName;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", subTotal=" + subTotal + ", menuName=" + menuName + "]";
	}

	
}
