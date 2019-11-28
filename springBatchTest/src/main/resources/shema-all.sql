CREATE DATABASE batchdb
CREATE TABLE employee  (
    employeeID BIGINT IDENTITY NOT NULL PRIMARY KEY,
    employeeFirstName VARCHAR(20),
    employeeLastName VARCHAR(20),
    employeeAge INTEGER,
    employeeSalary INTEGER
);