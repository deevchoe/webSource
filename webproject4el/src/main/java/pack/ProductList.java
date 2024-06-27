package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("컴포즈커피", 5000.0, "시원하다ㅏ", new Date()));
		products.add(new Product("달고나라떼", 5500.0, "달다 달어", new Date()));
		products.add(new Product("춘식이 우유", 3500.0, "패키지가 귀여웡!", new Date()));
		products.add(new Product("곤약젤리", 1500.0, "맛있지맛있지", new Date()));
		
		request.setAttribute("products", products);
		request.getRequestDispatcher("/pshow.jsp").forward(request, response);
	}

}
