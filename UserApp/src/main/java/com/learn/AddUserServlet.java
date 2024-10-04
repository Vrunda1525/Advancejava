package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUserServlet")
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
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/vrunds_db","root","root2511");
			//System.out.println("Connection Created");
			PreparedStatement statement = connection.prepareStatement("insert into user values (?,?,?)");
		
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, userName);

			int executeUpdate = statement.executeUpdate();
			if (executeUpdate > 0) {
				writer.append("User Inserted Successfully");
			} else {
				writer.append("Error in Inserting User Data");
			}
			statement.close();
		}
//		 catch (ClassNotFoundException e) {
//			e.printStackTrace();}
		 catch (SQLException e) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
