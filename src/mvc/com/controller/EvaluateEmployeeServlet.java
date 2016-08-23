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

import mvc.com.bean.CategoryInfo;
import mvc.com.bean.EmpInfo;
import mvc.com.bean.EvaluationlInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EvaluationDao;

/**
 * Servlet implementation class EmployeeGoalServlet
 */
@WebServlet("/EvaluateEmployeeServlet")
public class EvaluateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		String department = manager.getDepartment();
		System.out.println("AMIKA manager: " + manager.getAm_ika() + " Department: " + department);
	
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);
		EvaluationDao evaluationDao = new EvaluationDao();
		
		int amika = Integer.parseInt(request.getParameter("am_ika")); //current employee amika from form
		String empDepartment = request.getParameter("departmentTF");
		System.out.println("The current employee is: " + amika + " and belongs to" + empDepartment);
		
		if(operation.trim().equalsIgnoreCase("ΥΠΟΒΟΛΗ ΑΞΙΟΛΟΓΗΣΗΣ")){
			if(!department.trim().equalsIgnoreCase(empDepartment)){
				request.setAttribute("evaluationFailMessage", "Δεν έχετε τα δικαιώματα να πραγματοποιήσετε αυτή την αξιολόγηση, λόγω του ότι ο υπάλληλος δεν ανήκει στο τμήμα σας.");
			}
			else if(evaluationDao.evaluateEmployee(amika, request)&&evaluationDao.ratingPerCategory(request)){
				request.setAttribute("evaluationSuccessMessage", "Η υποβολή αξιολόγησης πραγματοποιήθηκε με επιτυχία!");
			}
			else{
				request.setAttribute("evaluationFailMessage", "Η υποβολή αξιολόγησης απέτυχε!");
			}
			request.getRequestDispatcher("/evaluationFrame.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
