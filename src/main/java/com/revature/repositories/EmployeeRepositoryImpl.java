package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.util.JDBCConnection;

public class EmployeeRepositoryImpl implements EmployeeRepository{
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Employee getEmployee(int id) {
		try
		{
			String sql = "SELECT * FROM employee WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Employee e = new Employee();
				e.setId(rs.getInt("emp_id"));
				e.setFname(rs.getString("emp_fname"));
				e.setLname(rs.getString("emp_lname"));
				e.setUsername(rs.getString("emp_username"));
				e.setPassword(rs.getString("emp_password"));
				e.setSuperid(rs.getInt("emp_supervior_id"));
				e.setTuition(rs.getInt("emp_tuition"));
				e.setRoleid(rs.getInt("emp_role_id"));
				return e;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		try
		{
			ArrayList<Employee> listOfEmployee = new ArrayList<Employee>();
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Employee e = new Employee();
				e.setId(rs.getInt("emp_id"));
				e.setFname(rs.getString("emp_fname"));
				e.setLname(rs.getString("emp_lname"));
				e.setUsername(rs.getString("emp_username"));
				e.setPassword(rs.getString("emp_password"));
				e.setSuperid(rs.getInt("emp_supervior_id"));
				e.setTuition(rs.getInt("emp_tuition"));
				e.setRoleid(rs.getInt("emp_role_id"));
				listOfEmployee.add(e);
			}
			return listOfEmployee;
		}catch(SQLException a)
		{
			a.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		try
		{
			String sql = "Update employee SET emp_fname = ?, emp_lname = ?, emp_username = ?, emp_password = ?, "
					+ "emp_supervior_id = ?, emp_tuition = ?, emp_role_id = ? WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getFname());
			ps.setString(2, e.getLname());
			ps.setString(3, e.getUsername());
			ps.setString(4, e.getPassword());
			ps.setString(5, Integer.toString(e.getSuperid()));
			ps.setString(6, Integer.toString(e.getTuition()));
			ps.setString(7, Integer.toString(e.getRoleid()));
			ps.setString(8, Integer.toString(e.getId()));
			ps.executeQuery();
			return true;
		}
		catch(SQLException a)
		{
			a.printStackTrace();
		}
		return false;
	}

}
