package com.revature.models;

public class SuperApproval {
	
	int id;
	String date;
	int cost;
	String status;
	int empid;
	int eventid;
	
	public SuperApproval() {
		super();
	}
	public SuperApproval(int id, String date, int cost, String status, int empid, int eventid) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.status = status;
		this.empid = empid;
		this.eventid = eventid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	
	@Override
	public String toString() {
		return "SuperApproval [id=" + id + ", date=" + date + ", cost=" + cost + ", status=" + status + ", empid="
				+ empid + ", eventid=" + eventid + "]";
	}
	
	
}
