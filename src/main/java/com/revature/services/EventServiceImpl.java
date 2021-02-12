package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Event;
import com.revature.repositories.EventRepository;
import com.revature.repositories.EventRepositoryImpl;

public class EventServiceImpl implements EventService{

	EventRepository a = new EventRepositoryImpl();
	@Override
	public boolean addEvent(Event e) {
		return a.addEvent(e);
	}

	@Override
	public Event getEvent(int i) {
		return a.getEvent(i);
	}

	@Override
	public List<Event> getAllEvents(int empid) {
		List<Event> list = a.getAllEvents(empid);
		return list;
	}

	@Override
	public boolean updateEvent(Event e) {
		return a.updateEvent(e);
	}

	@Override
	public boolean deleteEvent(int i) {
		return a.deleteEvent(i);
	}

	public List<Event> getPendingEvents(int empid) {
		List<Event> list = a.getAllEvents(empid);
		List<Event> pending = new ArrayList<Event>();
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getStatus().equals("pending"))
			{
				pending.add(list.get(i));
			}
		}
		return pending;
	}
	
	public List<Event> getApprovedEvents(int empid) {
		List<Event> list = a.getAllEvents(empid);
		List<Event> approved = new ArrayList<Event>();
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getStatus().equals("approved"))
			{
				approved.add(list.get(i));
			}
		}
		return approved;
	}
}
