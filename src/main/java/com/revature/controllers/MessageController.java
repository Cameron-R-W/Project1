package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.revature.models.Employee;
import com.revature.models.Event;
import com.revature.models.InformationRequest;
import com.revature.services.AdminService;
import com.revature.services.AdminServiceImpl;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.EventService;
import com.revature.services.EventServiceImpl;
import com.revature.services.InformationRequestService;
import com.revature.services.InformationRequestServiceImpl;

public class MessageController {

	public static InformationRequestService as = new InformationRequestServiceImpl();
	public static AdminService adminService = new AdminServiceImpl();
	public static EmployeeService empService = new EmployeeServiceImpl();
	public static Gson gson = new Gson();
	
 public static void sendMessage(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {

	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		InformationRequest a = gson.fromJson(request.getReader(), InformationRequest.class);
		a.setFrom_emp_id(id);
		boolean success = as.addInformationRequest(a);
		
		
		response.getWriter().append(gson.toJson(a));
	}
 
 public static void viewMessages(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {

	 	//Retrieve user id
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		List<InformationRequest> messages = as.getAllInformationRequestsSent(id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(messages);
		
		response.getWriter().append(jsonString);
	}
}
