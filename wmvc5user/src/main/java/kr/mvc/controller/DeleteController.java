package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class DeleteController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		boolean b = UserManager.instance().delete(userid);
		
		ModelAndView modelAndView = new ModelAndView();
		if(b) modelAndView.setViewName("list.m2");
		else modelAndView.setViewName("fail.html");
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
