<%@ page import="com.CD"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table, th, td {
    border: 1px solid black;
}
</style>
<title>CD INVENTORY</title>
</head>
<body>
<h1>CD Inventory</h1>
<jsp:useBean id="cd" class="com.CD"/>
<table>
<%    ArrayList<CD> cds = (ArrayList<CD>)request.getAttribute("cds");
for(int i = 0; i<cds.size(); i++)
{
	cd = cds.get(i);
	out.println("<tr>");
    out.println("<td ><div align='center'>"+"ID"+"</td>");       
    out.println("<td ><div align='center'>"+cd.getCdId()+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td ><div align='center'>"+"Title"+"</td>");       
    out.println("<td ><div align='center'>"+cd.getCdTitle()+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td ><div align='center'>"+"Price"+"</td>");       
    out.println("<td ><div align='center'>"+cd.getCdPrice()+"</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td ><div align='center'>"+"Category"+"</td>");       
    out.println("<td ><div align='center'>"+cd.getCdCategory()+"</td>");
    out.println("</tr>");
    out.println("<br/>");
} %>
</table>
</body>
</html>