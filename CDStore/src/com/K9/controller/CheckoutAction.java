package com.K9.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.K9.hibernate.bean.OrderItem;
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
        // TODO Auto-generated constructor stub
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
		session.setAttribute("finalShoppingCart", cart);
		ShippingInfo shippinginfo = new ShippingInfo();
		//TODO: shippingInfo not complete
		shippinginfo.
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postalcode = request.getParameter("postalcode");
		String phone = request.getParameter("phone");
		
		OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
		String result = opService.createOrder(shoppingCartInfo, shippingInfo);
		
		response.sendRedirect("payment.html");
	}

}
