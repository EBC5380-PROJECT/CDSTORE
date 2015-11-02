package com.K9.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.digest.DigestUtils;

import com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator;
import com.K9.WSClient.OrderProcessService.OrderProcessServiceSoapBindingStub;
import com.K9.session.bean.AccountInformation;
import com.K9.util.CallStatus;
import com.google.gson.Gson;;

/**
 * Servlet implementation class RegistrationAction
 */

public class RegistrationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("================1 commited: "+response.isCommitted());
		
		String userName = (String)request.getParameter("username");
		String firstName = (String)request.getParameter("firstname");
		String lastName = (String)request.getParameter("lastname");
		String email = (String)request.getParameter("email");
		String password = (String)request.getParameter("password");
		String confirmPassword = (String)request.getParameter("passwordconfirm");
		
		if(password == null || confirmPassword == null || !password.equals(confirmPassword)){
			request.setAttribute("error", "password error");
			response.sendRedirect("/CDStore/html/register.jsp");
			return;
		}
		
		AccountInformation accountInfo = new AccountInformation();
		accountInfo.setAccountName(userName);
		accountInfo.setFName(firstName);
		accountInfo.setLName(lastName);
		accountInfo.setEmail(email);
		accountInfo.setPassword1(password);
		

		String shippingaddress = (String)request.getParameter("shipping-address");
		String shippingcity = (String)request.getParameter("shipping-city");
		String shippingprovince = (String)request.getParameter("shipping-province");
		String shippingpostalcode = (String)request.getParameter("shipping-postalcode");
		String shippingphone = (String)request.getParameter("shipping-phone");
		
		accountInfo.setShippingAddressId(0);
		accountInfo.setShippingAddressStreet(shippingaddress);
		accountInfo.setShippingAddressCity(shippingcity);
		accountInfo.setShippingAddressProvince(shippingprovince);
		accountInfo.setShippingAddressPostalCode(shippingpostalcode);
		accountInfo.setShippingAddressPhone(shippingphone);
		accountInfo.setShippingAddressCountry("Canada");
		

		String billingaddress = (String)request.getParameter("billing-address");
		String billingcity = (String)request.getParameter("billing-city");
		String billingprovince = (String)request.getParameter("billing-province");
		String billingpostalcode = (String)request.getParameter("billing-postalcode");
		String billingphone = (String)request.getParameter("billing-phone");
		
		accountInfo.setbillingAddressId(0);
		accountInfo.setBillingAddressStreet(billingaddress);
		accountInfo.setBillingAddressCity(billingcity);
		accountInfo.setBillingAddressProvince(billingprovince);
		accountInfo.setBillingAddressPostalCode(billingpostalcode);
		accountInfo.setBillingAddressPhone(billingphone);
		accountInfo.setBillingAddressCountry("Canada");
//		System.out.println("================3 commited: "+response.isCommitted());
		Gson gson = new Gson();
		String accountInfoJson = gson.toJson(accountInfo);
		HttpSession session = request.getSession();
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
//			System.out.println("Registration accountName: "+"{\"accountName\":\""+userName+"\"}");
//			System.out.println("Registration accountInfoJson: "+accountInfoJson);
			String jsonResult = opService.creatAccount("{\"accountName\":\""+userName+"\"}", accountInfoJson);
//			System.out.println("Registration jsonResult: "+jsonResult);
			
			CallStatus result = gson.fromJson(jsonResult, CallStatus.class);
			
			if(result.getCallStatus()!=0){
				ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
				String error = rb.getString(String.valueOf(result.getCallStatus()));
				session.setAttribute("error", error);
//				System.out.println("================4 commited: "+response.isCommitted());
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String indexPage = pathRb.getString("index");
				response.sendRedirect(indexPage);
			}else{
				session.setAttribute("username", accountInfo.getAccountName());
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
//				System.out.println("================5 commited: "+response.isCommitted());
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String indexPage = pathRb.getString("index");
				response.sendRedirect(indexPage);
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
