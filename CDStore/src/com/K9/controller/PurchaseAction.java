package com.K9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.K9.session.bean.ShoppingCartInfo;
import com.google.gson.Gson;

/**
 * Servlet implementation class PurchaseAction
 */
@WebServlet("/PurchaseAction")
public class PurchaseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String jsonCart = (String) session.getAttribute("cart");
		Gson gson = new Gson();
		ArrayList<OrderItem> orderItems = gson.fromJson(jsonCart, ArrayList<OrderItem>);
		ShoppingCartInfo cart = new ShoppingCartInfo();
		cart.setOrderItem(orderItems);
		ShippingInfo shippinginfo = new ShippingInfo();
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postalcode = request.getParameter("postalcode");
		String phone = request.getParameter("phone");
		
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			String result = opService.createOrder(shoppingCartInfo, shippingInfo);
			if(result){
				session.setAttribute("error", "");
			}
			response.sendRedirect("confirm.html");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
