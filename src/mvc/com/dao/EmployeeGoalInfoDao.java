package mvc.com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import mvc.com.bean.GoalInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.util.DBConnection;

public class EmployeeGoalInfoDao {

	public static List<GoalInfo> assignedToEmployeessGoalsList(int employeeSession) throws SQLException, IOException, ServletException {

		List<ManagerInfo> managers;
		try {
			managers = EmpInfoDao.managerList();
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain managers from DB", e);
		}	

		List<GoalInfo> goalsAssignedToEmployees = new ArrayList<GoalInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String manager_name = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM trackingdb.goal AS g INNER JOIN trackingdb.employee_has_goal AS h ON g.id_goal = h.goal_id_goal1 AND employee_am_ika1='" + employeeSession + "' ORDER BY g.id_goal"); 
			while (resultSet.next()) {
				GoalInfo assignedToEmployeeGoal = new GoalInfo();
				assignedToEmployeeGoal.setId(resultSet.getInt("id_goal"));
				assignedToEmployeeGoal.setTitle(resultSet.getString("title"));
				assignedToEmployeeGoal.setCompleted(resultSet.getString("completed"));
				assignedToEmployeeGoal.setDeadline(resultSet.getDate("deadline"));
				assignedToEmployeeGoal.setDirections(resultSet.getString("directions"));
				assignedToEmployeeGoal.setResources_needed(resultSet.getString("resources_needed"));
				assignedToEmployeeGoal.setComplexity(resultSet.getInt("complexity"));
				assignedToEmployeeGoal.setSignificance(resultSet.getInt("significance"));
				assignedToEmployeeGoal.setType(resultSet.getString("goal_type"));
				assignedToEmployeeGoal.setConcerning_department(resultSet.getString("concerning_department"));
				assignedToEmployeeGoal.setBonus_ammount(resultSet.getInt("bonus_ammount"));
				assignedToEmployeeGoal.setIn_progress(resultSet.getString("in_progress"));
				int assigningManager = resultSet.getInt("manager_am_ika1");
				assignedToEmployeeGoal.setManager_am_ika(assigningManager);
				for(ManagerInfo manager:managers){
					if(manager.getAm_ika()==assigningManager){
						manager_name = manager.getLastname() + " " + manager.getFirstname();
					}
				}
				if (manager_name != null){
					System.out.println("manager_name: " + manager_name);
					assignedToEmployeeGoal.setManager_name(manager_name);
				}
				goalsAssignedToEmployees.add(assignedToEmployeeGoal); 
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
		return goalsAssignedToEmployees; //returning the list of completed goals
	}


	public boolean markAsCompleted(int id, HttpServletRequest request){
		String completed = request.getParameter("completedTF");
		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try{
			currentCon = DBConnection.getConnection();
			String query_no = "UPDATE goal SET completed='NO' WHERE id_goal = '" + id + "'";
			String query_yes = "UPDATE goal SET completed='YES' WHERE id_goal = '" + id + "'";
				if (completed.equalsIgnoreCase("YES")){
					prepStatement = currentCon.prepareStatement(query_yes);		
					prepStatement.executeUpdate();
				}
				else{
					prepStatement = currentCon.prepareStatement(query_no);
					prepStatement.executeUpdate();
				}
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
	
	public boolean markInProgress(int id, HttpServletRequest request){
		String inProgress = request.getParameter("inprogressTF");
		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try{
			currentCon = DBConnection.getConnection();
			String query_no = "UPDATE goal SET in_progress='NO' WHERE id_goal = '" + id + "'";
			String query_yes = "UPDATE goal SET in_progress='YES' WHERE id_goal = '" + id + "'";
				if (inProgress.equalsIgnoreCase("YES")){
					prepStatement = currentCon.prepareStatement(query_yes);		
					prepStatement.executeUpdate();
				}
				else{
					prepStatement = currentCon.prepareStatement(query_no);
					prepStatement.executeUpdate();
				}
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