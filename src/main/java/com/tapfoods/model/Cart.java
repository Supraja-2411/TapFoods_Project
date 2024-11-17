package com.tapfoods.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> items;
	private Integer restaurantId;  
	
	public Cart() {
		this.items = new HashMap<>();
	}
	
	 public int getTotalItems() {
	        int total = 0;
	        if (items != null) {
	            for (CartItem item : items.values()) {
	                total += item.getQuantity();
	            }
	        }
	        return total;
	    }
	
	public void addItem(CartItem item) {
		int menuId = item.getMenuId();
		
		if(items.containsKey(menuId)) {
			CartItem exsistingItem = items.get(menuId);
			exsistingItem.setQuantity(exsistingItem.getQuantity() + item.getQuantity());
		}
		else {
			items.put(menuId, item);
		}
	}
	
	public void updateItem(int menuId , int quantity) {
		
		if(items.containsKey(menuId)) {
			if(quantity <= 0) {
				items.remove(menuId);
			}
			else {
				items.get(menuId).setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int menuId) {
		items.remove(menuId);
	}
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public void clear() {
		items.clear();
	}
	public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

}
