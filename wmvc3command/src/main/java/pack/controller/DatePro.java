package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.DateModel;

public class DatePro implements CommandInter{

	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DateModel dateModel = new DateModel();	// 원래는 싱글톤 처리 해줘야해
		ArrayList<String> list = dateModel.getDate();
		request.setAttribute("datas", list);
		return "view2.jsp";
	}
}
