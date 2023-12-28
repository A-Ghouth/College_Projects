CREATE DATABASE FinalProject ;
Use FinalProject;
											
                                            /*TABLES*/
CREATE TABLE Employee(
EID INT NOT NULL,
Ename VARCHAR(50) NOT NULL,
Efamily VARCHAR(50) NOT NULL,
JobTitel VARCHAR(30) NOT NULL,
LastDate DATE ,
StartDate DATE NOT NULL,
Dob DATE,
Phone CHAR(10) NOT NULL ,
Email VARCHAR(100),
Address VARCHAR(100), 
PRIMARY KEY (EID)
);

CREATE TABLE Manger(
	EID INT PRIMARY KEY  NOT NULL,
    FOREIGN KEY (EID) REFERENCES Employee (EID)
    );
CREATE TABLE Worker(
	EID INT PRIMARY KEY NOT NULL,
    FOREIGN KEY (EID) REFERENCES Employee(EID),
    BrancheID INT NOT NULL,
    FOREIGN KEY (BrancheID) REFERENCES Branche(BrancheID)
    );
    
CREATE TABLE Branche(
BrancheID INT NOT NULL,
BrancheName VARCHAR(40),
EID INT,
PRIMARY KEY (BrancheID),
FOREIGN KEY (EID) REFERENCES Manger(EID));
CREATE TABLE college(
BrancheID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
College VARCHAR(40),
foreign key (BrancheID) references Branche(BrancheID)
);

CREATE TABLE PUBLISHER(
	PubID INT PRIMARY KEY AUTO_INCREMENT,
    PubNAME VARCHAR(60) NOT NULL, 
    ADDRESS VARCHAR(100) NOT NULL
);

CREATE TABLE BOOK(
	ISBN VARCHAR(30) PRIMARY KEY NOT NUll ,
    BOOK_NAME VARCHAR(100) NOT NULL, 
    NUMBER_OF_COPYIES INT NOT NULL,
    AUTHOR_NAME VARCHAR(40) NOT NULL,
    PUBLISHER_ID INT NOT NULL,
    FOREIGN KEY (PUBLISHER_ID) REFERENCES PUBLISHER(PubID)
);

CREATE TABLE borrow_record(
borrow_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
duo_date DATE ,
borrow_date DATE ,
EID INT ,
foreign key (EID) references Worker(EID)
);

CREATE TABLE STUDENT(
	S_ID INT PRIMARY KEY auto_increment,
    FIRST_NAME VARCHAR(40) NOT NULL,
    LAST_NAME VARCHAR(40) NOT NULL,
    S_DOB DATE NOT NULL,
    S_PHONE CHAR(10) NOT NULL,
    S_ADDRESS VARCHAR(40),
    DEPARTMENT varchar(40),
    S_COLLEGE VARCHAR(40)
);

CREATE TABLE Contain(
	ISBN VARCHAR(30) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN),
    BrancheID INT NOT NULL,
    PRIMARY KEY(ISBN , BrancheID),
    FOREIGN KEY (BrancheID) REFERENCES Branche (BrancheID)
);
CREATE TABLE Record(
	ISBN VARCHAR(30) NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN),
    borrow_id INT NOT NULL,
    PRIMARY KEY(ISBN , borrow_id),
    FOREIGN KEY (borrow_id) REFERENCES borrow_record (borrow_id)
);
CREATE TABLE Borrow(
	S_ID INT NOT NULL,
    FOREIGN KEY (S_ID) REFERENCES STUDENT(S_ID),
    PRIMARY KEY(S_ID , borrow_id),
    borrow_id INT NOT NULL,
    FOREIGN KEY (borrow_id) REFERENCES borrow_record (borrow_id)
);
										
                                        /*DATA*/
