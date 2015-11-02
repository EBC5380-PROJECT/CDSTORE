package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginAction
 */

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
		//get the username and password
		String userName = request.getParameter("username")==null?"":request.getParameter("username");
		String password = request.getParameter("password")==null?"":request.getParameter("password");
		HttpSession session = request.getSession();
		//if the username or password is null go back and show error
		if(userName.equals("")||password.equals("")){
			ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
			String error = rb.getString("1");
			session.setAttribute("error", error);
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
			return;
		}
		
		
		try {
			//Initiate web service client
			OrderProcessServiceSoapBindingStub opService = (OrderProcessServiceSoapBindingStub) new OrderProcessServiceServiceLocator().getOrderProcessService();
			//get user info from web service
			String jsonAccountInfo = opService.getAccount("{\"accountName\":\""+userName+"\"}", "{\"password\":\""+password+"\"}");
			//check the result
			if(!jsonAccountInfo.contains("callStatus")){//if there is not a callStatus in the result means login succeed
				//Transform user info from json to java bean object
				Gson gson = new Gson();
				AccountInformation accountInfo = gson.fromJson(jsonAccountInfo, AccountInformation.class);
				//set the username in the session for use in front end and filter
				session.setAttribute("username", userName);
				//generate login flag and store in session for filter
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				//generate the login flag string by hashing the username and current date so it won't be the same every day for the same user to prevent spoofing.
				session.setAttribute("login", DigestUtils.sha256Hex(dateFormat.format(date)+accountInfo.getAccountName()));
				//get the redirect path and redirect
				ResourceBundle pathRb = ResourceBundle.getBundle("com.K9.resources.pagePathBundle");
				String indexPage = pathRb.getString("index");
				response.sendRedirect(indexPage);
				
			}else{//if there is a callStatus means login failed
				Gson gson = new Gson();
				CallStatus result = gson.fromJson(jsonAccountInfo, CallStatus.class);
				//get the error message
				if(result.getCallStatus()!=0){
					ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
					String error = rb.getString(String.valueOf(result.getCallStatus()));
					session.setAttribute("error", error);
					String referer = request.getHeader("Referer");
					response.sendRedirect(referer);
				}

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
