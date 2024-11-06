DROP SCHEMA IF EXISTS ENVIOVITAL CASCADE;

CREATE SCHEMA IF NOT EXISTS ENVIOVITAL;


CREATE TABLE ENVIOVITAL.PROVINCIA(
                                     id SERIAL NOT NULL PRIMARY KEY,
                                     nombre VARCHAR(50)
);

CREATE TABLE ENVIOVITAL.USUARIO(
                                   id SERIAL NOT NULL PRIMARY KEY,
                                   username VARCHAR(25),
                                   password VARCHAR(500),
                                   email VARCHAR(50),
                                   rol INT NOT NULL
);

CREATE TABLE ENVIOVITAL.TIPOVEHICULO(
                                        id SERIAL NOT NULL PRIMARY KEY,
                                        nombre VARCHAR(500)
);

CREATE TABLE ENVIOVITAL.CONDUCTOR(
                                     id SERIAL NOT NULL PRIMARY KEY,
                                     nombre VARCHAR(50),
                                     apellidos VARCHAR(150),
                                     dni CHAR(9) unique,
                                     direccion VARCHAR(500),
                                     telefono VARCHAR(15),
                                     fecha_nacimiento DATE,
                                     id_usuario INT NOT NULL

);

CREATE TABLE ENVIOVITAL.ALMACEN(
                                   id SERIAL NOT NULL PRIMARY KEY,
                                   nombre VARCHAR(50),
                                   descripcion VARCHAR(500),
                                   es_activo BOOLEAN,
                                   id_provincia SMALLINT NOT NULL,
                                   id_usuario INT NOT NULL
);

CREATE TABLE ENVIOVITAL.EVENTO(
                                  id SERIAL NOT NULL PRIMARY KEY,
                                  nombre VARCHAR(50),
                                  descripcion VARCHAR(500),
                                  es_activo BOOLEAN,
                                  id_provincia SMALLINT NOT NULL
);

CREATE TABLE ENVIOVITAL.VEHICULO(
                                    id SERIAL NOT NULL PRIMARY KEY,
                                    marca VARCHAR(50),
                                    modelo VARCHAR(500),
                                    matricula VARCHAR(10),
                                    id_conductor INT NOT NULL,
                                    id_tipo_vehiculo INT NOT NULL
);

CREATE TABLE ENVIOVITAL.CONDUCTORALMACEN (
                                             id SERIAL NOT NULL PRIMARY KEY,
                                             id_almacen INT NOT NULL,
                                             id_conductor INT NOT NULL
);


ALTER TABLE ENVIOVITAL.CONDUCTOR ADD CONSTRAINT FK_CONDUCTOR_USUARIO FOREIGN KEY (id_usuario) REFERENCES ENVIOVITAL.USUARIO(id);
ALTER TABLE ENVIOVITAL.ALMACEN ADD CONSTRAINT FK_ALMACEN_PROVINCIA FOREIGN KEY (id_provincia) REFERENCES ENVIOVITAL.PROVINCIA(id);
ALTER TABLE ENVIOVITAL.ALMACEN ADD CONSTRAINT FK_ALMACEN_USUARIO FOREIGN KEY (id_usuario) REFERENCES ENVIOVITAL.USUARIO(id);
ALTER TABLE ENVIOVITAL.EVENTO ADD CONSTRAINT FK_EVENTO_PROVINCIA FOREIGN KEY (id_provincia) REFERENCES ENVIOVITAL.PROVINCIA(id);
ALTER TABLE ENVIOVITAL.VEHICULO ADD CONSTRAINT FK_VEHICULO_CONDUCTOR FOREIGN KEY (id_conductor) REFERENCES ENVIOVITAL.CONDUCTOR(id);
ALTER TABLE ENVIOVITAL.VEHICULO ADD CONSTRAINT FK_VEHICULO_TIPOVEHICULO FOREIGN KEY (id_tipo_vehiculo) REFERENCES ENVIOVITAL.TIPOVEHICULO(id);
ALTER TABLE ENVIOVITAL.CONDUCTORALMACEN ADD CONSTRAINT FK_CONDUCTORALMACEN_CONDUCTOR FOREIGN KEY (id_conductor) REFERENCES ENVIOVITAL.CONDUCTOR(id);
ALTER TABLE ENVIOVITAL.CONDUCTORALMACEN ADD CONSTRAINT FK_CONDUCTORALMACEN_ALMACEN FOREIGN KEY (id_almacen) REFERENCES ENVIOVITAL.ALMACEN(id);
