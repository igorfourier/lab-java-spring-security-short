# ⚡ INICIO RÁPIDO

## Requisitos Previos
1. **Java 17+** instalado
2. **Maven** instalado
3. **MySQL** corriendo en tu máquina

## Pasos para Ejecutar

### Opción 1: Script Automático (Recomendado)
```bash
./run.sh
```

### Opción 2: Manual

1. **Crear la base de datos:**
```bash
mysql -u root -p
CREATE DATABASE blog_db;
exit;
```

2. **Configurar credenciales** (si son diferentes):
Editar `src/main/resources/application.properties`

3. **Ejecutar la aplicación:**
```bash
mvn clean install
mvn spring-boot:run
```

## Probar la Aplicación

### 1. Endpoint Público (sin autenticación)
```bash
curl http://localhost:8080/api/posts
```

### 2. Endpoint Protegido (requiere admin)
```bash
curl -X POST http://localhost:8080/api/authors \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{"name": "Nuevo Autor"}'
```

## Credenciales de Admin
```
Usuario: admin
Contraseña: admin123
```

## Endpoints Principales

| Método | Endpoint | Acceso |
|--------|----------|--------|
| GET | `/api/posts` | Público |
| GET | `/api/authors` | Público |
| POST | `/api/posts` | Admin |
| POST | `/api/authors` | Admin |
| PUT | `/api/posts/{id}` | Admin |
| PUT | `/api/authors/{id}` | Admin |
| DELETE | `/api/posts/{id}` | Admin |
| DELETE | `/api/authors/{id}` | Admin |

## Ver Más Ejemplos
- **Documentación completa:** `USAGE.md`
- **Ejemplos de API:** `api-examples.http`

## Datos de Prueba
Al iniciar, se cargan automáticamente 3 autores y 3 posts de ejemplo.

¡Listo para usar! 🚀

