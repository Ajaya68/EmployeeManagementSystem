package com.ems.dao;

import com.ems.model.Employee;
import java.util.List;

/**
 * EmployeeDAO Interface
 * Defines the contract for all CRUD operations.
 * Demonstrates: Abstraction via Interface
 */
public interface EmployeeDAO {

    boolean addEmployee(Employee emp);
    boolean updateEmployee(Employee emp);
    boolean deleteEmployee(int empId);
    Employee searchEmployeeById(int empId);
    List<Employee> searchEmployeeByName(String name);
    List<Employee> getAllEmployees();
    boolean isEmployeeExists(int empId);
}
