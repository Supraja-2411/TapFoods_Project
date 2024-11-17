package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.model.Menu;

public interface MenuDAO {
	
	int addMenuItem(Menu m);
	ArrayList<Menu> getAllMenu(int restaurantId);//done
	ArrayList<Menu> getMenuItem(int menuId ); //done
	ArrayList<Menu> getAllMenuItems(String menuName); //done
	
	int updateMenu(Menu m);
	int deleteMenu(String menuName);

}
