package mvc.com.dao;

import java.io.*;
import java.sql.*;

import mvc.com.util.DBConnection;

public class PhotoDao {
	public InputStream getPhotoByEmployee(String photoName, String username, String query) throws  
	IllegalArgumentException, SQLException, ClassNotFoundException{
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Blob photoBlob = null;
		System.out.println("PhotoName: " + photoName);

		try {
			currentCon = DBConnection.getConnection();
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery(query); 
			while(resultSet.next()) {
					photoBlob = resultSet.getBlob("photo");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			currentCon.close();
		}
		return photoBlob.getBinaryStream();
	}
	
	public InputStream getPhotoByManager(String photoName, String username) throws IllegalArgumentException, SQLException, ClassNotFoundException{
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Blob photoBlob = null;
		System.out.println("PhotoName: " + photoName);

		try {
			currentCon = DBConnection.getConnection();
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT username, photo FROM manager WHERE photoName = '" + photoName + "' AND username = '" + username + "'"); 
			while(resultSet.next()) {
					photoBlob = resultSet.getBlob("photo");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			currentCon.close();
		}
		return photoBlob.getBinaryStream();
	}
}
