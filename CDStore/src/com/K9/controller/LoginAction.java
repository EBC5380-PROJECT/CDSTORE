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

import com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator;
import com.K9.WSClient.OrderProcessService.OrderProcessServiceSoapBindingStub;
import com.K9.session.bean.AccountInfo;
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
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		
		try {
			
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();

			HttpSession session = request.getSession();
			String jsonAccountInfo = opService.getAccount(userName, password);
			
			if(!jsonAccountInfo.contains("Error Message:")){
				Gson gson = new Gson();
				AccountInfo accountInfo = gson.fromJson(jsonAccountInfo, AccountInfo.class);
				session.setAttribute("username", userName);

				session.setAttribute("accountInfo", accountInfo);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
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
