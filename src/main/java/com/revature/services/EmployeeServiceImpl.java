package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepository;
import com.revature.repositories.EmployeeRepositoryImpl;


public class EmployeeServiceImpl implements EmployeeService{
	EmployeeRepository a = new EmployeeRepositoryImpl();
	
	@Override
	public Employee getEmployee(int id) {
		return a.getEmployee(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = a.getAllEmployees();
		return list;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		return a.updateEmployee(e);
	}

	@Override
	public Employee login(String username, String password)
	{
		ArrayList<Employee> listOfEmployees = (ArrayList<Employee>) a.getAllEmployees();
		for(Employee user : listOfEmployees)
		{
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
			{
				return user;
			}
		}
		return null;
	}
}
