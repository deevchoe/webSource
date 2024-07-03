package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.mvc.model.UserManager;

public class LoginController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("userid");
		String pwd = request.getParameter("password");
		
		// Model과 통신
		UserManager manager = UserManager.instance();
		boolean b = manager.login(id, pwd);
		
		ModelAndView modelAndView = new ModelAndView();
		if(b) { // 자료가 있을 때 = 로그인 성공
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", id);
			modelAndView.setViewName("list.m2"); 
			// 확장자가 m2여야 UserServlet 만나므로.
			// redirect여야 클라이언트 측에서 파일명을 가지고 새로운 요청을 보낼 수 있기 때문
		} else {
			modelAndView.setViewName("fail.html");
		}
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
