CREATE TABLE department
( 
  dept_id SERIAL NOT NULL,
  dept_name varchar NOT NULL,
  UNIQUE(dept_name),
  CONSTRAINT department_pk PRIMARY KEY (dept_id)
);

CREATE TABLE employee
( 
  emp_id SERIAL NOT NULL,
  first_name varchar NOT NULL,
  last_name varchar NOT NULL,
  email_id varchar NOT NULL,
  address varchar NOT NULL,
  status varchar NOT NULL,
  updated_on DATE,
  dept_id integer,
  UNIQUE(email_id),
  CONSTRAINT employee_pk PRIMARY KEY (emp_id),
  CONSTRAINT fk_department
    FOREIGN KEY (dept_id)
    REFERENCES department(dept_id)
);

