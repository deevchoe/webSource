package kr.mvc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ListController implements Controller{
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<UserDto> list = UserManager.instance().getUserAll();
		request.setAttribute("list", list);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list.jsp");
		modelAndView.setRedirect(false); // 15행 들고 가야 하니까. request에 담아서 list에 뿌릴거니까 redirect로 못 가. forwarding으로 기기기기ㅣ긱기기기기ㅣ기기기기
		
		return modelAndView;
	}
}
