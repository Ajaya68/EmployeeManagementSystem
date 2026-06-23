# 🏢 Employee Management System | Jun 2026
**Technologies:** Core Java • JDBC • Oracle SQL  
**Type:** Academic Project — Console-Based Application

---

## 📁 Project Structure

```
EmployeeManagementSystem/
│
├── src/
│   └── com/
│       └── ems/
│           ├── model/
│           │   └── Employee.java          ← Entity class (OOP - Encapsulation)
│           ├── dao/
│           │   ├── EmployeeDAO.java       ← Interface (Abstraction)
│           │   └── EmployeeDAOImpl.java   ← CRUD Implementation (JDBC)
│           ├── util/
│           │   └── DBConnection.java      ← Oracle DB Connection Utility
│           └── main/
│               ├── EmployeeService.java   ← Business Logic / User Input
│               └── MainApp.java          ← Entry Point (Main Method)
│
├── sql/
│   └── setup.sql                         ← Oracle SQL Table + Sample Data
│
└── README.md
```

---

## ⚙️ Features

| Feature             | Description                                    |
|---------------------|------------------------------------------------|
| ➕ Add Employee      | Insert a new employee record into Oracle DB    |
| ✏️ Update Employee   | Modify existing employee details               |
| 🗑️ Delete Employee   | Remove an employee with confirmation prompt    |
| 🔍 Search by ID      | Find employee using their unique ID            |
| 🔍 Search by Name    | Find employees using partial name match        |
| 📋 View All          | Display all employees in a formatted table     |

---

## 🛠️ Technologies Used

- **Core Java** – OOP, Exception Handling, Scanner, Collections
- **JDBC** – PreparedStatement, ResultSet, Connection
- **Oracle SQL** – DDL, DML, Constraints, Date Functions

---

## 🗄️ Database Table Schema

```sql
CREATE TABLE employees (
    emp_id       NUMBER(6)      PRIMARY KEY,
    emp_name     VARCHAR2(100)  NOT NULL,
    designation  VARCHAR2(100)  NOT NULL,
    department   VARCHAR2(50)   NOT NULL,
    salary       NUMBER(10, 2)  NOT NULL,
    email        VARCHAR2(100)  UNIQUE NOT NULL,
    phone        VARCHAR2(15),
    joining_date DATE           NOT NULL
);
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or above
- Oracle Database (XE or any edition)
- Oracle JDBC Driver (`ojdbc8.jar` or `ojdbc11.jar`)

### Steps

**1. Set up the Database**
```sql
-- Run the SQL script in Oracle SQL Developer or SQL*Plus:
@sql/setup.sql
```

**2. Configure DB Connection**  
Edit `DBConnection.java`:
```java
private static final String DB_URL      = "jdbc:oracle:thin:@localhost:1521:XE";
private static final String DB_USERNAME = "your_username";
private static final String DB_PASSWORD = "your_password";
```

**3. Compile the Project**
```bash
javac -cp .;ojdbc8.jar -d out src/com/ems/util/DBConnection.java
javac -cp .;ojdbc8.jar -d out src/com/ems/model/Employee.java
javac -cp .;ojdbc8.jar -d out src/com/ems/dao/EmployeeDAO.java
javac -cp .;ojdbc8.jar -d out src/com/ems/dao/EmployeeDAOImpl.java
javac -cp .;ojdbc8.jar -d out src/com/ems/main/EmployeeService.java
javac -cp .;ojdbc8.jar -d out src/com/ems/main/MainApp.java
```

**4. Run the Application**
```bash
java -cp out;ojdbc8.jar com.ems.main.MainApp
```

---

## 🧠 OOP Concepts Applied

| Concept        | Where Applied                              |
|----------------|--------------------------------------------|
| Encapsulation  | `Employee.java` – private fields + getters/setters |
| Abstraction    | `EmployeeDAO.java` – Interface contract    |
| Inheritance    | `EmployeeDAOImpl` implements `EmployeeDAO` |
| Modularity     | Separate layers: Model, DAO, Util, Main    |

---

## 📌 Author
**Ajaya [Java Developer]** | Academic Project  
Employee Management System | Source Code Jun 2026
