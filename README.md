# Hotel Castañas Microservicio  <img alt="Java" height="40" width="60" src="https://static.vecteezy.com/system/resources/thumbnails/014/011/391/small/hotel-3d-rendering-isometric-icon-png.png">

El proyeco "Hotel Castañas Microservicio" es una aplicación realizada con Spring Boot que permite a los usuarios crear reservas en la base de datos, junto con el manejo de las habitaciones y los clientes relacionados a estas. La API utiliza el protocolo HTTP para permitir que los usuarios interactúen con el sistema a través de una serie de endpoints de API definidos.

El gestor de dependencias utilizado es Gradle-Groovy y la versión de Java es Java 11.
La base de datos cuenta con un motor en MySQL manejado con JPA e Hibernate.
## Tecnologías utilizadas
- Spring Boot Framework.
- Gestor de dependencias: Gradle
- Lombok - anotaciones.
- Bases de datos: MySQL
- JPA e Hibernate.

## Seguridad <img align="center" alt="Pruebas" height="40" width="40" src="https://www.baeldung.com/wp-content/uploads/2020/03/lsso-course-page-shield-elements-icon.png">
La aplicación cuenta con una seguridad básica de Spring Security, la cual maneja una autenticación y acceso por roles.
Para tener acceso a los endpoints de la API, se debe autenticar con un usuario y contraseña válidos. La aplicación tiene dos roles: `WRITE` y `READ`. El rol `WRITE` permite al usuario acceder a todos los endpoints de la API, mientras que el rol `READ` solo permite al usuario acceder a los endpoints de lectura.
En caso de desear probar todos los endpoints en su dispositivo, habiendo lanzado la aplicación desde el IDLE de su preferencia, se puede hacer uso de los siguientes datos de acceso:
- Usuario: `admin`
- Contraseña: `gerencia123`

## CI:CD Integración continua y despliegue continuo <img align="center" alt="Pruebas" height="40" width="100" src="https://miro.medium.com/v2/resize:fit:612/0*KB2oUiVeUuC_zlAc.png">
La integración continúa es una práctica para automatizar la integración de los cambios del código, el proyecto hace uso de GitHub Actions. Al hacer un push al repositorio, se limpian los datos anteriores para garantizar que el despliegue sea de la versión correcta, se realizan las pruebas unitarias para garantizar que el proyecto continúe funcionado y se crea un archivo JA ejecutable.

Este archivo JAR se despliega automáticamente en el servicio de Railway, donde se ejecuta la aplicación. El servicio de Railway también aloja la base de datos MySQL utilizada por la aplicación. Railway ofrece un despliegue continúo que, además de permitir que los cambios se implemente automaticamente ofrece la posibilidad de acceder al microservicio en cualquier momento y en cualquier lugar.

## Configuración <img align="center" alt="Pruebas" height="40" width="40" src="https://cdn-icons-png.flaticon.com/512/6671/6671938.png">
Antes de comenzar, asegúrese de tener una base de datos configurada y actualice las credenciales de la base de datos en el archivo **application.properties**.
```java
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:8080/api/v1/
spring.datasource.username=root
spring.datasource.password={password}
spring.jpa.hibernate.ddl-auto=update
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```
## Documentación <img align="center" alt="Documentacion" height="40" width="40" src="https://www.alura.com.br/assets/api/cursos/512/swagger-documentando-apis.png">
La documentación de la API se genera automáticamente con Swagger. Este servicio ofrece una documentación que es fácil de entender y es amigable con todo tipo de usuarios.

