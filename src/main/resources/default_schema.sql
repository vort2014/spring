-- We don't need to create schema SA, because we login as SA
-- CREATE SCHEMA SA;

CREATE TABLE COMPANY (
  companyId NUMBER,
  companyName VARCHAR2(30) NOT NULL,
  employeeSize NUMBER NOT NULL,
  phone VARCHAR2(30) NOT NULL,
  PRIMARY KEY (companyId));

CREATE TABLE customer (
  customerId NUMBER,
  firstName VARCHAR2(30) NOT NULL,
  lastName VARCHAR2(30) NOT NULL,
  email VARCHAR2(30) NOT NULL,
  phone VARCHAR2(30) NOT NULL,
  customerDate TIMESTAMP NOT NULL,
  companyId NUMBER,
  PRIMARY KEY (customerId),
  FOREIGN KEY (companyId) REFERENCES COMPANY (companyId));