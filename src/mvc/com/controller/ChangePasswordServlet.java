package mvc.com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EmpInfoDao;
import mvc.com.dao.EvaluationDao;
import mvc.com.dao.LoginDao;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = request.getParameter("passwordTF");   
		LoginDao authenticateDao = new LoginDao();

		String managerUsernameSession = null,  employeeUsernameSession = null;
		int managerAmikaSession=0;
		int employeeAmikaSession=0;
		int currentAmika=0;
		String authenticateEmployee = null , authenticateManager = null;
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		
		
		if (employee!=null && employee.getAttribute().equalsIgnoreCase("employee")){
			employeeUsernameSession = employee.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
			employeeAmikaSession = employee.getAm_ika();
			System.out.println("AMIKA edit employee: " + employeeAmikaSession);
			authenticateEmployee = authenticateDao.authenticateEmployee(employeeUsernameSession, pass);
		}
		if (manager!=null){
			currentAmika = Integer.parseInt(request.getParameter("am_ika")); //current employee amika from form
			System.out.println("The current manager is: " + currentAmika);
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);
			authenticateManager = authenticateDao.authenticateManager(managerUsernameSession, pass); 
		}

		EmpInfoDao empDao = new EmpInfoDao();
		String success = "Η αλλαγή του κωδικού σας πρόσβασης, πραγματοποιήθηκε με επιτυχία!";

		if(authenticateManager != null&&authenticateManager.trim().equalsIgnoreCase("SUCCESS")) {
			if(empDao.changePassword(request, managerUsernameSession, "manager", pass)){
				request.setAttribute("successMessage", success); 
				
			}
		}
		else if(authenticateEmployee != null&&authenticateEmployee.trim().equalsIgnoreCase("SUCCESS")){
			if(empDao.changePassword(request, employeeUsernameSession, "employee", pass)){
				request.setAttribute("successMessage", success); 
			}
		}
		else
		{
			request.setAttribute("errorMessage", "Λάθος κωδικός πρόσβασης, προσπαθείστε ξανά!"); 
		}
		request.getRequestDispatcher("/changePassword.jsp").forward(request, response); //forward the request
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
