CREATE TABLE user_table (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(100),
  phone VARCHAR(20),
  dob DATE,
  role VARCHAR(50),
  username VARCHAR(50),
  password VARCHAR(50)
);

INSERT INTO user_table (first_name, last_name, email, phone, dob, role, username, password)
VALUES
  (1, 'John', 'Doe', 'johndoe@example.com', '1234567890', '1990-01-01', 'user', 'johndoe', 'password'),
  (2, 'Jane', 'Smith', 'janesmith@example.com', '9876543210', '1995-05-10', 'admin', 'janesmith', 'password'),
  (3, 'Mike', 'Johnson', 'mikejohnson@example.com', '5555555555', '1985-09-15', 'user', 'mikejohnson', 'password'),
  (4, 'Sarah', 'Williams', 'sarahwilliams@example.com', '4444444444', '1992-07-20', 'user', 'sarahwilliams', 'password'),
  (5, 'David', 'Brown', 'davidbrown@example.com', '1111111111', '1988-03-25', 'admin', 'davidbrown', 'password'),
  (6, 'Emily', 'Davis', 'emilydavis@example.com', '2222222222', '1994-11-30', 'user', 'emilydavis', 'password'),
  (7, 'Daniel', 'Wilson', 'danielwilson@example.com', '6666666666', '1987-06-05', 'user', 'danielwilson', 'password'),
  (8, 'Olivia', 'Taylor', 'oliviataylor@example.com', '7777777777', '1991-09-12', 'admin', 'oliviataylor', 'password'),
  (9, 'Michael', 'Anderson', 'michaelanderson@example.com', '8888888888', '1993-04-15', 'user', 'michaelanderson', 'password'),
  (10, 'Sophia', 'Thomas', 'sophiathomas@example.com', '9999999999', '1998-02-18', 'user', 'sophiathomas', 'password');

