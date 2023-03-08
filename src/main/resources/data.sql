create schema testtaskdb;

create table if not exists person(
    id int auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    date_of_birth DATE
);

insert into person (first_name, last_name, date_of_birth) VALUES ('Danylo', 'Kamyshanov', '2001-04-09');
insert into person (first_name, last_name, date_of_birth) VALUES ('Anastasiia', 'Burlai', '1994-09-18');
insert into person (first_name, last_name, date_of_birth) VALUES ('Hlib', 'Anisimov', '2000-11-22');
insert into person (first_name, last_name, date_of_birth) VALUES ('Alex', 'Perov', '2001-07-27');


