package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.Restaurant;

public interface RestaurantDAO {
	
	int addRestaurant(Restaurant r);
	ArrayList<Restaurant> getAllrestaurant(); //done
	Restaurant getSpecificRestaurant(int restaurantId);
	int updateRestaurant(Restaurant r);
	int deleteRestaurant(String restaurantName);

}
