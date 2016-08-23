package mvc.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.com.dao.RegisterDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
@MultipartConfig(maxFileSize = 16177215)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RegisterDao registerDao = new RegisterDao();
		
		if(!registerDao.userExists(request)){
			request.setAttribute("registerMessage", "Η εγγραφή σας στο σύστημα πραγματοποιήθηκε με επιτυχία! Αναμένεται e-mail από τον διαχειριστή για την πραγματοποίηση της σύνδεσης σας.");
			
		}
		else{
			request.setAttribute("registerMessage", "Υπάρχει ήδη εγγεγραμμένος χρήστης με αυτά τα στοιχεία στο σύστημα!");
		}
		request.getRequestDispatcher("/register.jsp").forward(request, response); 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
