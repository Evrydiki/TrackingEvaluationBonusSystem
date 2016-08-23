package mvc.com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mvc.com.bean.GoalInfo;
import mvc.com.util.DBConnection;

public class GoalInfoDao {

	public static int getTotalBonusByEmployee(int employee_am_ika) throws SQLException, IOException {
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int total_bonus = 0;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT sum(bonus_ammount) FROM trackingdb.goal AS g INNER JOIN trackingdb.employee_has_goal AS h ON g.id_goal=h.goal_id_goal1 AND h.employee_am_ika1='" + employee_am_ika + "' AND g.completed = '" + "YES" + "'"); 
			while (resultSet.next()) {
				total_bonus = resultSet.getInt(1);
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
		return total_bonus; 
	}
	
	public static List<GoalInfo> allGoalsList() throws SQLException, IOException {
		List<GoalInfo> allGoals = new ArrayList<GoalInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM goal"); 
			while (resultSet.next()) {
				GoalInfo goal = new GoalInfo();
				goal.setId(resultSet.getInt("id_goal"));
				goal.setTitle(resultSet.getString("title"));
				goal.setCompleted(resultSet.getString("completed"));
				goal.setDeadline(resultSet.getDate("deadline"));
				goal.setDirections(resultSet.getString("directions"));
				goal.setResources_needed(resultSet.getString("resources_needed"));
				goal.setComplexity(resultSet.getInt("complexity"));
				goal.setSignificance(resultSet.getInt("significance"));
				goal.setType(resultSet.getString("goal_type"));
				goal.setConcerning_department(resultSet.getString("concerning_department"));
				goal.setBonus_ammount(resultSet.getInt("bonus_ammount"));
				goal.setIn_progress(resultSet.getString("in_progress"));
				allGoals.add(goal); 
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
		return allGoals; //returning the list of completed goals
	}
	
	public static List<GoalInfo> incompletedList() throws SQLException, IOException {
		List<GoalInfo> incompletedGoals = new ArrayList<GoalInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM goal WHERE completed='NO'"); 
			while (resultSet.next()) {
				GoalInfo goal = new GoalInfo();
				goal.setId(resultSet.getInt("id_goal"));
				goal.setTitle(resultSet.getString("title"));
				goal.setCompleted(resultSet.getString("completed"));
				goal.setDeadline(resultSet.getDate("deadline"));
				goal.setDirections(resultSet.getString("directions"));
				goal.setResources_needed(resultSet.getString("resources_needed"));
				goal.setComplexity(resultSet.getInt("complexity"));
				goal.setSignificance(resultSet.getInt("significance"));
				goal.setType(resultSet.getString("goal_type"));
				goal.setConcerning_department(resultSet.getString("concerning_department"));
				goal.setBonus_ammount(resultSet.getInt("bonus_ammount"));
				goal.setIn_progress(resultSet.getString("in_progress"));
				incompletedGoals.add(goal); 
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
		return incompletedGoals; //returning the list of completed goals
	}

	public static List<GoalInfo> completedList() throws SQLException, IOException {
		List<GoalInfo> completedGoals = new ArrayList<GoalInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM goal WHERE completed='YES'"); 
			while (resultSet.next()) {
				GoalInfo goal = new GoalInfo();
				goal.setId(resultSet.getInt("id_goal"));
				goal.setTitle(resultSet.getString("title"));
				goal.setCompleted(resultSet.getString("completed"));
				goal.setDeadline(resultSet.getDate("deadline"));
				goal.setDirections(resultSet.getString("directions"));
				goal.setResources_needed(resultSet.getString("resources_needed"));
				goal.setComplexity(resultSet.getInt("complexity"));
				goal.setSignificance(resultSet.getInt("significance"));
				goal.setType(resultSet.getString("goal_type"));
				goal.setConcerning_department(resultSet.getString("concerning_department"));
				goal.setBonus_ammount(resultSet.getInt("bonus_ammount"));
				goal.setIn_progress(resultSet.getString("in_progress"));
				completedGoals.add(goal);
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
		return completedGoals; //returning the list of completed goals
	}
	
	public static List<GoalInfo> inProgressList() throws SQLException, IOException {
		List<GoalInfo> inProgressGoals = new ArrayList<GoalInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM goal WHERE in_progress='YES'"); 
			while (resultSet.next()) {
				GoalInfo goal = new GoalInfo();
				goal.setId(resultSet.getInt("id_goal"));
				goal.setTitle(resultSet.getString("title"));
				goal.setCompleted(resultSet.getString("completed"));
				goal.setDeadline(resultSet.getDate("deadline"));
				goal.setDirections(resultSet.getString("directions"));
				goal.setResources_needed(resultSet.getString("resources_needed"));
				goal.setComplexity(resultSet.getInt("complexity"));
				goal.setSignificance(resultSet.getInt("significance"));
				goal.setType(resultSet.getString("goal_type"));
				goal.setConcerning_department(resultSet.getString("concerning_department"));
				goal.setBonus_ammount(resultSet.getInt("bonus_ammount"));
				goal.setIn_progress(resultSet.getString("in_progress"));
				inProgressGoals.add(goal); 
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
		return inProgressGoals; //returning the list of completed goals
	}
}
