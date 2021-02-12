package com.revature.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.revature.models.BencoApproval;
import com.revature.util.JDBCConnection;


public class BencoApprovalRepositoryImpl implements BencoApprovalRepository{

	public static Connection conn = JDBCConnection.getConnection();
	@Override
	public boolean addBencoApproval(BencoApproval a) {
		try
		{
			System.out.println(a.toString());
			String sql = "CALL add_benco_approval(?,?,?,?,?)";

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
	public BencoApproval getBencoApproval(int id) {
		try
		{
			//craft the statement
			String sql = "SELECT * FROM benco_approval WHERE benco_approval_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			//execute the statement
			ps.executeQuery();
			
			//storing that result set
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				BencoApproval b = new BencoApproval();
				b.setId(rs.getInt("benco_approval_id"));
				b.setDate(rs.getString("benco_approval_date"));
				b.setCost(rs.getInt("benco_approval_cost"));
				b.setStatus(rs.getString("benco_approval_status"));
				b.setEmpid(rs.getInt("benco_approval_emp_id"));
				b.setEventid(rs.getInt("benco_approval_event_id"));
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
	public List<BencoApproval> getAllBencoApprovals(int empid) {
		List approvals = new ArrayList<BencoApproval>();
		try
		{
			String sql = "SELECT * FROM benco_approval";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("benco_approval_emp_id") == empid)
				{
					BencoApproval a = new BencoApproval();
					a.setId(rs.getInt("benco_approval_id"));
					a.setDate(rs.getString("benco_approval_date"));
					a.setCost(rs.getInt("benco_approval_cost"));
					a.setStatus(rs.getString("benco_approval_status"));
					a.setEmpid(rs.getInt("benco_approval_emp_id"));
					a.setEventid(rs.getInt("benco_approval_event_id"));
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
	public boolean updateBencoApproval(BencoApproval a) {
		try
		{
			String sql = "UPDATE Benco_approval SET benco_approval_date = ?, benco_approval_cost = ?, benco_approval_status = ?,"
					+ " benco_approval_emp_id = ?, benco_approval_event_id = ? WHERE benco_approval_id = ?";
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
	public boolean deleteBencoApproval(int id) {
		try
		{
			String sql = "DELETE benco_approval WHERE benco_approval_id = ?";
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
