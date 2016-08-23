package mvc.com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import mvc.com.util.DBConnection;

public class RegisterDao {
	public static final int bonus_earned = 0; // initial bonus for employee in registration
	public int index = 0; 

	public boolean userExists(HttpServletRequest request) throws IllegalStateException, IOException, ServletException
	{	
		//retrieve parameters from register.jsp		
		String attribute = request.getParameter("attributeTF");
		System.out.println("Attribute: " + attribute);
		int amika = 0, amka = 0, afm = 0, salary = 0;
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
		String sex = request.getParameter("sexTF"); 
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
		if (attribute.equals("employee")){
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
		}
		String job_position = request.getParameter("jobPositionTF");
		if (request.getParameter("salaryTF")!=null){
			salary = Integer.parseInt(request.getParameter("salaryTF"));
		} 
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

		InputStream inputStreamPhoto = null; // input stream of the upload file photo

		InputStream inputStreamCV = null; // input stream of the upload file CV

		// obtains the upload file photo part in this multipart request
		Part filePartPhoto = request.getPart("photo");
		String photoType = filePartPhoto.getContentType();
		String photoName = filePartPhoto.getName();
		if (photoType.trim().equalsIgnoreCase("image/jpeg")){
			photoName += username + "Photo.jpeg";
		}
		else if (photoType.trim().equalsIgnoreCase("image/png")){
			photoName += filePartPhoto.getName() + username + "Photo.png";
		}
		else if (photoType.trim().equalsIgnoreCase("image/jpg")){
			photoName += filePartPhoto.getName() + username + "Photo.jpg";
		}
		else if (photoType.trim().equalsIgnoreCase("image/tif")){
			photoName += filePartPhoto.getName() + username + "Photo.tif";
		}
		else if (photoType.trim().equalsIgnoreCase("image/gif")){
			photoName += filePartPhoto.getName() + username + "Photo.gif";
		}
		if (filePartPhoto != null) {
			// prints out some information for debugging
			System.out.println("Photo Name: " + photoName);
			System.out.println(filePartPhoto.getSize());
			System.out.println(filePartPhoto.getContentType());

			// obtains input stream of the upload file
			inputStreamPhoto = filePartPhoto.getInputStream();
		}

		// obtains the upload file CV part in this multipart request
		Part filePartCV = request.getPart("CV");
		String CVtype = filePartCV.getContentType();
		String CVName = filePartCV.getName();
		if (CVtype.trim().equalsIgnoreCase("text/plain")){
			CVName += CVName + username + ".txt";
		}
		else if (CVtype.trim().equalsIgnoreCase("application/pdf")){
			CVName += CVName + username + ".pdf";
		}
		else if (CVtype.trim().equalsIgnoreCase("application/msword")){
			CVName += CVName + username + ".doc";
		}
		if (filePartCV != null) {
			// prints out some information for debugging
			System.out.println("CV Name: " + CVName);
			System.out.println(filePartCV.getSize());
			System.out.println(filePartCV.getContentType());

			// obtains input stream of the upload file
			inputStreamCV = filePartCV.getInputStream();
		}

		//check if the employee or manager is already registered in the database to avoid dublicates
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;	
		PreparedStatement prepStatement = null;	
		String usernameDB = "";
		Integer amikaDB = null;
		Integer amkaDB = null;
		Integer afmDB = null;
		int rowsUpdated = 0;
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<Integer> amikas = new ArrayList<Integer>();
		ArrayList<Integer> afms = new ArrayList<Integer>();
		ArrayList<Integer> amkas = new ArrayList<Integer>();

		if (attribute.equals("employee")){
			try
			{
				currentCon = DBConnection.getConnection(); 
				statement = currentCon.createStatement();
				resultSet = statement.executeQuery("SELECT am_ika, amka, afm, username, password FROM employee"); 
				while (resultSet.next()) {
					if (username!=null && password!=null){ 
						amikaDB = resultSet.getInt("am_ika");
						amkaDB = resultSet.getInt("amka");
						afmDB = resultSet.getInt("afm");
						usernameDB = resultSet.getString("username");
						usernames.add(usernameDB);
						amikas.add(amikaDB);
						afms.add(afmDB);
						amkas.add(amkaDB);
						System.out.println("usernameDB: " + usernameDB );
					}
				}
				for (String useri:usernames){
					for (Integer afmi:afms){
						for (Integer amkai:amkas){
							for (Integer amikai:amikas){
								if(amika!=amikai && amka!=amkai && afm!=afmi  
										&& !username.equals(useri)){ 
									continue;
								}
								else{
									if (currentCon != null) {
										// closes the database connection
										try {
											currentCon.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									return true;
								}
							}
						}
					}
				}

				String query = "INSERT INTO employee (am_ika, amka, afm, username, password, firstname, lastname, sex, birthday, photo, photoName, CV, CVName, email, phone_number, department, job_position, salary, bonus_earned, date_of_recruitment, manager_am_ika) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
				//21 values to insert
				prepStatement = currentCon.prepareStatement(query);
				prepStatement.setInt(1, amika);
				prepStatement.setInt(2, amka); 
				prepStatement.setInt(3, afm); 
				prepStatement.setString(4, username); 
				prepStatement.setString(5, password); 
				prepStatement.setString(6, firstname);
				prepStatement.setString(7, lastname); 
				prepStatement.setString(8, sex); 
				prepStatement.setDate(9, new java.sql.Date(bday.getTime()));
				if (inputStreamPhoto != null) {
					// fetches input stream of the upload file for the blob column
					prepStatement.setBlob(10, inputStreamPhoto);
					prepStatement.setString(11, photoName);
				}
				if (inputStreamCV != null) {
					// fetches input stream of the upload file for the blob column
					prepStatement.setBlob(12, inputStreamCV);
					prepStatement.setString(13, CVName);
				}
				prepStatement.setString(14, email);
				prepStatement.setString(15, phone_number); 
				prepStatement.setString(16, department); 
				prepStatement.setString(17, job_position);
				prepStatement.setInt(18, salary); 
				prepStatement.setInt(19, bonus_earned); 
				prepStatement.setDate(20, new java.sql.Date(rdate.getTime()));
				prepStatement.setInt(21, employeeHasManager);
				rowsUpdated = prepStatement.executeUpdate(); 
				if (rowsUpdated > 0) {
					System.out.println("Employee Registered!");
					return false; 
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
		}

		if (attribute.equals("manager")){
			try
			{
				currentCon = DBConnection.getConnection(); 
				statement = currentCon.createStatement();
				resultSet = statement.executeQuery("SELECT am_ika, amka, afm, username, password FROM manager"); 
				while (resultSet.next()) {
					if (username!=null && password!=null){ 
						amikaDB = resultSet.getInt("am_ika");
						amkaDB = resultSet.getInt("amka");
						afmDB = resultSet.getInt("afm");
						usernameDB = resultSet.getString("username");
						usernames.add(usernameDB);
						amikas.add(amikaDB);
						afms.add(afmDB);
						amkas.add(amkaDB);
						System.out.println("usernameDB: " + usernameDB );
					}
				}
				for (String useri:usernames){
					for (Integer afmi:afms){
						for (Integer amkai:amkas){
							for (Integer amikai:amikas){
								if(amika!=amikai && amka!=amkai && afm!=afmi  
										&& !username.equals(useri)){ 
									continue;
								}
								else{
									if (currentCon != null) {
										// closes the database connection
										try {
											currentCon.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									return true;
								}
							}
						}
					}
				}

				String query = "INSERT INTO manager (am_ika, amka, afm, username, password, firstname, lastname, sex, birthday, photo, photoName, CV, CVName, email, phone_number, department, job_position, salary, date_of_recruitment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
				//19 values to insert
				prepStatement = currentCon.prepareStatement(query);
				prepStatement.setInt(1, amika);
				prepStatement.setInt(2, amka); 
				prepStatement.setInt(3, afm); 
				prepStatement.setString(4, username); 
				prepStatement.setString(5, password); 
				prepStatement.setString(6, firstname);
				prepStatement.setString(7, lastname); 
				prepStatement.setString(8, sex); 
				prepStatement.setDate(9, new java.sql.Date(bday.getTime()));
				if (inputStreamPhoto != null) {
					// fetches input stream of the upload file for the blob column
					prepStatement.setBlob(10, inputStreamPhoto);
					prepStatement.setString(11, photoName);
				}
				if (inputStreamCV != null) {
					// fetches input stream of the upload file for the blob column
					prepStatement.setBlob(12, inputStreamCV);
					prepStatement.setString(13, CVName);
				}
				prepStatement.setString(14, email);
				prepStatement.setString(15, phone_number); 
				prepStatement.setString(16, department); 
				prepStatement.setString(17, job_position);
				prepStatement.setInt(18, salary);
				prepStatement.setDate(19, new java.sql.Date(rdate.getTime()));
				rowsUpdated = prepStatement.executeUpdate(); 
				if (rowsUpdated > 0) {
					System.out.println("Manager Registered!");
					return false; 
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
			return true;
		}
		return true;
	}


	public static String md5(String input) {
		String md5 = null;
		if(null == input) return null;
		try {
			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}
