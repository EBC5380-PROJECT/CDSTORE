package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.ProductCatalogService.ProductCatalogServiceServiceLocator;
import com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub;

/**
 * Servlet implementation class CategoryAction
 */

public class CategoryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//create web service client
			ProductCatalogServiceSoapBindingStub pcService = (ProductCatalogServiceSoapBindingStub) new ProductCatalogServiceServiceLocator().getProductCatalogService();
			//get category list from web service
			String jsonCategoryList = pcService.getCategoryList();
			//return it back to front end
			PrintWriter out = response.getWriter();
			out.print(jsonCategoryList);
		} catch (ServiceException e) {
			//handle the web service error
			PrintWriter out = response.getWriter();
			ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
			String error = rb.getString("800");
			out.print("{\"error\":\""+error+"\"}");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
