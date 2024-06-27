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

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		HttpSession session = request.getSession(true);	// 사용자의 세션을 가져온다. 세션은 사용자가 웹사이트에 접속한 후에도 정보를 유지하는데 사용된다. 이 코드는 새로운 세션이 필요하면 생성한다.
		
		ArrayList<Goods> glist = (ArrayList<Goods>)session.getAttribute("list");	// 세션에서 list라는 이름의 속성을 가져온다. 여기에는 사용자의 쇼핑 카트에 담긴 상품 목록이 있다. 장바구니에 물건담기는 늘! 이런식으로 한다!!
		
		if(glist == null) glist = new ArrayList<Goods>();	// 최초의 상품이면 Goods 객체를 담을 glist 생성    컬렉션을 하나 만들어ㅓ
		glist.add(new Goods(name, price)); // 첫번째 상품이 glist ArrayList에 들어갔네
		session.setAttribute("list", glist);	// 큰 세션 안에 들어가있는 개개인의 데이터?가 들어가있는거다. 업데이트된 상품 목록을 다시 세션에 저장한다. 이렇게 하면 사용자가 페이지를 새로고침하거나 해당 페이지를 벗어나도 카트에 담긴 상품이 유지된다.
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body> " + name + "구입했습니다.");
		out.println("<br>[<a href = 'myshop.html'>계속 쇼핑</a>] ");
		out.println("[<a href='Buy'>결제하기</a>]<br>");
		
		out.println("<p><table width='80%'>");
		out.println("<tr><th>상품명</th><th>가격</th></tr>");
		for(int i = 0; i < glist.size(); i++) {
			Goods goods = (Goods)glist.get(i);
			out.println("<tr><td>" + goods.getName() + "</td>");
			out.println("<td>" + goods.getPrice() + "</td></tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
		out.close();
	}

}
