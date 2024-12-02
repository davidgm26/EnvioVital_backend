DROP SCHEMA IF EXISTS ENVIOVITAL CASCADE;

CREATE SCHEMA IF NOT EXISTS ENVIOVITAL;


CREATE TABLE ENVIOVITAL.PROVINCIA
(
    id     SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE ENVIOVITAL.USUARIO
(
    id       SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(25),
    password VARCHAR(500),
    rol      INT    NOT NULL
);

CREATE TABLE ENVIOVITAL.TIPOVEHICULO
(
    id     SERIAL NOT NULL PRIMARY KEY,
    nombre VARCHAR(500)
);

CREATE TABLE ENVIOVITAL.CONDUCTOR
(
    id               SERIAL NOT NULL PRIMARY KEY,
    nombre           VARCHAR(50),
    apellidos        VARCHAR(150),
    dni              CHAR(9) unique,
    direccion        VARCHAR(500),
    telefono         VARCHAR(15),
    fecha_nacimiento DATE,
    email    		 VARCHAR(50) UNIQUE,
    id_usuario       INT    NOT null,
    es_activo        BOOLEAN default TRUE


);

CREATE TABLE ENVIOVITAL.ALMACEN
(
    id           SERIAL   NOT NULL PRIMARY KEY,
    nombre       VARCHAR(50),
    descripcion  VARCHAR(500),
    direccion    VARCHAR(500),
    email    	 VARCHAR(50) UNIQUE,
    es_activo    BOOLEAN  default TRUE,
    id_provincia SMALLINT NOT NULL,
    id_usuario   INT      NOT null,
    foto_url 	 VARCHAR(500)	  default null
);

CREATE TABLE ENVIOVITAL.EVENTO
(
    id           SERIAL   NOT NULL PRIMARY KEY,
    nombre       VARCHAR(50),
    descripcion  VARCHAR(500),
    es_activo    BOOLEAN,
    id_provincia SMALLINT NOT null,
    foto_url 	 VARCHAR(500)	  default NULL
);

CREATE TABLE ENVIOVITAL.VEHICULO
(
    id               SERIAL NOT NULL PRIMARY KEY,
    marca            VARCHAR(50),
    modelo           VARCHAR(500),
    matricula        VARCHAR(10),
    id_conductor     INT    NOT NULL,
    id_tipo_vehiculo INT    NOT NULL
);


CREATE TABLE ENVIOVITAL.EVENTOALMACEN
(
    id           SERIAL NOT NULL PRIMARY KEY,
    id_evento    INT    NOT NULL,
    id_almacen   INT    NOT NULL,
    CONSTRAINT FK_EVENTOALMACEN_EVENTO FOREIGN KEY (id_evento) REFERENCES ENVIOVITAL.EVENTO (id),
    CONSTRAINT FK_EVENTOALMACEN_ALMACEN FOREIGN KEY (id_almacen) REFERENCES ENVIOVITAL.ALMACEN (id)
);

CREATE TABLE ENVIOVITAL.EVENTOALMACENCONDUCTOR
(
    id                 SERIAL NOT NULL PRIMARY KEY,
    id_eventoalmacen   INT    NOT NULL,
    id_conductor       INT    NOT NULL,
    CONSTRAINT FK_EVENTOALMACENCONDUCTOR_EVENTOALMACEN FOREIGN KEY (id_eventoalmacen) REFERENCES ENVIOVITAL.EVENTOALMACEN (id),
    CONSTRAINT FK_EVENTOALMACENCONDUCTOR_CONDUCTOR FOREIGN KEY (id_conductor) REFERENCES ENVIOVITAL.CONDUCTOR (id)
);





ALTER TABLE ENVIOVITAL.CONDUCTOR
    ADD CONSTRAINT FK_CONDUCTOR_USUARIO FOREIGN KEY (id_usuario) REFERENCES ENVIOVITAL.USUARIO (id);
ALTER TABLE ENVIOVITAL.ALMACEN
    ADD CONSTRAINT FK_ALMACEN_PROVINCIA FOREIGN KEY (id_provincia) REFERENCES ENVIOVITAL.PROVINCIA (id);
ALTER TABLE ENVIOVITAL.ALMACEN
    ADD CONSTRAINT FK_ALMACEN_USUARIO FOREIGN KEY (id_usuario) REFERENCES ENVIOVITAL.USUARIO (id);
ALTER TABLE ENVIOVITAL.EVENTO
    ADD CONSTRAINT FK_EVENTO_PROVINCIA FOREIGN KEY (id_provincia) REFERENCES ENVIOVITAL.PROVINCIA (id);
ALTER TABLE ENVIOVITAL.VEHICULO
    ADD CONSTRAINT FK_VEHICULO_CONDUCTOR FOREIGN KEY (id_conductor) REFERENCES ENVIOVITAL.CONDUCTOR (id);
ALTER TABLE ENVIOVITAL.VEHICULO
    ADD CONSTRAINT FK_VEHICULO_TIPOVEHICULO FOREIGN KEY (id_tipo_vehiculo) REFERENCES ENVIOVITAL.TIPOVEHICULO (id);



INSERT INTO ENVIOVITAL.PROVINCIA (nombre)
VALUES ('Álava'),
       ('Albacete'),
       ('Alicante'),
       ('Almería'),
       ('Asturias'),
       ('Ávila'),
       ('Badajoz'),
       ('Baleares'),
       ('Barcelona'),
       ('Burgos'),
       ('Cáceres'),
       ('Cádiz'),
       ('Cantabria'),
       ('Castellón'),
       ('Ciudad Real'),
       ('Córdoba'),
       ('Cuenca'),
       ('Gerona'),
       ('Granada'),
       ('Guadalajara'),
       ('Guipúzcoa'),
       ('Huelva'),
       ('Huesca'),
       ('Jaén'),
       ('La Rioja'),
       ('Las Palmas'),
       ('León'),
       ('Lérida'),
       ('Lugo'),
       ('Madrid'),
       ('Málaga'),
       ('Murcia'),
       ('Navarra'),
       ('Orense'),
       ('Palencia'),
       ('Pontevedra'),
       ('Salamanca'),
       ('Santa Cruz de Tenerife'),
       ('Segovia'),
       ('Sevilla'),
       ('Soria'),
       ('Tarragona'),
       ('Teruel'),
       ('Toledo'),
       ('Valencia'),
       ('Valladolid'),
       ('Vizcaya'),
       ('Zamora'),
       ('Zaragoza');


INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (1, 'admin', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 0);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (7, 'almacen1', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (8, 'almacen2', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (9, 'almacen3', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (10, 'almacen4', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (11, 'almacen5', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (2, 'conductor1', '$2a$10$AIjQb5I4z5Jfwdb/cP/kUelcGGCYs3TVkGxiOcNOJ9mrcuosOs4le', 1);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (3, 'conductor2', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (4, 'conductor3', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (5, 'conductor4', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (id, username, password, rol) VALUES (6, 'conductor5', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);



INSERT INTO ENVIOVITAL.TIPOVEHICULO (nombre)
VALUES ('Camion'),
       ('Furgoneta'),
       ('Coche'),
       ('Autobus'),
       ('Caravana');



INSERT INTO ENVIOVITAL.CONDUCTOR (nombre, apellidos, dni, direccion, telefono, id_usuario, fecha_nacimiento, email)
VALUES
    ('Antonio', 'García López', '12345678A', 'Calle Gran Vía, Madrid', '612345678', 2, '1980-05-15', 'antonio.garcia@example.es'),
    ('María', 'Martínez Sánchez', '12345679B', 'Calle Alcalá, Madrid', '622345678', 3, '1985-08-22', 'maria.martinez@example.es'),
    ('Carlos', 'Rodríguez Gómez', '12345680C', 'Avenida Diagonal, Barcelona', '632345678', 4, '1990-01-30', 'carlos.rodriguez@example.es'),
    ('Laura', 'Hernández Ruiz', '12345681D', 'Calle Serrano, Madrid', '642345678', 5, '1987-03-12', 'laura.hernandez@example.es'),
    ('Pedro', 'López Fernández', '12345682E', 'Paseo de Gracia, Barcelona', '652345678', 6, '1982-11-25', 'pedro.lopez@example.es');


INSERT INTO ENVIOVITAL.ALMACEN (nombre, descripcion, direccion, es_activo, id_provincia, id_usuario, email)
VALUES
    ('Almacén Central Madrid', 'Almacén principal de distribución en Madrid', 'Calle Mayor, 10, 28013 Madrid', TRUE, 1, 7, 'central.madrid@example.es'),
    ('Almacén Norte Barcelona', 'Almacén regional para Cataluña', 'Avenida Diagonal, 250, 08013 Barcelona', FALSE, 2, 8, 'norte.barcelona@example.es'),
    ('Centro Logístico Valencia', 'Centro de operaciones en Valencia', 'Calle Colón, 45, 46004 Valencia', TRUE, 3, 9, 'logistico.valencia@example.es'),
    ('Depósito Sevilla', 'Depósito regional para Andalucía', 'Avenida de la Constitución, 1, 41001 Sevilla', FALSE, 4, 10, 'deposito.sevilla@example.es'),
    ('Almacén Sur Málaga', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Málaga', TRUE, 5, 11, 'sur.malaga@example.es');




INSERT INTO ENVIOVITAL.EVENTO (nombre, descripcion, es_activo, id_provincia)
VALUES ('Catástrofe Madrid', 'Inundación por fuertes lluvias.', TRUE, 30),
       ('Catástrofe Barcelona', 'Incendio forestal en las afueras.', FALSE, 9),
       ('Catástrofe Valencia', 'Tornado en la zona urbana.', TRUE, 3),
       ('Catástrofe Sevilla', 'Severa ola de calor.', FALSE, 4),
       ('Catástrofe Málaga', 'Terremoto leve en el litoral.', TRUE, 5),
       ('Catástrofe Zaragoza', 'Nevada intensa paraliza el tráfico.', FALSE, 6),
       ('Catástrofe Bilbao', 'Desbordamiento del río Nervión.', TRUE, 7),
       ('Catástrofe Alicante', 'Tormenta eléctrica prolongada.', FALSE, 8),
       ('Catástrofe Murcia', 'Derrumbe de edificios por lluvias.', TRUE, 9),
       ('Catástrofe Granada', 'Alud en Sierra Nevada.', FALSE,15);


INSERT INTO ENVIOVITAL.VEHICULO (marca, modelo, matricula, id_conductor, id_tipo_vehiculo)
VALUES ('Ford', 'Transit', 'ABC123A', 1, 1),
       ('Mercedes', 'Sprinter', 'ABC123B', 2, 1),
       ('Opel', 'Astra', 'ABC123C', 3, 2),
       ('Volkswagen', 'Golf', 'ABC123D', 4, 2),
       ('Peugeot', '308', 'ABC123E', 5, 2),
       ('Renault', 'Trafic', 'ABC123F', 1, 1),
       ('Citroën', 'Jumpy', 'ABC123G', 2, 1),
       ('Fiat', 'Doblo', 'ABC123H', 3, 1),
       ('Toyota', 'Corolla', 'ABC123J', 4, 2),
       ('Nissan', 'NV200', 'ABC123K', 5, 1);



INSERT INTO ENVIOVITAL.EVENTOALMACEN (id_evento, id_almacen)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 1),
       (7, 2),
       (8, 3),
       (9, 4),
       (10, 5),
       (2, 1),
       (2, 3),
       (2, 4),
       (2, 5),
       (4, 2),
       (5, 3),
       (6, 4),
       (9, 5);

INSERT INTO ENVIOVITAL.EVENTOALMACENCONDUCTOR (id_eventoalmacen, id_conductor)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3);

