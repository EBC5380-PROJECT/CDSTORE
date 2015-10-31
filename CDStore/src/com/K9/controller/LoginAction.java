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

import com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator;
import com.K9.WSClient.OrderProcessService.OrderProcessServiceSoapBindingStub;
import com.K9.session.bean.AccountInfo;
import com.K9.util.CallStatus;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginAction
 */
//mbp@WebServlet("/LoginAction")
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

			
			if(!jsonAccountInfo.contains("callStatus")){
				Gson gson = new Gson();
				AccountInfo accountInfo = gson.fromJson(jsonAccountInfo, AccountInfo.class);
				session.setAttribute("username", userName);

//				session.setAttribute("accountInfo", accountInfo);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
				//TODO change the page path
				response.sendRedirect("/account.jsp");
				
			}else{
				Gson gson = new Gson();
				CallStatus result = gson.fromJson(jsonAccountInfo, CallStatus.class);
				
				if(result.getCallStatus()!=0){
					ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
					String error = rb.getString(String.valueOf(result.getCallStatus()));
					session.setAttribute("error", error);
					response.sendRedirect("sign.html");
				}

			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
