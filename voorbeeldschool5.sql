drop database if exists school;
create database school;
use school;

create table student (
	ovnummer varchar(5),
	roepnaam varchar(10),
	voorletters varchar(10),
	tussenvoegsels varchar(8),
	achternaam varchar(25),
	adres varchar(25),
	postcode varchar(7),
	woonplaats varchar(25),
	uitgeschreven date,
	foto blob,
	primary key(ovnummer)
) engine = innodb;


insert into student (ovnummer, roepnaam, voorletters, tussenvoegsels, achternaam, adres, postcode, woonplaats)
	values('1111', 'Piet', 'P.', '', 'Pietersen', 'Pietstraat 2', '1111 PP', 'Almere');
insert into student (ovnummer, roepnaam, voorletters, tussenvoegsels, achternaam, adres, postcode, woonplaats)
	values('2222', 'Jan', 'J.', '', 'Jansen', 'Janstraat 34', '2222 JJ', 'Almere');
insert into student (ovnummer, roepnaam, voorletters, tussenvoegsels, achternaam, adres, postcode, woonplaats)
	values('3333', 'Willem', 'W.', '', 'Willemsen', 'Willemstraat 45', '3333 WW', 'Almere');

create table groep (
	groepscode varchar(7),
	groepsomschrijving varchar(50),
	primary key(groepscode)
) engine = innodb;

insert into groep (groepscode, groepsomschrijving)
	values('XI4O3F', 'Applicatieontwikkelaars');
insert into groep (groepscode, groepsomschrijving)
	values('XI4O3FB', 'Applicatieontwikkelaars herkansers');

create table studentgroep (
	ovnummer varchar(5),
	groepscode varchar(7),
	primary key(ovnummer, groepscode),
	foreign key(ovnummer) references student(ovnummer),
	foreign key(groepscode) references groep(groepscode)
) engine = innodb;

insert into studentgroep (ovnummer, groepscode)
	values('1111', 'XI4O3F');
insert into studentgroep (ovnummer, groepscode)
	values('3333', 'XI4O3FB');