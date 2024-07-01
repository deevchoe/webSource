package pack.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerService
 */
@WebServlet("*.do")	// 확장자 do가 들어오면 service를 소환한다
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 방법 1 : parameter를 요청으로 사용
		//String command = request.getParameter("command");
		
		// 방법 2 : 파일명을 요청으로 사용
		String ss = request.getRequestURI();
		// /wmvc4sangpum/sang.do
		int idx = ss.lastIndexOf('/');
		StringTokenizer st = new StringTokenizer(ss.substring(idx + 1),".");
		ss = st.nextToken();
		//System.out.println("ss : " + ss);	// sang
		
		
		String command = ss;
		CommandInter inter = null;
		String viewName = "/WEB-INF/views/";

		try {
			if(command.equals("sang")) {
				inter = new SangpumImpl();
				viewName += inter.showData(request, response);
				request.getRequestDispatcher(viewName).forward(request, response);
			}else if(command.equals("jikwon")) {
				// ...
			}else {
				viewName = "error.html";
				response.sendRedirect(viewName);
			}
		} catch (Exception e) {
			System.out.println("service err : " + e);
		}
		
	}

}
