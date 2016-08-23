package mvc.com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve parameters from login.jsp
		String user = request.getParameter("userTF");
    	String pass = request.getParameter("passTF");    	
    	
    	LoginDao loginDao = new LoginDao(); 
    	
    	//authenticate employee
    	String loginManager = loginDao.authenticateManager(user, pass); 
    	String loginEmployee = loginDao.authenticateEmployee(user, pass);
    	String loginAdmin = loginDao.authenticateAdmin(user, pass);

    	if(loginManager.equals("SUCCESS")) {
    		// generate a new session object, or retrieve an existing one
        	HttpSession userSession = request.getSession(true); 
    		//check if the session is not new, invalidate it and create a new one
    		if (userSession.isNew() == false) {
    			userSession.invalidate();
    			userSession = request.getSession(true);
    		}
    		userSession.setAttribute("username", user);
     		System.out.println("Login User:" + user + " Pass: " + pass);
     		userSession.setAttribute("role", "manager");
       		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeManager.jsp"); 		
    		dispatcher.forward(request, response); //forward the request
    	}
    	else if(loginEmployee.equals("SUCCESS")){
    		// generate a new session object, or retrieve an existing one
    		HttpSession userSession = request.getSession(true); 
    		//check if the session is not new, invalidate it and create a new one
    		if (userSession.isNew() == false) {
    			userSession.invalidate();
    			userSession = request.getSession(true);
    		}
    		userSession.setAttribute("username", user);
    		System.out.println("Login User:" + user + " Pass:" + pass);
    		userSession.setAttribute("role", "employee");
       		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeEmployee.jsp"); 		
    		dispatcher.forward(request, response); //forward the request
    	}
    	else if(loginAdmin.equals("SUCCESS")){ 
    		// generate a new session object, or retrieve an existing one
    		HttpSession userSession = request.getSession(true); 
    		//check if the session is not new, invalidate it and create a new one
    		if (userSession.isNew() == false) {
    			userSession.invalidate();
    			userSession = request.getSession(true);
    		}
    		userSession.setAttribute("username", user);
    		System.out.println("Login User:" + user + " Pass:" + pass);
    		userSession.setAttribute("role", "admin");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/homeAdmin.jsp"); 		
    		dispatcher.forward(request, response); //forward the request
    	}
    	else
    	{
    		request.setAttribute("errorMessage", loginEmployee); 
    		request.getRequestDispatcher("/login.jsp").forward(request, response); //forward the request
    	}
  
	}

}
