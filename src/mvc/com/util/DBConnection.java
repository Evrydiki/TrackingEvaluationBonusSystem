package mvc.com.util;

import java.sql.*;

public class DBConnection {

	private static Connection con;
	
	/**
	 * Method that connects the web application to mysql
	 */
	public static Connection getConnection()
	{
		String url = "jdbc:mysql://127.0.0.1:3306/trackingdb"+
				"?verifyServerCertificate=false"+
				"&useSSL=false"+
				"&requireSSL=false"; // where mydatabase is the database name and 3306 is the port where mysql is running
		String username = "root"; 
		String password = "1234"; 

		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");// differs from DB server to server			
			}
			catch (ClassNotFoundException e)
			{
				System.out.println ( "loadDriver error: \n" + e.getMessage () ) ;
			}

			con = DriverManager.getConnection(url, username, password);
			System.out.println ("Connected to db") ;
		}
		catch (SQLException sqle)
		{
			System.out.println ("Connect error: " + sqle.getMessage ()) ; 
		}

		return con;
	}
	
}
