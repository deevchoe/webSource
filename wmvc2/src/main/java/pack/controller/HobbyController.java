package pack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.HobbyModel;

//@WebServlet("/hobby.do")
//@WebServlet({"/hobby.do", "/hobby2.do", "*.kor"})
public class HobbyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HobbyModel model;
	
	public void init(ServletConfig config) throws ServletException {
		model = new HobbyModel();
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String hobby = req.getParameter("hobby");
		
		// model을 통해 결과를 얻음
		ArrayList<String> list = model.getHobby(hobby);
		
		req.setAttribute("datas", list);
		String viewName = "/WEB-INF/views/show.jsp";
		
		req.getRequestDispatcher(viewName).forward(req, res);
	}

}