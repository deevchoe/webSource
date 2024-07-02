package kr.mvc.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.m2")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			// 방법 2 : 파일명을 요청으로 사용
			String ss = request.getRequestURI();
			// /wmvc4sangpum/sang.do
			int idx = ss.lastIndexOf('/');
			StringTokenizer st = new StringTokenizer(ss.substring(idx + 1),".");
			ss = st.nextToken();
			System.out.println("ss : " + ss);	// sang			
			
		} catch (Exception e) {
			System.out.println("service err : " + e);
		}
	}

}
