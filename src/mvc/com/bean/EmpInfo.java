package mvc.com.bean;

import java.io.*;
import java.util.Date;

/**
 * Bean class for the employee entity
 */
public class EmpInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int am_ika;
	private int amka;
	private int afm;
	private String username;
	private String firstname;
	private String lastname;
	private String sex;
	private Date birthday;
	private String photoName;
	private InputStream photoInput;
	private OutputStream photoOutput;
	private InputStream CV; 
	private String CVName;
	private String email;
	private String phone_number;
	private String department;
	private String jobPosition;	
	private int salary;
	private int bonus_earned;
	private Date date_of_Recruitment;
	private String attribute;
	
	public int getAm_ika() {
		return am_ika;
	}
	public void setAm_ika(int am_ika) {
		this.am_ika = am_ika;
	}
	public int getAmka() {
		return amka;
	}
	public void setAmka(int amka) {
		this.amka = amka;
	}
	public int getAfm() {
		return afm;
	}
	public void setAfm(int afm) {
		this.afm = afm;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}	
	public InputStream getPhotoInput() {
		return photoInput;
	}
	public void setPhotoInput(InputStream photoInput) {
		this.photoInput = photoInput;
	}
	public OutputStream getPhotoOutput() {
		return photoOutput;
	}
	public void setPhotoOutput(OutputStream photoOutput) {
		this.photoOutput = photoOutput;
	}
	public InputStream getCV() {
		return CV;
	}
	public void setCV(InputStream cV) {
		CV = cV;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBonus_earned() {
		return bonus_earned;
	}
	public void setBonus_earned(int bonus_earned) {
		this.bonus_earned = bonus_earned;
	}
	public Date getDate_of_Recruitment() {
		return date_of_Recruitment;
	}
	public void setDate_of_Recruitment(Date date_of_Recruitment) {
		this.date_of_Recruitment = date_of_Recruitment;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getCVName() {
		return CVName;
	}
	public void setCVName(String cVName) {
		CVName = cVName;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
