package com.tapfoods.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.Menu;
import com.tapfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
	HttpSession session;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		session = req.getSession();
		
		    
		    // Retrieve the cart from the session
		 Cart cart = (Cart) session.getAttribute("cart");
	        if (cart == null) {
	            cart = new Cart();
	            session.setAttribute("cart", cart);
	        }
	        
	        String action = req.getParameter("action");
	        String message = "";
	        boolean success = true;

	        try {
	            if ("add".equals(action)) {
	                addItemToCart(req, resp, cart);
	                message = "Item added successfully";
	            } else if ("update".equals(action)) {
	                updateCartItem(req, cart);
	                message = "Cart updated successfully";
	            } else if ("remove".equals(action)) {
	                removeCartItem(req, cart);
	                message = "Item removed successfully";
	            }
	            
	            // Check if it's an AJAX request
	            if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
	                resp.setContentType("application/json");
	                resp.setCharacterEncoding("UTF-8");
	                
	                // Create JSON response manually instead of using String.format
	                StringBuilder jsonResponse = new StringBuilder();
	                jsonResponse.append("{");
	                jsonResponse.append("\"status\":\"").append(success ? "success" : "error").append("\",");
	                jsonResponse.append("\"message\":\"").append(message).append("\",");
	                jsonResponse.append("\"cartCount\":").append(cart.getTotalItems());
	                jsonResponse.append("}");
	                
	                resp.getWriter().write(jsonResponse.toString());
	            } else {
	                // For normal requests, redirect to cart page
	                session.setAttribute("cart", cart);
	                resp.sendRedirect("cart.jsp");
	            }
	            
	        } catch (Exception e) {
	            // Handle error case
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            
	            StringBuilder errorResponse = new StringBuilder();
	            errorResponse.append("{");
	            errorResponse.append("\"status\":\"error\",");
	            errorResponse.append("\"message\":\"").append(e.getMessage()).append("\",");
	            errorResponse.append("\"cartCount\":0");
	            errorResponse.append("}");
	            
	            resp.getWriter().write(errorResponse.toString());
	        }
	    }

	    // Method to add an item to the cart
	    private void addItemToCart(HttpServletRequest req, HttpServletResponse resp, Cart cart) {
	        int menuId = Integer.parseInt(req.getParameter("menuId"));
	        int quantity = Integer.parseInt(req.getParameter("quantity"));
	        
	        // Retrieve the menu item based on the menuId
	        MenuDAO menuDAO = new MenuDAOImpl();
	        ArrayList<Menu> menuList = menuDAO.getMenuItem(menuId);
	        
	        if (menuList != null && !menuList.isEmpty()) {
	            Menu menu = menuList.get(0);
	            
	            // Get restaurant ID and handle different restaurant scenarios
	            int currentRestaurantId = menu.getRestaurantId();
	            Integer cartRestaurantId = cart.getRestaurantId();   
	            
	            // Prevent adding items from different restaurants
	            if (cartRestaurantId != null && !cartRestaurantId.equals(currentRestaurantId)) {
	                throw new IllegalStateException("You can only order from one restaurant at a time.");
	            }
	            
	            // Set the restaurant ID in the cart if it's the first item
	            if (cartRestaurantId == null) {
	                cart.setRestaurantId(currentRestaurantId);
	            }
	            
	            // Create a CartItem and add it to the cart
	            CartItem item = new CartItem(
	                menu.getMenuId(),
	                menu.getRestaurantId(),
	                menu.getMenuName(),
	                quantity,
	                menu.getPrice()
	            );
	            cart.addItem(item);
	        }
	    }
	    
	    private void updateCartItem(HttpServletRequest req, Cart cart) {
	        int menuId = Integer.parseInt(req.getParameter("menuId"));
	        int quantity = Integer.parseInt(req.getParameter("quantity"));
	        cart.updateItem(menuId, quantity);
	    }
	    
	    private void removeCartItem(HttpServletRequest req, Cart cart) {
	        int menuId = Integer.parseInt(req.getParameter("menuId"));
	        cart.removeItem(menuId);
	    }
		
		
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		//User loggedInUser = (User)session.getAttribute("UserLoginDetails");
//		Cart cart =(Cart)session.getAttribute("cart");
//		
//		if(cart == null) {
//			cart = new Cart();
//			session.setAttribute("cart", cart);
//		}
//		
//		String action = req.getParameter("action");
//		if("add".equals(action)) {
//			addItemToCart(req,resp, cart);
//		}
//		else if(action.equals("update")) {
//			updateCartItem(req,cart);
//		}
//		else if("remove".equals(action)) {
//			removeCartItem(req,cart);
//		}
//		
//		session.setAttribute("cart", cart);
//		resp.sendRedirect("cart.jsp");
//		
//	}
//	
//	
//	// Methods implementation
//	
//	private void addItemToCart(HttpServletRequest req, HttpServletResponse resp,Cart cart) {
//		int menuId = Integer.parseInt(req.getParameter("menuId"));
//		int quantity = Integer.parseInt(req.getParameter("quantity"));
//		
//		MenuDAO menuDAO = new MenuDAOImpl();
//		ArrayList<Menu> menuList = menuDAO.getMenuItem(menuId);
//		
//		if(menuList != null) {
//			Menu menu = menuList.get(0);
//			
//			 // Get stored restaurant ID from session, handle null case
//			int currentRestaurantId = menu.getRestaurantId(); 
//	        Integer cartRestaurantId = cart.getRestaurantId();    
//	        if (cartRestaurantId != null && !cartRestaurantId.equals(currentRestaurantId)) {
//                System.out.println("User tried to add items from a different restaurant.");
//                req.setAttribute("errorMessage", "You can only order from one restaurant at a time.");
//                try {
//					req.getRequestDispatcher("cart.jsp").forward(req, resp);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//                return;  // Stop further execution
//            }
//
//            // Set the restaurant ID in the cart if it's the first item
//            if (cartRestaurantId == null) {
//                cart.setRestaurantId(currentRestaurantId);
//                System.out.println("First restaurant ID set to: " + currentRestaurantId);
//            }
//
//			
//			CartItem item = new CartItem(
//					menu.getMenuId(),
//					menu.getRestaurantId(),
//					menu.getMenuName(),
//					quantity,
//					menu.getPrice()
//					);
//			cart.addItem(item);
//		}
//	}
//	
//	private void updateCartItem(HttpServletRequest req, Cart cart) {
//		int menuId = Integer.parseInt(req.getParameter("menuId"));
//		int quantity = Integer.parseInt(req.getParameter("quantity"));
//		cart.updateItem(menuId, quantity);
//	}
//	
//	private void removeCartItem(HttpServletRequest req, Cart cart) {
//		int menuId = Integer.parseInt(req.getParameter("menuId"));
//		cart.removeItem(menuId);
//	
//	}

//}
