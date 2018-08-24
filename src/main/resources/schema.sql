CREATE TABLE Employee (
   id INT PRIMARY KEY,
   first_name VARCHAR(50) NOT NULL,
   middle_initial VARCHAR(20) NOT NULL,
   last_name VARCHAR(20) NOT NULL,
   date_of_birth VARCHAR(20),
   date_of_employment VARCHAR(20),
   status VARCHAR(8) NOT NULL DEFAULT 'ACTIVE'
) As
    SELECT id, first_name, middle_initial, last_name, date_of_birth, date_of_employment, 'ACTIVE'
    FROM CSVREAD('classpath:employee_data.csv');