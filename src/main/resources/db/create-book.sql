create table book(
	id uuid not null primary key,
	isbn varchar(20) not null,
	title varchar(150) not null,
	publication_date date not null,
	gender varchar(30) not null,
	price numeric(18, 2),
	id_actor uuid not null references actor(id),
	constraint chk_gender check (gender in ('FICCATION', 'FANTASY', 'MISTERY', 'ROMANCE', 'BIOGRAFY', 'SCIENCE'))
);