package mvc.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.dao.AcceptRejectDAO;
import mvc.com.dao.EmployeeGoalInfoDao;

/**
 * Servlet implementation class ApproveRejectRegistrations
 */
@WebServlet("/ApproveRejectRegistrations")
public class ApproveRejectRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("username");
		System.out.println("Session: " + userSession);
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);
		AcceptRejectDAO acceptDao = new AcceptRejectDAO();
		String successMessage = "Η αποδοχή εγγραφών πραγματοποιήθηκε με επιτυχία!";
		
		String[] inactiveEmployees = request.getParameterValues("inactiveEmployeesCBOX");
		String[] inactiveManagers = request.getParameterValues("inactiveManagersCBOX");

		if(userSession.equals("admin")){
			if(operation.trim().equalsIgnoreCase("ΕΓΚΡΙΣΗ")){				
				if(inactiveEmployees != null && inactiveManagers != null){
					acceptDao.acceptEmployees(inactiveEmployees);
					acceptDao.acceptManagers(inactiveManagers);
					request.setAttribute("editSuccessMessage", successMessage);
				}
				else if(inactiveEmployees != null){
					acceptDao.acceptEmployees(inactiveEmployees);
					request.setAttribute("editSuccessMessage", successMessage);
				}
				else if(inactiveManagers != null){
					acceptDao.acceptManagers(inactiveManagers);
					request.setAttribute("editSuccessMessage", successMessage);
				}
				else if(inactiveEmployees == null && inactiveManagers == null){
					request.setAttribute("message", "Πρέπει να επιλέξετε έναν ή περισσότερους εργαζόμενους για να συνεχίσετε.");
				}
				else{
					request.setAttribute("editFailMessage", "Η αποδοχή εγγραφών απέτυχε!");
				}
			}
			request.getRequestDispatcher("/acceptRejectRegistrations.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
