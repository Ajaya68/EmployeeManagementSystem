package com.ems.model;

/**
 * Employee Model Class
 * Represents an Employee entity with encapsulated fields.
 * Demonstrates: Encapsulation, OOP concepts
 */
public class Employee {

    // Private fields - Encapsulation
    private int empId;
    private String empName;
    private String designation;
    private String department;
    private double salary;
    private String email;
    private String phone;
    private String joiningDate;

    // Default Constructor
    public Employee() {}

    // Parameterized Constructor
    public Employee(int empId, String empName, String designation,
                    String department, double salary, String email,
                    String phone, String joiningDate) {
        this.empId       = empId;
        this.empName     = empName;
        this.designation = designation;
        this.department  = department;
        this.salary      = salary;
        this.email       = email;
        this.phone       = phone;
        this.joiningDate = joiningDate;
    }

    // ──────────────────────── Getters & Setters ────────────────────────

    public int getEmpId()               { return empId; }
    public void setEmpId(int empId)     { this.empId = empId; }

    public String getEmpName()                  { return empName; }
    public void setEmpName(String empName)      { this.empName = empName; }

    public String getDesignation()                      { return designation; }
    public void setDesignation(String designation)      { this.designation = designation; }

    public String getDepartment()                   { return department; }
    public void setDepartment(String department)    { this.department = department; }

    public double getSalary()               { return salary; }
    public void setSalary(double salary)    { this.salary = salary; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public String getPhone()                { return phone; }
    public void setPhone(String phone)      { this.phone = phone; }

    public String getJoiningDate()                      { return joiningDate; }
    public void setJoiningDate(String joiningDate)      { this.joiningDate = joiningDate; }

    // toString – used for displaying records
    @Override
    public String toString() {
        return String.format(
            "| %-6d | %-20s | %-18s | %-12s | %-10.2f | %-25s | %-13s | %-12s |",
            empId, empName, designation, department, salary, email, phone, joiningDate
        );
    }
}
