package com.tapfoods.model;

public class Restaurant {
	
	private int restaurantId;
	private String restaurantName;
	private String foodName;
	private int deliveryTime;
	private String address;
	private double rating;
	private boolean isActive;
	private  int adminId;
	private String imgpath;
	
	
	public Restaurant() {
		super();
	}

	public Restaurant(String restaurantName, String foodName, int deliveryTime, String address, double rating,
			boolean isActive, int adminId, String imgpath) {
		super();
		this.restaurantName = restaurantName;
		this.foodName = foodName;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.rating = rating;
		this.isActive = isActive;
		this.adminId = adminId;
		this.imgpath = imgpath;
	}
	
	public Restaurant(int restaurantId, String restaurantName, String foodName, int deliveryTime, String address,
			double rating, boolean isActive, int adminId, String imgpath) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.foodName = foodName;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.rating = rating;
		this.isActive = isActive;
		this.adminId = adminId;
		this.imgpath = imgpath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getImgpath() {
		return imgpath; // adhu dhan use pannen
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", foodName="
				+ foodName + ", deliveryTime=" + deliveryTime + ", address=" + address + ", rating=" + rating
				+ ", isActive=" + isActive + ", adminId=" + adminId + ", imgpath=" + imgpath + "]";
	}	
}
