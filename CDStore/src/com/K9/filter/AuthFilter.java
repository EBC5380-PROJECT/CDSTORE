package com.K9.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		if (!(request instanceof HttpServletRequest && response instanceof HttpServletResponse)) {

		    chain.doFilter(request,response);
		    return;
		  }
		  HttpServletRequest httpRequest=(HttpServletRequest)request;
		  HttpServletResponse httpResponse=(HttpServletResponse)response;
		  HttpSession session = httpRequest.getSession();
		  String loginFlag = (String) session.getAttribute("login");
		  String username = (String) session.getAttribute("username");
		  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		  Date date = new Date();
		  System.out.println("==================loginflag"+loginFlag);
		  System.out.println("==================calculateflag"+DigestUtils.sha256Hex(dateFormat.format(date)+username));
		  
		
		if(loginFlag.equals(DigestUtils.sha256Hex(dateFormat.format(date)+username))){
			// pass the request along the filter chain
			chain.doFilter(httpRequest, httpResponse);
			
		}else{
			//TODO change redirect to error message
			httpResponse.sendRedirect("/CDStore/html/signin.jsp");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
