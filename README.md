# EnvioVital

## APP compuesta por lado servidor y lado cliente para la conexion entre almacenes y conductores para socorrer lugares catastroficos.

---

¡Bienvenido a EnvioVital! Este proyecto tiene como objetivo desarrollar un sistema para la comunicación eficiente entre almacenes y conductores para colaborar en una zona catastrofica localizada en España, permitiendo administrar Eventos, Almacenes y conductores.

## **Introducción**

API programado en Java con [Spring Boot](https://spring.io/projects/spring-boot), antes de arrancar la API es importante crear la base de datos, ubicada en la carpeta resources.


## Tecnologías utilizadas

- **Spring:** Se ha utilizado el framework de desarrollo de aplicaciones Java Spring para crear la API de backend y gestionar la lógica del negocio y la interacción con la base de datos.
- **Angular:** Se ha utilizado el framework Angular para desarrollar la aplicación web que se conecta a la API y ofrece una interfaz de usuario completa.
- **PostgreSQL:** Se utiliza la base de datos PostgreSQL.


## Características

- Gestión de Eventos:Como administrador podrás registrar y administrar información sobre los eventos, como descripcion, ubicación y nombre

- Gestión de Almacenes: Podrás mantener un registro de los conductores inscritos en tu almacen para un evento, incluyendo sus datos personales y lista de vehiculos.

- Gestión de Conductores: Podrás inscribirte a almacenes para un evento especifico, gestionar tus datos personales y vehiculos.

- Interfaz intuitiva: la interfaz de usuario ha sido diseñada de forma amigable y fácil de usar, permitiendo una navegación fluida y sencilla.
  
## Requisitos del sistema para desplegarlo.

- Java 23 o superior.
- Angular 18 (No aseguramos el correcto funcionamiento si se usa una versión inferior.)
- PGAdmin o equivalentes

## Instalación y configuración

1. Clona este repositorio en tu máquina local.

2. Ejecuta el fichero .SQL para generar la base de datos.

3. Ejecta el proyecto de spring con el comando `mvn spring-boot:run`
 
4. Si tienes la API arrancada podrás ver la documentacion [pinchando aqui](http://localhost:8080/swagger-ui/index.html)

## Usuarios

El proyecto cuenta con tres perfiles de usuario: Administrador, Conductor y  Almacen,  A continuación se detallan los usuarios predefinidos para cada perfil:

### Perfil de Administrador


Este usuario tiene privilegios de administrador y acceso completo a todas las funcionalidades de gestion de la aplicación.

### Perfil de Conductor


Este usuario tiene una lista de funcionalidades limitada a gestionar su perfil, y poder gestionar inscripciones propias a almacenes.

### Perfil de Almacen


Este usuario tiene una lista de funcionalidades limitada a gestionar su perfil, y poder gestionar inscripciones propias a eventos y conductores inscritos a él.

---

## Desarrollado por:

- [Luis Marin Carmona](https://github.com/lmarinc)
- [Alba Maria Vidal](https://github.com/amsafa)
- [Pablo Padilla](https://github.com/Ppabetiko18)
- [David Garcia](https://github.com/davidgm26)

## Documentacion

[Documentacion](https://drive.google.com/drive/folders/1NNvL3G1OE2V9uy3bORjVTl_nIWQOddP_?usp=drive_link)

El proyecto Envío Vital ha sido desarrollado por el grupo 5 como proyecto educativo para las Asignaturas de Entorno Cliente, Entorno Servidor y diseño de interfaces  del Centro SAFA Ntra Señora de los Reyes



¡Gracias por utilizar Envio Vital! Si tienes alguna pregunta o sugerencia, no dudes en contactarnos.
