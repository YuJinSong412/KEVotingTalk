/*테이블 생성*/

CREATE TABLE member_table (
 uname       VARCHAR(30),
 uemail     VARCHAR(30) not null,
 password    VARCHAR(30), 
  PRIMARY KEY(uemail)
) ENGINE=MYISAM CHARSET=utf8;

show tables;

drop table member_table;

drop table friendList;

INSERT INTO MEMBER_TABLE VALUES('찐찐','a','a');
insert into MEMBER_TABLE values('확찐자','b','b');
insert into MEMBER_TABLE values('노대장','c','c');
insert into MEMBER_TABLE values('정대리여신','d','d');

select * from MEMBER_TABLE;

select uname from member_table where uemail='a' and password='a'


/*테이블 생성*/
create table friendList(
 id int not null AUTO_INCREMENT,
 userEmail varchar(30),
 friendEmail varchar(30),
   PRIMARY KEY(id),
   foreign key (userEmail)
   references member_table (uemail),
   foreign key (friendEmail)
   references member_table (uemail)
) ENGINE=MYISAM CHARSET=utf8;

insert into friendList values(1,'a','b');
insert into friendList values(2,'a','c');
insert into friendList values(3,'a','d');

insert into friendList values(4,'b','a');
insert into friendList values(5,'b','c');
insert into friendList values(6,'b','d');

insert into friendList values(7,'c','b');
insert into friendList values(8,'c','a');
insert into friendList values(9,'c','d');

insert into friendList values(10,'d','b');
insert into friendList values(11,'d','c');
insert into friendList values(12,'d','a');

select * from friendList;