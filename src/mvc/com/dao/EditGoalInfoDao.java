package mvc.com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import mvc.com.util.DBConnection;

public class EditGoalInfoDao {

	public boolean edit(HttpServletRequest request, int id) {
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

		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "UPDATE goal SET title=?, deadline=?, directions=?, resources_needed=?, complexity=?, significance=?, goal_type=?, concerning_department=?, bonus_ammount=? WHERE id_goal = '" + id + "'"; 
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
			prepStatement.executeUpdate(); 
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}

	public int edittingManagerRights(int managerSession, int id) throws SQLException, IOException {
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int manager_am_ika = 0;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT manager_am_ika1 FROM employee_has_goal WHERE goal_id_goal1='" + id + "'"); 
			while (resultSet.next()) {
				manager_am_ika = resultSet.getInt("manager_am_ika1");
			}
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
		return manager_am_ika;
	}

	public static Set<Integer> assignedEmployeesList(int managerSession, int id) throws SQLException, IOException {
		Set<Integer> assignedEmployees = new HashSet<Integer>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT employee_am_ika1 FROM employee_has_goal WHERE goal_id_goal1='" + id + "'"); 
			while (resultSet.next()) {
				Integer am_ika = resultSet.getInt("employee_am_ika1");
				assignedEmployees.add(am_ika); 
			}
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
		return assignedEmployees; //returning the list of assigned employees
	}

	public static Set<Integer> unassignedEmployeesList(int managerSession, int id) throws SQLException, IOException {
		Set<Integer> unassignedEmployees = new HashSet<Integer>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT e.am_ika, e.firstname, e.lastname FROM employee AS e left join employee_has_goal AS h ON h.employee_am_ika1=e.am_ika AND goal_id_goal1='" + id + "' WHERE employee_am_ika1 IS NULL");
			while (resultSet.next()) {
				Integer am_ika = resultSet.getInt("am_ika");
				unassignedEmployees.add(am_ika); 
			}
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
		return unassignedEmployees; //returning the list of assigned employees
	}

	public boolean assignEditGoal(int managerSession, HttpServletRequest request, int id)
	{
		String[] amikaEmployeesAssigned = request.getParameterValues("employeeAssignCBOX");
		int[] amikaEmployeesAssignedCBox = new int[amikaEmployeesAssigned.length];
		System.out.println("Checked: ");
		for(int i=0; i<amikaEmployeesAssigned.length; i++){
			amikaEmployeesAssignedCBox[i] = Integer.parseInt(amikaEmployeesAssigned[i]);
			System.out.println(", " + amikaEmployeesAssignedCBox[i]);
		}
		System.out.println("Checkbox Length: " + amikaEmployeesAssignedCBox.length);

		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		//PreparedStatement prep = null;	

		//delete current assigned employees and insert the new checked ones by user
		try
		{
			currentCon = DBConnection.getConnection();
			int[] rowsUpdated = new int[0];
			String delete_query = "DELETE FROM employee_has_goal WHERE goal_id_goal1='"  + id + "'"; 
			prepStatement = currentCon.prepareStatement(delete_query);		
			prepStatement.executeUpdate(); 

			String insert_query = "INSERT INTO employee_has_goal (manager_am_ika1, employee_am_ika1, goal_id_goal1) VALUES (?, ?, ?)";
			prepStatement = currentCon.prepareStatement(insert_query);

			for (int i = 0; i < amikaEmployeesAssignedCBox.length; i++) {
				System.out.println(amikaEmployeesAssignedCBox[i] + ": to assign..");
				prepStatement.setInt(1, managerSession);
				prepStatement.setInt(2, amikaEmployeesAssignedCBox[i]); 
				prepStatement.setInt(3, id);
				prepStatement.addBatch();
			}
			rowsUpdated = prepStatement.executeBatch(); 
			if (rowsUpdated.length > 0) {
				System.out.println("Goal Assigned to Employee got Editted!");
				return true;
			}
		}
	catch (SQLException e) {
		System.out.println(e.getMessage());
		return false;
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

public boolean delete(int id) {
	Connection currentCon = null;
	PreparedStatement prepStatement = null;
	try
	{
		currentCon = DBConnection.getConnection(); 
		String delete_query = "DELETE FROM goal WHERE id_goal='"  + id + "'"; 
		prepStatement = currentCon.prepareStatement(delete_query);		
		prepStatement.executeUpdate(); 
		return true;
	}
	catch (SQLException e) {
		System.out.println(e.getMessage());
		return false;
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
}
