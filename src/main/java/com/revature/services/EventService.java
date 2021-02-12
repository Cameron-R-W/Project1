package com.revature.services;

import java.util.List;

import com.revature.models.Event;

public interface EventService {

	public boolean addEvent(Event e);
	public Event getEvent(int i);
	public List<Event> getAllEvents(int empid);
	public boolean updateEvent(Event e);
	public boolean deleteEvent(int i);
	public List<Event> getPendingEvents(int empid);
	public List<Event> getApprovedEvents(int empid);
}
