-- We don't need to create schema SA, because we login as SA
-- CREATE SCHEMA SA;

CREATE TABLE company (
  companyId INT NOT NULL,
  companyName VARCHAR(30) NOT NULL,
  employeeSize INT NOT NULL,
  phone VARCHAR(30) NOT NULL,
  PRIMARY KEY (companyId));

CREATE TABLE customer (
  customerId INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 5000, INCREMENT BY 1),
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  phone VARCHAR(30) NOT NULL,
  customerDate TIMESTAMP NOT NULL,
  companyId INT,
  PRIMARY KEY (customerId),
  FOREIGN KEY (companyId) REFERENCES company);