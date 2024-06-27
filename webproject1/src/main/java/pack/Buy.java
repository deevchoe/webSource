package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 가격 합을 구하고 출력 후 세션 삭제
		// 클라이언트가 넘겨준 session id를 이용해 세션 읽기 
		HttpSession session = request.getSession(false);
		if(session == null) return;	// doGet을 빠져나가는거
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");
		if(glist == null) return;
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p><table width='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		int total = 0;
		for(int i = 0; i < glist.size(); i++) {
			Goods goods = (Goods)glist.get(i);
			out.println("<tr><td>" + goods.getName() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td></tr>");
			total += goods.getPrice();
		}
		out.println("<tr><td colspan='2'>결제 총액 : " + total + "</td></tr>");
		// 실제적인 결제는 현실적으로 불가. 결제가 되었다고 가정
		// 결제가 완료되었으므로 세션 삭제
		//session.invalidate();	// 해당 고객의 모든 세션 삭제
		session.removeAttribute("list");	// 해당 고객의 특정 세션 삭제
		
		out.println("<br>고객님 감사합니다:D");
		
		out.println("<br>[<a href = 'myshop.html'>새 마음으로 쇼핑!</a>] ");
		out.println("</table>");
		out.println("</body></html>");
		out.close();
	}

}
