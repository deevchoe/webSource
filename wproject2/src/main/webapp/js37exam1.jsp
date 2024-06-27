<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
{"jikwon": [
<%
Connection conn = null;		// 데이터베이스 연결
PreparedStatement pstmt = null;		// SQL 쿼리를 실행하기 위한 객체
ResultSet rs = null;	// 쿼리 실행 결과를 저장하는 객체

try {
    Class.forName("org.mariadb.jdbc.Driver");
    String url = "jdbc:mariadb://localhost:3306/test";
    conn = DriverManager.getConnection(url, "root", "9112");

    request.setCharacterEncoding("UTF-8"); 
    String jikwonGen = request.getParameter("gender"); 

    String sql = "SELECT jikwon_no 사번, jikwon_name 이름, jikwon_jik 직급, DATE_FORMAT(jikwon_ibsail, '%Y') 입사년 FROM jikwon";
    if (jikwonGen.equals("남") || jikwonGen.equals("여")) {
        sql+=" WHERE jikwon_gen = ?";
    }
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, jikwonGen);

        rs = pstmt.executeQuery();
        String result = "";
        
        while (rs.next()) {
            result += "{";
            result += "\"사번\" : \"" + rs.getString("사번") + "\",";
            result += "\"이름\" : \"" + rs.getString("이름") + "\",";
            result += "\"직급\" : \"" + rs.getString("직급") + "\",";
            result += "\"입사년\" : \"" + rs.getString("입사년") + "\"";
            result += "},";
        }
        
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        
        out.print(result);

} catch (Exception e) {
    System.out.println("에러 : " + e);
} finally {
    try {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    } catch (Exception e) {
        System.out.println("리소스 닫기 에러 : " + e);
    }
}
%>
] }
