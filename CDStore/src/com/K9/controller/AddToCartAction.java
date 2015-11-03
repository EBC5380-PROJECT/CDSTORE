package com.K9.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddToCartAction
 */

public class AddToCartAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartAction() {
		super();        
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initiate variables and get session from request
		HttpSession session = request.getSession();
		String jsonCart = (String) session.getAttribute("cart");
		System.out.println("Add to Cart before:"+(String) session.getAttribute("cart"));
		System.out.println("========update cart:"+request.getParameter("cart"));
		//Check where the request is coming from
		if(null == request.getParameter("itemId") && null != request.getParameter("cart")){//Coming from shopping cart
			//get the json cart info from request
			jsonCart = request.getParameter("cart")==null?"[]":request.getParameter("cart");

		}else{//Coming from add to cart button
			//Get parameters of the item and cast formats
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String itemName = URLDecoder.decode(request.getParameter("itemName"),"UTF-8");
			float price = Float.parseFloat(request.getParameter("price"));
			int quantity = request.getParameter("quantity")==null?1:Integer.parseInt(request.getParameter("quantity"));
			
			Gson gson = new Gson();
			//Get the shopping cart and transfer to ArrayList 
			//if the cart is empty, initiate it
			ArrayList<Map> cart = jsonCart!=null?gson.fromJson(jsonCart, ArrayList.class):new ArrayList<Map>();
			
			//cast the item's attributes into correct formats (Gson transform number into Double type)
			//and if the new item is existing one, update the quantity
			boolean hasMatch = false;
			for(int i = 0; i<cart.size(); i++){
				Map tmpItem = cart.get(i);
				Double tmpId = (Double) tmpItem.get("itemId");
				Double tmpQuantity = (Double) tmpItem.get("quantity");
				tmpItem.put("itemId", tmpId.intValue());
				tmpItem.put("quantity", tmpQuantity.intValue());
				if(tmpId.intValue()==itemId){
					tmpItem.put("quantity", tmpQuantity.intValue()+quantity);
					hasMatch = true;
				}
				cart.set(i, tmpItem);
			}
			//If the new item is not in the cart, add it
			if(!hasMatch){
				Map item = new HashMap();
				item.put("itemId", itemId);
				item.put("itemName", itemName);
				item.put("price", price);
				item.put("quantity", quantity);
				cart.add(item);
			}

			//Tranform the cart back to json string
			jsonCart = gson.toJson(cart);

		}
		//update the cart in session
		session.setAttribute("cart", jsonCart);
//		System.out.println("Add to Cart after:"+(String) session.getAttribute("cart"));
		//remove the error message
		session.removeAttribute("error");
		//get the origin page and redirect back
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
