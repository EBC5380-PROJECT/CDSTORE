package com.K9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator;
import com.K9.WSClient.OrderProcessService.OrderProcessServiceSoapBindingStub;
import com.K9.hibernate.bean.OrderItem;
import com.K9.session.bean.OrderItemShoppingCart;
import com.K9.session.bean.ShippingInfo;
import com.K9.session.bean.ShoppingCartInfo;
import com.K9.util.CallStatus;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckoutAction
 */

public class CheckoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initiate variables and get session from request
		HttpSession session = request.getSession();
		String jsonCart = (String) session.getAttribute("cart");
		String username = (String) session.getAttribute("username");
		Double taxRate = 0.13;
		Double totalcost = 0.0;
		Gson gson = new Gson();
		ArrayList<Map> items = gson.fromJson(jsonCart, ArrayList.class);
		ArrayList<OrderItemShoppingCart> newShoppingCartInfo = new ArrayList<OrderItemShoppingCart>();
		ShippingInfo shippinginfo = new ShippingInfo();
		Map purchaseOrder = new HashMap();
		
		//cast the item's attributes into correct formats (Gson transform number into Double type) 
		//and place it into a new shopping cart object
		//and calculate the total cost
		for(Map item: items){
			OrderItemShoppingCart newItem = new OrderItemShoppingCart();
			Double tmpId = (Double) item.get("itemId");
			Double tmpQuantity = (Double) item.get("quantity");
			Double tmpprice = (Double) item.get("quantity");
			newItem.setAccountName(username);
			newItem.setCdId(tmpId.intValue());
			newItem.setQuantity(tmpQuantity.intValue());
			totalcost += tmpprice*tmpQuantity;
			newShoppingCartInfo.add(newItem);	
		}
		totalcost = totalcost*(1+taxRate);
		//Transform the new shopping cart object into json string
		String newJsonShoppingCartInfo = gson.toJson(newShoppingCartInfo);
		
		//set shipping info
		shippinginfo.setAccountName((String)session.getAttribute("username"));
		shippinginfo.setTaxes(taxRate);
		shippinginfo.setShippingCharge(0.0);
		shippinginfo.setTotalCost(totalcost);
		//Transform the shipping info object into json string
		String jsonshippinginfo = gson.toJson(shippinginfo);
		//set the shipping info into session to use in the next step
		session.setAttribute("finalShippingInfo", jsonshippinginfo);
		
		//set order info
		purchaseOrder.put("orderId", 0);
		purchaseOrder.put("accountId", 0);
		purchaseOrder.put("status", "");
		purchaseOrder.put("shippingCharge", 0.0);
		purchaseOrder.put("taxes", totalcost*taxRate);
		purchaseOrder.put("totalCost", totalcost);
		
		try {
			//Initiate web service client
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			//call the seb service and get result
			String jsonResult = opService.createOrder(newJsonShoppingCartInfo, jsonshippinginfo);
			//check the result
			if(jsonResult.contains("callStatus")){//if the result has callStatus means it is failed
				//Transform the json result into java object
				CallStatus result = gson.fromJson(jsonResult, CallStatus.class);
				//get the error message from resource bundle
				ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
				String error = rb.getString(String.valueOf(result.getCallStatus()));
				//set the error message into session and give it back
				session.setAttribute("error", error);
				String referer = request.getHeader("Referer");
				response.sendRedirect(referer);
			}else{//if it is not failed
				//get the created orderId from the result
				Map resultMap = gson.fromJson(jsonResult, Map.class);
				Double orderId = (Double) resultMap.get("orderId");
				purchaseOrder.put("orderId", orderId.intValue());
				String jsonPurchaseOrder = gson.toJson(purchaseOrder);
				//insert the created order info into session for next step
				session.setAttribute("finalPurchaseOrder", jsonPurchaseOrder);
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String paymentPage = pathRb.getString("payment");
				response.sendRedirect(paymentPage);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
