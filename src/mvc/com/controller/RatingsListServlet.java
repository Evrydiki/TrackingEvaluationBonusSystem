package mvc.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.CategoryInfo;
import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EvaluationDao;

/**
 * Servlet implementation class RatingsListServlet
 */
@WebServlet("/RatingsListServlet")
public class RatingsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String managerUsernameSession = null,  employeeUsernameSession = null;
		int managerAmikaSession=0;
		int employeeAmikaSession=0;
		int currentAmika=0;
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		if (employee!=null){
			employeeUsernameSession = employee.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
			employeeAmikaSession = employee.getAm_ika();
			System.out.println("AMIKA edit employee: " + employeeAmikaSession);
		}
		else if (manager!=null){
			currentAmika = Integer.parseInt(request.getParameter("employee_am_ika")); //current employee amika from form
			System.out.println("The current employee is: " + currentAmika);
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);
		}
		
		EvaluationDao evaluationDao = new EvaluationDao();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			List<CategoryInfo> ratings = evaluationDao.ratingsList(employeeAmikaSession, id);
			session.setAttribute("ratings", ratings);
			for (CategoryInfo rating:ratings){
				System.out.println("title: " + rating.getCategory_title() + " rating: " + rating.getRating());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain evaluations from DB", e);
		}

		try {
			List<CategoryInfo> viewRatings = evaluationDao.ratingsList(currentAmika, id);
			session.setAttribute("viewRatings", viewRatings);
			for (CategoryInfo rating:viewRatings){
				System.out.println("title: " + rating.getCategory_title() + " rating: " + rating.getRating());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain evaluations from DB", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
