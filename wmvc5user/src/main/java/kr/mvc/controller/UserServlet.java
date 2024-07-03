package kr.mvc.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.m2")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModelAndView modelAndView = null;
	private Controller controller = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			String ss = request.getRequestURI(); // 전체 파일경로
			int idx = ss.lastIndexOf('/'); // 마지막 slash 문자의 index 값 찾기
			StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
			ss = st.nextToken(); // 파일명만 저장 (login, list, insert, view, ..)
			String command = ss;
			
			controller = getController(command); // 아래 정의한 메소드
			modelAndView = controller.execute(request, response);
			
			// 파일 호출 방식 선택
			if(modelAndView.isRedirect()) {
				response.sendRedirect(modelAndView.getViewName());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + modelAndView.getViewName());
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("service err: " + e);
		}
	}

	public Controller getController(String command) throws Exception{
		if(command.equals("login")) {
			controller = new LoginController();
		} else if(command.equals("list")) {
			controller = new ListController();
		} else if(command.equals("insert")) {
			controller = new InsertController();
		} else if(command.equals("view")) {
			controller = new ViewController();
		} else if(command.equals("logout")) {
			controller = new LogoutController();
		} else if(command.equals("updateform")) {
			controller = new UpdateFormController();
		} else if(command.equals("update")) {
			controller = new UpdateController();
		} else if(command.equals("delete")) {
			controller = new DeleteController();
		}
		return controller;
	}
}
