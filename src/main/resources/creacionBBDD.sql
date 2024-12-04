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

CREATE TABLE ENVIOVITAL.ALERTA
(
    id SERIAL PRIMARY KEY,
    mensaje VARCHAR(255) NOT NULL,
    id_usuario INTEGER NOT NULL,
    vista BOOLEAN DEFAULT FALSE,
    CONSTRAINT FK_ALERTA_USUARIO FOREIGN KEY (id_usuario) REFERENCES ENVIOVITAL.USUARIO(id)
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

INSERT INTO ENVIOVITAL.PROVINCIA (id, nombre)
VALUES (1, 'Álava'),
       (2, 'Albacete'),
       (3, 'Alicante'),
       (4, 'Almería'),
       (5, 'Asturias'),
       (6, 'Ávila'),
       (7, 'Badajoz'),
       (8, 'Baleares'),
       (9, 'Barcelona'),
       (10, 'Burgos'),
       (11, 'Cáceres'),
       (12, 'Cádiz'),
       (13, 'Cantabria'),
       (14, 'Castellón'),
       (15, 'Ciudad Real'),
       (16, 'Córdoba'),
       (17, 'Cuenca'),
       (18, 'Gerona'),
       (19, 'Granada'),
       (20, 'Guadalajara'),
       (21, 'Guipúzcoa'),
       (22, 'Huelva'),
       (23, 'Huesca'),
       (24, 'Jaén'),
       (25, 'La Rioja'),
       (26, 'Las Palmas'),
       (27, 'León'),
       (28, 'Lérida'),
       (29, 'Lugo'),
       (30, 'Madrid'),
       (31, 'Málaga'),
       (32, 'Murcia'),
       (33, 'Navarra'),
       (34, 'Orense'),
       (35, 'Palencia'),
       (36, 'Pontevedra'),
       (37, 'Salamanca'),
       (38, 'Santa Cruz de Tenerife'),
       (39, 'Segovia'),
       (40, 'Sevilla'),
       (41, 'Soria'),
       (42, 'Tarragona'),
       (43, 'Teruel'),
       (44, 'Toledo'),
       (45, 'Valencia'),
       (46, 'Valladolid'),
       (47, 'Vizcaya'),
       (48, 'Zamora'),
       (49, 'Zaragoza');

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

INSERT INTO ENVIOVITAL.TIPOVEHICULO (id, nombre)
VALUES (1, 'Camion'),
       (2, 'Furgoneta'),
       (3, 'Coche'),
       (4, 'Autobus'),
       (5, 'Caravana');

INSERT INTO ENVIOVITAL.CONDUCTOR (id, nombre, apellidos, dni, direccion, telefono, id_usuario, fecha_nacimiento, email)
VALUES (1, 'Antonio', 'García López', '12345678A', 'Calle Gran Vía, Madrid', '612345678', 2, '1980-05-15', 'antonio.garcia@example.es'),
       (2, 'María', 'Martínez Sánchez', '12345679B', 'Calle Alcalá, Madrid', '622345678', 3, '1985-08-22', 'maria.martinez@example.es'),
       (3, 'Carlos', 'Rodríguez Gómez', '12345680C', 'Avenida Diagonal, Barcelona', '632345678', 4, '1990-01-30', 'carlos.rodriguez@example.es'),
       (4, 'Laura', 'Hernández Ruiz', '12345681D', 'Calle Serrano, Madrid', '642345678', 5, '1987-03-12', 'laura.hernandez@example.es'),
       (5, 'Pedro', 'López Fernández', '12345682E', 'Paseo de Gracia, Barcelona', '652345678', 6, '1982-11-25', 'pedro.lopez@example.es');

INSERT INTO ENVIOVITAL.ALMACEN (id, nombre, descripcion, direccion, es_activo, id_provincia, id_usuario, email)
VALUES
    (1, 'Almacén Central Madrid', 'Almacén principal de distribución en Madrid', 'Calle Mayor, 10, 28013 Madrid', TRUE, 30, 7, 'central.madrid@example.es'),
    (2, 'Almacén Norte Barcelona', 'Almacén regional para Cataluña', 'Avenida Diagonal, 250, 08013 Barcelona', FALSE, 9, 8, 'norte.barcelona@example.es'),
    (3, 'Centro Logístico Valencia', 'Centro de operaciones en Valencia', 'Calle Colón, 45, 46004 Valencia', TRUE, 45, 9, 'logistico.valencia@example.es'),
    (4, 'Depósito Sevilla', 'Depósito regional para Andalucía', 'Avenida de la Constitución, 1, 41001 Sevilla', FALSE, 40, 10, 'deposito.sevilla@example.es'),
    (5, 'Almacén Sur Málaga', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Málaga', TRUE, 31, 11, 'sur.malaga@example.es');

INSERT INTO ENVIOVITAL.EVENTO (id, nombre, descripcion, es_activo, id_provincia)
VALUES
    (1, 'Catástrofe Madrid', 'Inundación por fuertes lluvias.', TRUE, 30),
    (2, 'Catástrofe Barcelona', 'Incendio forestal en las afueras.', FALSE, 9),
    (3, 'Catástrofe Valencia', 'Tornado en la zona urbana.', TRUE, 45),
    (4, 'Catástrofe Sevilla', 'Severa ola de calor.', FALSE, 40),
    (5, 'Catástrofe Málaga', 'Terremoto leve en el litoral.', TRUE, 31),
    (6, 'Catástrofe Zaragoza', 'Nevada intensa paraliza el tráfico.', FALSE, 49),
    (7, 'Catástrofe Bilbao', 'Desbordamiento del río Nervión.', TRUE, 47),
    (8, 'Catástrofe Alicante', 'Tormenta eléctrica prolongada.', FALSE, 3),
    (9, 'Catástrofe Murcia', 'Derrumbe de edificios por lluvias.', TRUE, 32),
    (10, 'Catástrofe Granada', 'Alud en Sierra Nevada.', FALSE, 19),
    (11, 'Catástrofe Valladolid', 'Granizada severa.', TRUE, 46),
    (12, 'Catástrofe La Rioja', 'Deslizamiento de tierra.', FALSE, 25),
    (13, 'Catástrofe Navarra', 'Inundación por desbordamiento de río.', TRUE, 33),
    (14, 'Catástrofe Almería', 'Incendio en zona rural.', FALSE, 4),
    (15, 'Catástrofe Burgos', 'Tormenta de nieve.', TRUE, 10);

INSERT INTO ENVIOVITAL.VEHICULO (id, marca, modelo, matricula, id_conductor, id_tipo_vehiculo)
VALUES (1, 'Ford', 'Transit', 'ABC123A', 1, 1),
       (2, 'Mercedes', 'Sprinter', 'ABC123B', 2, 1),
       (3, 'Opel', 'Astra', 'ABC123C', 3, 2),
       (4, 'Volkswagen', 'Golf', 'ABC123D', 4, 2),
       (5, 'Peugeot', '308', 'ABC123E', 5, 2),
       (6, 'Renault', 'Trafic', 'ABC123F', 1, 1),
       (7, 'Citroën', 'Jumpy', 'ABC123G', 2, 1),
       (8, 'Fiat', 'Doblo', 'ABC123H', 3, 1),
       (9, 'Toyota', 'Corolla', 'ABC123J', 4, 2),
       (10, 'Nissan', 'NV200', 'ABC123K', 5, 1);

INSERT INTO ENVIOVITAL.EVENTOALMACEN (id, id_evento, id_almacen)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 4, 4),
       (5, 5, 5),
       (6, 6, 1),
       (7, 7, 2),
       (8, 8, 3),
       (9, 9, 4),
       (10, 10, 5),
       (11, 2, 1),
       (12, 2, 3),
       (13, 2, 4),
       (14, 2, 5),
       (15, 4, 2),
       (16, 5, 3),
       (17, 6, 4),
       (18, 9, 5);

INSERT INTO ENVIOVITAL.EVENTOALMACENCONDUCTOR (id, id_eventoalmacen, id_conductor)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 1),
       (5, 2, 2),
       (6, 2, 3);
