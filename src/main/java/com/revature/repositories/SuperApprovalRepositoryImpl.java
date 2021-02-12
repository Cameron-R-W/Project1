package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.SuperApproval;
import com.revature.models.SuperApproval;
import com.revature.util.JDBCConnection;

public class SuperApprovalRepositoryImpl implements SuperApprovalRepository{
	public static Connection conn = JDBCConnection.getConnection();
	@Override
	public boolean addSuperApproval(SuperApproval a) {
		try
		{
			System.out.println(a.toString());
			String sql = "CALL add_super_approval(?,?,?,?,?)";

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
	public SuperApproval getSuperApproval(int id) {
		try
		{
			//craft the statement
			String sql = "SELECT * FROM super_approval WHERE super_approval_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			//execute the statement
			ps.executeQuery();
			
			//storing that result set
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				SuperApproval b = new SuperApproval();
				b.setId(rs.getInt("super_approval_id"));
				b.setDate(rs.getString("super_approval_date"));
				b.setCost(rs.getInt("super_approval_cost"));
				b.setStatus(rs.getString("super_approval_status"));
				b.setEmpid(rs.getInt("super_approval_emp_id"));
				b.setEventid(rs.getInt("super_approval_event_id"));
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
	public List<SuperApproval> getAllSuperApprovals(int empid) {
		List approvals = new ArrayList<SuperApproval>();
		try
		{
			String sql = "SELECT * FROM super_approval";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("super_approval_emp_id") == empid)
				{
					SuperApproval a = new SuperApproval();
					a.setId(rs.getInt("super_approval_id"));
					a.setDate(rs.getString("super_approval_date"));
					a.setCost(rs.getInt("super_approval_cost"));
					a.setStatus(rs.getString("super_approval_status"));
					a.setEmpid(rs.getInt("super_approval_emp_id"));
					a.setEventid(rs.getInt("super_approval_event_id"));
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
	public boolean updateSuperApproval(SuperApproval a) {
		try
		{
			String sql = "UPDATE super_approval SET super_approval_date = ?, super_approval_cost = ?, super_approval_status = ?,"
					+ " super_approval_emp_id = ?, super_approval_event_id = ? WHERE super_approval_id = ?";
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
	public boolean deleteSuperApproval(int id) {
		try
		{
			String sql = "DELETE super_approval WHERE super_approval_id = ?";
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
