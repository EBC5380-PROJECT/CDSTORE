package com.K9.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.OrderProcessService.*;
import com.K9.session.bean.AccountInfo;
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
		accountInfo.setEmail(email);
		accountInfo.setPassword1(password);
		
		
		
		Gson gson = new Gson();
		String accountInfoJson = gson.toJson(accountInfo);
		
		try {
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			opService.creatAccount(userName, accountInfoJson);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