INSERT INTO `employee` VALUES (39100,'saad','medni','manger','2025-12-30','2018-11-08','1980-12-16','5043643478','gfhfttrsw@gmail.com','dammam'),(39101,'aziz','arkobi','manger',NULL,'2020-05-09','1978-12-11','5344344578','gjfse52@gmail.com','riyad'),(39102,'hamza','Ghouth','manger','2022-03-01','2019-10-15','1994-08-14','5342104556','r685wqtf@gmail.com','makkah'),(39103,'hasan','mechant','manger','2023-01-05','2022-05-11','1998-03-03','5404378537','iyteiktet@gmail.com','arrar'),(39104,'hatim','rehely','manger','2030-06-15','2015-12-30','1993-02-02','5375343543','iet7it7@gmail.com','qatif'),(39105,'ahmed','musl','manger','2021-06-22','2012-02-20','1987-09-09','5043877377','ei7tiets5@gmail.com','yanbu'),(39106,'ali','SHAIKH','worker',NULL,'2020-03-03','1986-02-01','5024453545','56ie5gd@gmail.com','makkah'),(39107,'aymen','Sehmi','worker',NULL,'2020-04-27','1987-07-01','5045364387','rw6uw@gmail.com','madinah'),(39108,'hani','khan','worker',NULL,'2017-05-13','1986-02-22','5045348764','u5eue5@gmail.com','madinah'),(39109,'omar','turki','worker','2030-02-04','2019-11-06','1979-03-04','5045367534','w56u56u@gmail.com','Hafar Al-Batin'),(39110,'Bader','Ghouth','worker',NULL,'2015-01-07','1975-06-05','5044200949','DBAYUD@gmail.com','jeddah'),(39111,'kalel','ahmed','worker','2024-01-01','2012-01-01','1985-02-06','5333245105','wdefrg@gmail.com','madinah');
INSERT INTO `college` VALUES (35,'Computer '),(36,'Computer '),(37,'Computer '),(38,'Bussiness'),(39,'Bussiness'),(40,'engneering');
INSERT INTO `student` VALUES (75100,'OMAR','ALTURKI','2000-01-27','5041234567','HAMRA','CS','COMPUTER'),(75101,'KHALED','AHMED','2001-02-07','5041234582','QUDS','EE','ENGINEERING'),(75102,'SAAD','TURKI','2002-11-12','5041234597','SHIFA','ME','ENGINEERING'),(75103,'JAMAL','KHALIL','2001-01-01','5041234544','NASEEM','IT','COMPUTER'),(75104,'SULTAN','FAHAD','2001-11-09','5041234505','MALQA','HR','BISSINESS'),(75105,'TALAL','YASSER','2001-01-09','5041234510','HAMRA','CE','ENGINEERING'),(75106,'JAAFAR','KHURISHI','2001-11-27','5041234511','JARIR','CS','COMPUTER'),(75107,'AHMED','GHOUTH','2000-02-07','5041234569','YASMEEN','CS','COMPUTER'),(75108,'ABDUL','KHALAF','2001-08-09','5041234420','KHALIL','IS','COMPUTER'),(75109,'JASIM','KHAMES','2001-03-19','5041234590','QUBA','MK','BISSINESS'),(75110,'NAWAF','LAFI','2001-10-22','5041234570','HAMRA','EE','ENGINEERING'),(75111,'QASIM','LUTFI','2001-04-15','5041234599','NASEEM','IS','COMPUTER'),(75112,'MAJED','FALAH','2002-01-01','5041234588','QUDS','IT','COMPUTER'),(75113,'OTHMAN','ALTURKI','2001-04-03','5041234544','MALAZ','CS','COMPUTER'),(75114,'OMAR','ghouth','2009-05-03','5056616119','quba','CS','COMPUTER');
INSERT INTO `borrow_record` VALUES (1,'2020-01-27','2020-01-27',39106),(2,'2021-02-07','2021-02-07',39107),(3,'2020-11-12','2020-11-12',39108),(4,'2020-01-01','2020-01-01',39109),(5,'2021-11-09','2021-11-09',39110),(6,'2020-01-09','2020-01-09',39111),(7,'2022-11-27','2022-11-27',39106),(8,'2021-02-07','2021-02-07',39107),(9,'2020-08-09','2020-08-09',39108),(10,'2022-03-19','2022-03-19',39109),(11,'2020-10-22','2020-10-22',39110),(12,'2021-04-15','2021-04-15',39111),(13,'2020-01-01','2020-01-01',39106),(14,'2020-04-03','2020-04-03',39107),(15,'2022-05-03','2022-05-03',39108),(16,'2022-01-27','2022-01-27',39109),(17,'2021-02-07','2021-02-07',39110),(18,'2021-11-12','2021-11-12',39111),(19,'2020-01-01','2020-01-01',39106),(20,'2022-11-09','2022-11-09',39107);
INSERT INTO `publisher` VALUES (1,'Pearson','North America Hudson '),(2,'Michel Jorden','United States Pennsylvania '),(3,'Hamza Hasn','Boston MA'),(4,'Hasan hafiz','New York City'),(5,'CareerCup','New York City'),(6,'Addison-Wesley Professional','Los Angloes'),(7,'Microsoft Press','Untied Kindom'),(8,'The MIT Press','United KingdomDuchess Marylebone'),(9,'No Starch Press','San Francisco'),(10,'O Reilly Media','Sebastopo California');
INSERT INTO `manger` VALUES (39100),(39101),(39102),(39103),(39104),(39105);
INSERT INTO `contain` VALUES ('978-0131103627',35),('978-0136073734',35),('978-0201616224',35),('978-0262035613',35),('978-0735619678',35),('978-1449373320',35),('978-0073383095',36),('978-0134670942',36),('978-0073376226',37),('978-0201558029',37),('978-0201835953',38),('978-0262640688',38),('978-0321573513',38),('978-1593275990',38),('978-0078035623',39),('978-0134092669',39),('978-0984782857',39),('978-1627790369',39),('978-0073523323',40),('978-1285741550',40);
INSERT INTO `book` VALUES ('978-0073376226','Data Communications and Networking',4,'Ahmed Ghouth',2),('978-0073383095','Discrete Mathematics and Its Applications ',1,'Kenneth H Rosen',2),('978-0073523323','Database System Concepts',2,'Abraham Silberschatz',2),('978-0078035623','College Algebra and Trigonometry',3,'Julie Miller',2),('978-0131103627','C Programming Language',3,'Brian W Kernighan',1),('978-0134092669','Computer Systems ',2,'Randal Bryant',1),('978-0134670942','Introduction to Java Programming and Data Structures',3,'Y Daniel Liang',1),('978-0136073734','Computer Organization and Architecture Designing ',2,'Dr William Stallings',1),('978-0201558029','Concrete Mathematics A Foundation for Computer Science',5,'Ronald Graham',6),('978-0201616224','The Pragmatic Programmer',4,'Andrew Hunt',6),('978-0201835953','Mythical Man MonthThe Essays on Software Engineering',1,'Frederick Brooks Jr',6),('978-0262035613','Deep Learning',1,'Ian Goodfellow',8),('978-0262640688','The Elements of Computing Systems Building',2,'Noam Nisan',8),('978-0321573513','Algorithms',5,'Robert Sedgewick',6),('978-0735619678','Code Complete A Practical Handbook of Software',3,'Steve McConnell',7),('978-0984782857','Cracking the Coding Interview',1,'Gayle Laakmann McDowell',5),('978-1285741550','Calculus',4,'James Stewart',3),('978-1449373320','Designing Data Intensive Applications',5,'Martin Kleppmann',10),('978-1593275990','Automate the Boring Stuff with Python',1,' AI Sweigart',9),('978-1627790369','Algorithms to Live By The Computer Science of Human ',5,'Brian Christian',4);
INSERT INTO `borrow` VALUES (75103,1),(75102,2),(75103,3),(75100,4),(75101,5),(75109,6),(75111,7),(75110,8),(75103,9),(75114,10),(75105,11),(75110,12),(75103,13),(75114,14),(75105,15),(75104,16),(75113,17),(75112,18),(75106,19),(75112,20);
INSERT INTO `record` VALUES ('978-0134670942',1),('978-0136073734',2),('978-0131103627',3),('978-0262640688',4),('978-1627790369',5),('978-0073523323',6),('978-1285741550',7),('978-0073376226',8),('978-0262640688',9),('978-1285741550',10),('978-0073376226',11),('978-1285741550',12),('978-0131103627',13),('978-1593275990',14),('978-0321573513',15),('978-0321573513',16),('978-0262035613',17),('978-0131103627',18),('978-0201558029',19),('978-0262640688',20);
INSERT INTO `worker` VALUES (39106,35),(39107,36),(39108,37),(39109,38),(39110,39),(39111,40);
INSERT INTO `branche` VALUES (35,'cs',39100),(36,'HR',39101),(37,'ELCTRIC',39102),(38,'IT',39103),(39,'IS',39104),(40,'Markting',39105);

										
                                        /*Queris*/