Para acceder a la documentación, abra un navegador web y vaya a [Documentación Swagger](http://hotelcastanas-production.up.railway.app/swagger-ui/index.html#/).

## Pruebas Unitarias <img align="center" alt="Pruebas" height="40" width="40" src="https://media.giphy.com/media/1sMGC0XjA1Hk58wppo/giphy.gif">
Se han incluido pruebas unitarias utilizando Mockito y JUnit para asegurar que los servicios de Cliente, Envío y Empleado funcionan correctamente.
Las pruebas unitarias se encuentran en la carpeta src/test/java del proyecto.

## Patrón de Diseño
Este proyecto utiliza el patrón de diseño DTO (Data Transfer Object) para transferir datos entre las diferentes capas de la aplicación. Los DTO son objetos simples que contienen campos y métodos de acceso, y se utilizan para transferir datos entre los controladores y los servicios.
En este caso se utilizan para facilitar el manejo de las respuestas y el control del manejo de los datos sensibles.

## Diagrama del Modelo Entidad-Relación <img align="center" alt="MySQL" height="40" width="60" src="https://d1.awsstatic.com/asset-repository/products/amazon-rds/1024px-MySQL.ff87215b43fd7292af172e2a5d9b844217262571.png">
Este es el diagrama del modelo entidad-relación para la base de datos MySQL del proyecto de sistema de reservas:
![diagrama](https://github.com/dianaperezdv/HotelCastanas/blob/3c31af30b7109fbb41aa9d896249a34c0d89908e/DB_DIAGRAM.png)

-
## Diagrama del proyecto por paquetes

```java
com.example.Reservas
├── Configurer
│   └── SwaggerConfig.java
├── Controllers
│   ├── ClienteController.java
│   ├── HabitacionController.java
│   ├── ReservaController.java
├── DTO
│   ├── ClienteDTO.java
│   ├── HabitacionDTO.java
│   ├── ReservaDTO.java
│   ├── SolicitudReservaDTO.java
├── Exception
│   ├── ApiExceptionHandler.java
│   └── ApiRequestException.java
├── Models
│   ├── Cliente.java
│   ├── Habitacion.java
│   ├── Reserva.java
├── Repositories
│   ├── ClienteRepository.java
│   ├── HabitacionRepository.java
│   ├── ReservaRepository.java
├── Segurity
│   └── WebSecurityConfig.java
├── Services
│   ├── ClienteService.java
│   ├── HabitacionService.java
│   ├── ReservaService.java
└── ReservasApplication.java
```

El proyecto está organizado en cuatro paquetes principales, cada uno correspondiente a un nivel de abstracción diferente:

- El paquete **Configurer** contiene las clases de configuración para la base de datos y Swagger.


- El paquete **Controllers** contiene las clases controladoras para los servicios de Cliente, Habitación y Envio.
    - Los controladores contienen los endpoint para recibir peticiones HTTP, a través de estas el usuario realiza cambios en la base de datos.


- El paquete **DTO** contiene las clases DTO (Data Transfer Object) para los objetos Cliente, Empleado y Envío que se utilizan para transferir datos entre la capa de presentación y la capa de servicios.


- El paquete **Exception** contiene las clases de Excepciones (ApiExceptionHandler, ApiRequestException) para personalizar el manejo de errores y envíar mensajes que permitan al usuario comprender las respuestas del servidor.
-
- El paquete **Models** contiene las clases de entidades JPA (Java Persistence API) para los objetos Cliente, Empleado y Envio, que se utilizan para mapear las tablas de la base de datos.

- El paquete **Repositories** contiene las interfaces de repositorios JPA para los objetos Cliente, Empleado y Envio, que se utilizan para interactuar con la base de datos.
    - Por medio de JPA podemos comunicarnos con la base de datos sin necesidad de utilizar querys SQL, sino que utilizamos métodos de JPA que nos permiten realizar las operaciones básicas de una base de datos (insertar, actualizar, eliminar, consultar). En este caso, utilizamos los métodos de JPA para realizar las operaciones CRUD (Create, Read, Update, Delete) en la base de datos.


- El paquete **Security** contiene la clase de seguridad que se utiliza para autenticación y autorización de seguridad
    - La clase WebSecurityConfig extiende la clase WebSecurityConfigurerAdapter, que proporciona métodos de configuración por defecto para la seguridad HTTP. Sobreescribimos algunos de estos métodos para personalizar la configuración de seguridad. Autorizamos los endpoints para que los usuarios puedan acceder a ellos según su rol.


- El paquete **Services** contiene las clases de servicios para los servicios de Cliente, Habitación y Reserva que contienen la lógica de negocio. Estos servicios son implementados por los endpoints de los controladores.


- La clase ReservasApplication es la clase principal del proyecto que se utiliza para iniciar la aplicación.



## Endpoints <img align="center" alt="endpoints" height="40" width="40" src="https://user-images.githubusercontent.com/115324147/233541782-7b18ad4a-54d2-4304-945c-db24491a886e.png">

El proyecto cuenta con 3 controladores, a continuación indicaremos los endpoints de cada uno de ellos con algunos ejemplos de las peticiones:

#### ClienteController
| Método Http   | EndPoint                                                        | Descripción                  |
| ------------- |-----------------------------------------------------------------|------------------------------|
|`POST`         | ``(http://localhost:8080/apiMensajeria/v1/cliente)``            | Crea un nuevo cliente        |



Endpoints:
- **POST /cliente** - Crea un nuevo cliente

Ejemplo de petición:

```java
	{
        "cedula": 12345,
        "nombre":"Diana",
        "apellido":"Perez",
        "direccion" :"calle 46 # 69-90",
        "edad" : "30"
        "email" : "dianis@gmail.com",
        
    }
```


#### HabitacionController

| Método Http   | EndPoint                                                                             | Descripción                                                                       |
| ------------- |--------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
|`POST`         | ``(http://localhost:8080/api/v1/habitacion)``                                        | Crea una nueva habitación                                                         |
|`GET`       | ``(http://localhost:8080/apiMensajeria/v1/habitacion?fecha=2023-10-10)``             | Devuelve una lista de las habitaciones disponibles por fecha                      |
| `DELETE`    | ``(http://localhost:8080/apiMensajeria/v1/empleados?fecha=2023-10-10&tipo=PREMIUM)`` | Devuelve una lista de las habitaciones disponibles por fecha y tipo de habitación |



Endpoints:
- **POST /habitacion** - Crea una nueva habitación

Ejemplo de petición:
```java
{
        "numero": 111,
        "tipoHabitacion": "PREMIUM",
        "precioBase": 1000.0
        
}
```

- **GET /habitacion** - Obtener habitaciones disponibles por fecha y tipo "PREMIUM"

Respuesta esperada:

```java
{ [
        {
          "numero": 111,
          "tipoHabitacion": "PREMIUM",
          "precioBase": 1000.0
        },
        {
          "numero": 222,
          "tipoHabitacion": "PREMIUM",
          "precioBase": 3000.0
        }
        {
          "numero": 333,
          "tipoHabitacion": "PREMIUM",
          "precioBase": 3000.0
        }
   ]
}
```

#### ReservaController
| Método Http | EndPoint                                         | Descripción                                       |
|-------------|--------------------------------------------------|---------------------------------------------------|
| `POST`      | ``(http://localhost:8080/api/v1/reservas)``      | Crea una nueva reserva                            |
| `GET`       | ``(http://localhost:8080/api/v1/reservas/12345`` | Obtener lista de todas las reservas de un cliente |

Endpoints:
- **POST /reservas** - Crea una nueva reserva

Ejemplo de petición:

```java
{
        "cedula":12345,
        "numero": "111",
        "fecha":"2023-10-10",
        
        }
```


Respuesta esperada:

```java
{
    "fechaReserva": "2023-10-10",
    "habitacion":
        {
          "numero": 111,
          "tipoHabitacion": "PREMIUM",
          "precioBase": 1000.0
        },
    "cliente":
        {
        "cedula": 12345,
        "nombre":"Diana",
        "apellido":"Perez",
        "direccion" :"calle 46 # 69-90",
        "edad" : "30"
        "email" : "dianis@gmail.com",
        }
    "totalPago": 1000.0
}
```


