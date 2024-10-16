package com.learn;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BasicServlet
 */
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//RequestDispatcher requestDispatcher= request.getRequestDispatcher("homeServlet");
		//requestDispatcher.forward(request, response); //url will not be changeing in output,status code:  no status code,use for form processing
		//requestDispatcher.include(request, response); //url will not be changeing in output,status code: status code not change,use for header & footer
		
		
		response.sendRedirect("homeServlet");//url will be changeing in output, status code: 302, use for login form
		
		
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
