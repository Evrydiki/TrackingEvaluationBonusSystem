package mvc.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationlInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private Date date;
	private String comments;
	private Date next_evaluation_date;
	private int total_rating;
	private int employee_am_ika;
	private List<CategoryInfo> ratings = new ArrayList<CategoryInfo>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getNext_evaluation_date() {
		return next_evaluation_date;
	}
	public void setNext_evaluation_date(Date next_evaluation_date) {
		this.next_evaluation_date = next_evaluation_date;
	}
	public int getTotal_rating() {
		return total_rating;
	}
	public void setTotal_rating(int total_rating) {
		this.total_rating = total_rating;
	}
	public int getEmployee_am_ika() {
		return employee_am_ika;
	}
	public void setEmployee_am_ika(int employee_am_ika) {
		this.employee_am_ika = employee_am_ika;
	}
}
