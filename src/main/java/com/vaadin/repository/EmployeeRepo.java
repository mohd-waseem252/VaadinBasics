package com.vaadin.repository;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.entity.Employee;
import com.vaadin.ui.Button;

public class EmployeeRepo {

	List<Employee> empList=new ArrayList<>();
	
	{
		empList.add(new Employee(101,"Vishal", 10000));
		empList.add(new Employee(102,"Ganesh", 150000));
		empList.add(new Employee(103,"Inder", 14000));
		empList.add(new Employee(104,"Shlok", 12000));
	}
	
	public List<Employee> getEmployees(){
		
		return this.empList;
	}
	
	public void remove(Employee emp) {
		for (Employee employee : empList) {
			if(employee.getId()==emp.getId()) {
				this.empList.remove(employee);
				break;
			}
		}
	}
}
