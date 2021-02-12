package com.revature.models;

public class Event {

	int id;
	String fname;
	String lname;
	String location;
	String description;
	String status;
	String time;
	int reimbursment;
	String letterGrade;
	String date;
	int gradeid;
	int typeid;
	int empid;
	
	public Event()
	{
		super();
	}
	
	public Event(int id, String fname, String lname, String location, String description, String status, String time,
			int reimbursment, String letterGrade, String date, int gradeid, int typeid, int empid) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.location = location;
		this.description = description;
		this.status = status;
		this.time = time;
		this.reimbursment = reimbursment;
		this.letterGrade = letterGrade;
		this.date = date;
		this.gradeid = gradeid;
		this.typeid = typeid;
		this.empid = empid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getReimbursment() {
		return reimbursment;
	}

	public void setReimbursment(int reimbursment) {
		this.reimbursment = reimbursment;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getGradeid() {
		return gradeid;
	}

	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", fname=" + fname + ", lname=" + lname + ", location=" + location + ", description="
				+ description + ", status=" + status + ", time=" + time + ", reimbursment=" + reimbursment
				+ ", letterGrade=" + letterGrade + ", date=" + date + ", gradeid=" + gradeid + ", typeid=" + typeid
				+ ", empid=" + empid + "]";
	}
	
	
}
