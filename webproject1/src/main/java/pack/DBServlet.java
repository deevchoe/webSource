package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// init에서 객체를 만들고 destroy에서 해제
	
	public void init(ServletConfig config) throws ServletException {	// 최초 접속자만 DB에 연결될 수 있도록 했다.
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "9112");
			pstmt = conn.prepareStatement("select * from sangdata");	// pstmt의 선처리방식을 이용
			// SQL문을 미리 준비해놓고 바인딩 변수(? 연산자)를 사용해서 반복되는 비슷한 SQL문을 쉽게 처리
		} catch (Exception e) {
			System.out.println("init err : " + e.getMessage());
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();	// 출력객체생성
			out.println("<html><body>");
			out.println("<h2>* 상품 자료 *</h2>");
			
			try {
				rs = pstmt.executeQuery();
				while(rs.next()) {
					out.println(rs.getString("code") + " " +
							rs.getString("sang") + " " +
							rs.getString("su") + " " +
							rs.getString("dan") + "<br>");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			out.println("</html></body>");
			out.close();
		} catch (Exception e) {
			System.out.println("service err : " + e.getMessage());
		}
	}
	
	public void destroy() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
