package mvc.com.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.dao.EmpInfoDao;
import mvc.com.dao.PhotoDao;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 16177215;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Servlet to obtain photo for every employee to show from the list of names for the manager
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
		String photo_name = request.getParameter("photoName");
		System.out.println("Parameter photo name: " + photo_name);
		String user = request.getParameter("username");
		System.out.println("Parameter username: " + user);
		String attribute = request.getParameter("attribute");
		System.out.println("Parameter attribute: " + attribute);
		
		InputStream photoStream = null;
		String query = "";
		String manager_query = "SELECT username, photo FROM manager WHERE photoName = '" + photo_name + "' AND username = '" + user + "'";
		String employee_query = "SELECT username, photo FROM employee WHERE photoName = '" + photo_name + "' AND username = '" + user + "'";
		if(attribute.trim().equalsIgnoreCase("employee")){
			query = employee_query;
		}
		else if(attribute.trim().equalsIgnoreCase("manager")){
			query = manager_query;
		}

				if(photo_name.trim() != null && user != null){
				
					try {
						PhotoDao photoDao = new PhotoDao();
						photoStream = photoDao.getPhotoByEmployee(photo_name, user, query);
						System.out.println("Employee Username: " + user + "photo: " + photo_name);
						// Prepare streams.
						BufferedInputStream input = null;
						BufferedOutputStream output = null;

						try {
							response.setContentType(getServletContext().getMimeType(photo_name));
							// Open streams
							input = new BufferedInputStream(photoStream, DEFAULT_BUFFER_SIZE);
							output = new BufferedOutputStream(response.getOutputStream(),
									DEFAULT_BUFFER_SIZE);

							// Write file contents to response.
							byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
							int length;
							while ((length = input.read(buffer)) > 0) {
								output.write(buffer, 0, length);
							}
						} finally {
							output.close();
							input.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Employee with username:" + user + " doesn't have a photo.");
					String noPhotoMessage = "Δεν έχετε φωτογραφία.";
					request.setAttribute("noPhoto", noPhotoMessage);
				}
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
