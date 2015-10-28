package com.K9.filter;

import java.io.IOException;
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
		  String userId = (String) session.getAttribute("username");
		  
		
		if((boolean)session.getAttribute("login")){
			// pass the request along the filter chain
			chain.doFilter(httpRequest, httpResponse);
			
		}else{
			//TODO change redirect to error message
			httpResponse.sendRedirect("/login.jsp");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
