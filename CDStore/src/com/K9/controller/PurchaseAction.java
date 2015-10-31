package com.K9.controller;

import java.io.IOException;
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
//mbp@WebServlet("/PurchaseAction")
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

		
		String paymentmethod = (String) request.getAttribute("paymentmethod");
		String cardname = (String) request.getAttribute("cardholdername");
		String cardnumber = (String) request.getAttribute("cardnumber");
		String expyear = (String) request.getAttribute("expyear");
		String expmonth = (String) request.getAttribute("expmonth");
		int securitycode = (int) request.getAttribute("securitycode");
		
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
				response.sendRedirect("payment.html");
			}else{
				response.sendRedirect("finish.html");
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
