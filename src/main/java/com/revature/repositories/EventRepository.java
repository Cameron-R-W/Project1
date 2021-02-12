package com.revature.repositories;

import java.util.List;

import com.revature.models.Event;

public interface EventRepository {

	public boolean addEvent(Event e);
	public Event getEvent(int i);
	public List<Event> getAllEvents(int empid);
	public boolean updateEvent(Event e);
	public boolean deleteEvent(int i);
}
