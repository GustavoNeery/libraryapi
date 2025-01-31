create table actor(
	id uuid not null primary key,
	name varchar(100) not null,
	date_born date not null,
	nationality varchar(50) not null
);