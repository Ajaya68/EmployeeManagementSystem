package com.ems.main;

import com.ems.dao.EmployeeDAO;
import com.ems.dao.EmployeeDAOImpl;
import com.ems.model.Employee;

import java.util.List;
import java.util.Scanner;

/**
 * EmployeeService – Business Logic Layer
 * Handles user interactions and delegates to DAO layer.
 * Demonstrates: Modular Design, Exception Handling, OOP
 */
public class EmployeeService {

    private final EmployeeDAO dao;
    private final Scanner scanner;

    public EmployeeService() {
        this.dao     = new EmployeeDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    // ─────────────────────────────────────────────
    //  ADD EMPLOYEE
    // ─────────────────────────────────────────────
    public void addEmployee() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       ADD NEW EMPLOYEE        ║");
        System.out.println("╚══════════════════════════════╝");

        try {
            System.out.print("  Enter Employee ID   : ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (dao.isEmployeeExists(id)) {
                System.out.println("  [!] Employee ID " + id + " already exists!");
                return;
            }

            System.out.print("  Enter Name           : ");
            String name = scanner.nextLine().trim();

            System.out.print("  Enter Designation    : ");
            String desig = scanner.nextLine().trim();

            System.out.print("  Enter Department     : ");
            String dept = scanner.nextLine().trim();

            System.out.print("  Enter Salary         : ");
            double salary = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("  Enter Email          : ");
            String email = scanner.nextLine().trim();

            System.out.print("  Enter Phone          : ");
            String phone = scanner.nextLine().trim();

            System.out.print("  Enter Joining Date (YYYY-MM-DD) : ");
            String date = scanner.nextLine().trim();

            Employee emp = new Employee(id, name, desig, dept, salary, email, phone, date);

            if (dao.addEmployee(emp)) {
                System.out.println("\n  ✔ Employee added successfully!");
            } else {
                System.out.println("\n  ✘ Failed to add employee.");
            }

        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Invalid numeric input: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  UPDATE EMPLOYEE
    // ─────────────────────────────────────────────
    public void updateEmployee() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       UPDATE EMPLOYEE         ║");
        System.out.println("╚══════════════════════════════╝");

        try {
            System.out.print("  Enter Employee ID to Update : ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (!dao.isEmployeeExists(id)) {
                System.out.println("  [!] Employee ID " + id + " not found.");
                return;
            }

            // Show current record
            Employee current = dao.searchEmployeeById(id);
            System.out.println("\n  Current Record:");
            printTableHeader();
            System.out.println(current.toString());
            printTableFooter();

            System.out.println("\n  Enter New Details (press ENTER to keep current):");

            System.out.print("  Name         [" + current.getEmpName() + "] : ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) name = current.getEmpName();

            System.out.print("  Designation  [" + current.getDesignation() + "] : ");
            String desig = scanner.nextLine().trim();
            if (desig.isEmpty()) desig = current.getDesignation();

            System.out.print("  Department   [" + current.getDepartment() + "] : ");
            String dept = scanner.nextLine().trim();
            if (dept.isEmpty()) dept = current.getDepartment();

            System.out.print("  Salary       [" + current.getSalary() + "] : ");
            String salaryStr = scanner.nextLine().trim();
            double salary = salaryStr.isEmpty() ? current.getSalary() : Double.parseDouble(salaryStr);

            System.out.print("  Email        [" + current.getEmail() + "] : ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) email = current.getEmail();

            System.out.print("  Phone        [" + current.getPhone() + "] : ");
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) phone = current.getPhone();

            Employee updated = new Employee(id, name, desig, dept, salary, email, phone, current.getJoiningDate());

            if (dao.updateEmployee(updated)) {
                System.out.println("\n  ✔ Employee updated successfully!");
            } else {
                System.out.println("\n  ✘ Failed to update employee.");
            }

        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Invalid numeric input: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  DELETE EMPLOYEE
    // ─────────────────────────────────────────────
    public void deleteEmployee() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       DELETE EMPLOYEE         ║");
        System.out.println("╚══════════════════════════════╝");

        try {
            System.out.print("  Enter Employee ID to Delete : ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (!dao.isEmployeeExists(id)) {
                System.out.println("  [!] Employee ID " + id + " not found.");
                return;
            }

            Employee emp = dao.searchEmployeeById(id);
            System.out.println("\n  Record to be Deleted:");
            printTableHeader();
            System.out.println(emp.toString());
            printTableFooter();

            System.out.print("\n  Are you sure you want to delete? (yes/no) : ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes") || confirm.equals("y")) {
                if (dao.deleteEmployee(id)) {
                    System.out.println("\n  ✔ Employee deleted successfully!");
                } else {
                    System.out.println("\n  ✘ Failed to delete employee.");
                }
            } else {
                System.out.println("  [i] Delete operation cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Invalid numeric input: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  SEARCH EMPLOYEE
    // ─────────────────────────────────────────────
    public void searchEmployee() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       SEARCH EMPLOYEE         ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("  1. Search by ID");
        System.out.println("  2. Search by Name");
        System.out.print("  Choose Option : ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                searchById();
                break;
            case "2":
                searchByName();
                break;
            default:
                System.out.println("  [!] Invalid option.");
        }
    }

    private void searchById() {
        try {
            System.out.print("  Enter Employee ID : ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Employee emp = dao.searchEmployeeById(id);
            if (emp != null) {
                printTableHeader();
                System.out.println(emp.toString());
                printTableFooter();
            } else {
                System.out.println("  [!] No employee found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Invalid ID: " + e.getMessage());
        }
    }

    private void searchByName() {
        System.out.print("  Enter Name (or partial name) : ");
        String name = scanner.nextLine().trim();
        List<Employee> list = dao.searchEmployeeByName(name);
        if (!list.isEmpty()) {
            printTableHeader();
            list.forEach(e -> System.out.println(e.toString()));
            printTableFooter();
            System.out.println("  Total records found: " + list.size());
        } else {
            System.out.println("  [!] No employee found with name: " + name);
        }
    }

    // ─────────────────────────────────────────────
    //  VIEW ALL EMPLOYEES
    // ─────────────────────────────────────────────
    public void viewAllEmployees() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║       ALL EMPLOYEE RECORDS        ║");
        System.out.println("╚══════════════════════════════════╝");
        List<Employee> list = dao.getAllEmployees();
        if (!list.isEmpty()) {
            printTableHeader();
            list.forEach(e -> System.out.println(e.toString()));
            printTableFooter();
            System.out.println("  Total Records: " + list.size());
        } else {
            System.out.println("  [i] No employee records found in database.");
        }
    }

    // ─────────────────────────────────────────────
    //  TABLE DISPLAY HELPERS
    // ─────────────────────────────────────────────
    private void printTableHeader() {
        System.out.println();
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(22) + "+" + "-".repeat(20) + "+"
                + "-".repeat(14) + "+" + "-".repeat(12) + "+" + "-".repeat(27) + "+"
                + "-".repeat(15) + "+" + "-".repeat(14) + "+");
        System.out.printf("| %-6s | %-20s | %-18s | %-12s | %-10s | %-25s | %-13s | %-12s |%n",
                "EMP_ID", "NAME", "DESIGNATION", "DEPARTMENT", "SALARY", "EMAIL", "PHONE", "JOINING DATE");
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(22) + "+" + "-".repeat(20) + "+"
                + "-".repeat(14) + "+" + "-".repeat(12) + "+" + "-".repeat(27) + "+"
                + "-".repeat(15) + "+" + "-".repeat(14) + "+");
    }

    private void printTableFooter() {
        System.out.println("+" + "-".repeat(8) + "+" + "-".repeat(22) + "+" + "-".repeat(20) + "+"
                + "-".repeat(14) + "+" + "-".repeat(12) + "+" + "-".repeat(27) + "+"
                + "-".repeat(15) + "+" + "-".repeat(14) + "+");
    }
}
