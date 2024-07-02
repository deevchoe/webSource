package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller{	// Contoller의 파생 클래스
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userid");
		String pwd = request.getParameter("password");
		
		// 모델과 통신
		
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
}