-- Actualizar la secuencia de la tabla USUARIO
SELECT setval(pg_get_serial_sequence('enviovital.usuario', 'id'), coalesce(max(id), 1)) FROM enviovital.usuario;

-- Actualizar la secuencia de la tabla ALMACEN
SELECT setval(pg_get_serial_sequence('enviovital.almacen', 'id'), coalesce(max(id), 1)) FROM enviovital.almacen;

-- Actualizar la secuencia de la tabla CONDUCTOR
SELECT setval(pg_get_serial_sequence('enviovital.conductor', 'id'), coalesce(max(id), 1)) FROM enviovital.conductor;

-- Actualizar la secuencia de la tabla PROVINCIA
SELECT setval(pg_get_serial_sequence('enviovital.provincia', 'id'), coalesce(max(id), 1)) FROM enviovital.provincia;

-- Actualizar la secuencia de la tabla TIPOVEHICULO
SELECT setval(pg_get_serial_sequence('enviovital.tipovehiculo', 'id'), coalesce(max(id), 1)) FROM enviovital.tipovehiculo;

-- Actualizar la secuencia de la tabla EVENTO
SELECT setval(pg_get_serial_sequence('enviovital.evento', 'id'), coalesce(max(id), 1)) FROM enviovital.evento;

-- Actualizar la secuencia de la tabla VEHICULO
SELECT setval(pg_get_serial_sequence('enviovital.vehiculo', 'id'), coalesce(max(id), 1)) FROM enviovital.vehiculo;

-- Actualizar la secuencia de la tabla EVENTOALMACEN
SELECT setval(pg_get_serial_sequence('enviovital.eventoalmacen', 'id'), coalesce(max(id), 1)) FROM enviovital.eventoalmacen;

-- Actualizar la secuencia de la tabla EVENTOALMACENCONDUCTOR
SELECT setval(pg_get_serial_sequence('enviovital.eventoalmacenconductor', 'id'), coalesce(max(id), 1)) FROM enviovital.eventoalmacenconductor;