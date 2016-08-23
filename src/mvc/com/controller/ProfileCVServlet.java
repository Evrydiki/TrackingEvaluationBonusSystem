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
import mvc.com.dao.EmpInfoDao;
import mvc.com.dao.PhotoDao;

/**
 * Servlet implementation class CVServlet
 */
@WebServlet("/profileCV.jsp/*")
public class ProfileCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 16177215;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream CVInputStream = null;
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
		List<EmpInfo> employees = (List<EmpInfo>) session.getAttribute("employees");

		for (EmpInfo employee:employees){
			if(employee.getCVName().trim() != null){
				if(employee.getUsername().equals(employeeUsernameSession)){
					String query = "SELECT username, CV, CVName FROM employee WHERE CVName = '" + employee.getCVName() + "' AND username = '" + employee.getUsername() + "'";
					try {
						CVDao cvDao = new CVDao();
						CVInputStream = cvDao.geCVByEmployee(employee.getCVName(), employee.getUsername(), query);
					} catch (IllegalArgumentException | ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Employee Username: " + employee.getUsername() + "CV: " + employee.getCVName());

					OutputStream output = null;
					try{
						int fileLength = CVInputStream.available(); 
						ServletContext context = getServletContext(); 
						String mimeType = context.getMimeType(employee.getCVName()); 
						if (mimeType == null) { 
							mimeType = "application/octet-stream"; 
						} 
						response.setContentType(mimeType); 
						response.setContentLength(fileLength);
						String headerKey = "Content-Disposition"; 
						String headerValue = String.format("attachment; filename=\"%s\"", employee.getCVName()); 
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
		}

		List<ManagerInfo> managers = null;
		try {
			managers = EmpInfoDao.managerList();
		}
		catch (SQLException e){
			throw new ServletException("Cannot obtain managers from DB", e);
		}
		for (ManagerInfo manager:managers){
			if(manager.getCVName().trim() != null){
				if(manager.getUsername().equals(managerUsernameSession)){
					manager = (ManagerInfo) session.getAttribute("manager");
					System.out.println("am_ika: " + manager.getAm_ika() + "firstname: " + manager.getFirstname());
					System.out.println("CV name:" + manager.getPhotoName());
					if(manager.getPhotoName().trim() != null){
						try {
							CVDao cvDao = new CVDao();
							CVInputStream = cvDao.getCVByManager(manager.getCVName(), manager.getUsername());
						} catch (IllegalArgumentException | ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						System.out.println("Manager Username: " + manager.getUsername() + "CV: " + manager.getCVName());

						OutputStream output = null;
						try{
							int fileLength = CVInputStream.available(); 
							ServletContext context = getServletContext(); 
							String mimeType = context.getMimeType(manager.getCVName()); 
							if (mimeType == null) { 
								mimeType = "application/octet-stream"; 
							} 
							response.setContentType(mimeType); 
							response.setContentLength(fileLength);
							String headerKey = "Content-Disposition"; 
							String headerValue = String.format("attachment; filename=\"%s\"", manager.getCVName()); 
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
