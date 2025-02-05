create table author(
	id uuid not null primary key,
	name varchar(100) not null,
	date_born date not null,
	nationality varchar(50) not null.
	date_registration timestamp,
	date_update timestamp,
	user_id uuid not null
);