package mvc.com.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import mvc.com.util.DBConnection;

public class InsertNewGoalDao {

	public boolean submitGoal(HttpServletRequest request) {
		//retrieve parameters
		int goalSignificance = 0, goalComplexity = 0;
		String goalType = null;
		String goalTitle = request.getParameter("goalTitleTF");
		System.out.println("goalTitle: " + goalTitle);
		String goalDeadline = request.getParameter("goalDeadlineTF");
		System.out.println("Uformatted goalDeadline:  " + goalDeadline);
		Date goal_deadline = null;
		if (goalDeadline!=null){
			try {
				goal_deadline = new SimpleDateFormat("yyyy-MM-dd").parse(goalDeadline);
			} catch (ParseException e1) {
				System.out.println("Cannot parse goalDeadline");
				e1.printStackTrace();
			} 
		}
		System.out.println("goalDeadline: " + goal_deadline);
		String goalGuides = request.getParameter("goalGuidesTF");
		System.out.println("goalGuides: " + goalGuides);
		String goalResourcesAssigned = request.getParameter("goalResourcesAssignedTF");
		System.out.println("goalResourcesAssigned: " + goalResourcesAssigned);		
		goalComplexity = Integer.parseInt(request.getParameter("goalComplexityTF")); 
		System.out.println("goalComplexity = " + goalComplexity);
		if (goalComplexity!=0){
			if (goalComplexity == 1){
				goalComplexity = 1;
			}
			else if(goalComplexity == 2) {
				goalComplexity = 2;
			}
			else if(goalComplexity == 3) {
				goalComplexity = 3;
			}
			else if(goalComplexity == 4) {
				goalComplexity = 4;
			}
			else {
				goalComplexity = 5;
			}
		}
		goalSignificance = Integer.parseInt(request.getParameter("goalSignificanceTF")); 
		System.out.println("goalSignificance = " + goalSignificance);
		if (goalSignificance!=0){
			if (goalSignificance == 1){
				goalSignificance = 1;
			}
			else if(goalSignificance == 2) {
				goalSignificance = 2;
			}
			else if(goalSignificance == 3) {
				goalSignificance = 3;
			}
			else if(goalSignificance == 4) {
				goalSignificance = 4;
			}
			else {
				goalSignificance = 5;
			}
		}
		goalType = request.getParameter("goalTypeTF"); 
		if (goalType!=null){
			if (goalType.equals("short-term")){
				goalType = "short-term";
			}
			else if(goalType.equals("medium-term")){
				goalType = "medium-term";
			}
			else if(goalType.equals("long-term")){
				goalType = "long-term";
			}
		}
		String concerningDepartment = request.getParameter("concerningDepartmentTF");
		int goalBonusAmmount = Integer.parseInt(request.getParameter("goalBonusAmmountTF")); 

		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		int rowsUpdated = 0;

		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "INSERT INTO goal (title, deadline, directions, resources_needed, complexity, significance, goal_type, concerning_department, bonus_ammount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			//9 values to insert
			prepStatement = currentCon.prepareStatement(query);
			prepStatement.setString(1, goalTitle);
			prepStatement.setDate(2, new java.sql.Date(goal_deadline.getTime())); 
			prepStatement.setString(3, goalGuides); 
			prepStatement.setString(4, goalResourcesAssigned); 
			prepStatement.setInt(5, goalComplexity); 
			prepStatement.setInt(6, goalSignificance);
			prepStatement.setString(7, goalType); 
			prepStatement.setString(8, concerningDepartment);
			prepStatement.setInt(9, goalBonusAmmount);			
			rowsUpdated = prepStatement.executeUpdate(); 
			if (rowsUpdated > 0) {
				System.out.println("Goal Inserted!");
				return true;
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
		return false;
	}

	public boolean assignGoal(int manager_am_ika, HttpServletRequest request)
	{
		String[] amikaEmployeesAssigned = request.getParameterValues("employeeAssignCBOX");
		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		ResultSet resultSet = null;
		int[] rowsUpdated = new int[0];
		int id = -1;

		try
		{
			currentCon = DBConnection.getConnection(); 
			String last_insert_query = "SELECT max(id_goal) FROM goal ";
			prepStatement = currentCon.prepareStatement(last_insert_query);
			resultSet = prepStatement.executeQuery(last_insert_query); 				
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				System.out.println("ID = " + id);
			}
			String insert_query = "INSERT INTO employee_has_goal (manager_am_ika1, employee_am_ika1, goal_id_goal1) VALUES (?, ?, ?)"; 
			//3 values to insert
			prepStatement = currentCon.prepareStatement(insert_query);
			if(amikaEmployeesAssigned.length > 0){
				for (int i = 0; i < amikaEmployeesAssigned.length; i++) {
					System.out.println(amikaEmployeesAssigned[i] + " ");
					prepStatement.setInt(1, manager_am_ika);
					prepStatement.setString(2, amikaEmployeesAssigned[i]); 
					prepStatement.setInt(3, id);
					prepStatement.addBatch();
				}
				rowsUpdated = prepStatement.executeBatch(); 
				if (rowsUpdated.length > 0) {
					System.out.println("Goal Assigned to Employee!");
					return true;
				}
			}
			else{
				return false;
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
		return false;
	}
}
