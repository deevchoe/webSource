package pack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.JudgeModel;
import pack.model.MulModel;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MulModel mulModel;
	private JudgeModel judgeModel;
	
	public void init(ServletConfig config) throws ServletException {
		mulModel = new MulModel();
		judgeModel = new JudgeModel();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mulNum = request.getParameter("mulNum");	// 구구단 숫자 --> webapp에 있는 html 파일이 넘김
		String judgeNum = request.getParameter("judgeNum");	// 홀짝 판별 숫자 --> webapp에 있는 html 파일이 넘김
		
		if(mulNum == null) {
			String result = judgeModel.judge(judgeNum);	// 홀짝 판별하는 Model 호출
			request.setAttribute("datas", result);
			request.getRequestDispatcher("/WEB-INF/views/judgeshow.jsp").forward(request, response);
		}else if (judgeNum == null) {
			ArrayList<String> list = mulModel.mul(mulNum);
			request.setAttribute("datas", list);
			request.getRequestDispatcher("/WEB-INF/views/multableshow.jsp").forward(request, response);
		}
	}

}
