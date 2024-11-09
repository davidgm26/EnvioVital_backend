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
    id_usuario       INT    NOT NULL

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
    id_usuario   INT      NOT NULL
);

CREATE TABLE ENVIOVITAL.EVENTO
(
    id           SERIAL   NOT NULL PRIMARY KEY,
    nombre       VARCHAR(50),
    descripcion  VARCHAR(500),
    es_activo    BOOLEAN,
    id_provincia SMALLINT NOT NULL
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


INSERT INTO ENVIOVITAL.USUARIO (username, password, rol)
VALUES ('admin', 'Contrasena1', 1),
       ('user2', 'Contrasena1', 2),
       ('user3', 'Contrasena1', 2),
       ('user4', 'Contrasena1', 2),
       ('user5', 'Contrasena1', 2),
       ('user6', 'Contrasena1', 2),
       ('user7', 'Contrasena1', 2),
       ('user8', 'Contrasena1', 2),
       ('user9', 'Contrasena1', 2),
       ('user10', 'Contrasena1', 2);


INSERT INTO ENVIOVITAL.TIPOVEHICULO (nombre)
VALUES ('Camion'),
       ('Furgoneta'),
       ('Coche'),
       ('Autobus'),
       ('Caravana');


INSERT INTO ENVIOVITAL.CONDUCTOR (nombre, apellidos, dni, direccion, telefono, id_usuario, fecha_nacimiento, email)
VALUES ('Antonio', 'García López', '12345678A', 'Calle Gran Vía, Madrid', '612345678', 1, '1980-05-15', 'antonio.garcia@example.es'),
       ('María', 'Martínez Sánchez', '12345679B', 'Calle Alcalá, Madrid', '622345678', 2, '1985-08-22', 'maria.martinez@example.es'),
       ('Carlos', 'Rodríguez Gómez', '12345680C', 'Avenida Diagonal, Barcelona', '632345678', 3, '1990-01-30', 'carlos.rodriguez@example.es'),
       ('Laura', 'Hernández Ruiz', '12345681D', 'Calle Serrano, Madrid', '642345678', 4, '1987-03-12', 'laura.hernandez@example.es'),
       ('Pedro', 'López Fernández', '12345682E', 'Paseo de Gracia, Barcelona', '652345678', 5, '1982-11-25', 'pedro.lopez@example.es'),
       ('Sara', 'González Pérez', '12345683F', 'Calle Larios, Málaga', '662345678', 6, '1993-07-19', 'sara.gonzalez@example.es'),
       ('Javier', 'Gómez Torres', '12345684G', 'Calle Mayor, Valencia', '672345678', 7, '1981-04-09', 'javier.gomez@example.es'),
       ('Ana', 'Díaz Jiménez', '12345685H', 'Avenida de la Constitución, Sevilla', '682345678', 8, '1986-10-17', 'ana.diaz@example.es'),
       ('Luis', 'Martín Romero', '12345686J', 'Calle San Fernando, Sevilla', '692345678', 9, '1995-06-02', 'luis.martin@example.es'),
       ('Elena', 'Ruiz Sánchez', '12345687K', 'Avenida América, Madrid', '602345678', 10, '1988-09-14', 'elena.ruiz@example.es');

INSERT INTO ENVIOVITAL.ALMACEN (nombre, descripcion, direccion, es_activo, id_provincia, id_usuario, email)
VALUES ('Almacén Central Madrid', 'Almacén principal de distribución en Madrid', 'Calle Mayor, 10, 28013 Madrid', TRUE, 1, 1, 'central.madrid@example.es'),
       ('Almacén Norte Barcelona', 'Almacén regional para Cataluña', 'Avenida Diagonal, 250, 08013 Barcelona', FALSE, 2, 2, 'norte.barcelona@example.es'),
       ('Centro Logístico Valencia', 'Centro de operaciones en Valencia', 'Calle Colón, 45, 46004 Valencia', TRUE, 3, 3, 'logistico.valencia@example.es'),
       ('Depósito Sevilla', 'Depósito regional para Andalucía', 'Avenida de la Constitución, 1, 41001 Sevilla', FALSE, 4, 4, 'deposito.sevilla@example.es'),
       ('Almacén Sur Málaga', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Málaga', TRUE, 5, 5, 'sur.malaga@example.es'),
       ('Centro de Distribución Zaragoza', 'Centro de distribución para Aragón', 'Paseo de la Independencia, 5, 50001 Zaragoza', FALSE, 6, 6, 'distribucion.zaragoza@example.es'),
       ('Almacén Bilbao', 'Almacén de logística en el País Vasco', 'Calle Gran Vía, 25, 48001 Bilbao', TRUE, 7, 7, 'logistica.bilbao@example.es'),
       ('Almacén Levante Alicante', 'Almacén en Alicante para la zona de Levante', 'Avenida de la Estación, 22, 03005 Alicante', FALSE, 8, 8, 'levante.alicante@example.es'),
       ('Centro Logístico Murcia', 'Centro logístico en Murcia', 'Calle de la Libertad, 18, 30003 Murcia', TRUE, 9, 9, 'logistico.murcia@example.es'),
       ('Almacén Granada', 'Almacén para la zona de Granada y alrededores', 'Calle Recogidas, 7, 18010 Granada', FALSE, 10, 10, 'almacen.granada@example.es');



INSERT INTO ENVIOVITAL.EVENTO (nombre, descripcion, es_activo, id_provincia)
VALUES ('Catástrofe Madrid', 'Inundación por fuertes lluvias.', TRUE, 1),
       ('Catástrofe Barcelona', 'Incendio forestal en las afueras.', FALSE, 2),
       ('Catástrofe Valencia', 'Tornado en la zona urbana.', TRUE, 3),
       ('Catástrofe Sevilla', 'Severa ola de calor.', FALSE, 4),
       ('Catástrofe Málaga', 'Terremoto leve en el litoral.', TRUE, 5),
       ('Catástrofe Zaragoza', 'Nevada intensa paraliza el tráfico.', FALSE, 6),
       ('Catástrofe Bilbao', 'Desbordamiento del río Nervión.', TRUE, 7),
       ('Catástrofe Alicante', 'Tormenta eléctrica prolongada.', FALSE, 8),
       ('Catástrofe Murcia', 'Derrumbe de edificios por lluvias.', TRUE, 9),
       ('Catástrofe Granada', 'Alud en Sierra Nevada.', FALSE, 10);


INSERT INTO ENVIOVITAL.VEHICULO (marca, modelo, matricula, id_conductor, id_tipo_vehiculo)
VALUES ('Ford', 'Transit', 'ABC123A', 1, 1),
       ('Mercedes', 'Sprinter', 'ABC123B', 2, 1),
       ('Opel', 'Astra', 'ABC123C', 3, 2),
       ('Volkswagen', 'Golf', 'ABC123D', 4, 2),
       ('Peugeot', '308', 'ABC123E', 5, 2),
       ('Renault', 'Trafic', 'ABC123F', 6, 1),
       ('Citroën', 'Jumpy', 'ABC123G', 7, 1),
       ('Fiat', 'Doblo', 'ABC123H', 8, 1),
       ('Toyota', 'Corolla', 'ABC123J', 9, 2),
       ('Nissan', 'NV200', 'ABC123K', 10, 1);



INSERT INTO ENVIOVITAL.EVENTOALMACEN (id_evento, id_almacen)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

INSERT INTO ENVIOVITAL.EVENTOALMACENCONDUCTOR (id_eventoalmacen, id_conductor)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 1);


