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
import mvc.com.dao.GoalInfoDao;
import mvc.com.dao.InsertNewGoalDao;
import mvc.com.dao.EditGoalInfoDao;
import mvc.com.dao.EmpInfoDao;

/**
 * Servlet implementation class EditGoalInfoServlet
 */
@WebServlet("/EditGoalInfoServlet")
public class EditGoalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		int managerSession = manager.getAm_ika();
		System.out.println("AMIKA edit manager: " + managerSession);
		int id = 0;
		id = Integer.parseInt(request.getParameter("id")); //current id from form
		if (id!=0){
			System.out.println("The current goal is: " + id);
		}
		else{
			System.out.println("There is not a current id goal.");
		}
		EditGoalInfoDao editGoal = new EditGoalInfoDao();
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);

		int managerToEdit = 0;
		try {
			managerToEdit = editGoal.edittingManagerRights(managerSession, id);
			System.out.println("managerToEdit: " + managerToEdit);
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain manager_am_ika from DB", e);
		}

		try {
			List<EmpInfo> employees = EmpInfoDao.employeeList();	
			session.setAttribute("employees", employees); // Will be available as ${employees} in JSP
			Set<Integer> assignedEmployees = (Set<Integer>) session.getAttribute("assignedEmployees");
			for (Integer assignedEmployee:assignedEmployees){				
				for (EmpInfo employee:employees){
					if(employee.getAm_ika()==assignedEmployee){
						System.out.println("Matched assigned AMIKA: " + assignedEmployee + ": " + employee.getAm_ika());
					}
				}
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain employees from DB", e);
		}
		
		try {
			Set<Integer> assignedEmployees = EditGoalInfoDao.assignedEmployeesList(managerSession, id);
			session.setAttribute("assignedEmployees", assignedEmployees); // Will be available as ${assignedEmployees} in JSP
			for(Integer assignedEmployee:assignedEmployees){
				System.out.println("assigned AMIKA: " + assignedEmployee);
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain assigned employees from DB", e);
		}

		try {
			Set<Integer> unassignedEmployees = EditGoalInfoDao.unassignedEmployeesList(managerSession, id);
			request.setAttribute("unassignedEmployees", unassignedEmployees); // Will be available as ${unassignedEmployees} in JSP
			for(Integer unassignedEmployee:unassignedEmployees){
				System.out.println("Unassigned AMIKA: " + unassignedEmployee);
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain unassigned employees from DB", e);
		}

		if(operation.trim().equalsIgnoreCase("Edit")){
			if(managerSession != managerToEdit){
				System.out.println("No rights to edit! " + "managerSession: " + managerSession + " managerToEdit: " + managerToEdit);
				request.setAttribute("editGoalFailMessage", "Δεν έχετε τα κατάλληλα δικαιώματα για να αλλάξετε το συγκεκριμένο στόχο. Μόνο ο δημιουργός του μπορεί να τον επεξεργαστεί.");
				request.getRequestDispatcher("/empty.jsp").forward(request, response);
			}
			else if(editGoal.edit(request, id)&&editGoal.assignEditGoal(managerSession, request, id)&&managerSession==managerToEdit){
				request.setAttribute("editGoalSuccessMessage", "Η επεξεργασία των στοιχείων πραγματοποιήθηκε με επιτυχία!");
			}
			else{
				request.setAttribute("editGoalFailMessage", "Η επεξεργασία των στοιχείων απέτυχε!");
			}
			request.getRequestDispatcher("/editGoalFrame.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
