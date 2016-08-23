package mvc.com.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * Servlet implementation class ProfilePhotoServlet
 */
@WebServlet("/profile-photo.jsp/*")
public class ProfilePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 16177215;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Servlet to obtain photo depending on the user session for the employee or the manager profile
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting user session
		HttpSession session = request.getSession();
		ManagerInfo managerSession = (ManagerInfo) session.getAttribute("manager");
		String managerUsernameSession = null,  employeeUsernameSession = null;
		EmpInfo employeeSession = (EmpInfo) session.getAttribute("employee");
		if (employeeSession!=null){
			employeeUsernameSession = employeeSession.getUsername();
			System.out.println("Employee Username Session: " + employeeUsernameSession);
		}
		if (managerSession!=null){
			managerUsernameSession = managerSession.getUsername();
			System.out.println("Manager Username Session: " + managerUsernameSession);
		}	
		
		InputStream photoStream = null;
		List<EmpInfo> employees = null;
		try {
			employees = EmpInfoDao.employeeList();
		}
		catch (SQLException e){
			throw new ServletException("Cannot obtain employees from DB", e);
		}
		for (EmpInfo employee:employees){
			if(employee.getUsername().equals(employeeUsernameSession)){
				employee = (EmpInfo) session.getAttribute("employee");
				System.out.println("am_ika: " + employee.getAm_ika() + "firstname: " + employee.getFirstname());
				System.out.println("Photo name:" + employee.getPhotoName());
				if(employee.getPhotoName().trim() != null){
					String query = "SELECT username, photo FROM employee WHERE photoName = '" + employee.getPhotoName() + "' AND username = '" + employee.getUsername() + "'";
					try {
						PhotoDao photoDao = new PhotoDao();
						photoStream = photoDao.getPhotoByEmployee(employee.getPhotoName(), employee.getUsername(), query);
						System.out.println("Session employee Username: " + employee.getUsername() + "photo: " + employee.getPhotoName());
						// Prepare streams.
						BufferedInputStream input = null;
						BufferedOutputStream output = null;

						try {
							response.setContentType(getServletContext().getMimeType(employee.getPhotoName()));
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
					System.out.println("Employee with username: '" + employee.getUsername() + "' doesn't have a photo.");
					String noPhotoMessage = "Δεν έχετε φωτογραφία.";
					request.setAttribute("noPhoto", noPhotoMessage);
				}
			}
		}

		List<ManagerInfo> managers = null;
		try {
			managers = EmpInfoDao.managerList();
		}
		catch (SQLException e){
			throw new ServletException("Cannot obtain managers from DB", e);
		}
		for (ManagerInfo manager:managers){
			if(manager.getUsername().equals(managerUsernameSession)){
				manager = (ManagerInfo) session.getAttribute("manager");
				System.out.println("am_ika: " + manager.getAm_ika() + "firstname: " + manager.getFirstname());
				System.out.println("Photo name:" + manager.getPhotoName());
				if(manager.getPhotoName().trim() != null){
					try {
						PhotoDao photoDao = new PhotoDao();
						photoStream = photoDao.getPhotoByManager(manager.getPhotoName(), manager.getUsername());
						System.out.println("manager.getUsername(): " + manager.getUsername() + "photo: " + manager.getPhotoName());
						// Prepare streams.
						BufferedInputStream input = null;
						BufferedOutputStream output = null;

						try {
							response.setContentType(getServletContext().getMimeType(manager.getPhotoName()));
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
					System.out.println("Manager with username: '" + manager.getUsername() + "' doesn't have a photo.");
					String noPhotoMessage = "Δεν έχετε φωτογραφία.";
					request.setAttribute("noPhoto", noPhotoMessage);
				}
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
