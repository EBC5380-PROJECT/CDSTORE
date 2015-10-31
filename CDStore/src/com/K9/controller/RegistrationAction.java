package com.K9.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.digest.DigestUtils;

import com.K9.WSClient.OrderProcessService.*;
import com.K9.session.bean.AccountInfo;
import com.K9.util.CallStatus;
import com.google.gson.Gson;;

/**
 * Servlet implementation class RegistrationAction
 */
@WebServlet("/RegistrationAction")
public class RegistrationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = (String)request.getAttribute("username");
		String firstName = (String)request.getAttribute("firstname");
		String lastName = (String)request.getAttribute("lastname");
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		String confirmPassword = (String)request.getAttribute("confirmpassword");
		
		if(password == null || confirmPassword == null || !password.equals(confirmPassword)){
			request.setAttribute("error", "password error");
			response.sendRedirect("/login.jsp");
		}
		
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountName(userName);
		accountInfo.setFName(firstName);
		accountInfo.setLName(lastName);
		accountInfo.setEmail(email);
		accountInfo.setPassword1(password);
		
		String shippingname = (String)request.getAttribute("shipping-name");
		String shippingaddress = (String)request.getAttribute("shipping-address");
		String shippingcity = (String)request.getAttribute("shipping-city");
		String shippingprovince = (String)request.getAttribute("shipping-province");
		String shippingpostalcode = (String)request.getAttribute("shipping-postalcode");
		String shippingphone = (String)request.getAttribute("shipping-phone");
		
		accountInfo.setShippingAddressId(0);
		accountInfo.setShippingAddressStreet(shippingaddress);
		accountInfo.setShippingAddressCity(shippingcity);
		accountInfo.setShippingAddressProvince(shippingprovince);
		accountInfo.setShippingAddressPostalCode(shippingpostalcode);
		accountInfo.setShippingAddressPhone(shippingphone);
		accountInfo.setShippingAddressCountry("Canada");
		

		String billingaddress = (String)request.getAttribute("billing-address");
		String billingcity = (String)request.getAttribute("billing-city");
		String billingprovince = (String)request.getAttribute("billing-province");
		String billingpostalcode = (String)request.getAttribute("billing-postalcode");
		String billingphone = (String)request.getAttribute("billing-phone");
		
		accountInfo.setbillingAddressId(0);
		accountInfo.setBillingAddressStreet(billingaddress);
		accountInfo.setBillingAddressCity(billingcity);
		accountInfo.setBillingAddressProvince(billingprovince);
		accountInfo.setBillingAddressPostalCode(billingpostalcode);
		accountInfo.setBillingAddressPhone(billingphone);
		accountInfo.setBillingAddressCountry("Canada");
		
		Gson gson = new Gson();
		String accountInfoJson = gson.toJson(accountInfo);
		HttpSession session = request.getSession();
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			String jsonResult = opService.creatAccount(userName, accountInfoJson);
			CallStatus result = gson.fromJson(jsonResult, CallStatus.class);
			
			if(result.getCallStatus()!=0){
				ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
				String error = rb.getString(String.valueOf(result.getCallStatus()));
				session.setAttribute("error", error);
				response.sendRedirect("register.html");
			}else{
				session.setAttribute("username", accountInfo.getAccountName());
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
				
				response.sendRedirect("/index.html");
			}
			
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
