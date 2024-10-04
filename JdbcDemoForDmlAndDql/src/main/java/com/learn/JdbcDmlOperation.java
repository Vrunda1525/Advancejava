package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcDmlOperation
 */
@WebServlet("/jdbcDmlOperation")
public class JdbcDmlOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdbcDmlOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			// Load Drive Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Get the Connection from DriverManager
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vrunds_db","root","root2511");
			
			// Create Statement using connection object
			Statement statement = connection.createStatement();
			
			int result = statement.executeUpdate("update person set firstName='Mit'");
			response.getWriter().append("Result: " + result);
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append(" Served at: ").append(request.getContextPath());
	}

}
