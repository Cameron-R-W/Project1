package com.revature.models;

public class InformationRequest {
	int id;
	String info;
	int to_emp_id;
	int from_emp_id;
	
	public InformationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InformationRequest(int id, String info, int to_emp_id, int from_emp_id) {
		super();
		this.id = id;
		this.info = info;
		this.to_emp_id = to_emp_id;
		this.from_emp_id = from_emp_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getTo_emp_id() {
		return to_emp_id;
	}

	public void setTo_emp_id(int to_emp_id) {
		this.to_emp_id = to_emp_id;
	}

	public int getFrom_emp_id() {
		return from_emp_id;
	}

	public void setFrom_emp_id(int from_emp_id) {
		this.from_emp_id = from_emp_id;
	}

	@Override
	public String toString() {
		return "InformationRequest [id=" + id + ", info=" + info + ", to_emp_id=" + to_emp_id + ", from_emp_id="
				+ from_emp_id + "]";
	}
	
	
}
