package mvc.com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean class for the goal entity
 */
public class GoalInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String completed;
	private Date deadline;
	private String directions;
	private String resources_needed;
	private int complexity;
	private int significance;
	private String type;
	private String concerning_department;
	private int bonus_ammount;
	private String in_progress;
	private int manager_am_ika;
	private String manager_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getResources_needed() {
		return resources_needed;
	}
	public void setResources_needed(String resources_needed) {
		this.resources_needed = resources_needed;
	}
	public int getComplexity() {
		return complexity;
	}
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
	public int getSignificance() {
		return significance;
	}
	public void setSignificance(int significance) {
		this.significance = significance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConcerning_department() {
		return concerning_department;
	}
	public void setConcerning_department(String concerning_department) {
		this.concerning_department = concerning_department;
	}
	public int getBonus_ammount() {
		return bonus_ammount;
	}
	public void setBonus_ammount(int bonus_ammount) {
		this.bonus_ammount = bonus_ammount;
	}
	public String getIn_progress() {
		return in_progress;
	}
	public void setIn_progress(String in_progress) {
		this.in_progress = in_progress;
	}
	public int getManager_am_ika() {
		return manager_am_ika;
	}
	public void setManager_am_ika(int manager_am_ika) {
		this.manager_am_ika = manager_am_ika;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
}
