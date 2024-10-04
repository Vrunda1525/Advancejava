package com.learn;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcPreparedStatement
 */
@WebServlet("/jdbcPreparedStatement")
public class JdbcPreparedStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JdbcPreparedStatement() {
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
			PreparedStatement preparedstatement = connection.prepareStatement("select * from person where personId=? ");
			preparedstatement.setInt(1, 1);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				String firstName = resultset.getString(2);
				String lastName = resultset.getString(3);
				response.getWriter().append("FirstName: " + firstName + " LastName: " + lastName);

			}
			
			// Callable Statement
			
			//CallableStatement callableStatement = connection.prepareCall("call getUserName()"); 
			 
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
