package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.removeAttribute("userid");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.html");
		modelAndView.setRedirect(true); // 클라이언트 통해서 서블릿 불러야돼
		
		return modelAndView;
	}
}
