package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestJstl")
public class TestJstl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String irum = "신기해";
		request.setAttribute("irum", irum);
		
		Person person = new Person();	// 이건 원래 init 메소드에서 사용해야해ㅐ 여기서 쓰면 안돼!
		person.setName("한국인");
		request.setAttribute("person", person);
		
		Student student = new Student();
		student.setAge(22);
		request.setAttribute("student", student);
		
		String[] ani = {"댕댕이", "냥냥이", "햄스터"};
		request.setAttribute("animal", ani);
		
		String[] foods = {"떡볶이", "피자", "햄버거"};
		List<Object> list = new ArrayList<Object>();
		list.add(ani);
		list.add(foods);
		request.setAttribute("list", list);
		
		//response.sendRedirect("testjstl.jsp?irum=irum&person=person&list=list"); 	// 이런식으로 하면 안돼ㅐ testjstl.jsp?irum=irum만 넘어가. 나머지는 안넘어가
		request.getRequestDispatcher("testjstl.jsp").forward(request, response);	// request랑 response는 순서 바꾸면 안된다ㅏㅏ
	}

}
