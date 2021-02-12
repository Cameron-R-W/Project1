package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Event;

public interface AdminService {
	public void sendEventForApproval(int empID, Event event);
	public List<Event> viewIncRequest(int empID);
	public void approveEvent(Employee emp, Event event);
	public void deleteEvent(Employee emp, Event event);
}
