package com.K9.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		String userName = "";
		String firstName = "";  
		String lastName = "";
		String email = "";
		String password = "";
		String confirmPassword = "";
		
		userName = (String)request.getAttribute("username");
		firstName = (String)request.getAttribute("firstname");
		lastName = (String)request.getAttribute("lastname");
		email = (String)request.getAttribute("email");
		password = (String)request.getAttribute("password");
		confirmPassword = (String)request.getAttribute("confirmpassword");
		
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
		
		
		
		Gson gson = new Gson();
		String accountInfoJson = gson.toJson(accountInfo);
		
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			String result = opService.creatAccount(userName, accountInfoJson);
			CallStatus callStatus = gson.fromJson(result, CallStatus.class);
			if(callStatus.getCallStatus()!=0){
				
			}
			HttpSession session = request.getSession();
			session.setAttribute("username", accountInfo.getAccountName());
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
			
			
			response.sendRedirect("/account.jsp");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
