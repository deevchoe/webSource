package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

@WebServlet("/ScoreAdd")

public class ScoreAdd extends HttpServlet {	// ScoreAdd 클래스는 HttpServlet을 상속받아 서블릿 기능을 수행.
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	// 사용자의 HTTP POST 요청을 처리. 요청으로부터 세션을 가져오거나 생성.
		request.setCharacterEncoding("utf-8");	// 요청 데이터의 인코딩을 UTF-8로 설정
		
		HttpSession session = request.getSession(true);
		// 세션 있으면 읽고, 없으면 생성하기

		ArrayList<ScoreDTO> slist = (ArrayList<ScoreDTO>)session.getAttribute("list");
		// DTO 생성, 세션의 list값을 받음
		// 세션 객체에서 list라는 이름의 속성을 가져와서 ArrayList<ScoreDTO> 타입으로 캐스팅.
		
		// 세션 객체? 웹 애플리케이션에서 각 사용자마다 고유한 세션 객체가 있다. 세션은 사용자가 웹 사이트를 방문하는 동안 정보를 유지하는데 사용된다. 예를 들어, 로그인 상태나 쇼핑 카트의 내용을 저장할 수 있다.
		// session.getAttribute("list") : 이 메소드는 세션 객체에서 "list"라는 이름으로 저장된 속성을 가져온다. 이 이름으로 점수 리스트가 세션에 저장되어 있다.
		// 반환값 : session.getAttribute("list")는 Object 타입을 반환한다. 속성이 없으면 null을 반환한다.
		// (ArrayList<ScoreDTO>) : 세션에서 가져온 속성은 Object 타입이기 때문에, 실제로 사용할 수 있도록 캐스팅한다.
		// <ArrayList<ScoreDTO> : 이 서블릿에서는 list 속성이 ScoreDTO 객체들을 담고있는 ArrayList임을 알고 있으므로, 해당 타입으로 캐스팅한다.
		
		if (slist == null) {
			// 최초의 경우 세션이 없기 때문에 if 블럭으로 들어옴
			slist = new ArrayList<ScoreDTO>();
			// ScoreDTO 타입의 객체를 담을 리스트 생성
		}
		
		// request.getParameter 메소드를 통해 폼 데이터를 가져온다. 이는 사용자가 입력한 점수 정보이다.
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		// 국어 점수와 영어 점수는 연산이 필요하기 때문에 캐스팅해준다.
		
		
		// 중복체크
		// 사용자가 입력한 no가 이미 존재하는지 확인. 중복되면 exscoremain.html로 리다이렉트
		for(int i = 0; i < slist.size(); i++) {
			if(no.equals(slist.get(i).getNo())) {	// 입력받은 no 값이 세션 리스트 안에 이미 저장되어 있을 경우
				response.sendRedirect("exscoremain.html");	// 자바에서 이전화면으로 돌아가는거
				// 자바 스크립트에서돌아가는거 location.href
				return;
			}
		}
		// 중복되지 않으면 새로운 ScoreDTO 객체를 만들어서 slist에 추가하고, 세션에 저장.
		slist.add(new ScoreDTO(no, name, kor, eng));
		// 생성자를 이용하여 ScoreDTO 멤버필드에 값 치환
		// slist에 값 추가
		session.setAttribute("list", slist);
		// 값이 추가된 slist를 session에 추가
		
		
		response.setContentType("text/html;charset=utf-8");	// 응답 콘텐츠 타입 설정
		PrintWriter out = response.getWriter();	// 출력객체생성

		// HTML 테이블을 생성하여 slist의 내용을 출력
		out.println("<p><table width='80%' border='1'>");
		out.println("<tr><th>번호</th><th>이름</th><th>국어</th><th>영어</th><th>총점</th></tr>");
		int total = 0;
		int tot = 0;
		for(int i = 0; i < slist.size(); i++) {
			// slist(ArrayList)의 전체 항목을 출력하기 위한 for문
			ScoreDTO dto = (ScoreDTO)slist.get(i);
			// slist의 i 번째 값들을 ScoreDTO 타입으로 casting 후 치환
			total = dto.getKor() + dto.getEng();
			tot++;
			
				out.println("<tr>");
				out.println("<td>" + dto.getNo() + "</td>");
				out.println("<td>" + dto.getName() + "</td>");
				out.println("<td>" + dto.getKor() + "</td>");
				out.println("<td>" + dto.getEng() + "</td>");
				out.println("<td>" + total + "</td></tr>");
		}
			
		out.println("</table>");
		out.println("<br>인원수 : " + tot + "명");
		out.println("<br><a style='color: blue; text-decoration: underline; cursor:pointer' onclick='history.back()'>새로입력</a>");
		out.println("<a href='ScoreDelete'>세션삭제</a><br>");
		out.println("</body></html>");
		out.close();
	
	}
}