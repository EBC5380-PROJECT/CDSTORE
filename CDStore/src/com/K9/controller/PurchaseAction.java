package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
import com.K9.session.bean.PaymentInfo;
import com.K9.session.bean.ShoppingCartInfo;
import com.K9.util.CallStatus;
import com.google.gson.Gson;

/**
 * Servlet implementation class PurchaseAction
 */

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
		Gson gson = new Gson();
		boolean verified = true;
		String errorMessage = "";
		if(null == request.getParameter("paymentmethod")||request.getParameter("paymentmethod").length()==0){
			verified = false;
			errorMessage = "Please select payment method.\n";
		}
		if(null == request.getParameter("cardholdername")||request.getParameter("cardholdername").length()==0){
			verified = false;
			errorMessage += "Please input cardholder's name.\n";
		}
		if(null == request.getParameter("cardnumber")||request.getParameter("cardnumber").length()==0){
			verified = false;
			errorMessage += "Please input card number.\n";
		}
		if(null == request.getParameter("expyear")||request.getParameter("expyear").length()==0
				||null == request.getParameter("expmonth")||request.getParameter("expmonth").length()==0){
			verified = false;
			errorMessage = "Please select expiry.\n";
		}
		if(null == request.getParameter("securitycode")||request.getParameter("securitycode").length()==0){
			verified = false;
			errorMessage += "Please input security code.\n";
		}
		if(!verified){
			session.setAttribute("error", errorMessage);
			ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
			String paymentPage = pathRb.getString("payment");
			response.sendRedirect(paymentPage);
			return;
		}
		String paymentmethod = (String) request.getParameter("paymentmethod");
		String cardname = (String) request.getParameter("cardholdername");
		String cardnumber = (String) request.getParameter("cardnumber");
		String expyear = (String) request.getParameter("expyear");
		String expmonth = (String) request.getParameter("expmonth");
		int securitycode = Integer.parseInt(request.getParameter("securitycode"));
		
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setCreditCardHolderName(cardname);
		paymentInfo.setCreditCardNumber(cardnumber);
		paymentInfo.setExpiryDate(expmonth+"/"+expyear);
		paymentInfo.setCcv(securitycode);
		
		String finalShippingInfo = (String)session.getAttribute("finalShippingInfo");
		String finalPurchaseOrder = (String)session.getAttribute("finalPurchaseOrder");
		String finalPaymentInfo = gson.toJson(paymentInfo);
		
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			String jsonResult = opService.confirmOrder(finalPurchaseOrder, finalShippingInfo, finalPaymentInfo);

			CallStatus result = gson.fromJson(jsonResult, CallStatus.class);
			
			if(result.getCallStatus()!=0){
				ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
				String error = rb.getString(String.valueOf(result.getCallStatus()));
				session.setAttribute("error", error);
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String paymentPage = pathRb.getString("payment");
				response.sendRedirect(paymentPage);
			}else{
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String finishPage = pathRb.getString("finish");
				response.sendRedirect(finishPage);
			}
			
		} catch (ServiceException e) {
			//handle the web service error
			ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
			String error = rb.getString("800");
			e.printStackTrace();
			//set the error message into session and give it back
			session.setAttribute("error", error);
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
		
		
	}

}
