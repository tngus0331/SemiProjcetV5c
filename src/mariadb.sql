-- member
create table member(
	mno int primary key auto_increment,
	name varchar(15) not null,
	jumin varchar(18) not null,

	userid varchar(18) not null,
	passwd varchar(18) not null,

	zipcode varchar(7) not null,
	addr1 varchar(50) not null,
	addr2 varchar(50) not null,
	email varchar(50) not null,
	mobile varchar(13) not null,
	regdate datetime default current_timestamp
);

-- board
create table board (
	bno int primary key auto_increment,
	title varchar(50) not null,
	userid varchar(18) not null,
	regdate datetime default current_timestamp,
	thumbup int default 0,
	views int default 0,
	contents mediumtext not null
);

-- pds
create table pds (
	pno int primary key auto_increment,
	title varchar(50) not null,
	userid varchar(18) not null,
	regdate datetime default current_timestamp,
	thumbup int default 0,
	views int default 0,
	contents mediumtext not null,
	fname varchar(50) not null,
	fsize int default 0,
	fdown int default 0,
	ftype varchar(10)
);

-- gallery
create table gallery (
	gno int primary key auto_increment,
	title varchar(50) not null,
	userid varchar(18) not null,
	regdate datetime default current_timestamp,
	thumbup int default 0,
	views int default 0,
	contents mediumtext not null,
	fname1 varchar(50),
	fname2 varchar(50),
	fname3 varchar(50)

);
