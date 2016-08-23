package mvc.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mvc.com.bean.CategoryInfo;
import mvc.com.bean.EvaluationlInfo;
import mvc.com.util.DBConnection;

public class EvaluationDao {

	public boolean evaluateEmployee(int amika, HttpServletRequest request) {
		//retrieve parameters
		int total = 0;
		double totald = 0.0;
		String type = request.getParameter("typeTF");
		System.out.println("Type:  " + type);
		String evaluationDate = request.getParameter("dateTF");
		System.out.println("Uformatted date:  " + evaluationDate);
		Date date = null;
		if (evaluationDate!=null){
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(evaluationDate);
			} catch (ParseException e1) {
				System.out.println("Cannot parse date");
				e1.printStackTrace();
			} 
		}
		String comments = request.getParameter("commentsTF");
		String nextEvaluationDate = request.getParameter("nextEvaluationDateTF");
		System.out.println("Uformatted nextEvaluationDate:  " + nextEvaluationDate);
		Date next_evaluation_date = null;
		if (nextEvaluationDate!=null){
			try {
				next_evaluation_date = new SimpleDateFormat("yyyy-MM-dd").parse(nextEvaluationDate);
			} catch (ParseException e1) {
				System.out.println("Cannot parse nextEvaluationDate");
				e1.printStackTrace();
			} 
		}
		totald = Double.parseDouble(request.getParameter("totalTF")); 
		System.out.println("Total before: " + totald);
		total = roundUp(totald);
		System.out.println("Total after: " + total);

		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		int rowsUpdated = 0;

		try
		{
			currentCon = DBConnection.getConnection(); 
			String query = "INSERT INTO employee_evaluation (type, date, comments, next_evaluation_date, total_rating, employee_am_ika) VALUES (?, ?, ?, ?, ?, ?)"; 
			//6 values to insert
			prepStatement = currentCon.prepareStatement(query);
			prepStatement.setString(1, type);
			prepStatement.setDate(2, new java.sql.Date(date.getTime())); 
			prepStatement.setString(3, comments); 
			prepStatement.setDate(4, new java.sql.Date(next_evaluation_date.getTime()));
			prepStatement.setInt(5, total); 
			prepStatement.setInt(6, amika); 	
			rowsUpdated = prepStatement.executeUpdate(); 
			if (rowsUpdated > 0) {
				System.out.println("Evaluation Inserted!");
				return true;
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
		return false;
	}

	private static int roundUp(double d) {
		return (d > (int) d) ? (int) d + 1 : (int) d;
	}

	public boolean ratingPerCategory(HttpServletRequest request) {
		//retrieve parameters
		String[] titles = request.getParameterValues("title");
		String[] ratings = request.getParameterValues("rating");
		int[] categoryIds = new int[titles.length];
		int[] ratingsInt = new int[ratings.length];
		for(int i=0; i<ratingsInt.length; i++){
			ratingsInt[i] = Integer.parseInt(ratings[i]);
		}
		
		for (int i = 0; i < titles.length; i++) {
			categoryIds[i] = i + 1;
		}
		
//		for (int i = 0; i < titles.length; i++) {
//		if(titles[i].trim().equalsIgnoreCase("Διαθεσιμότητα")){
//			categoryIds[i] = 1;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Ανεξαρτησία")){
//			categoryIds[i] = 2;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Πρωτοβουλία")){
//			categoryIds[i] = 3;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Γνώση Εργασίας")){
//			categoryIds[i] = 4;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Κρίση")){
//			categoryIds[i] = 5;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Παραγωγικότητα")){
//			categoryIds[i] = 6;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Ποιότητα")){
//			categoryIds[i] = 7;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Αξιοπιστία")){
//			categoryIds[i] = 8;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Εργασιακές Σχέσεις")){
//			categoryIds[i] = 9;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Δημιουργικότητα")){
//			categoryIds[i] = 10;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Διαχείριση Στόχων")){
//			categoryIds[i] = 11;
//		}
//		else if(titles[i].trim().equalsIgnoreCase("Ανάληψη πολύπλοκων στόχων")){
//			categoryIds[i] = 12;
//		}
		
		Connection currentCon = null;
		PreparedStatement prepStatement = null;	
		ResultSet resultSet = null;
		int[] rowsUpdated = new int[0];
		int id = -1;

		try
		{
			currentCon = DBConnection.getConnection(); 
			String last_insert_query = "SELECT max(id) FROM employee_evaluation ";
			prepStatement = currentCon.prepareStatement(last_insert_query);
			resultSet = prepStatement.executeQuery(last_insert_query); 				
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				System.out.println("ID = " + id);
			}
			
			String insert_query = "INSERT INTO evaluation_has_category (evaluation_id, category_id, rating) VALUES (?, ?, ?)"; 
			prepStatement = currentCon.prepareStatement(insert_query);
			//3 values to insert
			for (int j = 0; j < ratingsInt.length; j++) {
				System.out.println("id: " + categoryIds[j] + "title" + titles[j] + ": " + ratings[j]);
				prepStatement.setInt(1, id);
				prepStatement.setInt(2, categoryIds[j]);
				prepStatement.setInt(3, ratingsInt[j]); 
				prepStatement.addBatch();
			}
			System.out.println("_____________________________");
			rowsUpdated = prepStatement.executeBatch(); 
			if (rowsUpdated.length > 0) {
				System.out.println("Evaluation Inserted!");
				return true;
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
		return false;
	}
	
	
	public List<EvaluationlInfo> myEvaluationsList(int employee_am_ika) throws SQLException {
		List<EvaluationlInfo> myEvaluations = new ArrayList<EvaluationlInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM trackingdb.employee_evaluation AS e WHERE employee_am_ika='" + employee_am_ika + "' ORDER BY e.id"); 
			while (resultSet.next()) {
				EvaluationlInfo myEvaluation = new EvaluationlInfo();
				myEvaluation.setId(resultSet.getInt("id"));
				myEvaluation.setType(resultSet.getString("type"));
				myEvaluation.setDate(resultSet.getDate("date"));
				myEvaluation.setComments(resultSet.getString("comments"));
				myEvaluation.setNext_evaluation_date(resultSet.getDate("next_evaluation_date"));
				myEvaluation.setTotal_rating(resultSet.getInt("total_rating"));
				myEvaluation.setEmployee_am_ika(resultSet.getInt("employee_am_ika"));
				myEvaluations.add(myEvaluation); 
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
		return myEvaluations; //returning the list of completed goals
	}
	
	public List<CategoryInfo> ratingsList(int employee_am_ika, int id) throws SQLException {
		List<CategoryInfo> ratings = new ArrayList<CategoryInfo>();
		Connection currentCon = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			currentCon = DBConnection.getConnection(); 
			statement = currentCon.createStatement();
			resultSet = statement.executeQuery("SELECT title, rating FROM trackingdb.employee_evaluation AS e" +
					" INNER JOIN trackingdb.evaluation_has_category AS h" +
					" ON e.id ='"+ id +"' AND h.evaluation_id ='"+ id +"' AND employee_am_ika='" + employee_am_ika + "'" +
					" INNER JOIN trackingdb.category AS c ON c.id=h.category_id;"); 
			while (resultSet.next()) {
				CategoryInfo categoryPerRating = new CategoryInfo();
				categoryPerRating.setCategory_title(resultSet.getString(1));
				categoryPerRating.setRating(resultSet.getInt(2));
				ratings.add(categoryPerRating); 
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
		return ratings; //returning the list of completed goals
	}
}
