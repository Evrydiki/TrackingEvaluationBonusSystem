package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EditGoalInfoDao;
import mvc.com.dao.EmpInfoDao;

/**
 * Servlet implementation class displayEmpInfoServlet
 */

@WebServlet("/EmpInfoServlet")
public class EmpInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null){
			request.setAttribute("noSessionMessage", "Η συνεδρία σας έχει λήξει, παρακαλώ συνδεθείτε. ");
			response.sendRedirect("/login.jsp");
		}
		String usernameSession = (String) session.getAttribute("username");
		System.out.println("UsernameSession emplist: " + usernameSession);

		try {
			List<EmpInfo> employees = EmpInfoDao.employeeList();
			session.setAttribute("employees", employees); // Will be available as ${employees} in JSP
			for (EmpInfo employee:employees){
				for (EmpInfo employeeFromList:employees){
					session.setAttribute("employeeFromList", employeeFromList);
					if(employee.getUsername().equals(usernameSession)){
						System.out.println("AMIKA: " + employee.getAm_ika() + " Firstname: " + employee.getFirstname() );						
						session.setAttribute("employee", employee); //bean to show employee info in profile
					}
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain employees from DB", e);
		}

		try {
			List<ManagerInfo> managers = EmpInfoDao.managerList();
			request.setAttribute("managers", managers); // Will be available as ${managers} in JSP
			for(ManagerInfo manager:managers){
				System.out.println("AMIKA: " + manager.getAm_ika() + " Firstname: " + manager.getFirstname() );
				if(manager.getUsername().equals(usernameSession)){
					System.out.println("AMIKA: " + manager.getAm_ika() + " Firstname: " + manager.getFirstname() );
					session.setAttribute("manager", manager); //bean to show manager info in profile
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain managers from DB", e);
		}

		try {
			List<ManagerInfo> activeManagers = EmpInfoDao.activeManagerList();
			request.setAttribute("activeManagers", activeManagers); // Will be available as ${activeManagers} in JSP
			for(ManagerInfo activeManager:activeManagers){
				System.out.println("active AMIKA: " + activeManager.getAm_ika() + " Firstname: " + activeManager.getFirstname() );
				session.setAttribute("activeManagers", activeManagers); //bean to show manager info in profile
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain inactiveManagers from DB", e);
		}

		try {
			List<EmpInfo> activeEmployees = EmpInfoDao.activeEmployeeList();
			request.setAttribute("activeEmployees", activeEmployees); // Will be available as ${activeEmployees} in JSP
			for (EmpInfo activeEmployee:activeEmployees){
				if(activeEmployee.getUsername().equals(usernameSession)){
					System.out.println("AMIKA: " + activeEmployee.getAm_ika() + " Firstname: " + activeEmployee.getFirstname() );						
					session.setAttribute("employee", activeEmployee); //bean to show employee info in profile
				}			
				}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain inactiveEmployees from DB", e);
		}

		try {
			List<ManagerInfo> inactiveManagers = EmpInfoDao.inactiveManagerList();
			request.setAttribute("inactiveManagers", inactiveManagers); // Will be available as ${inactiveManagers} in JSP
			for(ManagerInfo inactiveManager:inactiveManagers){
				System.out.println("inactive AMIKA: " + inactiveManager.getAm_ika() + " Firstname: " + inactiveManager.getFirstname() );
				session.setAttribute("inactiveManagers", inactiveManagers); //bean to show manager info in profile
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain inactiveManagers from DB", e);
		}

		try {
			List<EmpInfo> inactiveEmployees = EmpInfoDao.inactiveEmployeeList();
			request.setAttribute("inactiveEmployees", inactiveEmployees); // Will be available as ${inactiveEmployees} in JSP
			for (EmpInfo inactiveEmployee:inactiveEmployees){
				System.out.println("inactive AMIKA: " + inactiveEmployee.getAm_ika() + " Firstname: " + inactiveEmployee.getFirstname() );
				session.setAttribute("inactiveEmployees", inactiveEmployees); //bean to show manager info in profile
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain inactiveEmployees from DB", e);
		}

		//		if(usernameSession.equals("admin")){
		//			session.setAttribute("username", "admin");
		//		}
	}

}
