package mvc.com.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.util.DBConnection;

public class AcceptRejectDAO {
	public void acceptEmployees(String[] inactiveUsers){		
		int[] inactiveUsersCBox = new int[inactiveUsers.length];
		System.out.println("Checked: ");
		for(int i=0; i<inactiveUsers.length; i++){
			inactiveUsersCBox[i] = Integer.parseInt(inactiveUsers[i]);
			System.out.println(inactiveUsersCBox[i] + ", ");
		}
		System.out.println("Checkbox Length: " + inactiveUsersCBox.length);

		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try{
			currentCon = DBConnection.getConnection(); 
			for(int i=0; i<inactiveUsers.length; i++){
				prepStatement = currentCon.prepareStatement("UPDATE employee SET active='1' WHERE am_ika = '" + inactiveUsers[i] + "'");
				System.out.println(" " + inactiveUsers[i]);
				prepStatement.executeUpdate();
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
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
	}

	
	public void acceptManagers(String[] inactiveUsers){
		int[] inactiveUsersCBox = new int[inactiveUsers.length];
		System.out.println("Checked: ");
		for(int i=0; i<inactiveUsers.length; i++){
			inactiveUsersCBox[i] = Integer.parseInt(inactiveUsers[i]);
			System.out.println(inactiveUsersCBox[i] + ", ");
		}
		System.out.println("Checkbox Length: " + inactiveUsersCBox.length);

		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try{
			currentCon = DBConnection.getConnection();
			for(int i=0; i<inactiveUsers.length; i++){
				prepStatement = currentCon.prepareStatement("UPDATE manager SET active='1' WHERE am_ika = '" + inactiveUsers[i] + "'");
				System.out.println(" " + inactiveUsers[i]);
				prepStatement.executeUpdate();
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
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
	}
	
	public static List<ManagerInfo> managerList() throws SQLException {
		List<ManagerInfo> managers = new ArrayList<ManagerInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT am_ika, amka, afm, username, firstname, lastname, sex, birthday, photo, photoName, CV, CVName, email, phone_number, department, job_position, salary, date_of_recruitment FROM manager"); 
			while (resultSet.next()) {
				ManagerInfo manager = new ManagerInfo();
				manager.setAm_ika(resultSet.getInt("am_ika"));
				manager.setAmka(resultSet.getInt("amka"));
				manager.setAfm(resultSet.getInt("afm"));
				manager.setUsername(resultSet.getString("username"));
				manager.setFirstname(resultSet.getString("firstname"));
				manager.setLastname(resultSet.getString("lastname"));
				manager.setSex(resultSet.getString("sex"));
				manager.setBirthday(resultSet.getDate("birthday"));
				manager.setPhotoName(resultSet.getString("photoName"));
				manager.setCVName(resultSet.getString("CVName"));
				manager.setEmail(resultSet.getString("email"));
				manager.setPhone_number(resultSet.getString("phone_number"));
				manager.setDepartment(resultSet.getString("department"));
				manager.setJobPosition(resultSet.getString("job_position"));
				manager.setSalary(resultSet.getInt("salary"));
				manager.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				managers.add(manager);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
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
		return managers; //returning the list of managers
	}
}
