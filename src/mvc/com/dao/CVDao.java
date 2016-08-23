package mvc.com.dao;

import java.io.InputStream;
import java.sql.*;

import mvc.com.util.DBConnection;

public class CVDao {

	public InputStream geCVByEmployee(String CVName, String username, String query) throws  
	IllegalArgumentException, SQLException, ClassNotFoundException{
		Connection currentCon = null;
		ResultSet resultSet = null;
		Statement statement = null;	
		Blob CVBlob = null;

		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
					CVBlob = resultSet.getBlob("CV");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			currentCon.close();
		}
		return CVBlob.getBinaryStream();
	}
	
	public InputStream getCVByManager(String CVName, String username) throws  
	IllegalArgumentException, SQLException, ClassNotFoundException{
		Connection currentCon = null;
		ResultSet resultSet = null;
		Statement statement = null;	
		Blob CVBlob = null;

		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT username, CV, CVName FROM manager WHERE CVName = '" + CVName + "' AND username = '" + username + "'");
			while(resultSet.next()) {
					CVBlob = resultSet.getBlob("CV");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			currentCon.close();
		}
		return CVBlob.getBinaryStream();
	}
}
