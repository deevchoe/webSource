package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class InsertController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		UserForm userForm = new UserForm();
		userForm.setUserid(request.getParameter("userid"));
		userForm.setPassword(request.getParameter("password"));
		userForm.setName(request.getParameter("name"));
		userForm.setEmail(request.getParameter("email"));
		
		// Model과 통신
		int result = UserManager.instance().insert(userForm); // instace 메소드는 static이라 누워있어
		
		ModelAndView modelAndView = new ModelAndView();
		if(result == 1) { // insert 후 목록 보고 싶으면 UserServlet에서 command.equals("list") 만나야 해
			modelAndView.setViewName("list.m2");
		} else {
			modelAndView.setViewName("fail.html");
		}
		modelAndView.setRedirect(true); // 클라이언트 통해서 서블릿 불러야돼
		return modelAndView;
	}
}