/*Q1*/
SELECT student.S_ID, FIRST_NAME,LAST_NAME, book_name, book.ISBN 
from book, student,borrow_record, borrow ,record 
where student.S_ID = borrow.S_ID AND borrow.borrow_id = borrow_record.borrow_id AND borrow_record.borrow_id = record.borrow_id AND record.ISBN = book.ISBN AND student.S_ID = 75100;

/*Q2*/
select FIRST_NAME ,  LAST_NAME
from student 
where FIRST_NAME 
LIKE "%J___%" 
order by FIRST_NAME,LAST_NAME;

/*Q3*/
select college.College, sum(number_of_copyies) 
from book,contain,branche,college 
where book.ISBN = contain.ISBN AND contain.BrancheID = branche.BrancheID AND branche.BrancheID = college.BrancheID   
group by  college.College;

/*Q4*/
select book_name, book.isbn 
from book, contain, branche, college 
where book.isbn = contain.isbn and contain.BrancheID = branche.BrancheID and branche.BrancheID = college.BrancheID and Branche.branchename = 'CS';

/*Q5*/
select * 
from employee 
where address = 'madinah' and startdate < '2019-12-12';

/*Q6*/
select  count(record.isbn) 
from book, record 
where  record.ISBN = book.ISBN AND record.isbn = "978-0262640688" ;

/*Q7*/
select book_name, book.isbn,borrow_record.borrow_id,borrow_record.borrow_date 
from book, record, borrow_record 
where book.isbn = record.isbn and record.borrow_id = borrow_record.borrow_id and month(borrow_record.borrow_date) = month("2222-01-01");

/*Q8*/
select book_name, book.isbn,borrow_record.borrow_id, borrow_record.duo_date 
from book, record, borrow_record 
where book.isbn = record.isbn and record.borrow_id = borrow_record.borrow_id and borrow_record.duo_date <= '2020-12-12' ;

/*Q9*/
select book_name, count(record.isbn) 
from book, record 
where book.isbn = record.isbn 
group by book_name 
order by  count(record.isbn) desc  limit 5;








