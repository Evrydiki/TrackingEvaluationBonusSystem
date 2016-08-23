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
import mvc.com.dao.EditGoalInfoDao;
import mvc.com.dao.EmployeeGoalInfoDao;

/**
 * Servlet implementation class EmployeeGoalServlet
 */
@WebServlet("/EmployeeGoalServlet")
public class EmployeeGoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		int employeeSession = employee.getAm_ika();
		System.out.println("AMIKA session employee: " + employeeSession);
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);
		EmployeeGoalInfoDao empGoal = new EmployeeGoalInfoDao();
		
		int id = 0;
		id = Integer.parseInt(request.getParameter("id")); //current id from form
		if (id!=0){
			System.out.println("The current goal is: " + id);
		}
		else{
			System.out.println("There is not a current id goal.");
		}  
		
		if(operation.trim().equalsIgnoreCase("ΥΠΟΒΟΛΗ ΑΛΛΑΓΩΝ")){
			if(empGoal.markAsCompleted(id, request)&&empGoal.markInProgress(id, request)){
				request.setAttribute("editGoalSuccessMessage", "Οι αλλαγές σας πραγματοποιήθηκαν με επιτυχία!");
			}
			else{
				request.setAttribute("editGoalFailMessage", "Η υποβολή αλλαγών απέτυχε!");
			}
			request.getRequestDispatcher("/empty.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
