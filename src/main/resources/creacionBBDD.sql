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
    dni              CHAR(9) UNIQUE,
    direccion        VARCHAR(500),
    telefono         VARCHAR(15),
    fecha_nacimiento DATE,
    email    		 VARCHAR(50) UNIQUE,
    id_usuario       INT    NOT NULL,
    es_activo        BOOLEAN default TRUE

);

CREATE TABLE ENVIOVITAL.ALMACEN
(
    id           SERIAL   NOT NULL PRIMARY KEY,
    nombre       VARCHAR(50),
    descripcion  VARCHAR(500),
    direccion    VARCHAR(500),
    email        VARCHAR(50) UNIQUE,
    es_activo    BOOLEAN  DEFAULT TRUE,
    id_provincia SMALLINT NOT NULL,
    id_usuario   INT      NOT NULL,
    foto_url VARCHAR(255)
);

CREATE TABLE ENVIOVITAL.EVENTO
(
    id           SERIAL   NOT NULL PRIMARY KEY,
    nombre       VARCHAR(50),
    descripcion  VARCHAR(500),
    es_activo    BOOLEAN,
    id_provincia SMALLINT NOT NULL,
    foto_url VARCHAR(255)
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

-- Add constraints
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


INSERT INTO enviovital.usuario (username, password, rol) VALUES ('admin', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 0);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen1', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen2', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen3', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen4', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen5', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('conductor1', '$2a$10$AIjQb5I4z5Jfwdb/cP/kUelcGGCYs3TVkGxiOcNOJ9mrcuosOs4le', 1);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('conductor2', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('conductor3', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('conductor4', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('conductor5', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 1);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen6', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacenr7', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen8', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen9', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen10', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen11', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen12', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen13', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen14', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);
INSERT INTO enviovital.usuario (username, password, rol) VALUES ('almacen15', '$2a$10$SYnbgV9pDlVVN6A9Mip7P.JjlUFoZqsSynYhmew0PrEM2wwPXa7.C', 2);



INSERT INTO ENVIOVITAL.TIPOVEHICULO (nombre)
VALUES ('Camion'),
       ('Furgoneta'),
       ('Coche'),
       ('Autobus'),
       ('Caravana');



INSERT INTO ENVIOVITAL.CONDUCTOR (nombre, apellidos, dni, direccion, telefono, id_usuario, fecha_nacimiento, email, es_activo)
VALUES
    ('Antonio', 'García López', '12345678A', 'Calle Gran Vía, Madrid', '612345678', 2, '1980-05-15', 'antonio.garcia@example.es',TRUE),
    ('María', 'Martínez Sánchez', '12345679B', 'Calle Alcalá, Madrid', '622345678', 3, '1985-08-22', 'maria.martinez@example.es',TRUE),
    ('Carlos', 'Rodríguez Gómez', '12345680C', 'Avenida Diagonal, Barcelona', '632345678', 4, '1990-01-30', 'carlos.rodriguez@example.es',TRUE),
    ('Laura', 'Hernández Ruiz', '12345681D', 'Calle Serrano, Madrid', '642345678', 5, '1987-03-12', 'laura.hernandez@example.es',TRUE),
    ('Pedro', 'López Fernández', '12345682E', 'Paseo de Gracia, Barcelona', '652345678', 6, '1982-11-25', 'pedro.lopez@example.es',TRUE),
    ('Alba', 'Vidal Galan', '12345000E', 'Paseo de Gracia, Barcelona', '652345608', 7, '1980-11-25', 'ala.lz@example.com',TRUE),
    ('Antonio', 'Perez Garcia', '10045000E', 'Paseo de Gracia, Barcelona', '600345608', 8, '1988-12-22', 'anala.lyz@example.com',TRUE),
    ('Luis', 'Ferraz Lope', '47340693Y', 'Cañada Real, Lebrija', '722443099', 9, '1995-11-11', 'payasa@live.com',TRUE),
    ('Pablo', 'Pedroso Perez', '47340690I', 'Calle Gol, Sevilla', '722443000', 10, '1990-01-01', 'pedroso@live.com',TRUE),
    ('Alberto', 'Gordez Pino', '46320994H', ' Calle Calatrava, 89', '722443770', 11, '1991-09-09', 'pineo@live.com',TRUE);


