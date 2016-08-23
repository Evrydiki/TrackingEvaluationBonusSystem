package mvc.com.bean;

import java.io.Serializable;

public class CategoryInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String category_title;
	private int rating;
	
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
