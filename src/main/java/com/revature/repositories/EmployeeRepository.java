package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeRepository {

	public Employee getEmployee(int id);
	public List<Employee> getAllEmployees();
	public boolean updateEmployee(Employee e);
}
