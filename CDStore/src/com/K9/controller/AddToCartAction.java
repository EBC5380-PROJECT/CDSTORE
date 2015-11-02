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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String jsonCart = (String) session.getAttribute("cart");
		System.out.println("Add to Cart before:"+(String) session.getAttribute("cart"));
		System.out.println("========update cart:"+request.getParameter("cart"));
		if(null == request.getParameter("itemId") && null != request.getParameter("cart")){
			
			jsonCart = request.getParameter("cart");

		}else{

			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String itemName = URLDecoder.decode(request.getParameter("itemName"),"UTF-8");
			float price = Float.parseFloat(request.getParameter("price"));
			int quantity = request.getParameter("quantity")==null?1:Integer.parseInt(request.getParameter("quantity"));

			Map item = new HashMap();
			item.put("itemId", itemId);
			item.put("itemName", itemName);
			item.put("price", price);
			item.put("quantity", quantity);

			Gson gson = new Gson();
			ArrayList<Map> cart = jsonCart!=null?gson.fromJson(jsonCart, ArrayList.class):new ArrayList<Map>();

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

			if(!hasMatch){
				cart.add(item);
			}

			
			jsonCart = gson.toJson(cart);
			session.setAttribute("cart", jsonCart);
			

		}

		session.setAttribute("cart", jsonCart);
		System.out.println("Add to Cart after:"+(String) session.getAttribute("cart"));
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