INSERT INTO ENVIOVITAL.ALMACEN (nombre, descripcion, direccion, es_activo, id_provincia, id_usuario, email, foto_url)
VALUES
    ('Almacén Central Madrid', 'Almacén principal de distribución en Madrid', 'Calle Mayor, 10, 28013 Madrid', TRUE, 30, 7, 'central.madrid@example.es', NULL),
    ('Almacén Norte Barcelona', 'Almacén regional para Cataluña', 'Avenida Diagonal, 250, 08013 Barcelona', FALSE, 9, 8, 'norte.barcelona@example.es', NULL),
    ('Centro Logístico Valencia', 'Centro de operaciones en Valencia', 'Calle Colón, 45, 46004 Valencia', TRUE, 45, 9, 'logistico.valencia@example.es', NULL),
    ('Depósito Sevilla', 'Depósito regional para Andalucía', 'Avenida de la Constitución, 1, 41001 Sevilla', TRUE, 40, 10, 'deposito.sevilla@example.es', NULL),
    ('Almacén Sur Málaga', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Málaga', TRUE, 31, 11, 'sur.mlaga@example.es', NULL),
    ('Almacén Murcia', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Murcia', TRUE, 31, 12, 'sur.murica@example.es', NULL),
    ('Almacén Teruel', 'Almacén de productos de primera', 'almacen Japón, 12, 29015 Teruel', TRUE, 43, 13, 'sur.malaga@example.es', NULL),
    ('Almacén Cádiz', 'Punto de recogida de ropa.', 'Calle Rojo, 15, 29010 Cádiz', TRUE, 12, 14, 'sur.ca@example.es', NULL),
    ('Almacén Murcia', 'Almacén de productos del sur', 'Calle Larios, 12, 29015 Murcia', TRUE, 31, 15, 'sur.magada@example.es', NULL),
    ('Almacén Graná', 'Centro de operaciones integral', 'Calle Materos, 89, 47003 Granada', true, 19, 16, 'granada.ex@gmail.com', NULL),
    ('Almacén Vasco', 'Almacenaje de importación', 'Calle Xenix, 42, 20015 Gerona', TRUE, 18, 17, 'sur.geronaga@example.es', NULL),
    ('Almacén Rueda', 'Almacenaje de productos ortopédicos', 'Calle Ladio, 22, 45015 Cuenca', TRUE, 17, 18, 'sur.ruedaaga@example.es', NULL),
    ('Almacén Jenioso', 'Espacios aceituneros y de aceites', 'Calle Magio, 29, 40015 Cuenca', TRUE, 24, 19, 'sur.jatttentttttaga@example.es', NULL),
    ('Almacén Soriano', 'Punto de recogida principal soriano.', 'Calle Robledo, 29, 40015 Soria', TRUE, 41, 20, 'sur.jaenaga@example.es', NULL),
    ('Almacén Navarrete', 'Almacenes navarros central', 'Calle Madrid, 29, 40995 Navarra', TRUE, 33, 21, 'sur.jatttttnaga@example.es', NULL);


select * from provincia p ;

INSERT INTO ENVIOVITAL.EVENTO (nombre, descripcion, es_activo, id_provincia, foto_url)
VALUES
    ('Devastación Madrid', 'Inundación por fuertes lluvias.', TRUE, 30, null),
    ('Catástrofe Barcelona', 'Incendio forestal en las afueras.', FALSE, 9, null),
    ('Caos valenciano', 'Tornado en la zona urbana.', TRUE, 45, null),
    ('Arde en Sevilla', 'Severa ola de calor.', TRUE, 40, null),
    ('Temblor Torremolinos', 'Terremoto leve en el litoral.', TRUE, 31, null),
    ('Catástrofe Zaragoza', 'Nevada intensa paraliza el tráfico.', FALSE, 49, null),
    ('Inundación Valladolid', 'Desbordamiento del río Nervión.', TRUE, 46, null),
    ('Corde de luz en Valencia', 'Tormenta eléctrica prolongada', TRUE, 3, null),
    ('Catástrofe Murcia', 'Derrumbe de edificios por lluvias.', TRUE, 32, null),
    ('Catástrofe Granada', 'Alud en Sierra Nevada.', TRUE, 19, null),
    ('Se hunde Lebrija', 'Inundaciones por las marismas.', TRUE, 40, null),
    ('Catástrofe segoviano', 'Huelga de transportistas', TRUE, 39, null),
    ('Catástrofe Toledo', 'Pérdida de la cosecha local por plaga.', TRUE, 44, null),
    ('Plagas en Andalucía', 'Pérdida de la cosecha local.', TRUE, 22, null),
    ('Incendio Forestal', 'Gran quema en poblados de la sierra', TRUE, 24, null),
    ('Tornado en Chipiona', 'Inundación en el litoral', TRUE, 12, null),
    ('Sequía extremeña', 'Pérdida del cultivo ', TRUE, 7, null),
    ('Tormenta en Bilbao', 'Lluvias torrenciales en la costa vasca.', TRUE, 7, null),
    ('Granizo en León', 'Granizada masiva que afecta a la agricultura.', TRUE, 27, null),
    ('Marea Negra Huelva', 'Fuga de petróleo en la costa.', TRUE, 22, null),
    ('Colapso en Salamanca', 'Cierre de calles por tormentas.', TRUE, 37, null),
    ('Riesgo volcánico en Tenerife', 'Posibles erupciones en el Teide.', TRUE, 38, null),
    ('Nevada en Burgos', 'Nevadas intensas cierran las carreteras principales.', TRUE, 10, null),
    ('Incendio en Alicante', 'Fuego masivo afecta a zonas rurales.', TRUE, 3, null),
    ('Deslave en Málaga', 'Alud arrastra viviendas en la zona rural.', TRUE, 31, null),
    ('Ciclón en Menorca', 'El paso del ciclón afecta a las islas Baleares.', TRUE, 8, null),
    ('Tormenta de arena en Badajoz', 'Tormenta de polvo que cubre la ciudad.', TRUE, 7, null),
    ('Causa de daños en Córdoba', 'Desbordamiento de ríos en el sur de Córdoba.', TRUE, 16, null),
    ('Terremoto en Albacete', 'Levecho terremoto sacudió la región.', TRUE, 2, null),
    ('Inundaciones en Tarragona', 'Desbordamiento de ríos en el litoral tarraconense.', TRUE, 42, null),
    ('Fuego en Castellón', 'Incendio forestal arrasa bosque en la región.', TRUE, 14, null);






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
VALUES
    (1, 1),
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
    (9, 5),
    (11, 6),   -- Evento 11 en Almacén 6
    (12, 7),   -- Evento 12 en Almacén 7
    (13, 8),   -- Evento 13 en Almacén 8
    (14, 9),   -- Evento 14 en Almacén 9
    (15, 10),  -- Evento 15 en Almacén 10
    (3, 6),    -- Evento 3 en Almacén 6
    (4, 7),    -- Evento 4 en Almacén 7
    (5, 8),    -- Evento 5 en Almacén 8
    (6, 9),    -- Evento 6 en Almacén 9
    (7, 10),   -- Evento 7 en Almacén 10
    (8, 6),    -- Evento 8 en Almacén 6
    (9, 7),    -- Evento 9 en Almacén 7
    (10, 8),   -- Evento 10 en Almacén 8
    (11, 9),   -- Evento 11 en Almacén 9
    (12, 10),  -- Evento 12 en Almacén 10
    (16, 1),   -- Evento 16 en Almacén 1
    (17, 2),   -- Evento 17 en Almacén 2
    (18, 3),   -- Evento 18 en Almacén 3
    (19, 4),   -- Evento 19 en Almacén 4
    (20, 5),   -- Evento 20 en Almacén 5
    (21, 6),   -- Evento 21 en Almacén 6
    (22, 7),   -- Evento 22 en Almacén 7
    (23, 8),   -- Evento 23 en Almacén 8
    (24, 9),   -- Evento 24 en Almacén 9
    (25, 10),  -- Evento 25 en Almacén 10
    (16, 6),   -- Evento 16 en Almacén 6
    (17, 7),   -- Evento 17 en Almacén 7
    (18, 8),   -- Evento 18 en Almacén 8
    (19, 9),   -- Evento 19 en Almacén 9
    (20, 10),  -- Evento 20 en Almacén 10
    (21, 1),   -- Evento 21 en Almacén 1
    (22, 2),   -- Evento 22 en Almacén 2
    (23, 3),   -- Evento 23 en Almacén 3
    (24, 4),   -- Evento 24 en Almacén 4
    (25, 5),   -- Evento 25 en Almacén 5
    (26, 6),   -- Evento 26 en Almacén 6
    (27, 7),   -- Evento 27 en Almacén 7
    (28, 8),   -- Evento 28 en Almacén 8
    (29, 9),   -- Evento 29 en Almacén 9
    (30, 10),  -- Evento 30 en Almacén 10
    (3, 1),    -- Evento 3 en Almacén 1
    (3, 2),    -- Evento 3 en Almacén 2
    (3, 4),    -- Evento 3 en Almacén 4
    (3, 5),    -- Evento 3 en Almacén 5
    (3, 6),    -- Evento 3 en Almacén 6
    (3, 7),    -- Evento 3 en Almacén 7
    (4, 1),    -- Evento 4 en Almacén 1
    (4, 2),    -- Evento 4 en Almacén 2
    (4, 3),    -- Evento 4 en Almacén 3
    (4, 5),    -- Evento 4 en Almacén 5
    (4, 6),    -- Evento 4 en Almacén 6
    (4, 7),    -- Evento 4 en Almacén 7
    (5, 1),    -- Evento 5 en Almacén 1
    (5, 2),    -- Evento 5 en Almacén 2
    (5, 4),    -- Evento 5 en Almacén 4
    (5, 5),    -- Evento 5 en Almacén 5
    (5, 6),    -- Evento 5 en Almacén 6
    (5, 8),    -- Evento 5 en Almacén 8
    (6, 2),    -- Evento 6 en Almacén 2
    (6, 3),    -- Evento 6 en Almacén 3
    (6, 4),    -- Evento 6 en Almacén 4
    (6, 5),    -- Evento 6 en Almacén 5
    (6, 7);    -- Evento 6 en Almacén 7


INSERT INTO ENVIOVITAL.EVENTOALMACENCONDUCTOR (id_eventoalmacen, id_conductor)
VALUES
    (1, 1),  -- Evento 1 en Almacén 1, Conductor Antonio García
    (1, 2),  -- Evento 1 en Almacén 1, Conductor María Martínez
    (1, 3),  -- Evento 1 en Almacén 1, Conductor Carlos Rodríguez
    (2, 1),  -- Evento 2 en Almacén 2, Conductor Antonio García
    (2, 2),  -- Evento 2 en Almacén 2, Conductor María Martínez
    (2, 3),  -- Evento 2 en Almacén 2, Conductor Carlos Rodríguez
    (3, 1),  -- Evento 3 en Almacén 3, Conductor Antonio García
    (3, 2),  -- Evento 3 en Almacén 3, Conductor María Martínez
    (3, 3),  -- Evento 3 en Almacén 3, Conductor Carlos Rodríguez
    (4, 1),  -- Evento 4 en Almacén 4, Conductor Antonio García
    (4, 2),  -- Evento 4 en Almacén 4, Conductor María Martínez
    (4, 3),  -- Evento 4 en Almacén 4, Conductor Carlos Rodríguez
    (5, 1),  -- Evento 5 en Almacén 5, Conductor Antonio García
    (5, 2),  -- Evento 5 en Almacén 5, Conductor María Martínez
    (5, 3),  -- Evento 5 en Almacén 5, Conductor Carlos Rodríguez
    (6, 1),  -- Evento 6 en Almacén 6, Conductor Antonio García
    (6, 2),  -- Evento 6 en Almacén 6, Conductor María Martínez
    (6, 3),  -- Evento 6 en Almacén 6, Conductor Carlos Rodríguez
    (7, 1),  -- Evento 7 en Almacén 7, Conductor Antonio García
    (7, 2),  -- Evento 7 en Almacén 7, Conductor María Martínez
    (7, 3),  -- Evento 7 en Almacén 7, Conductor Carlos Rodríguez
    (8, 1),  -- Evento 8 en Almacén 8, Conductor Antonio García
    (8, 2),  -- Evento 8 en Almacén 8, Conductor María Martínez
    (8, 3),  -- Evento 8 en Almacén 8, Conductor Carlos Rodríguez
    (9, 1),  -- Evento 9 en Almacén 9, Conductor Antonio García
    (9, 2),  -- Evento 9 en Almacén 9, Conductor María Martínez
    (9, 3),  -- Evento 9 en Almacén 9, Conductor Carlos Rodríguez
    (10, 1), -- Evento 10 en Almacén 10, Conductor Antonio García
    (10, 2), -- Evento 10 en Almacén 10, Conductor María Martínez
    (10, 3), -- Evento 10 en Almacén 10, Conductor Carlos Rodríguez
    (11, 1), -- Evento 11 en Almacén 1, Conductor Antonio García
    (11, 2), -- Evento 11 en Almacén 1, Conductor María Martínez
    (11, 3), -- Evento 11 en Almacén 1, Conductor Carlos Rodríguez
    (12, 1), -- Evento 12 en Almacén 2, Conductor Antonio García
    (12, 2), -- Evento 12 en Almacén 2, Conductor María Martínez
    (12, 3), -- Evento 12 en Almacén 2, Conductor Carlos Rodríguez
    (13, 1), -- Evento 13 en Almacén 3, Conductor Antonio García
    (13, 2), -- Evento 13 en Almacén 3, Conductor María Martínez
    (13, 3), -- Evento 13 en Almacén 3, Conductor Carlos Rodríguez
    (14, 1), -- Evento 14 en Almacén 4, Conductor Antonio García
    (14, 2), -- Evento 14 en Almacén 4, Conductor María Martínez
    (14, 3), -- Evento 14 en Almacén 4, Conductor Carlos Rodríguez
    (15, 1), -- Evento 15 en Almacén 5, Conductor Antonio García
    (15, 2), -- Evento 15 en Almacén 5, Conductor María Martínez
    (15, 3), -- Evento 15 en Almacén 5, Conductor Carlos Rodríguez
    (16, 1), -- Evento 16 en Almacén 6, Conductor Antonio García
    (16, 2), -- Evento 16 en Almacén 6, Conductor María Martínez
    (16, 3), -- Evento 16 en Almacén 6, Conductor Carlos Rodríguez
    (17, 1), -- Evento 17 en Almacén 7, Conductor Antonio García
    (17, 2), -- Evento 17 en Almacén 7, Conductor María Martínez
    (17, 3), -- Evento 17 en Almacén 7, Conductor Carlos Rodríguez
    (18, 1), -- Evento 18 en Almacén 8, Conductor Antonio García
    (18, 2), -- Evento 18 en Almacén 8, Conductor María Martínez
    (18, 3), -- Evento 18 en Almacén 8, Conductor Carlos Rodríguez
    (19, 1), -- Evento 19 en Almacén 9, Conductor Antonio García
    (19, 2), -- Evento 19 en Almacén 9, Conductor María Martínez
    (19, 3), -- Evento 19 en Almacén 9, Conductor Carlos Rodríguez
    (20, 1), -- Evento 20 en Almacén 10, Conductor Antonio García
    (20, 2), -- Evento 20 en Almacén 10, Conductor María Martínez
    (20, 3); -- Evento 20 en Almacén 10, Conductor Carlos Rodríguez







