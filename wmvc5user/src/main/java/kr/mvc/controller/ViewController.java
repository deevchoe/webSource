package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ViewController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세 보기 처리
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		// Model과 통신
		UserDto dto = UserManager.instance().findUser(userid);
		request.setAttribute("user", dto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view.jsp");
		modelAndView.setRedirect(false); // 파라미터로 String이 아닌 dto를 넘길 순 없으니, sendRedirect로 불가능해
		return modelAndView;
	}
}
