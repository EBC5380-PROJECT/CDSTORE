package com;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DatabaseAccess extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/cd";

	private static final String USER = "admin";
	private static final String PASS = "admin";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;

		response.setContentType("text/html");

		try {

			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT cdid, title, price, category FROM cd";
			ResultSet rs = stmt.executeQuery(sql);

			ArrayList<CD> cds = new ArrayList<CD>();
            if(rs != null){
            	while(rs.next()){
                    CD cd=new CD();
                    cd.setCdId(rs.getString("cdid"));
                    cd.setCdTitle(rs.getString("title"));
                    cd.setCdPrice(rs.getInt("price"));
                    cd.setCdCategory(rs.getString("category"));
                    cds.add(cd);
                }
            }

			rs.close();
			stmt.close();
			conn.close();
        
	        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
	        request.setAttribute("cds",cds);
	        rd.forward(request,response);
	        
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} 
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		}
	}
}