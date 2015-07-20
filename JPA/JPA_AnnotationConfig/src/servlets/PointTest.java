package servlets;

import java.io.IOException;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Point;
import model.PointServies;

@WebServlet("/PointTest")
public class PointTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");

		Point p = new Point();
		p.setFigure(request.getParameter("f"));
		p.setX(Float.valueOf(request.getParameter("x")));
		p.setY(Float.valueOf(request.getParameter("y")));
		p.setZ(Float.valueOf(request.getParameter("z")));
		System.out.println(p);
		
		PointServies ps = new PointServies();
		try {
			ps.addMovie(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
