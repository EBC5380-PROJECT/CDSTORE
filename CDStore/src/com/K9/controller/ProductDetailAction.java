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
 * Servlet implementation class ItemDetailAction
 */

public class ProductDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initiate variables
		int productId;
		String jsonProductInfo = "";
		String tmpProductId = request.getParameter("productid");
		//check the parameter first
		if(tmpProductId==null||tmpProductId.trim().equals("")){
			jsonProductInfo = "";
		}else{
			productId = Integer.parseInt(tmpProductId);		
			try {
				//initiate web service client
				ProductCatalogServiceSoapBindingStub pcService = (ProductCatalogServiceSoapBindingStub) new ProductCatalogServiceServiceLocator().getProductCatalogService();
				//get the json product info from web service
				jsonProductInfo = pcService.getProductInfo(productId);
			} catch (ServiceException e) {
				//handle the web service error
				PrintWriter out = response.getWriter();
				ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.messageBundle");
				String error = rb.getString("800");
				out.print("{\"error\":\""+error+"\"}");
				e.printStackTrace();
				return;
			}
		}
		//send the json product info to front end
		PrintWriter out = response.getWriter();
		out.print(jsonProductInfo);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
