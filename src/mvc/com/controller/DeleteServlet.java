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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		int managerSession = manager.getAm_ika();
		System.out.println("AMIKA edit manager: " + managerSession);
		int id = Integer.parseInt(request.getParameter("id")); //current id from form
		System.out.println("The current goal id is: " + id);
		EditGoalInfoDao deleteGoal = new EditGoalInfoDao();
		String operation = request.getParameter("operation");
		System.out.println("Operation: " + operation);

		int managerToEdit = 0;
		try {
			managerToEdit = deleteGoal.edittingManagerRights(managerSession, id);
			System.out.println("managerToEdit: " + managerToEdit);
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain manager_am_ika from DB", e);
		}

		if (operation.trim().equalsIgnoreCase("Delete")){
			if(managerSession != managerToEdit){
				System.out.println("No rights to delete!");
				request.setAttribute("deleteFailMessage", "Δεν έχετε τα κατάλληλα δικαιώματα για να διαγράψετε το συγκεκριμένο στόχο. Μόνο ο δημιουργός του μπορεί να τον διαγράψει.");
			}
			else if(deleteGoal.delete(id)&&managerSession == managerToEdit){
				request.setAttribute("deleteSuccessMessage", "Η διαγραφή του στόχου πραγματοποιήθηκε με επιτυχία!");
			}
			else{
				request.setAttribute("deleteFailMessage", "Η διαγραφή του στόχου απέτυχε!");
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
