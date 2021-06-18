CREATE DATABASE jsp_project;

CREATE TABLE medico (
  id_medico int(11) NOT NULL AUTO_INCREMENT,
  area text DEFAULT NULL,
  img_profile text DEFAULT NULL,
  nombre text DEFAULT NULL,
  apell_pat text DEFAULT NULL,
  apell_mat text DEFAULT NULL,
  direccion text DEFAULT NULL,
  email text DEFAULT NULL,
  tel text DEFAULT NULL,
  hora_inic text DEFAULT NULL,
  hora_fin text DEFAULT NULL,
  PRIMARY KEY (id_medico)
);

INSERT INTO medico (id_medico, area, img_profile, nombre, apell_pat, apell_mat, direccion, email, tel, hora_inic, hora_fin) VALUES
(1, 'cirujano', 'login.jpg', 'shaun', 'murphy', 'null', '915 WILSHIRE BLVD, LOS ANGELES', 'shaun.murphy@uaem.mx', '6895885627', '10:30', '22:00'),
(2, 'cirujano', NULL, 'Audrey ', 'Melendez', 'Lim', 'North Miami Florida United States', 'Audrey.Lim@gmail.com', '503(4548)467-84-81', '10:30 a.m', '12:00 a.m');



CREATE TABLE paciente (
  id_paciente int(11) NOT NULL AUTO_INCREMENT,
  name text DEFAULT NULL,
  doctor text DEFAULT NULL,
  fecha date DEFAULT NULL,
  hora text DEFAULT NULL,
  tel text DEFAULT NULL,
  correo text DEFAULT NULL,
  genero text DEFAULT NULL,
  motivo text DEFAULT NULL,
  sintomas text DEFAULT NULL,
  estatus text DEFAULT NULL,
  PRIMARY KEY (id_paciente)
);


INSERT INTO paciente (id_paciente, name, doctor, fecha, hora, tel, correo, genero, motivo, sintomas, estatus) VALUES
(1, 'Paciente', 'shaun', '2021-06-15', '9:00', '212234', 'luis.peralta1@gmail.com', 'masculino', 'cita medica', 'dolor de garganta', 'cancelado'),
(2, 'Paciente', 'shaun', '2021-06-16', '10:00', '12345678', 'luis.peralta2@gmail.com', 'masculino', 'cita medica', 'tengo gripa', 'activo'),
(3, 'Paciente2', 'shaun', '2021-06-16', '10:00', '12345678', 'luis.peralta3@gmail.com', 'masculino', 'cita medica', 'quitar puntos', 'activo');



CREATE TABLE roles (
  ID_Rol int(11) NOT NULL AUTO_INCREMENT,
  Name_Rol text DEFAULT NULL,
  Permissions text NOT NULL,
  url text NOT NULL,
  ID_permisos int(11) DEFAULT NULL,
  PRIMARY KEY (ID_Rol)
);



INSERT INTO roles (ID_Rol, Name_Rol, Permissions, url, ID_permisos) VALUES
(1, 'Admin', 'ver panel administrador', '/administrador/index.jsp', 1),
(2, 'Paciente', 'ver panel administrador', '/administrador/index.jsp', 2),
(3, 'Doctor', 'ver panel administrador', '/administrador/index.jsp', 3);



CREATE TABLE user (
  id_user int(11) NOT NULL AUTO_INCREMENT,
  name text DEFAULT NULL,
  email text DEFAULT NULL,
  password varchar(15) DEFAULT NULL,
  imagen_user text DEFAULT NULL,
  ID_Rol int(11) DEFAULT NULL,
  PRIMARY KEY (id_user)
);



INSERT INTO user (id_user, name, email, password, imagen_user, ID_Rol) VALUES
(1, 'Admin', 'luis.peralta1@gmail.com', '1234', '6.jpg', 1),
(2, 'Paciente', 'luis.peralta2@gmail.com', '1234', 'wallhaven-6omkow.jpg', 2),
(3, 'Doctor', 'doctor@gmail.com', '1234', '6.jpg', 3);


