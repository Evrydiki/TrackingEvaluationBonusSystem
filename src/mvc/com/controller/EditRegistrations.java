package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.dao.EmpInfoDao;

/**
 * Servlet implementation class EditRegistrations
 */
@WebServlet("/EditRegistrations")
public class EditRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("username");
		System.out.println("Session: " + userSession);
		int amika = Integer.parseInt(request.getParameter("am_ika")); //current employee amika from form
		System.out.println("The current employee is: " + amika);
		EmpInfoDao edit = new EmpInfoDao();
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);

		if(operation.trim().equalsIgnoreCase("Edit Employee")){
			try {
				if(edit.editEmployee(request, amika)){
					request.setAttribute("editEmployeeSuccessMessage", "Η επεξεργασία των στοιχείων πραγματοποιήθηκε με επιτυχία!");
				}
				else{
					request.setAttribute("editEmployeeFailMessage", "Η επεξεργασία των στοιχείων απέτυχε!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/editRegistrationsFrame.jsp").forward(request, response);
		}
		else if(operation.trim().equalsIgnoreCase("Edit Manager")){
			try {
				if(edit.editManager(request, amika)){
					request.setAttribute("editEmployeeSuccessMessage", "Η επεξεργασία των στοιχείων πραγματοποιήθηκε με επιτυχία!");
				}
				else{
					request.setAttribute("editEmployeeFailMessage", "Η επεξεργασία των στοιχείων απέτυχε!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/editRegistrationsFrame.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
