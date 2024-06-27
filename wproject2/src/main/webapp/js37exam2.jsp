<%@page import="java.sql.DriverManager"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
{"gogek":
[
<%
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    // DB 연결하기
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3306/test";
		conn = DriverManager.getConnection(url, "root", "123");

		// sql문
		String sql = "select gogek_name, gogek_tel, " +
                        "case when gogek_jumin like '%-1%' then '남' else '여' end as gogek_gen, " +
                        "jikwon_no, jikwon_name from gogek inner join jikwon on jikwon_no = gogek_damsano" +
                        " where jikwon_no = ? and jikwon_name = ?";

        
        request.setCharacterEncoding("UTF-8"); 
        String jikwonNo = request.getParameter("empNo");
        String jikwonName = request.getParameter("empName");

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, jikwonNo);
		pstmt.setString(2, jikwonName);
		rs = pstmt.executeQuery();


		String result = "";
		while (rs.next()) {
			result += "{";
			result += "\"gogek_name\":" + "\"" + rs.getString("gogek_name") + "\",";
			result += "\"gogek_tel\":" + "\"" + rs.getString("gogek_tel") + "\",";
			result += "\"gogek_gen\":" + "\"" + rs.getString("gogek_gen") + "\",";
			result += "\"jikwon_no\":" + "\"" + rs.getString("jikwon_no") + "\",";
			result += "\"jikwon_name\":" + "\"" + rs.getString("jikwon_name") + "\"";
			result += "},";
		}

		if(result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		out.print(result);

	} catch (Exception e) {
		System.out.println("loading failed : " + e);
	} finally {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {

		}

	}

%>
]
}
