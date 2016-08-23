package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.GoalInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EditGoalInfoDao;
import mvc.com.dao.EmployeeGoalInfoDao;
import mvc.com.dao.GoalInfoDao;

/**
 * Servlet implementation class goalInfoServlet
 */
@WebServlet("/GoalInfoServlet")
public class GoalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String managerUsernameSession = null,  employeeUsernameSession = null;
		int managerAmikaSession=0;
		int employeeAmikaSession=0;
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		if (employee!=null){
			employeeUsernameSession = employee.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
			employeeAmikaSession = employee.getAm_ika();
			System.out.println("AMIKA edit employee: " + employeeAmikaSession);
		}
		if (manager!=null){
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);
		}
		
		
		try {
			int total_bonus = GoalInfoDao.getTotalBonusByEmployee(employeeAmikaSession);
			session.setAttribute("total_bonus", total_bonus);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			List<GoalInfo> allGoals = GoalInfoDao.allGoalsList();
			session.setAttribute("allGoals", allGoals);
			for (GoalInfo goal:allGoals){
				System.out.println("id: " + goal.getId() + " title: " + goal.getTitle() + " Significance: " + goal.getSignificance());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain goals from DB", e);
		}
		try {
			List<GoalInfo> incompletedGoals = GoalInfoDao.incompletedList();
			session.setAttribute("incompletedGoals", incompletedGoals);
			for (GoalInfo incompletedGoal:incompletedGoals){
				System.out.println("id: " + incompletedGoal.getId() + " title: " + incompletedGoal.getTitle() + " Significance: " + incompletedGoal.getSignificance());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain goals from DB", e);
		}
		try {
			List<GoalInfo> completedGoals = GoalInfoDao.incompletedList();
			session.setAttribute("completedGoals", completedGoals);
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain goals from DB", e);
		}
		try {
			List<GoalInfo> inProgressGoals = GoalInfoDao.inProgressList();
			session.setAttribute("inProgressGoals", inProgressGoals);
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain goals from DB", e);
		}
		
		try {
			List<GoalInfo> goalsAssignedToEmployees = EmployeeGoalInfoDao.assignedToEmployeessGoalsList(employeeAmikaSession);
			session.setAttribute("goalsAssignedToEmployees", goalsAssignedToEmployees); // Will be available as ${assignedEmployees} in JSP
			for(GoalInfo goalAssignedToEmployees:goalsAssignedToEmployees){
				System.out.println("Goal id assigned: " + goalAssignedToEmployees.getId());
				System.out.println("Assigned from manager: " + goalAssignedToEmployees.getManager_am_ika());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain assigned goals from DB", e);
		}
	}
}
