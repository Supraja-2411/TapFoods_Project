package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Menu;

public class MenuDAOImpl implements MenuDAO {
	Connection con;
	private PreparedStatement pstmt;
	int status = 0;
	private Statement stmt;
	private ResultSet resultSet;
	ArrayList<Menu> menuList = new ArrayList<Menu>();
	private Menu menu;
	
	private static final String ADD_MENU = "INSERT INTO `menu`(`restaurantId`,`menuName`, `description`,`price`, `isAvailable`,`imgPath`) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM `menu` where `restaurantId`= ?";
    private static final String SELECT_ON_MENUID = "SELECT * FROM `menu` where `menuId` = ?";
    private static final String SELECT_ON_MENUNAME =" SELECT * FROM `menu` where `menuName` LIKE ?";
    
    private static final String UPDATE_MENU = "UPDATE `menu` SET `menuName` = ?, `description` = ?,`price` = ?,"
    		+ " `isAvailable` = ?,`imgPath` =? WHERE `menuId` = ?";
    private static final String DELETE_ON_MENUNAME = "DELETE FROM `menu` WHERE `menuName` = ?";
    
    public MenuDAOImpl() {
    	try {
    		con = DBUtils.myDBConnect();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	@Override
	public int addMenuItem(Menu m) {
		try {
			pstmt = con.prepareStatement(ADD_MENU);
			pstmt.setInt(1, m.getRestaurantId());
	        pstmt.setString(2, m.getMenuName());
	        pstmt.setString(3, m.getDescription());
	        pstmt.setDouble(4, m.getPrice());
	        pstmt.setBoolean(5, m.isAvailable());
	        pstmt.setString(6, m.getImgpath());
	        
	        status = pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ArrayList<Menu> getAllMenu(int menuId) {
		try { 
			
            pstmt = con.prepareStatement(SELECT_ALL);
            pstmt.setInt(1, menuId);
            
            resultSet = pstmt.executeQuery();
            menuList = extractMenuFromResultSet(resultSet);
        }
        catch(SQLException e) {
			e.printStackTrace();
		}
        return menuList;
	}

	@Override
	public  ArrayList<Menu> getMenuItem(int menuId) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_MENUID);
			pstmt.setInt(1, menuId); 
			
			resultSet = pstmt.executeQuery();
			menuList = extractMenuFromResultSet(resultSet);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
	@Override
	public  ArrayList<Menu> getAllMenuItems(String menuName) {
		try {
			pstmt = con.prepareStatement(SELECT_ON_MENUNAME);
			pstmt.setString(1, "%" + menuName + "%");; 
			
			resultSet = pstmt.executeQuery();
			menuList = extractMenuFromResultSet(resultSet);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public int updateMenu(Menu m) {
		try {
            pstmt = con.prepareStatement(UPDATE_MENU);
            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getMenuName());
            pstmt.setString(3, m.getDescription());
            pstmt.setDouble(4, m.getPrice());
            pstmt.setBoolean(5, m.isAvailable());
            pstmt.setString(6, m.getImgpath());
            pstmt.setInt(7, m.getMenuId());

            status = pstmt.executeUpdate();
        }
		catch (SQLException e) {
            e.printStackTrace();
        }
		return status;
	}

	@Override
	public int deleteMenu(String menuName) {
		try {
			pstmt = con.prepareStatement(DELETE_ON_MENUNAME);
			pstmt.setString(1,menuName);
			
			status = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	private ArrayList<Menu> extractMenuFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                menuList.add(new Menu(resultSet.getInt("menuId"),
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("menuName"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getBoolean("isAvailable"),
                    resultSet.getString("imgPath")));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
