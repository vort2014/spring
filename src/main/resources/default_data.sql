-- fill COMPANY table
INSERT INTO company(companyId,companyName,employeeSize,phone)VALUES(1,'Roga i Kopita',10,'555-340-1230');
INSERT INTO company(companyId,companyName,employeeSize,phone)VALUES(2,'Apple',20,'555-123-4567');

-- Date format is java.sql.Timestamp.valueOf(pattern)
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('John','Smith', 'John.Smith@comcast.net', '555-340-1230', '1962-09-23 03:23:34.234',1);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Mary','Johnson', 'mary.johnson@comcast.net', '555-123-4567', '1962-09-23 03:23:34.234',1);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Bob','Collins', 'bob.collins@yahoo.com', '555-012-3456', '1962-09-23 03:23:34.234',1);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Rebecca','Mayer', 'rebecca.mayer@gmail.com', '555-205-8212', '1962-09-23 03:23:34.234',1);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Anthony','Clark', 'anthony.clark@gmail.com', '555-256-1901', '1962-09-23 03:23:34.234',2);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Judy','Sousa', 'judy.sousa@verizon.net', '555-751-1207', '1962-09-23 03:23:34.234',2);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Christopher','Patriquin', 'patriquinc@yahoo.com', '555-316-1803', '1962-09-23 03:23:34.234',2);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Deborah','Smith', 'debsmith@comcast.net', '555-256-3421', '1962-09-23 03:23:34.234',2);
INSERT INTO customer(FirstName,LastName,Email,Phone,CustomerDate,companyId)VALUES('Jennifer','McGinn', 'jmcginn@comcast.net', '555-250-0918', '1962-09-23 03:23:34.234',2);