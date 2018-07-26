


create table customer(
mobileNo varchar2(10),
name varchar2(12),
balance number(10,2),
primary key(mobileNo)
);
create table transactions(
mobileNo varchar2(10) ,
trasactionType varchar2(30),
balance number(10,2),
transactionDate varchar2(30),
transactionStatus varchar2(30),
foreign key(mobileNo) references customer(mobileNo)
);