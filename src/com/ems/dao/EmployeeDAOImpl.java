package com.ems.dao;

import com.ems.model.Employee;
import com.ems.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDAOImpl – Data Access Object Implementation
 * Implements all CRUD operations using JDBC and Oracle SQL.
 * Demonstrates: OOP Inheritance/Interface, JDBC, Exception Handling
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection conn;

    public EmployeeDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    // ──────────────────────────────────────────────
    //  1. ADD EMPLOYEE
    // ──────────────────────────────────────────────
    @Override
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (emp_id, emp_name, designation, department, " +
                     "salary, email, phone, joining_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp.getEmpId());
            ps.setString(2, emp.getEmpName());
            ps.setString(3, emp.getDesignation());
            ps.setString(4, emp.getDepartment());
            ps.setDouble(5, emp.getSalary());
            ps.setString(6, emp.getEmail());
            ps.setString(7, emp.getPhone());
            ps.setString(8, emp.getJoiningDate());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("[ERROR] Add Employee Failed: " + e.getMessage());
            return false;
        }
    }

    // ──────────────────────────────────────────────
    //  2. UPDATE EMPLOYEE
    // ──────────────────────────────────────────────
    @Override
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET emp_name=?, designation=?, department=?, " +
                     "salary=?, email=?, phone=? WHERE emp_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getEmpName());
            ps.setString(2, emp.getDesignation());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());
            ps.setString(5, emp.getEmail());
            ps.setString(6, emp.getPhone());
            ps.setInt(7, emp.getEmpId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("[ERROR] Update Employee Failed: " + e.getMessage());
            return false;
        }
    }

    // ──────────────────────────────────────────────
    //  3. DELETE EMPLOYEE
    // ──────────────────────────────────────────────
    @Override
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("[ERROR] Delete Employee Failed: " + e.getMessage());
            return false;
        }
    }

    // ──────────────────────────────────────────────
    //  4. SEARCH BY ID
    // ──────────────────────────────────────────────
    @Override
    public Employee searchEmployeeById(int empId) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        Employee emp = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Search by ID Failed: " + e.getMessage());
        }
        return emp;
    }

    // ──────────────────────────────────────────────
    //  5. SEARCH BY NAME
    // ──────────────────────────────────────────────
    @Override
    public List<Employee> searchEmployeeByName(String name) {
        String sql = "SELECT * FROM employees WHERE UPPER(emp_name) LIKE UPPER(?)";
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Search by Name Failed: " + e.getMessage());
        }
        return list;
    }

    // ──────────────────────────────────────────────
    //  6. GET ALL EMPLOYEES
    // ──────────────────────────────────────────────
    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees ORDER BY emp_id";
        List<Employee> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Get All Employees Failed: " + e.getMessage());
        }
        return list;
    }

    // ──────────────────────────────────────────────
    //  7. CHECK IF EMPLOYEE EXISTS
    // ──────────────────────────────────────────────
    @Override
    public boolean isEmployeeExists(int empId) {
        String sql = "SELECT COUNT(*) FROM employees WHERE emp_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Existence Check Failed: " + e.getMessage());
        }
        return false;
    }

    // ──────────────────────────────────────────────
    //  Helper: Map ResultSet → Employee Object
    // ──────────────────────────────────────────────
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("emp_id"),
            rs.getString("emp_name"),
            rs.getString("designation"),
            rs.getString("department"),
            rs.getDouble("salary"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("joining_date") != null
                ? rs.getDate("joining_date").toString()
                : "N/A"
        );
    }
}
