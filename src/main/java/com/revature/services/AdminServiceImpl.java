package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.BencoApproval;
import com.revature.models.DepheadApproval;
import com.revature.models.Employee;
import com.revature.models.Event;
import com.revature.models.SuperApproval;

public class AdminServiceImpl implements AdminService{

	public static EmployeeService empService = new EmployeeServiceImpl();
	public static EventService eventService = new EventServiceImpl();
	public static SuperApprovalService superService = new SuperApprovalServiceImpl();
	public static DepheadApprovalService depheadService = new DepheadApprovalServiceImpl();
	public static BencoApprovalService bencoService = new BencoApprovalServiceImpl();
	
	@Override
	public void sendEventForApproval(int empID, Event event) {
		Employee emp = empService.getEmployee(empID);
		int supervisorID = emp.getSuperid();
		Employee supervisor = empService.getEmployee(supervisorID);
		
		//The events id is initially set to 0 but once added to the database its id is something else
		//We need to find that event again by matching all the fields to the event we have in the parameter. They are the same events just with different ID's
		//Once we find the event from the DB with the matching fields we set the id for the event in our params to the matching events id
		ArrayList<Event> checkEvents = (ArrayList<Event>) eventService.getAllEvents(empID);
		System.out.println(event.toString());
		for(Event e : checkEvents)
		{
			if(e.getFname().equals(event.getFname()) && e.getLname().equals(event.getLname()) && e.getDate().equals(event.getDate()) && e.getDescription().equals(event.getDescription())
					&& e.getTypeid() == event.getTypeid() && e.getGradeid() == event.getGradeid())
			{
				event.setId(e.getId());
			}
		}
		//supervisor is just a supervisor
		//Create the approval for the supervisor based on employee & event info
		if(supervisor.getRoleid() == 2)
		{
			SuperApproval superApproval = new SuperApproval();
			superApproval.setDate(event.getDate());
			superApproval.setCost(event.getReimbursment());
			superApproval.setStatus(event.getStatus());
			superApproval.setEmpid(supervisor.getId());
			superApproval.setEventid(event.getId());
			superService.addSuperApproval(superApproval);
			System.out.println("reached");
		}
		//supervisor is a department head
		else if(supervisor.getRoleid() == 3)
		{
			DepheadApproval depheadApproval = new DepheadApproval();
			depheadApproval.setDate(event.getDate());
			depheadApproval.setCost(event.getReimbursment());
			depheadApproval.setStatus(event.getStatus());
			depheadApproval.setEmpid(supervisor.getId());
			depheadApproval.setEventid(event.getId());
			depheadService.addDepheadApproval(depheadApproval);
		}
		//supervisor is benco
		else if(supervisor.getRoleid() == 4)
		{
			BencoApproval bencoApproval = new BencoApproval();
			bencoApproval.setDate(event.getDate());
			bencoApproval.setCost(event.getReimbursment());
			bencoApproval.setStatus(event.getStatus());
			bencoApproval.setEmpid(supervisor.getId());
			bencoApproval.setEventid(event.getId());
			bencoService.addBencoApproval(bencoApproval);
		}
	}

	@Override
	public List<Event> viewIncRequest(int empID) {
		List<SuperApproval> superApprovals; 
		List<DepheadApproval> depheadApprovals;
		List<BencoApproval> bencoApprovals;
		List<Event> allPendingEvents = eventService.getAllEvents(empID);
		List<Event> emp_approval_events = new ArrayList<Event>();
		Employee supervisor = empService.getEmployee(empID);
		int type = supervisor.getRoleid();
		//Store pending events that match this employee's events they need to approve into superEvents
		//The events for this employee to approve are stored into depheadApprovals/superApprovals/bencoApprovals 
		if(type == 2)
		{
			superApprovals = superService.getAllSuperApprovals(empID);

			for(SuperApproval approval : superApprovals)
			{
				Event e = eventService.getEvent(approval.getEventid());
				if(e.getStatus().equals("pending"))
				{
					emp_approval_events.add(e);
				}
			}
		}
		else if(type == 3)
		{
			depheadApprovals = depheadService.getAllDepheadApprovals(empID);
			for(DepheadApproval approval : depheadApprovals)
			{
				Event e = new Event();
				e = eventService.getEvent(approval.getEventid());
				System.out.println(e.getStatus());
				if(e.getStatus().equals("pending"))
				{
					emp_approval_events.add(e);
				}
			}
		}
		else if(type == 4)
		{
			bencoApprovals = bencoService.getAllBencoApprovals(empID);
			for(BencoApproval approval : bencoApprovals)
			{
				Event e = eventService.getEvent(approval.getEventid());
				if(e.getStatus().equals("pending"))
				{
					emp_approval_events.add(e);
				}
			}
		}
		return emp_approval_events;
	}

