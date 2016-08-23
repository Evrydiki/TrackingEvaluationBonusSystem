package mvc.com.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mvc.com.util.DBConnection;

public class LoginDao {

	public String authenticateEmployee(String user, String pass)
	{
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		String usernameDB = "";
		String passwordDB = "";

		try
		{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement(); 
			resultSet = statement.executeQuery("SELECT username,password,active FROM employee"); 
			while (resultSet.next()) {
				usernameDB = resultSet.getString("username"); 
				System.out.println("UsernameDB: " + usernameDB);
				passwordDB = resultSet.getString("password"); 
				System.out.println("PasswordDB: " + passwordDB);
				if(user.equals(usernameDB)&&(passwordDB.equals(RegisterDao.md5(pass)))&&resultSet.getInt("active")==1) 
				{
					return "SUCCESS"; 
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Η σύνδεση απέτυχε, προσπαθήστε ξανά!"; 
	}

	public String authenticateManager(String user, String pass)
	{
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		String usernameDB = "";
		String passwordDB = "";

		try
		{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement(); 
			resultSet = statement.executeQuery("SELECT username,password,active FROM manager"); 
			while (resultSet.next()) {
				usernameDB = resultSet.getString("username"); 
				passwordDB = resultSet.getString("password");
				if(user.equals(usernameDB)&& (passwordDB.equals(RegisterDao.md5(pass)))&&resultSet.getInt("active")==1) 
				{
					return "SUCCESS"; 
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Η σύνδεση απέτυχε, προσπαθήστε ξανά!"; 
	}
	
	public String authenticateAdmin(String user, String pass)
	{
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		String usernameDB = "";
		String passwordDB = "";

		try
		{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement(); 
			resultSet = statement.executeQuery("SELECT username,password FROM admin"); 
			while (resultSet.next()) {
				usernameDB = resultSet.getString("username"); 
				passwordDB = resultSet.getString("password"); 
				if( user.equals(usernameDB)&& (passwordDB.equals(pass))) 
				{
					return "SUCCESS"; 
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Η σύνδεση απέτυχε, προσπαθήστε ξανά!"; 
	}
}


