# BuscaPrecio API

API REST desarrollada con Spring Boot para la gestión de comercios, usuarios y ofertas. Permite a los usuarios registrar comercios, gestionar ofertas y consultar precios.

## 📋 Requisitos Previos

### Dependencias del Sistema

| Herramienta | Versión Mínima | Descripción |
|-------------|----------------|-------------|
| **Java JDK** | 17+ | Entorno de ejecución de Java |
| **Maven** | 3.8+ | Gestión de dependencias y construcción |
| **MySQL** | 8.0+ | Base de datos relacional |
| **Git** | 2.30+ | Control de versiones |

### Dependencias del Proyecto

- **Spring Boot** 3.3.2
- **Spring Data JPA** - Persistencia de datos
- **Spring Validation** - Validación de DTOs
- **Flyway** - Migraciones de base de datos
- **Lombok** - Reducción de código boilerplate
- **MySQL Connector** - Driver de conexión a MySQL

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/BuscaPrecioApi.git
cd BuscaPrecioApi
```

### 2. Configurar Variables de Entorno

Configura las siguientes variables de entorno para la conexión a la base de datos:

**Windows (PowerShell):**
```powershell
$env:BPRECIO_DB_URL = "jdbc:mysql://localhost:3306/buscaprecio"
$env:DB_USERNAME = "tu_usuario"
$env:DB_PASS = "tu_contraseña"
```

**Linux/macOS:**
```bash
export BPRECIO_DB_URL="jdbc:mysql://localhost:3306/buscaprecio"
export DB_USERNAME="tu_usuario"
export DB_PASS="tu_contraseña"
```

### 3. Crear la Base de Datos

```sql
CREATE DATABASE buscaprecio;
```

> **Nota:** Las tablas se crean automáticamente mediante migraciones de Flyway al iniciar la aplicación.

### 4. Compilar y Ejecutar

```bash
# Compilar el proyecto
./mvnw clean install

# Ejecutar la aplicación
./mvnw spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## 📡 Endpoints

### Usuarios (`/user`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/user/new` | Crear nuevo usuario |
| `GET` | `/user/{id}` | Obtener usuario por ID |
| `GET` | `/user` | Listar usuarios (paginado) |
| `PUT` | `/user` | Modificar usuario |
| `DELETE` | `/user/{id}` | Eliminar usuario |

#### Ejemplos de Request

**Crear Usuario:**
```json
POST /user/new
{
  "nombre": "Juan Pérez",
  "email": "juan@email.com",
  "password": "contraseña123",
  "rol": "ADMIN"
}
```

**Modificar Usuario:**
```json
PUT /user
{
  "id": 1,
  "nombre": "Juan Actualizado",
  "email": "juan.nuevo@email.com",
  "password": "nuevaContraseña",
  "rol": "USER"
}
```

### Comercios (`/comercio`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/comercio/new` | Registrar nuevo comercio |
| `GET` | `/comercio/{id}` | Obtener comercio por ID |
| `GET` | `/comercio/all` | Listar comercios (paginado) |
| `DELETE` | `/comercio/{id}` | Eliminar comercio |

#### Ejemplos de Request

**Crear Comercio:**
```json
POST /comercio/new
{
  "nombre": "Supermercado El Sol",
  "direccion": {
    "calle": "Av. Principal 123",
    "barrio": "Centro",
    "provincia": "Buenos Aires"
  },
  "encargado": {
    "id": 1
  }
}
```

## ✨ Características Principales

- **Gestión de Usuarios:** CRUD completo con validaciones
- **Gestión de Comercios:** Registro y administración de comercios con direcciones
- **Sistema de Ofertas:** Vinculación de ofertas a comercios
- **Paginación:** Soporte de paginación en listados
- **Validaciones:** Validación de datos de entrada con Jakarta Validation
- **Migraciones Automáticas:** Control de versiones de la base de datos con Flyway
- **Manejo de Excepciones:** Handler global de excepciones personalizado

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas (Layered Architecture) basada en el patrón MVC:

```
src/main/java/com/buscaprecio/api/
├── ApiApplication.java          # Clase principal
├── controladores/               # Capa de Presentación (Controllers)
│   ├── ComercioController.java
│   └── UserController.java
├── servicios/                   # Capa de Negocio (Services)
│   ├── ComercioService.java
│   └── UserService.java
├── repositorio/                 # Capa de Persistencia (Repositories)
│   ├── ComercioRepository.java
│   └── UserRepository.java
├── modelo/                      # Entidades y DTOs
│   ├── comercio/
│   │   ├── Comercio.java
│   │   ├── DatosRegistrarComercio.java
│   │   ├── DatosRespuestaComercio.java
│   │   └── DatosListadoComercios.java
│   ├── user/
│   │   ├── User.java
│   │   ├── DatosCrearUsuario.java
│   │   ├── DatosModificarUsuario.java
│   │   └── DatosRespuestaUsuario.java
│   ├── oferta/
│   │   └── Oferta.java
│   └── direccion/
│       └── Direccion.java
└── excepciones/                 # Manejo de Excepciones
    └── ExceptionHandlerGlobal.java
```

### Modelo de Datos

```
┌─────────────┐       ┌─────────────┐       ┌─────────────┐
│   Usuario   │1─────*│  Comercio   │1─────*│   Oferta    │
├─────────────┤       ├─────────────┤       ├─────────────┤
│ id          │       │ id          │       │ id          │
│ nombre      │       │ nombre      │       │ descripcion │
│ email       │       │ abonado     │       │ precio      │
│ password    │       │ fecha_abono │       │ comercio_id │
│ rol         │       │ usuario_id  │       └─────────────┘
└─────────────┘       │ direccion_id│
                      └──────┬──────┘
                             │1
                             │
                             │1
                      ┌──────┴──────┐
                      │  Direccion  │
                      ├─────────────┤
                      │ id          │
                      │ calle       │
                      │ barrio      │
                      │ provincia   │
                      └─────────────┘
```

### Flujo de Datos

1. **Controller:** Recibe las peticiones HTTP y valida los datos de entrada
2. **Service:** Contiene la lógica de negocio
3. **Repository:** Interactúa con la base de datos mediante Spring Data JPA
4. **Model:** Define las entidades y DTOs para transferencia de datos

## 🧪 Testing

El proyecto incluye dependencias para testing:

```bash
# Ejecutar todos los tests
./mvnw test
```

- **JUnit 5** - Framework de testing
- **Mockito** - Mocking de dependencias
- **REST Assured** - Testing de endpoints REST

## 📝 Migraciones de Base de Datos

Las migraciones se encuentran en `src/main/resources/db/migration/`:

| Archivo | Descripción |
|---------|-------------|
| `V1__crearTablaUsers.sql` | Creación de tabla usuarios |
| `V2__crearTablaComercios.sql` | Creación de tabla comercios |
| `V3__crearTablaOfertas.sql` | Creación de tabla ofertas |
| `V4__crearTablaDireccion.sql` | Creación de tabla direcciones |
| `V5__add-foreignKeyComercio.sql` | FK comercio-usuario |
| `V6__addForeignKeyComercioDireccion.sql` | FK comercio-direccion |

## 📄 Licencia

Este proyecto está bajo desarrollo.

---

Desarrollado con ❤️ usando Spring Boot

