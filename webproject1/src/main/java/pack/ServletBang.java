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

@WebServlet("/ServletBang")
public class ServletBang extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "9112");
			pstmt = conn.prepareStatement("insert into guest(name,subject,content) values(?,?,?)");	
		} catch (Exception e) {
			System.out.println("init err : " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 		// 한글 깨짐 방지
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		try {
			// 수신된 자료로 insert 처리. 자료 검증은 생략합니다.
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			
			//response.sendRedirect("minibang.html");	// ServletBang을 찍고 클라이언트를 통해서 minibang.html을 서버에게 요청
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();	// 출력객체생성
			out.println("<html><body>");
			out.println("<b>" + name + "</b>님 등록완료");			
			out.println("<br><a href='minibang.html'>새글 입력</a>");			
			out.println("<br><a href='ServletBangList'>방명록 리스트 보기</a>");
			out.println("</body></html>");
			out.close();
		} catch (Exception e) {
			System.out.println("doPost err : " + e);
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
