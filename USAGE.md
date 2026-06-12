# Blog Security - Spring Boot Application

## Descripción
Sistema de gestión de contenido para un blog con múltiples autores utilizando Spring Security.

## Características
- ✅ Entidades: BlogPost y Author
- ✅ Spring Security con autenticación
- ✅ Rol de ADMIN
- ✅ Endpoints REST para gestionar posts y autores
- ✅ GET público, otros endpoints protegidos (solo ADMIN)

## Requisitos
- Java 17+
- MySQL 8.0+
- Maven 3.6+

## Configuración de Base de Datos

1. Crear base de datos MySQL:
```sql
CREATE DATABASE blog_db;
```

2. Configurar credenciales en `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=root
```

## Instalación y Ejecución

1. Clonar el repositorio
2. Navegar al directorio del proyecto
3. Ejecutar:
```bash
mvn clean install
mvn spring-boot:run
```

La aplicación se ejecutará en: `http://localhost:8080`

## Credenciales de Admin

Para acceder a endpoints protegidos:
- **Usuario:** admin
- **Contraseña:** admin123

## Endpoints API

### Authors (Autores)

#### GET - Público (sin autenticación)
```bash
# Obtener todos los autores
GET http://localhost:8080/api/authors

# Obtener autor por ID
GET http://localhost:8080/api/authors/1
```

#### POST - Solo ADMIN
```bash
# Crear nuevo autor
POST http://localhost:8080/api/authors
Authorization: Basic admin:admin123
Content-Type: application/json

{
  "name": "Nuevo Autor"
}
```

#### PUT - Solo ADMIN
```bash
# Actualizar autor
PUT http://localhost:8080/api/authors/1
Authorization: Basic admin:admin123
Content-Type: application/json

{
  "name": "Nombre Actualizado"
}
```

#### DELETE - Solo ADMIN
```bash
# Eliminar autor
DELETE http://localhost:8080/api/authors/1
Authorization: Basic admin:admin123
```

### Blog Posts

#### GET - Público (sin autenticación)
```bash
# Obtener todos los posts
GET http://localhost:8080/api/posts

# Obtener post por ID
GET http://localhost:8080/api/posts/1
```

#### POST - Solo ADMIN
```bash
# Crear nuevo post
POST http://localhost:8080/api/posts
Authorization: Basic admin:admin123
Content-Type: application/json

{
  "author": {
    "id": 1
  },
  "title": "Título del Post",
  "post": "Contenido del post..."
}
```

#### PUT - Solo ADMIN
```bash
# Actualizar post
PUT http://localhost:8080/api/posts/1
Authorization: Basic admin:admin123
Content-Type: application/json

{
  "author": {
    "id": 1
  },
  "title": "Título Actualizado",
  "post": "Contenido actualizado..."
}
```

#### DELETE - Solo ADMIN
```bash
# Eliminar post
DELETE http://localhost:8080/api/posts/1
Authorization: Basic admin:admin123
```

## Datos de Prueba

Al iniciar la aplicación, se cargan automáticamente datos de ejemplo:

**Autores:**
- Aiko Tanaka
- Jonas Schmidt
- Cas Van Dijk

**Posts:**
- "Boost Your Productivity with 10 Easy Tips" (Aiko Tanaka)
- "How to Focus" (Aiko Tanaka)
- "Learn to Speed Read in 30 Days" (Jonas Schmidt)

## Ejemplos con cURL

### Público - GET sin autenticación
```bash
curl http://localhost:8080/api/posts
curl http://localhost:8080/api/authors
```

### Admin - POST/PUT/DELETE con autenticación
```bash
# Crear autor
curl -X POST http://localhost:8080/api/authors \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{"name": "New Author"}'

# Crear post
curl -X POST http://localhost:8080/api/posts \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "author": {"id": 1},
    "title": "My New Post",
    "post": "This is the content..."
  }'

# Actualizar post
curl -X PUT http://localhost:8080/api/posts/1 \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "author": {"id": 1},
    "title": "Updated Title",
    "post": "Updated content..."
  }'

# Eliminar post
curl -X DELETE http://localhost:8080/api/posts/1 \
  -u admin:admin123
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/ironhack/blog/
│   │   ├── BlogApplication.java
│   │   ├── DataLoader.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthorController.java
│   │   │   └── BlogPostController.java
│   │   ├── model/
│   │   │   ├── Author.java
│   │   │   └── BlogPost.java
│   │   └── repository/
│   │       ├── AuthorRepository.java
│   │       └── BlogPostRepository.java
│   └── resources/
│       └── application.properties
└── test/
```

## Tecnologías Utilizadas

- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- Maven
- Bean Validation

## Seguridad

- **Spring Security** implementado con autenticación HTTP Basic
- **Role-Based Access Control (RBAC)**: Solo usuarios con rol ADMIN pueden modificar datos
- **Endpoints GET**: Acceso público sin autenticación
- **Endpoints POST/PUT/DELETE**: Requieren autenticación y rol ADMIN

## Notas

- El proyecto usa H2 auto-create para facilitar el desarrollo
- CSRF está deshabilitado para facilitar las pruebas con herramientas como Postman o cURL
- Las contraseñas están encriptadas con BCrypt

