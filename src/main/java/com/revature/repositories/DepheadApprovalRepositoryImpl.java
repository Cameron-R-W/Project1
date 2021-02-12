package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.DepheadApproval;
import com.revature.models.DepheadApproval;
import com.revature.util.JDBCConnection;

public class DepheadApprovalRepositoryImpl implements DepheadApprovalRepository{
	
	public static Connection conn = JDBCConnection.getConnection();
	
	@Override
	public boolean addDepheadApproval(DepheadApproval a) {
		try
		{
			System.out.println(a.toString());
			String sql = "CALL add_dephead_approval(?,?,?,?,?)";
			//Callable statements are used to call a procedure
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, a.getDate());
			cs.setString(2, Integer.toString(a.getCost()));
			cs.setString(3, a.getStatus());
			cs.setString(4, Integer.toString(a.getEmpid()));
			cs.setString(5, Integer.toString(a.getEventid()));
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
	public DepheadApproval getDepheadApproval(int id) {
		try
		{
			//craft the statement
			String sql = "SELECT * FROM dephead_approval WHERE dephead_approval_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			//execute the statement
			ps.executeQuery();
			
			//storing that result set
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				DepheadApproval b = new DepheadApproval();
				b.setId(rs.getInt("dephead_approval_id"));
				b.setDate(rs.getString("dephead_approval_date"));
				b.setCost(rs.getInt("dephead_approval_cost"));
				b.setStatus(rs.getString("dephead_approval_status"));
				b.setEmpid(rs.getInt("dephead_approval_emp_id"));
				b.setEventid(rs.getInt("dephead_approval_event_id"));
				return b;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DepheadApproval> getAllDepheadApprovals(int empid) {
		List approvals = new ArrayList<DepheadApproval>();
		try
		{
			String sql = "SELECT * FROM dephead_approval";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("dephead_approval_emp_id") == empid)
				{
					DepheadApproval a = new DepheadApproval();
					a.setId(rs.getInt("dephead_approval_id"));
					a.setDate(rs.getString("dephead_approval_date"));
					a.setCost(rs.getInt("dephead_approval_cost"));
					a.setStatus(rs.getString("dephead_approval_status"));
					a.setEmpid(rs.getInt("dephead_approval_emp_id"));
					a.setEventid(rs.getInt("dephead_approval_event_id"));
					approvals.add(a);
				}
			}
			return approvals;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateDepheadApproval(DepheadApproval a) {
		try
		{
			String sql = "UPDATE dephead_approval SET dephead_approval_date = ?, dephead_approval_cost = ?, dephead_approval_status = ?,"
					+ " dephead_approval_emp_id = ?, dephead_approval_event_id = ? WHERE dephead_approval_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getDate());
			ps.setString(2, Integer.toString(a.getCost()));
			ps.setString(3, a.getStatus());
			ps.setString(4, Integer.toString(a.getEmpid()));
			ps.setString(5, Integer.toString(a.getEventid()));
			ps.setString(6, Integer.toString(a.getId()));
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
	public boolean deleteDepheadApproval(int id) {
		try
		{
			String sql = "DELETE dephead_approval WHERE dephead_approval_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		return false;
	}

}
