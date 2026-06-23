package com.ems.main;

import com.ems.util.DBConnection;
import java.util.Scanner;

/**
 * ┌──────────────────────────────────────────────────────────────────┐
 * │        EMPLOYEE MANAGEMENT SYSTEM  |  Jun 2026                   │
 * │        Technologies : Core Java + JDBC + Oracle SQL              │
 * │        Author       : Java Developer (Academic Project)          │
 * └──────────────────────────────────────────────────────────────────┘
 *
 * MainApp – Entry Point of the Application
 * Provides the console-based menu and drives the application flow.
 */
public class MainApp {

    public static void main(String[] args) {

        Scanner scanner       = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        printBanner();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("  Enter your choice : ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    service.addEmployee();
                    break;
                case "2":
                    service.updateEmployee();
                    break;
                case "3":
                    service.deleteEmployee();
                    break;
                case "4":
                    service.searchEmployee();
                    break;
                case "5":
                    service.viewAllEmployees();
                    break;
                case "0":
                    System.out.println("\n  Thank you for using Employee Management System!");
                    System.out.println("  Closing database connection...");
                    DBConnection.closeConnection();
                    running = false;
                    break;
                default:
                    System.out.println("\n  [!] Invalid choice. Please enter a valid option (0-5).");
            }

            if (running) {
                System.out.print("\n  Press ENTER to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("\n  Goodbye! ✔");
    }

    // ─────────────────────────────────────────────
    //  Banner Display
    // ─────────────────────────────────────────────
    private static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════════════╗");
        System.out.println("  ║         EMPLOYEE MANAGEMENT SYSTEM  |  Jun 2026         ║");
        System.out.println("  ║         Technologies: Core Java | JDBC | Oracle SQL     ║");
        System.out.println("  ║         Type        : Academic Project                  ║");
        System.out.println("  ╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // ─────────────────────────────────────────────
    //  Menu Display
    // ─────────────────────────────────────────────
    private static void printMenu() {
        System.out.println();
        System.out.println("  ┌──────────────────────────────┐");
        System.out.println("  │         MAIN MENU            │");
        System.out.println("  ├──────────────────────────────┤");
        System.out.println("  │  1. Add    Employee           │");
        System.out.println("  │  2. Update Employee           │");
        System.out.println("  │  3. Delete Employee           │");
        System.out.println("  │  4. Search Employee           │");
        System.out.println("  │  5. View All Employees        │");
        System.out.println("  │  0. Exit                      │");
        System.out.println("  └──────────────────────────────┘");
    }
}
