package pack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		//System.out.println(command);
		String viewName = "";
		CommandInter inter = null;	// 클라이언트 요청 처리를 인터페이스 사용
		
		try {
			if(command.equals("message")) {
				inter = new MessagePro();	// MessagePro 객체의 주소를 가질 수도 있고
			}else if(command.equals("date")) {
				inter = new DatePro();
			}
			
			viewName = "/WEB-INF/views/" + inter.showData(request, response);
			request.getRequestDispatcher(viewName).forward(request, response);
		} catch (Exception e) {
			System.out.println("service err : " + e);
		}
	}
}
