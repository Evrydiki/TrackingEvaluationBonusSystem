package mvc.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.dao.InsertNewGoalDao;
import mvc.com.bean.ManagerInfo;

/**
 * Servlet implementation class InsertNewGoalServlet
 */
@WebServlet("/InsertNewGoalServlet")
public class InsertNewGoalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		System.out.println("AMIKA manager: " + manager.getAm_ika());
		
		InsertNewGoalDao insertNewGoalDao = new InsertNewGoalDao();
		if(!session.isNew()&&insertNewGoalDao.submitGoal(request)&&insertNewGoalDao.assignGoal(manager.getAm_ika(),  request)){			
			request.setAttribute("submitSuccessGoalMessage", "Ο στόχος σας καταχωρήθηκε με επιτυχία!");			
		}
		else{
			request.setAttribute("submitFailGoalMessage", "Παρακαλούμε ξανά ελέγξτε όλα τα πεδία!");
		}
		request.getRequestDispatcher("/goalsTabbed.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
