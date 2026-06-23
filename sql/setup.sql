-- ============================================================
--  EMPLOYEE MANAGEMENT SYSTEM | Oracle SQL Setup Script
--  Date        : Jun 2026
--  Description : Creates the EMPLOYEES table and inserts
--                sample records for testing.
-- ============================================================

-- Step 1: Drop existing table (if any)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE employees CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Step 2: Create EMPLOYEES table
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

-- Step 3: Insert Sample Records
INSERT INTO employees VALUES (1001, 'Arjun Sharma',   'Software Engineer',    'IT',        72000.00, 'arjun.sharma@ems.com',   '9876543210', TO_DATE('2023-06-15', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1002, 'Priya Mehta',    'Business Analyst',     'Finance',   65000.00, 'priya.mehta@ems.com',    '9823456789', TO_DATE('2022-11-01', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1003, 'Rahul Verma',    'Senior Developer',     'IT',        95000.00, 'rahul.verma@ems.com',    '9812345678', TO_DATE('2021-03-20', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1004, 'Sneha Gupta',    'HR Manager',           'HR',        80000.00, 'sneha.gupta@ems.com',    '9901234567', TO_DATE('2020-08-10', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1005, 'Vikram Singh',   'Database Admin',       'IT',        88000.00, 'vikram.singh@ems.com',   '9845678901', TO_DATE('2022-01-05', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1006, 'Anjali Patel',   'UI/UX Designer',       'Design',    60000.00, 'anjali.patel@ems.com',   '9934567890', TO_DATE('2023-09-12', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1007, 'Rohit Nair',     'Project Manager',      'Management',105000.00,'rohit.nair@ems.com',     '9867890123', TO_DATE('2019-04-18', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1008, 'Kavya Reddy',    'QA Engineer',          'Testing',   58000.00, 'kavya.reddy@ems.com',    '9778901234', TO_DATE('2024-02-28', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1009, 'Aditya Kumar',   'DevOps Engineer',      'IT',        92000.00, 'aditya.kumar@ems.com',   '9789012345', TO_DATE('2021-07-07', 'YYYY-MM-DD'));
INSERT INTO employees VALUES (1010, 'Meena Joshi',    'Finance Analyst',      'Finance',   70000.00, 'meena.joshi@ems.com',    '9890123456', TO_DATE('2022-05-22', 'YYYY-MM-DD'));

COMMIT;

-- Step 4: Verify Records
SELECT * FROM employees ORDER BY emp_id;

-- ============================================================
--  USEFUL QUERIES FOR REFERENCE
-- ============================================================

-- Count employees by department
-- SELECT department, COUNT(*) AS total FROM employees GROUP BY department ORDER BY total DESC;

-- Employees with salary above 80000
-- SELECT emp_name, designation, salary FROM employees WHERE salary > 80000 ORDER BY salary DESC;

-- Search employee by name (partial match)
-- SELECT * FROM employees WHERE UPPER(emp_name) LIKE UPPER('%arjun%');
