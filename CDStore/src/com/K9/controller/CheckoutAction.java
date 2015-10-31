package com.K9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		Gson gson = new Gson();
		ArrayList<HashMap> items = gson.fromJson(jsonCart, ArrayList.class);
		Double totalcost = 0.0;
		for(HashMap item: items){

			totalcost += (float)item.get("price")*(int)item.get("price");
		}
		

		ShippingInfo shippinginfo = new ShippingInfo();
		//TODO: shippingInfo not complete
		shippinginfo.setAccountId(session.getAttribute("username"));
		shippinginfo.setTaxes(0.13);
		shippinginfo.setShippingCharge(0.0);
		shippinginfo.setTotalCost(totalcost);

		String jsonshippinginfo = gson.toJson(shippinginfo);
		
		OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
		String result = opService.createOrder(jsonCart, jsonshippinginfo);
		
		response.sendRedirect("Payment.html");
	}

}
