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
import mvc.com.bean.EvaluationlInfo;
import mvc.com.bean.GoalInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EvaluationDao;
import mvc.com.dao.GoalInfoDao;

/**
 * Servlet implementation class EvaluationsInfoServlet
 */
@WebServlet("/EvaluationsInfoServlet")
public class EvaluationsInfoServlet extends HttpServlet {
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
			currentAmika = Integer.parseInt(request.getParameter("am_ika")); //current employee amika from form
			System.out.println("The current employee is: " + currentAmika);
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);
		}
		EvaluationDao evaluationDao = new EvaluationDao();

		try {
			List<EvaluationlInfo> myEvaluations = evaluationDao.myEvaluationsList(employeeAmikaSession);
			session.setAttribute("myEvaluations", myEvaluations);
			for (EvaluationlInfo myEvaluation:myEvaluations){
				System.out.println("id: " + myEvaluation.getId());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain myEvaluations from DB", e);
		}
		
		try {
			List<EvaluationlInfo> employeeEvaluations = evaluationDao.myEvaluationsList(currentAmika);
			session.setAttribute("employeeEvaluations", employeeEvaluations);
			for (EvaluationlInfo employeeEvaluation:employeeEvaluations){
				System.out.println("id: " + employeeEvaluation.getId());
			}
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain goals from DB", e);
		}
	}
}
