package com.K9.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.AuthorisationService.*;
import com.K9.WSClient.OrderProcessService.*;

import com.K9.util.PasswordHash;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = "";
		String password = "";
		
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		try {
			
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();

			HttpSession session = request.getSession();
			String jsonAccountInfo = opService.getAccount(userName, password);
			
			if(!jsonAccountInfo.contains("Error Message:")){
				Gson gson = new Gson();
				HashMap<String,String> accountInfo = gson.fromJson(jsonAccountInfo, HashMap.class);
				session.setAttribute("userName", userName);
				session.setAttribute("login", true);
				session.setAttribute("userId", accountInfo.get("userId"));
				session.setAttribute("accountInfo", accountInfo);
				
				//TODO change the page path
				response.sendRedirect("/account.jsp");
				
			}else{
				session.setAttribute("error", jsonAccountInfo);
				//TODO change the page path
				response.sendRedirect("/error.jsp");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
