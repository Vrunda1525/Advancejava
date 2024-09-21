import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		//register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//establish the connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/vrunds_db","root","root2511");
		System.out.println("Connection Created");
		
		//Create Statement
		Statement stmt = con.createStatement();
		
		//execute statement
		stmt.executeUpdate("INSERT INTO PERSON VALUES (1,'Vrunda','Shah','Ahmedabad')");
		System.out.println("Record added successfully");
		
		con.close();

	}
}