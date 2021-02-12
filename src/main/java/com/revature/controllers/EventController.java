package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import com.revature.models.Employee;
import com.revature.models.Event;
import com.revature.services.AdminService;
import com.revature.services.AdminServiceImpl;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.EventService;
import com.revature.services.EventServiceImpl;

public class EventController {
	public static EventService as = new EventServiceImpl();
	public static AdminService adminService = new AdminServiceImpl();
	public static EmployeeService empService = new EmployeeServiceImpl();
	public static Gson gson = new Gson();
	
 public static void addEvent(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {

	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		Event a = gson.fromJson(request.getReader(), Event.class);
		a.setEmpid(id);
		boolean success = as.addEvent(a);
		
		//add the event to the supervisors inbox
		adminService.sendEventForApproval(id, a);
		
		response.getWriter().append(gson.toJson(a));
	}
 
 public static void viewPendingEvents(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException 
 {
	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		List<Event> pendingEvents = as.getPendingEvents(id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(pendingEvents);
		
		response.getWriter().append(jsonString);
}
 
 public static void viewApprovedEvents(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException 
 {
	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		List<Event> approvedEvents = as.getApprovedEvents(id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(approvedEvents);
		
		response.getWriter().append(jsonString);
}
 public static void viewIncRequests(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException 
 {
	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		ArrayList<Event> listOfRequests = (ArrayList<Event>) adminService.viewIncRequest(id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(listOfRequests);
		
		response.getWriter().append(jsonString);
	}
 
 public static void approveEvent(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {

	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		Event a = gson.fromJson(request.getReader(), Event.class);
		Employee supervisor = empService.getEmployee(id);
		adminService.approveEvent(supervisor, a);
		
		response.getWriter().append(gson.toJson(a));
	}
 
 
 public static void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {

	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		Event a = gson.fromJson(request.getReader(), Event.class);
		Employee supervisor = empService.getEmployee(id);
		adminService.deleteEvent(supervisor, a);
		
		response.getWriter().append(gson.toJson(a));
	}
}
