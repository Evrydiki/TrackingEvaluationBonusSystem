package mvc.com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.CVDao;
import mvc.com.dao.PhotoDao;

/**
 * Servlet implementation class CVServlet
 */
@WebServlet("/CVServlet")
public class CVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 16177215;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String managerUsernameSession = null,  employeeUsernameSession = null;
		int managerAmikaSession=0;
		int employeeAmikaSession=0;
		HttpSession session = request.getSession();
		ManagerInfo manager = (ManagerInfo) session.getAttribute("manager");
		EmpInfo employee = (EmpInfo) session.getAttribute("employee");
		if (employee!=null){
			employeeUsernameSession = employee.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
			employeeAmikaSession = employee.getAm_ika();
			System.out.println("AMIKA edit employee: " + employeeAmikaSession);
		}
		if (manager!=null){
			managerUsernameSession = manager.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
			managerAmikaSession = manager.getAm_ika();
			System.out.println("AMIKA edit manager: " + managerAmikaSession);

		}
		String CV_name = request.getParameter("CVName");
		System.out.println("Parameter CV name: " + CV_name);
		String user = request.getParameter("username");
		System.out.println("Parameter username: " + user);
		String attribute = request.getParameter("attribute");
		System.out.println("Parameter attribute: " + attribute);
		InputStream CVInputStream = null;

		InputStream photoStream = null;
		String query = "";
		String manager_query = "SELECT username, CV, CVName FROM manager WHERE CVName = '" + CV_name + "' AND username = '" + user + "'";
		String employee_query = "SELECT username, CV, CVName FROM employee WHERE CVName = '" + CV_name + "' AND username = '" + user + "'";
		if(attribute.trim().equalsIgnoreCase("employee")){
			query = employee_query;
		}
		else if(attribute.trim().equalsIgnoreCase("manager")){
			query = manager_query;
		}
		
		if(CV_name.trim() != null){
			try {
				CVDao cvDao = new CVDao();
				CVInputStream = cvDao.geCVByEmployee(CV_name, user, query);
			} catch (IllegalArgumentException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Employee Username: " + user + "CV: " + CV_name);

			OutputStream output = null;
			try{
				int fileLength = CVInputStream.available(); 
				ServletContext context = getServletContext(); 
				String mimeType = context.getMimeType(CV_name); 
				if (mimeType == null) { 
					mimeType = "application/octet-stream"; 
				} 
				response.setContentType(mimeType); 
				response.setContentLength(fileLength);
				String headerKey = "Content-Disposition"; 
				String headerValue = String.format("attachment; filename=\"%s\"", CV_name); 
				response.setHeader(headerKey, headerValue); 
				output = response.getOutputStream(); 
				byte[] buffer = new byte[DEFAULT_BUFFER_SIZE]; 
				int bytesRead = -1; 
				while ((bytesRead = CVInputStream.read(buffer)) != -1) { 
					output.write(buffer, 0, bytesRead); 
				} 
			}finally {
				output.close();
				CVInputStream.close();
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
