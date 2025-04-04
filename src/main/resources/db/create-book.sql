create table book(
	id uuid not null primary key,
	isbn varchar(20) not null unique,
	title varchar(150) not null,
	publication_date date not null,
	gender varchar(30) not null,
	price numeric(18, 2),
    date_registration timestamp,
    date_update timestamp,
    user_id uuid not null,
	id_author uuid not null references author(id),
	constraint chk_gender check (gender in ('FICCATION', 'FANTASY', 'MISTERY', 'ROMANCE', 'BIOGRAFY', 'SCIENCE'))
);