package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServlet")
@WebServlet( name = "HelloServlet",  urlPatterns = {  "/HelloServlet",  "/good.kor",  "/jiyeon"  },loadOnStartup = 1)      // 요청이 없어도, 웹서비스가 시작되면 서블릿 수행
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	// 무시하면 돼. 지워도 돼

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get 요청 성공"); // 개발자가 서버 컴퓨터 내의 콘솔 창으로 보고싶단 얘~기
		
		// 서블릿(자바는 자바인데 웹용 자바)으로 클라이언트 브라우저에 데이터 전송
		response.setContentType("text/html;charset=utf-8");	// Mime type과 문자 코드
		PrintWriter out = response.getWriter();	// 웹용 출력   out 객체가 만들어졌네ㅔ
		out.println("<html><body>");
		out.println("<h1>서블릿문서</h1>");
		out.println("안녕 반가워");
		out.println("</body></html>");
		out.close();	// 할 일을 다 했으니 닫아줘야지~
		// 이렇게 서블릿으로 웹페이지 만드는 일은 너무 힘들다. 퇴근 못한다~
		
	}

}
