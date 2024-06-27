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

@WebServlet("/ScoreDelete")
public class ScoreDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// 세션 생성, 있으면 읽음 (없어도 만들지 않음)
		PrintWriter out = response.getWriter();
		
		if(session == null) return;
		// 만들어진 세션이 존재하지 않으면 메소드를 종료. 즉, 아무 작업도 하지 않음.
		
		ArrayList<ScoreDTO> slist = (ArrayList<ScoreDTO>)session.getAttribute("list");	// 세션에서 list라는 이름의 속성을 가져온다. 여기에는 사용자의 쇼핑 카트에 담긴 상품 목록이 있다. 장바구니에 물건담기는 늘! 이런식으로 한다!!
		// 세션에서 "list"라는 이름의 속성을 가져와 ArrayList<ScoreDTO> 타입으로 캐스팅.
		if(slist == null) return;
		// 세션이 비워져 있으면 삭제 작업하지 않음
		
		session.removeAttribute("list");
		// 클라이언트 list의 값을 지움
		// 세션에서 list라는 이름의 속성을 제거. 이는 세션에 저장된 점수 목록을 삭제하는 것.
		
		response.sendRedirect("exscoremain.html");
		// 점수 입력 창으로 되돌아감
	}
}
