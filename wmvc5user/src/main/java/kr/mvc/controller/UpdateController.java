package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class UpdateController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		UserForm userForm = new UserForm();
		userForm.setUserid(request.getParameter("userid"));
		userForm.setPassword(request.getParameter("password"));
		userForm.setName(request.getParameter("name"));
		userForm.setEmail(request.getParameter("email"));
		
		// Model과 통신
		boolean b = UserManager.instance().update(userForm);
		
		ModelAndView modelAndView = new ModelAndView();
		if(b) modelAndView.setViewName("list.m2");
		else modelAndView.setViewName("fail.html");
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
