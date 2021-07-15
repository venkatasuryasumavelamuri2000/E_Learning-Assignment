use exampledb;

create table college (College_Name varchar(30), 
College_Code varchar(30) NOT NULL PRIMARY KEY, College_Pwd VARCHAR(30));
select * from college;

create table course (College_Code varchar(30), 
Course_Name VARCHAR(30), Course_Id int NOT NULL PRIMARY KEY, 
FOREIGN KEY (College_Code) REFERENCES college(College_Code));
select * from course;

create table user (College_Code varchar(30), 
User_Name VARCHAR(30), User_Roll_No int NOT NULL PRIMARY KEY, User_Pwd varchar(30), 
FOREIGN KEY (College_Code) REFERENCES college(College_Code));
select * from user;