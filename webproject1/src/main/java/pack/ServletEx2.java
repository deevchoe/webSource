package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.other.ServletEx2Other;

@WebServlet("/ServletEx2")
public class ServletEx2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletEx2Other other;

	public void init(ServletConfig config) throws ServletException {	// 얘는 최초 한번만 수행
		// 서버는 init() 메소드를 호출해서 Servlet을 초기화한다.
		other = new ServletEx2Other("고길동");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	// Mime type과 문자 코드
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>서블릿 문서 Ex2</h1>");
		
		// 지역 변수 출력
		int a = 10, b = 20;
		out.println("a:" + a +", b:" + b);
		
		// 현재 클래스의 메소드 호출
		int tot = calcData(a, b);
		out.println("<br>두 수의 합은 " + tot);
		
		// 클래스 호출
		//ServletEx2Other other = new ServletEx2Other("홍길동");
		String ir = other.getIrum();
		out.println("<br>이름은 " + ir);
		
		out.println("</body></html>");
		out.close();
	}
	
	private int calcData(int a, int b) {
		int imsi = a + b;
		return imsi;
	}

}
