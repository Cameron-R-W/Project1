package com.revature.services;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeService {
	public Employee getEmployee(int id);
	public List<Employee> getAllEmployees();
	public boolean updateEmployee(Employee e);
	public Employee login(String username, String password);
}
