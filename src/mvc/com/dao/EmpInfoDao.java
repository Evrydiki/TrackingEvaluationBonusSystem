package mvc.com.dao;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import mvc.com.bean.EmpInfo;
import mvc.com.bean.ManagerInfo;
import mvc.com.util.DBConnection;

public class EmpInfoDao {

	public static List<EmpInfo> employeeList() throws SQLException, IOException {
		List<EmpInfo> employees = new ArrayList<EmpInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT am_ika, amka, afm, username, firstname, lastname, sex, birthday, photo, photoName, CV, CVName, email, phone_number, department, job_position, salary, bonus_earned, date_of_recruitment FROM employee"); 
			while (resultSet.next()) {
				EmpInfo employee = new EmpInfo();
				employee.setAm_ika(resultSet.getInt("am_ika"));
				employee.setAmka(resultSet.getInt("amka"));
				employee.setAfm(resultSet.getInt("afm"));
				employee.setUsername(resultSet.getString("username"));
				employee.setFirstname(resultSet.getString("firstname"));
				employee.setLastname(resultSet.getString("lastname"));
				employee.setSex(resultSet.getString("sex"));
				employee.setBirthday(resultSet.getDate("birthday"));
				Blob photoBlob = resultSet.getBlob("photo");
				employee.setPhotoInput(photoBlob.getBinaryStream());
				employee.setPhotoName(resultSet.getString("photoName"));
				employee.setCVName(resultSet.getString("CVName"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone_number(resultSet.getString("phone_number"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setJobPosition(resultSet.getString("job_position"));
				employee.setSalary(resultSet.getInt("salary"));
				employee.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				employee.setAttribute("employee");
				employees.add(employee);
			}
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employees; //returning the list of employees
	}

	public static List<ManagerInfo> managerList() throws SQLException {
		List<ManagerInfo> managers = new ArrayList<ManagerInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT am_ika, amka, afm, username, firstname, lastname, sex, birthday, photo, photoName, CV, CVName, email, phone_number, department, job_position, salary, date_of_recruitment FROM manager"); 
			while (resultSet.next()) {
				ManagerInfo manager = new ManagerInfo();
				manager.setAm_ika(resultSet.getInt("am_ika"));
				manager.setAmka(resultSet.getInt("amka"));
				manager.setAfm(resultSet.getInt("afm"));
				manager.setUsername(resultSet.getString("username"));
				manager.setFirstname(resultSet.getString("firstname"));
				manager.setLastname(resultSet.getString("lastname"));
				manager.setSex(resultSet.getString("sex"));
				manager.setBirthday(resultSet.getDate("birthday"));
				manager.setPhotoName(resultSet.getString("photoName"));
				manager.setCVName(resultSet.getString("CVName"));
				manager.setEmail(resultSet.getString("email"));
				manager.setPhone_number(resultSet.getString("phone_number"));
				manager.setDepartment(resultSet.getString("department"));
				manager.setJobPosition(resultSet.getString("job_position"));
				manager.setSalary(resultSet.getInt("salary"));
				manager.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				manager.setAttribute("manager");
				managers.add(manager);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return managers; //returning the list of managers
	}

	public static List<EmpInfo> inactiveEmployeeList() throws SQLException, IOException {
		List<EmpInfo> inactiveEmployees = new ArrayList<EmpInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM employee WHERE active=" + "'0'"); 
			while (resultSet.next()) {
				EmpInfo employee = new EmpInfo();
				employee.setAm_ika(resultSet.getInt("am_ika"));
				employee.setAmka(resultSet.getInt("amka"));
				employee.setAfm(resultSet.getInt("afm"));
				employee.setUsername(resultSet.getString("username"));
				employee.setFirstname(resultSet.getString("firstname"));
				employee.setLastname(resultSet.getString("lastname"));
				employee.setSex(resultSet.getString("sex"));
				employee.setBirthday(resultSet.getDate("birthday"));
				Blob photoBlob = resultSet.getBlob("photo");
				employee.setPhotoInput(photoBlob.getBinaryStream());
				employee.setPhotoName(resultSet.getString("photoName"));
				employee.setCVName(resultSet.getString("CVName"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone_number(resultSet.getString("phone_number"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setJobPosition(resultSet.getString("job_position"));
				employee.setSalary(resultSet.getInt("salary"));
				employee.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				employee.setAttribute("employee");
				inactiveEmployees.add(employee);
			}
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return inactiveEmployees; //returning the list of employees
	}

	public static List<ManagerInfo> inactiveManagerList() throws SQLException, IOException {
		List<ManagerInfo> inactiveManagers = new ArrayList<ManagerInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM manager WHERE active=" + "'0'"); 
			while (resultSet.next()) {
				ManagerInfo manager = new ManagerInfo();
				manager.setAm_ika(resultSet.getInt("am_ika"));
				manager.setAmka(resultSet.getInt("amka"));
				manager.setAfm(resultSet.getInt("afm"));
				manager.setUsername(resultSet.getString("username"));
				manager.setFirstname(resultSet.getString("firstname"));
				manager.setLastname(resultSet.getString("lastname"));
				manager.setSex(resultSet.getString("sex"));
				manager.setBirthday(resultSet.getDate("birthday"));
				manager.setPhotoName(resultSet.getString("photoName"));
				manager.setCVName(resultSet.getString("CVName"));
				manager.setEmail(resultSet.getString("email"));
				manager.setPhone_number(resultSet.getString("phone_number"));
				manager.setDepartment(resultSet.getString("department"));
				manager.setJobPosition(resultSet.getString("job_position"));
				manager.setSalary(resultSet.getInt("salary"));
				manager.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				manager.setAttribute("manager");
				inactiveManagers.add(manager);
			}
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return inactiveManagers; 
	}

	public static List<EmpInfo> activeEmployeeList() throws SQLException, IOException {
		List<EmpInfo> inactiveEmployees = new ArrayList<EmpInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM employee WHERE active=" + "'1'"); 
			while (resultSet.next()) {
				EmpInfo employee = new EmpInfo();
				employee.setAm_ika(resultSet.getInt("am_ika"));
				employee.setAmka(resultSet.getInt("amka"));
				employee.setAfm(resultSet.getInt("afm"));
				employee.setUsername(resultSet.getString("username"));
				employee.setFirstname(resultSet.getString("firstname"));
				employee.setLastname(resultSet.getString("lastname"));
				employee.setSex(resultSet.getString("sex"));
				employee.setBirthday(resultSet.getDate("birthday"));
				Blob photoBlob = resultSet.getBlob("photo");
				employee.setPhotoInput(photoBlob.getBinaryStream());
				employee.setPhotoName(resultSet.getString("photoName"));
				employee.setCVName(resultSet.getString("CVName"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone_number(resultSet.getString("phone_number"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setJobPosition(resultSet.getString("job_position"));
				employee.setSalary(resultSet.getInt("salary"));
				employee.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				employee.setAttribute("employee");
				inactiveEmployees.add(employee);
			}
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return inactiveEmployees; //returning the list of employees
	}

	public static List<ManagerInfo> activeManagerList() throws SQLException, IOException {
		List<ManagerInfo> inactiveManagers = new ArrayList<ManagerInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM manager WHERE active=" + "'1'"); 
			while (resultSet.next()) {
				ManagerInfo manager = new ManagerInfo();
				manager.setAm_ika(resultSet.getInt("am_ika"));
				manager.setAmka(resultSet.getInt("amka"));
				manager.setAfm(resultSet.getInt("afm"));
				manager.setUsername(resultSet.getString("username"));
				manager.setFirstname(resultSet.getString("firstname"));
				manager.setLastname(resultSet.getString("lastname"));
				manager.setSex(resultSet.getString("sex"));
				manager.setBirthday(resultSet.getDate("birthday"));
				manager.setPhotoName(resultSet.getString("photoName"));
				manager.setCVName(resultSet.getString("CVName"));
				manager.setEmail(resultSet.getString("email"));
				manager.setPhone_number(resultSet.getString("phone_number"));
				manager.setDepartment(resultSet.getString("department"));
				manager.setJobPosition(resultSet.getString("job_position"));
				manager.setSalary(resultSet.getInt("salary"));
				manager.setDate_of_Recruitment(resultSet.getDate("date_of_recruitment"));
				manager.setAttribute("manager");
				inactiveManagers.add(manager);
			}
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return inactiveManagers; 
	}

	public boolean deleteEmployee(int amika) {
		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try
		{
			currentCon = DBConnection.getConnection(); 
			String delete_query = "DELETE FROM employee WHERE am_ika='"  + amika + "'"; 
			prepStatement = currentCon.prepareStatement(delete_query);		
			prepStatement.executeUpdate(); 
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean deleteManager(int amika) {
		Connection currentCon = null;
		PreparedStatement prepStatement = null;
		try
		{
			currentCon = DBConnection.getConnection(); 
			String delete_query = "DELETE FROM manager WHERE am_ika='"  + amika + "'"; 
			prepStatement = currentCon.prepareStatement(delete_query);		
			prepStatement.executeUpdate(); 
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	public boolean changePassword(HttpServletRequest request, String userSession, String attribute, String pswd) throws ServletException
	{
		String pass = request.getParameter("passTF");
		String password = RegisterDao.md5(pass); 
		
		Connection currentCon = null;	
		PreparedStatement prepStatement = null;	

		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "UPDATE " + attribute + " SET password=? WHERE username='" + userSession + "' AND password='" + RegisterDao.md5(pswd) + "'";
			prepStatement = currentCon.prepareStatement(query);
			prepStatement.setString(1, password); 
			prepStatement.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			throw new ServletException("Cannot update pass", e);
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}

	public boolean editEmployee(HttpServletRequest request, int amika) throws SQLException, ServletException {
		//retrieve parameters
		int amka = 0, afm = 0;
		if (request.getParameter("amikaTF")!=null){
			amika = Integer.parseInt(request.getParameter("amikaTF"));
		}
		if (request.getParameter("amkaTF")!=null){
			amka = Integer.parseInt(request.getParameter("amkaTF"));
		}
		if (request.getParameter("afmTF")!=null){
			afm = Integer.parseInt(request.getParameter("afmTF"));
		}
		String username = request.getParameter("userTF");
		String firstname = request.getParameter("firstnameTF");
		String lastname = request.getParameter("lastnameTF");
		String birthday = request.getParameter("birthdayTF");	
		Date bday = null;
		if (birthday!=null){
			try {
				bday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			} catch (ParseException e1) {
				System.out.println("Cannot parse bday");
				e1.printStackTrace();
			} 
		}
		String email = request.getParameter("emailTF");
		String phone_number = request.getParameter("phoneNumberTF");
		String department = request.getParameter("departmentTF");
		int employeeHasManager = 0; 
		// define manager for employee
		if (department.equals("Marketing")){
			employeeHasManager = 101236;
		}
		else if (department.equals("Production")){
			employeeHasManager = 101237;
		}
		else if (department.equals("Purchasing")){
			employeeHasManager = 101238;
		}
		else if (department.equals("Research and Development")){
			employeeHasManager = 101243;
		}
		else if (department.equals("Public Relations")){
			employeeHasManager = 101239;
		}
		else if (department.trim().equals("Finance and Accounting")){
			employeeHasManager = 101240;
		}
		else if (department.equals("Human Resources")){
			employeeHasManager = 101241;
		}
		else if (department.equals("Information Technology")){
			employeeHasManager = 101235;
		}
		else if (department.equals("Legal")){
			employeeHasManager = 101242;
		}
		String job_position = request.getParameter("jobPositionTF");
		String date_of_recruitment = request.getParameter("dateOfRecruitmentTF");
		Date rdate = null;
		if (date_of_recruitment!=null){

			try {
				rdate = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_recruitment);
			} catch (ParseException e1) {
				System.out.println("Cannot parse Date");
				e1.printStackTrace();
			}  
		}
		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "UPDATE employee SET amka=?, afm=?, username=?, firstname=?, lastname=?, birthday=?, email=?, phone_number=?, department=?, job_position=?, date_of_recruitment=?, manager_am_ika=? WHERE am_ika = '" + amika + "'";
			prepStatement = currentCon.prepareStatement(query);
			prepStatement.setInt(1, amka); 
			prepStatement.setInt(2, afm); 
			prepStatement.setString(3, username); 
			prepStatement.setString(4, firstname);
			prepStatement.setString(5, lastname); 
			prepStatement.setDate(6, new java.sql.Date(bday.getTime()));
			prepStatement.setString(7, email);
			prepStatement.setString(8, phone_number); 
			prepStatement.setString(9, department); 
			prepStatement.setString(10, job_position);
			prepStatement.setDate(11, new java.sql.Date(rdate.getTime()));
			prepStatement.setInt(12, employeeHasManager);
			prepStatement.executeUpdate(); 
			return true;
		}catch (SQLException e) {
			throw new ServletException("Cannot update employees", e);
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}
	
	public boolean editManager(HttpServletRequest request, int amika) throws SQLException, ServletException {
		//retrieve parameters
		int amka = 0, afm = 0;
		if (request.getParameter("amikaTF")!=null){
			amika = Integer.parseInt(request.getParameter("amikaTF"));
		}
		if (request.getParameter("amkaTF")!=null){
			amka = Integer.parseInt(request.getParameter("amkaTF"));
		}
		if (request.getParameter("afmTF")!=null){
			afm = Integer.parseInt(request.getParameter("afmTF"));
		}
		String username = request.getParameter("userTF");
		String pass = request.getParameter("passTF");
		String password = RegisterDao.md5(pass); 
		String firstname = request.getParameter("firstnameTF");
		String lastname = request.getParameter("lastnameTF");
		String birthday = request.getParameter("birthdayTF");	
		Date bday = null;
		if (birthday!=null){
			try {
				bday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			} catch (ParseException e1) {
				System.out.println("Cannot parse bday");
				e1.printStackTrace();
			} 
		}
		String email = request.getParameter("emailTF");
		String phone_number = request.getParameter("phoneNumberTF");
		String job_position = request.getParameter("jobPositionTF");
		String date_of_recruitment = request.getParameter("dateOfRecruitmentTF");
		Date rdate = null;
		if (date_of_recruitment!=null){

			try {
				rdate = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_recruitment);
			} catch (ParseException e1) {
				System.out.println("Cannot parse Date");
				e1.printStackTrace();
			}  
		}
		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "UPDATE manager SET amka=?, afm=?, username=?, password=?, firstname=?, lastname=?, birthday=?, email=?, phone_number=?, job_position=?, date_of_recruitment=? WHERE am_ika = '" + amika + "'"; 
			prepStatement = currentCon.prepareStatement(query);
			prepStatement.setInt(1, amka); 
			prepStatement.setInt(2, afm); 
			prepStatement.setString(3, username); 
			prepStatement.setString(4, password); 
			prepStatement.setString(5, firstname);
			prepStatement.setString(6, lastname); 
			prepStatement.setDate(7, new java.sql.Date(bday.getTime()));
			prepStatement.setString(8, email);
			prepStatement.setString(9, phone_number);
			prepStatement.setString(10, job_position);
			prepStatement.setDate(11, new java.sql.Date(rdate.getTime()));
			prepStatement.executeUpdate(); 
			return true;
		}
		catch (SQLException e) {
			throw new ServletException("Cannot update managers", e);
		}
		finally {
			if (currentCon != null) {
				// closes the database connection
				try {
					currentCon.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}
}
