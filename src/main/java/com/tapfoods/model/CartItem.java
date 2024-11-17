package com.tapfoods.model;

public class CartItem {

	private int menuId;
	private int restaurantId;
	private String menuName;
	private int quantity;
	private double price;
	
	
	
	public CartItem() {
		super();
	}

	public CartItem(int  menuId, int restaurantId, String menuName, int quantity, double price) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.menuName = menuName;
		this.quantity = quantity;
		this.price = price;
	}

	public int getMenuId() {
		return  menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" +  menuId + ", restaurantId=" + restaurantId + ", menuName=" + menuName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
}
