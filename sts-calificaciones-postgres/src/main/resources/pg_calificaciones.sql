CREATE DATABASE escuela;

CREATE TABLE t_alumnos
(
	id_t_usuarios SERIAL not null,
	nombre varchar(80),
	ap_paterno varchar(80),
	ap_materno varchar(80),
	activo int,
	primary key (id_t_usuarios)
);

insert into t_alumnos (nombre,ap_paterno,ap_materno,activo) 
VALUES ('JUAN','LOPEZ','CERDA',1),
('SOFIA','BELTRAN','RAMOS',1),
('CARLOS','ZARATE','RUEDA',1);

select * from t_alumnos;

create table t_materias(
	id_t_materias SERIAL not null,
	nombre varchar(80),
	activo bool,
	primary key (id_t_materias)
);

insert into t_materias (nombre,activo) 
values('MATEMATICA',true),
	  ('PROGRAMACION I',true),
	  ('INGENIERIA DE SOFTWARE',true);
	  
select * from t_materias;	

CREATE TABLE t_calificaciones
(
	id_t_calificaciones SERIAL not null,
	id_t_materias int not null,
	id_t_usuarios int not null,
	calificacion decimal(4,2),
	fecha_registro date,
	primary key(id_t_calificaciones),
	foreign key (id_t_materias) references t_materias(id_t_materias),
	foreign key (id_t_usuarios) references t_alumnos(id_t_usuarios)
);

insert into t_calificaciones
(id_t_materias,id_t_usuarios,calificacion,fecha_registro)
values(1,1,8,now());


insert into t_calificaciones
(id_t_materias,id_t_usuarios,calificacion,fecha_registro)
values(1,2,9,now());

insert into t_calificaciones
(id_t_materias,id_t_usuarios,calificacion,fecha_registro)
values(1,3,10,now());

select * from t_calificaciones;

select * from t_materias;