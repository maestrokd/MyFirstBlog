
-- auto-generated definition
create sequence hibernate_sequence;

alter sequence hibernate_sequence
  owner to "user";


create table if not exists roles
(
	id serial not null
		constraint roles_pkey
			primary key,
	name varchar(50)
)
;

alter table roles owner to "user"
;

create table if not exists users
(
	id serial not null
		constraint users_pkey
			primary key,
	email varchar(255),
	enabled boolean not null,
	login varchar(255),
	name varchar(255),
	password varchar(255)
)
;

alter table users owner to "user"
;

create table if not exists posts
(
	id serial not null
		constraint posts_pkey
			primary key,
	body text,
	date timestamp,
	title varchar(255),
	user_id integer
		constraint fk_users_posts
			references users
)
;

alter table posts owner to "user"
;

create table if not exists users_roles
(
	user_id integer not null
		constraint fk2o0jvgh89lemvvo17cbqvdxaa
			references users,
	role_id integer not null
		constraint fkj6m8fwv7oqv74fcehir1a9ffy
			references roles,
	constraint users_roles_pkey
		primary key (user_id, role_id)
)
;

alter table users_roles owner to "user"
;

create table if not exists verification_token
(
	id bigint not null
		constraint verification_token_pkey
			primary key,
	expiry_date timestamp,
	token varchar(255),
	user_id integer not null
		constraint fk3asw9wnv76uxu3kr1ekq4i1ld
			references users
)
;

alter table verification_token owner to "user"
;

