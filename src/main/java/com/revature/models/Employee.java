package com.revature.models;

public class Employee {
	
	int id;
	String fname;
	String lname;
	String username;
	String password;
	int superid;
	int tuition;
	int roleid;
	
	public Employee()
	{
		super();
	}
	public Employee(int id, String fname, String lname, String username, String password, int superid, int tuition,
			int roleid) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.superid = superid;
		this.tuition = tuition;
		this.roleid = roleid;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSuperid() {
		return superid;
	}
	public void setSuperid(int superid) {
		this.superid = superid;
	}
	public int getTuition() {
		return tuition;
	}
	public void setTuition(int tuition) {
		this.tuition = tuition;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", password="
				+ password + ", superid=" + superid + ", tuition=" + tuition + ", roleid=" + roleid + "]";
	}
	
}
