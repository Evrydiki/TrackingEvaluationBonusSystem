package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EditGoalInfoDao;
import mvc.com.dao.EmpInfoDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteEmployeeManagerServlet")
public class DeleteEmployeeManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userSession = (String) session.getAttribute("username");
		System.out.println("Session: " + userSession);
		int amika = Integer.parseInt(request.getParameter("am_ika")); //current id from form
		System.out.println("The current employee is: " + amika);
		EmpInfoDao delete = new EmpInfoDao();
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);

		if (operation.trim().equalsIgnoreCase("DeleteEmployee")){
			if(delete.deleteEmployee(amika)){
				request.setAttribute("deleteSuccessMessage", "Η διαγραφή του εργαζομένου πραγματοποιήθηκε με επιτυχία!");
			}
			else{
				request.setAttribute("deleteFailMessage", "Η διαγραφή του εργαζομένου απέτυχε!");
			}
			request.getRequestDispatcher("/empty.jsp").forward(request, response); 	
		}
		else if (operation.trim().equalsIgnoreCase("DeleteManager")){
			if(delete.deleteManager(amika)){
				request.setAttribute("deleteSuccessMessage", "Η διαγραφή του manager πραγματοποιήθηκε με επιτυχία!");
			}
			else{
				request.setAttribute("deleteFailMessage", "Η διαγραφή του manager απέτυχε!");
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
