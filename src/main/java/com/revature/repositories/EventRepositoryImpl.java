package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Event;
import com.revature.util.JDBCConnection;



public class EventRepositoryImpl implements EventRepository{
	public static Connection conn = JDBCConnection.getConnection();
	@Override
	public boolean addEvent(Event a) {
		try
		{		
			String sql = "CALL add_event(?,?,?,?,?,?,?,?,?,?,?,?)";
			//Callable statements are used to call a procedure
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, a.getFname());
			cs.setString(2, a.getLname());
			cs.setString(3, a.getLocation());
			cs.setString(4, a.getDescription());
			cs.setString(5, a.getStatus());
			cs.setString(6, a.getTime());
			cs.setString(7, Integer.toString(a.getReimbursment()));
			cs.setString(8, a.getLetterGrade());
			cs.setString(9, a.getDate());
			cs.setString(10, Integer.toString(a.getGradeid()));
			cs.setString(11, Integer.toString(a.getTypeid()));
			cs.setString(12, Integer.toString(a.getEmpid()));
			
			cs.execute();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Event getEvent(int i) {
		try
		{
			//craft the statement
			String sql = "SELECT * FROM event WHERE event_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(i));
			
			//execute the statement
			ps.executeQuery();
			
			//storing that result set
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Event e = new Event();
				e.setId(rs.getInt("event_id"));
				e.setFname(rs.getString("event_emp_fname"));
				e.setLname(rs.getString("event_emp_lname"));
				e.setLocation(rs.getString("event_location"));
				e.setDescription(rs.getString("event_description"));
				e.setStatus(rs.getString("event_status"));
				e.setTime(rs.getString("event_time"));
				e.setReimbursment(rs.getInt("event_reimbursment"));
				e.setLetterGrade(rs.getString("event_lettergrade"));
				e.setDate(rs.getString("event_date"));
				e.setGradeid(rs.getInt("event_grade_id"));
				e.setTypeid(rs.getInt("event_type_id"));
				e.setEmpid(rs.getInt("event_emp_id"));
				return e;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Event> getAllEvents(int empid) {
		List<Event> userEvents = new ArrayList<Event>();
		try
		{
			String sql = "SELECT * FROM event";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("event_emp_id") == empid)
				{
					Event e = new Event();
					e.setId(rs.getInt("event_id"));
					e.setFname(rs.getString("event_emp_fname"));
					e.setLname(rs.getString("event_emp_lname"));
					e.setLocation(rs.getString("event_location"));
					e.setDescription(rs.getString("event_description"));
					e.setStatus(rs.getString("event_status"));
					e.setTime(rs.getString("event_time"));
					e.setReimbursment(rs.getInt("event_reimbursment"));
					e.setLetterGrade(rs.getString("event_lettergrade"));
					e.setDate(rs.getString("event_date"));
					e.setGradeid(rs.getInt("event_grade_id"));
					e.setTypeid(rs.getInt("event_type_id"));
					e.setEmpid(rs.getInt("event_emp_id"));
					userEvents.add(e);
				}
			}
			return userEvents;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEvent(Event e) {
		try
		{
			String sql = "Update event SET event_emp_fname = ?, event_emp_lname = ?, event_location = ?, "
					+ " event_description = ?, event_status = ?, event_time = ?, event_reimbursment = ?, event_lettergrade = ?,"
					+ " event_date = ?, event_grade_id = ?, event_type_id = ?, event_emp_id = ? WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
						
			
			ps.setString(1, e.getFname());
			ps.setString(2, e.getLname());
			ps.setString(3, e.getLocation());
			ps.setString(4, e.getDescription());
			ps.setString(5, e.getStatus());
			ps.setString(6, e.getTime());
			ps.setString(7, Integer.toString(e.getReimbursment()));
			ps.setString(8, e.getLetterGrade());
			ps.setString(9, e.getDate());
			ps.setString(10, Integer.toString(e.getGradeid()));
			ps.setString(11, Integer.toString(e.getTypeid()));
			ps.setString(12, Integer.toString(e.getEmpid()));
			ps.setString(13, Integer.toString(e.getId()));
			
			ps.executeQuery();
			return true;
		}
		catch(SQLException a)
		{
			a.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEvent(int i) {
		try
		{
			System.out.println("reached delete method");
			String sql = "DELETE event WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(i));
			ps.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
