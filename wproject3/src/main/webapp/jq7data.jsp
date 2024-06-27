<?xml version="1.0" encoding="utf-8"?>

<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

String buser = request.getParameter("buser");
//System.out.println(buser);

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
	Class.forName("org.mariadb.jdbc.Driver");
	String url="jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "9112");
	
	String sql = "select jikwon_no, jikwon_name from jikwon" +
			" inner join buser on buser_num=buser_no where buser_name=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, buser);
	rs = pstmt.executeQuery();
		
	String result = "";
	
	while(rs.next()){
		result += "(" + rs.getString("jikwon_no") + " " +
						rs.getString("jikwon_name") + ")";	// (1 홍길동)
	}
	out.print("<ele>");
	out.print("<make>" + result + "</make>");
	out.print("</ele>");
}catch(Exception e){
	System.out.println("에러 : " + e);
	
}finally{
	try{
		rs.close();
		pstmt.close();
		conn.close();
	}catch(Exception e){
		
	}
}
%>