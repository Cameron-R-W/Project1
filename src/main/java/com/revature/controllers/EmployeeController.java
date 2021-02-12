package com.revature.controllers;

import java.io.IOException;
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
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;

public class EmployeeController {


	public static EmployeeService as = new EmployeeServiceImpl();
	public static Gson gson = new Gson();
	public static void getEmployee (HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		Employee e = as.login(username, password);
		String employeeId = String.valueOf(e.getId());

		Cookie cookie1 = new Cookie("loggedInUser", e.getUsername());
		Cookie cookie2 = new Cookie("employeeID", employeeId);
        cookie1.setSecure(true);
        cookie2.setSecure(true);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
		
		response.getWriter().append(gson.toJson(e));
	}
	public static void viewEmployee (HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Cookie c[]=request.getCookies();
		int id = Integer.parseInt(c[2].getValue());
		Employee e = as.getEmployee(id);

		response.getWriter().append(gson.toJson(e));
	}
	 public static void viewEmployees(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException 
	 {
		 	//Retrieve user id
			Cookie c[]=request.getCookies();
			int id = Integer.parseInt(c[2].getValue());
			List<Employee> employees = as.getAllEmployees();
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(employees);
			
			response.getWriter().append(jsonString);
	}
}
