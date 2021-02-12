package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.EventController;
import com.revature.controllers.MessageController;

public class RequestHelper {

	public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String uri = request.getRequestURI();
		System.out.println(uri);
	
		switch(uri)
		{
		case "/Project1/getEmployee.do":
		{
			EmployeeController.getEmployee(request, response);
			break;
		}
		case "/Project1/viewEmployee.do":
		{
			EmployeeController.viewEmployee(request, response);
			break;
		}
		case "/Project1/viewEmployees.do":
		{
			EmployeeController.viewEmployees(request, response);
			break;
		}
		case "/Project1/addEvent.do":
		{
			EventController.addEvent(request, response);
			break;
		}
		case "/Project1/viewPendingEvents.do":
		{
			EventController.viewPendingEvents(request, response);
			break;
		}
		case "/Project1/viewApprovedEvents.do":
		{
			EventController.viewApprovedEvents(request, response);
			break;
		}
		case "/Project1/viewIncRequests.do":
		{
			EventController.viewIncRequests(request, response);
			break;
		}
		case "/Project1/approveEvent.do":
		{
			EventController.approveEvent(request, response);
			break;
		}
		case "/Project1/deleteEvent.do":
		{
			EventController.deleteEvent(request, response);
			break;
		}
		case "/Project1/sendMessage.do":
		{
			MessageController.sendMessage(request, response);
			break;
		}
		case "/Project1/viewMessages.do":
		{
			MessageController.viewMessages(request, response);
			break;
		}
		default:
		{
			response.sendError(418,"Default case hit. Cannot brew coffee, is teapot");
			break;
		}
		}
	}
}
