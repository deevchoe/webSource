package pack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.MessageModel;

public class MessagePro implements CommandInter{
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 적당한 모델을 선택해 수행 결과 얻기
		MessageModel messageModel = new MessageModel();	// 원래는 싱글톤으로 처리해줘야해
		String msg = messageModel.messageProcess();
		request.setAttribute("datas", msg);
		return "view1.jsp";
	}
}
