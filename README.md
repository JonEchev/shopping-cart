# shopping-cart

API para la gestión de Carrito de Compras.

> <p>#jpa #java #spring-boot </p>

---

## Tabla de Contenido

- [Descripción](#descripción)
- [Construido con](#construido-con)
- [Desarrollo](#desarrollo)
- [Endpoints](#endpoints)
- [Autor y contacto](#autor-y-contacto)

---

## Descripción

API's que permiten la gestión de un carrito de compras. 

- En el documento: [Evaluación Técnica Backend - OUM.pdf](https://github.com/JonEchev/shopping-cart/blob/main/requirement_document/Evaluaci%C3%B3n%20T%C3%A9cnica%20Backend%20-%20OUM.pdf) se encuentra el requerimiento completo que se desarrollo para está API.

---

## Construido con

El código se encuentra implementado con Java y Gradle usando las siguientes librerías:

- Lombok - Para simplificar la creación de los medios de acceso a datos de un objeto
- Spring Data JPA - Gestion de los datos en la Base de Datos PostgreSQL
- Log4j - Para escribir mensajes de registro
- Swagger - Para la documentación técnica del código

---

## Desarrollo
1. Ingresar a la carpeta: [deliverables](https://github.com/JonEchev/shopping-cart/tree/main/deliverables) en la cual se encuentran los entregables de esta API.
2. Abrir el documento: [Instrucciones_Instalacion_Ejecucion_ShoppingCart_Indra.pdf](https://github.com/JonEchev/shopping-cart/blob/main/deliverables/Instrucciones_Instalacion_Ejecucion_ShoppingCart_Indra.pdf)
3. Instale Java 17 y un IDE de desarrollo como IntelliJ.
4. Clone este repositorio desde GitHub: https://github.com/JonEchev/shopping-cart rama: main.
5. Valide los datos de configuración de la BD en PostgreSQL mediante el archivo: [application.yaml](https://github.com/JonEchev/shopping-cart/blob/main/src/main/resources/application.yaml)
6. Permita la construcción del proyecto con gradle.
7. Inicialice el proyecto, ejecutando la clase: CarApplication.java
8. La documentación Swagger del backend se encuentra en la siguiente ruta: http://localhost:80/shopping/swagger-ui/index.html

Para ejecutar las pruebas unitarias:
1. Ejecute el proyecto desde test en gradle.

## Endpoints

- **LOCAL**
    - API (POST): http://localhost:80/shopping/api/cart/crear
    - API (POST): http://localhost:80/shopping/api/cart/agregar/:cartId
    - API (GET): http://localhost:80/shopping/api/cart/:cartId
    - API (DELETE): http://localhost:80/shopping/api/cart/eliminar/:cartId/:productId
    - API (PUT): http://localhost:80/shopping/api/cart/actualizar/:cartId
    - API (POST): http://localhost:80/shopping/api/cart/aplicar-cupon/:cartId?codeCoupon=DESC15

## Autor y contacto

- **Nombre**: Jonatan Echeverry
- **Correo electrónico**: jonechev1@gmail.com
- **GitHub**: [@JonEchev](https://github.com/JonEchev)
- **LinkedIn**: [jonatan-echeverry](https://www.linkedin.com/in/jonatan-echeverry-7130251a0/)
- **Blog:** [Crazy Genius!](https://crazycuestionct.blogspot.com/search/label/Programaci%C3%B3n)