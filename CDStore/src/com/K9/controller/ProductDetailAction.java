package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.ProductCatalogService.ProductCatalogServiceServiceLocator;
import com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub;

/**
 * Servlet implementation class ItemDetailAction
 */
@WebServlet("/ProductDetailAction")
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
		// TODO Auto-generated method stub
		int productId = 0;
		String jsonProductInfo = "";
		
		productId = Integer.getInteger(request.getParameter("productid") != null?request.getParameter("productid"):"0");
		
		try {
			ProductCatalogServiceSoapBindingStub pcService = (ProductCatalogServiceSoapBindingStub) new ProductCatalogServiceServiceLocator().getProductCatalogService();
			jsonProductInfo = pcService.getProductInfo(productId);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(jsonProductInfo);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
