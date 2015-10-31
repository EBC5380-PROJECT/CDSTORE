package com.K9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator;
import com.K9.WSClient.OrderProcessService.OrderProcessServiceSoapBindingStub;
import com.K9.hibernate.bean.OrderItem;
import com.K9.session.bean.ShippingInfo;
import com.K9.session.bean.ShoppingCartInfo;
import com.K9.util.CallStatus;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckoutAction
 */
@WebServlet("/CheckoutAction")
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
		HttpSession session = request.getSession();
		String jsonCart = (String) session.getAttribute("cart");
		String username = (String) session.getAttribute("username");
		double taxRate = 0.13;
		Gson gson = new Gson();
		ArrayList<HashMap> items = gson.fromJson(jsonCart, ArrayList.class);
		Double totalcost = 0.0;
		for(HashMap item: items){

			totalcost += (float)item.get("price")*(int)item.get("price");
		}
		totalcost = totalcost*(1+taxRate);
		

		ShippingInfo shippinginfo = new ShippingInfo();
		//TODO: shippingInfo not complete
		shippinginfo.setAccountName(session.getAttribute("username"));
		shippinginfo.setTaxes(taxRate);
		shippinginfo.setShippingCharge(0.0);
		shippinginfo.setTotalCost(totalcost);

		String jsonshippinginfo = gson.toJson(shippinginfo);
		
		session.setAttribute("finalShippingInfo", jsonshippinginfo);
		HashMap<String,String> purchaseOrder = new HashMap<String, String>();
		purchaseOrder.put("orderId", "0");
		purchaseOrder.put("accountName", username);
		purchaseOrder.put("status", "ORDERED");
		purchaseOrder.put("shippingCharge", "0.0");
		purchaseOrder.put("taxes", String.valueOf(totalcost*taxRate));
		purchaseOrder.put("totalCost", totalcost.toString());
		
		String jsonPurchaseOrder = gson.toJson(purchaseOrder);
		
		session.setAttribute("finalPurchaseOrder", jsonPurchaseOrder);
		
		OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
		String jsonResult = opService.createOrder(jsonCart, jsonshippinginfo);
		CallStatus result = gson.fromJson(jsonResult, CallStatus.class);
		
		if(result.getCallStatus()!=0){
			ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
			String error = rb.getString(String.valueOf(result.getCallStatus()));
			session.setAttribute("error", error);
			response.sendRedirect("shipping.html");
		}else{
			response.sendRedirect("Payment.html");
		}
		
		
	}

}
