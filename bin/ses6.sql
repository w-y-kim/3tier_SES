
CREATE TABLE HUMAN (
	NAME VARCHAR(20) NOT NULL,
	AGE NUMBER(3) NOT NULL, 
	JUMIN VARCHAR(20) PRIMARY KEY, 
	TYPE VARCHAR(20) NOT NULL);

	CREATE TABLE PROFESSOR( 
	JUMIN VARCHAR(20) NOT NULL,constraint PROFESSOR_JUMIN_FK foreign key(JUMIN) references HUMAN(JUMIN) on delete cascade, 
	MAJOR VARCHAR(30) NOT NULL 
	);

CREATE TABLE TRAINEE (
	JUMIN VARCHAR(20) NOT NULL, CONSTRAINT TRAINEE_FK FOREIGN KEY (JUMIN) REFERENCES HUMAN (JUMIN) ON DELETE CASCADE, 
	STDNO VARCHAR(20) NOT NULL
	);

CREATE TABLE STAFF (
	JUMIN VARCHAR(20) NOT NULL, CONSTRAINT STAFF_FK FOREIGN KEY (JUMIN) REFERENCES HUMAN (JUMIN)ON DELETE CASCADE,  
	FIELD VARCHAR(20) NOT NULL
	);

	
commit


drop table human;
drop table professor;
drop table trainee;
drop table staff;