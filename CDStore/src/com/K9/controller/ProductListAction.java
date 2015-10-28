package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.ProductCatalogService.*;

/**
 * Servlet implementation class ItemListAction
 */
@WebServlet("/ProductListAction")
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductListAction() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = 0;
		String jsonProductList = "";

		categoryId = Integer.getInteger(request.getParameter("category") != null?request.getParameter("category"):"0");
		
		try {
			ProductCatalogServiceSoapBindingStub pcService = (ProductCatalogServiceSoapBindingStub) new ProductCatalogServiceServiceLocator().getProductCatalogService();
			if(categoryId != 0){
				jsonProductList = pcService.getProductListByCategory(categoryId);
			}else{
				jsonProductList = pcService.getProductList();
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(jsonProductList);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
