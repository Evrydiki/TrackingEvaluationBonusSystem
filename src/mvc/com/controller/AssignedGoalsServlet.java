package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EditGoalInfoDao;

/**
 * Servlet implementation class AssignedGoalsServlet
 */
@WebServlet("/AssignedGoalsServlet")
public class AssignedGoalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String managerUsernameSession = null,  employeeUsernameSession = null;
		int managerAmikaSession=0;
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		if (employee!=null){
			employeeUsernameSession = employee.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
		}
		if (manager!=null){
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);
		}

		int id = Integer.parseInt(request.getParameter("id")); //current id from form
		if (id!=0){
			System.out.println("The current goal is: " + id);
		}
		else{
			System.out.println("There is not a current id goal.");
		}
		
		try {
			Set<Integer> assignedEmployees = EditGoalInfoDao.assignedEmployeesList(managerAmikaSession, id);
			session.setAttribute("assignedEmployees", assignedEmployees); // Will be available as ${assignedEmployees} in JSP
			for(Integer assignedEmployee:assignedEmployees){
				System.out.println("assigned AMIKA: " + assignedEmployee);
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain assigned employees from DB", e);
		}

		try {
			Set<Integer> unassignedEmployees = EditGoalInfoDao.unassignedEmployeesList(managerAmikaSession, id);
			request.setAttribute("unassignedEmployees", unassignedEmployees); // Will be available as ${unassignedEmployees} in JSP
			for(Integer unassignedEmployee:unassignedEmployees){
				System.out.println("Unassigned AMIKA: " + unassignedEmployee);
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain unassigned employees from DB", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