	public void approveEvent(Employee supervisor, Event event)
	{
		List<SuperApproval> superApprovals; 
		List<DepheadApproval> depheadApprovals;
		List<BencoApproval> bencoApprovals;
		
		//Check roles
		int type = supervisor.getRoleid();
		//super
		if(type == 2)
		{
			superApprovals = superService.getAllSuperApprovals(supervisor.getId());
			for(SuperApproval approval : superApprovals)
			{
				//find the event that matches the event id for the approval
				if(approval.getEventid() == event.getId())
				{

					//then send the approval to the department head
					DepheadApproval newApproval = new DepheadApproval();
					newApproval.setDate(approval.getDate());
					newApproval.setCost(approval.getCost());
					newApproval.setStatus(approval.getStatus());
					newApproval.setEmpid(supervisor.getSuperid());
					newApproval.setEventid(approval.getEventid());
					
					depheadService.addDepheadApproval(newApproval);
					
					//set the approval to have a status of approved
					approval.setStatus("approved");
					superService.updateSuperApproval(approval);
				}
			}
		}
		//department head
		if(type == 3)
		{
			depheadApprovals = depheadService.getAllDepheadApprovals(supervisor.getId());
			for(DepheadApproval approval : depheadApprovals)
			{
				//find the event that matches the event id for the approval
				if(approval.getEventid() == event.getId())
				{

					//then send the approval to the department head
					BencoApproval newApproval = new BencoApproval();
					newApproval.setDate(approval.getDate());
					newApproval.setCost(approval.getCost());
					newApproval.setStatus(approval.getStatus());
					newApproval.setEmpid(supervisor.getSuperid());
					newApproval.setEventid(approval.getEventid());
					
					bencoService.addBencoApproval(newApproval);
					
					//set the approval to have a status of approved
					approval.setStatus("approved");
					depheadService.updateDepheadApproval(approval);
				}
			}
		}
		//benco
		if(type == 4)
		{
			bencoApprovals = bencoService.getAllBencoApprovals(supervisor.getId());
			for(BencoApproval approval : bencoApprovals)
			{
				if(approval.getEventid() ==  event.getId())
				{
					//Set the benco's approval to approved
					approval.setStatus("approved");
					
					//Update the employee's tuition
					Employee emp = empService.getEmployee(event.getEmpid());
					//University Course = 80% 1/ Seminars = 60% 2/ Cert Prep = 75% 3/ Cert = 100% 4, Tech training 90% 5, other = 6 30%
					int reimbursement = event.getReimbursment();
					int amountAwarded;
					int empTuition = emp.getTuition();
					if(event.getTypeid() == 1)
					{
						amountAwarded = (int) (reimbursement * .8);
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					else if(event.getTypeid() == 2)
					{
						amountAwarded = (int) (reimbursement * .6);
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					else if(event.getTypeid() == 3)
					{
						amountAwarded = (int) (reimbursement * .75);
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					else if(event.getTypeid() == 4)
					{
						amountAwarded = reimbursement;
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					else if(event.getTypeid() == 5)
					{
						amountAwarded = (int) (reimbursement * .9);
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					else if(event.getTypeid() == 6)
					{
						amountAwarded = (int) (reimbursement * .3);
						empTuition = empTuition - amountAwarded;
						emp.setTuition(empTuition);
						empService.updateEmployee(emp);
					}
					
				}
				bencoService.updateBencoApproval(approval);

			}
			//update the event to be approved in the event table 
			eventService.updateEvent(event);
		}
	}
	
	public void deleteEvent(Employee emp, Event event)
	{
		if(emp.getRoleid() == 2)
		{
			ArrayList<SuperApproval> approvals = (ArrayList<SuperApproval>) superService.getAllSuperApprovals(emp.getId());
			for(SuperApproval approval : approvals)
			{
				if(approval.getEventid() == event.getId())
				{
					superService.deleteSuperApproval(approval.getId());
				}
			}
		}
		else if(emp.getRoleid() == 3)
		{
			ArrayList<DepheadApproval> approvals = (ArrayList<DepheadApproval>) depheadService.getAllDepheadApprovals(emp.getId());
			for(DepheadApproval approval : approvals)
			{
				if(approval.getEventid() == event.getId())
				{
					depheadService.deleteDepheadApproval(approval.getId());
				}
			}
		}
		else if(emp.getRoleid() == 4)
		{
			ArrayList<BencoApproval> approvals = (ArrayList<BencoApproval>) bencoService.getAllBencoApprovals(emp.getId());
			for(BencoApproval approval : approvals)
			{
				if(approval.getEventid() == event.getId())
				{
					bencoService.deleteBencoApproval(approval.getId());
				}
			}
		}
		eventService.deleteEvent(event.getId());
	}
}
