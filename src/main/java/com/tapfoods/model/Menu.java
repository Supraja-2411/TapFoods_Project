package com.tapfoods.model;

public class Menu {
	
	private int menuId;
	private int restaurantId;
	private String menuName;
	private String description;
	private double price;
	private boolean isAvailable;
	private String imgpath;
	
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(int restaurantId, String menuName, String description, double price, boolean isAvailable,
			String imgpath) {
		super();
		this.restaurantId = restaurantId;
		this.menuName = menuName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}

	public Menu(int menuId, int restaurantId, String menuName, String description, double price, boolean isAvailable,
			String imgpath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.menuName = menuName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}

	public int getMenuId() {
		return menuId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", menuName=" + menuName + ", description="
				+ description + ", price=" + price + ", isAvailable=" + isAvailable + ", imgpath=" + imgpath + "]";
	}
	
	

}
