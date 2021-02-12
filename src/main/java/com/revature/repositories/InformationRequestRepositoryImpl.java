package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.InformationRequest;
import com.revature.util.JDBCConnection;



public class InformationRequestRepositoryImpl implements InformationRequestRepository{
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean addInformationRequest(InformationRequest a) {
		try
		{
			System.out.println(a.toString());
			String sql = "CALL add_information_request(?,?,?)";

			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, a.getInfo());
			cs.setString(2, Integer.toString(a.getTo_emp_id()));
			cs.setString(3, Integer.toString(a.getFrom_emp_id()));
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
	public InformationRequest getInformationRequest(int id) {
		try
		{
			String sql = "SELECT * FROM information_request";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("information_request_id") == id)
				{
					InformationRequest a = new InformationRequest();
					a.setId(rs.getInt("information_request_id"));
					a.setInfo(rs.getString("information_request_info"));
					a.setTo_emp_id(rs.getInt("information_request_to_emp_id"));
					a.setFrom_emp_id(rs.getInt("information_request_from_emp_id"));
					return a;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//List of information requests received by this user (this user started this request)
	public List<InformationRequest> getAllInformationRequests(int empid) {
		ArrayList<InformationRequest> requests = new ArrayList<InformationRequest>();
		try
		{
			String sql = "SELECT * FROM information_request";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("information_request_from_emp_id") == empid)
				{
					InformationRequest a = new InformationRequest();
					a.setId(rs.getInt("information_request_id"));
					a.setInfo(rs.getString("information_request_info"));
					a.setTo_emp_id(rs.getInt("information_request_to_emp_id"));
					a.setFrom_emp_id(rs.getInt("information_request_from_emp_id"));
					requests.add(a);
				}
			}
			return requests;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	//List of information requests sent to this user 
	public List<InformationRequest> getAllInformationRequestsSent(int empid) {
		ArrayList<InformationRequest> requests = new ArrayList<InformationRequest>();
		try
		{
			String sql = "SELECT * FROM information_request";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("information_request_to_emp_id") == empid)
				{
					InformationRequest a = new InformationRequest();
					a.setId(rs.getInt("information_request_id"));
					a.setInfo(rs.getString("information_request_info"));
					a.setTo_emp_id(rs.getInt("information_request_to_emp_id"));
					a.setFrom_emp_id(rs.getInt("information_request_from_emp_id"));
					requests.add(a);
				}
			}
			return requests;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateInformationRequest(InformationRequest a) {
		try
		{
			String sql = "UPDATE information_request SET information_request_info = ?, information_request_to_emp_id = ?, information_request_from_emp_id = ? WHERE information_request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getInfo());
			ps.setString(2, Integer.toString(a.getTo_emp_id()));
			ps.setString(3, Integer.toString(a.getFrom_emp_id()));
			ps.setString(4, Integer.toString(a.getId()));
			ps.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteInformationRequest(int id) {
		try
		{
			String sql = "DELETE information_request WHERE information_request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
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
