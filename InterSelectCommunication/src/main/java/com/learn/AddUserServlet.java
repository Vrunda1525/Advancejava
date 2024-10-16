package com.learn;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection;
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		   // connection = DriverManager.getConnection("jdbc:mysql://localhost/vrunds_db","root","root2511");
			String connectionURL = config.getServletContext().getInitParameter("connectionURL");
			String username = config.getServletContext().getInitParameter("username");
			String password = config.getServletContext().getInitParameter("password");
			connection = DriverManager.getConnection(connectionURL, username,password);
			System.out.println("Connection Created");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		
		try {
			PreparedStatement statement = connection.prepareStatement("insert into user values (?,?,?)");
		
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, userName);

			int executeUpdate = statement.executeUpdate();
			statement.close();

			if (executeUpdate > 0) {
				response.sendRedirect("success.html");
			} else {
				RequestDispatcher requestDispatcher= request.getRequestDispatcher("addUser.html");
				requestDispatcher.forward(request, response);
			}
		}
		 catch (SQLException e) {
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("addUserFailure.html");
			requestDispatcher.forward(request, response);
			e.printStackTrace();
			writer.append("Error in Inserting User Data");
		}
		
	}
	
	@Override
	public void destroy() {
		if(connection != null) {
			try {
				connection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
