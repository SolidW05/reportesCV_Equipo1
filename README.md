# ReporTA — Sistema de Reportes Ciudadanos

ReporTA es una plataforma de reportes ciudadanos orientada a Jalisco, México, que permite a los ciudadanos reportar problemas urbanos como baches, basura acumulada, alumbrado deficiente y daños en infraestructura pública mediante geolocalización en un mapa interactivo.

El sistema está basado en una arquitectura de microservicios utilizando Spring Boot, Astro SSR, Docker y Eureka Service Discovery.

---

# Tabla de Contenido

* Características Principales
* Arquitectura del Sistema
* Tecnologías Utilizadas
* Servicios del Sistema
* Flujo General del Sistema
* Estados de Reporte
* Instalación y Ejecución
* Variables de Entorno
* Endpoints Principales
* Seguridad
* Dockerización
* Video Demonstration
* Equipo de Desarrollo
* Licencia

---

# Características Principales

* Registro y autenticación de usuarios
* Verificación de cuenta vía correo electrónico
* Autenticación con JWT
* Creación de reportes ciudadanos
* Geolocalización mediante Google Maps API
* Subida de imágenes en reportes
* Panel administrativo para autoridades
* Gestión de estados de incidencias
* Arquitectura basada en microservicios
* API Gateway centralizado
* Descubrimiento dinámico de servicios mediante Eureka
* Dockerización completa del sistema

---

# Arquitectura del Sistema

```text
CLIENTE (Browser)
        │
        ▼
Astro SSR Frontend
        │
        ▼
API Gateway
        │
 ┌──────┼──────┐
 ▼      ▼      ▼
User   Report  Location
Svc    Svc     Svc
        │
        ▼
Autoridades Service
```

---

# Tecnologías Utilizadas

## Backend

* Java 21 / 25
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Spring Cloud Gateway
* Spring WebFlux
* Eureka Server
* JWT (jjwt)
* Maven

## Frontend

* Astro SSR
* TypeScript
* Tailwind CSS
* Leaflet.js
* Vite

## Bases de Datos

* PostgreSQL
* MySQL

## Infraestructura y DevOps

* Docker
* Docker Compose
* Multi-stage Dockerfiles

---

# Servicios del Sistema

| Servicio            | Puerto | Responsabilidad                        |
| ------------------- | ------ | -------------------------------------- |
| eureka-server       | 8761   | Registro y descubrimiento de servicios |
| api-gateway         | 8080   | Gateway centralizado                   |
| user-service        | 8081   | Usuarios y autenticación               |
| autoridades-service | 8082   | Gestión de autoridades                 |
| report-service      | 8083   | CRUD de reportes                       |
| location-service    | 8084   | Geocodificación                        |
| reporta-app         | 4321   | Frontend SSR                           |

---

# Instalación y Ejecución

## Requisitos Previos

* Docker Desktop
* Docker Compose
* Git
* API Key de Google Maps
* Cuenta Gmail con App Password

## Clonar el Repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd reporta
```

## Configurar Variables Sensibles

```yaml
GOOGLE_MAPS_API_KEY: YOUR_GOOGLE_MAPS_KEY
SPRING_MAIL_USERNAME: your_email@gmail.com
SPRING_MAIL_PASSWORD: YOUR_APP_PASSWORD
JWT_SECRET: YOUR_SECRET_KEY
```

## Construir y Ejecutar el Proyecto

```bash
docker compose up --build
```

## Acceso a la Aplicación

Frontend:

```txt
http://localhost:4321
```

Eureka Dashboard:

```txt
http://localhost:8761
```

---

# Seguridad

El sistema utiliza JWT (JSON Web Token) para autenticación stateless.

* Tokens firmados con HS256
* Expiración de 8 horas
* Validación distribuida entre servicios
* Contraseñas protegidas con BCrypt

---

# Video Demonstration

El proyecto cuenta con un video demostrativo donde se explica:

* Arquitectura del sistema
* Funcionamiento de los microservicios
* Flujo de autenticación
* Creación de reportes
* Panel administrativo
* Integración con Google Maps
* Comunicación entre servicios
* Despliegue utilizando Docker

## Ver Video

```txt
https://www.youtube.com/watch?v=7COMwgt5SJY
```

---

# Equipo de Desarrollo

* Fernando Corona Preciado
* Jesús Axel García de la Cruz
* Carlos Samuel Fierros García
* Juan Ramón Rodríguez Armas

Centro Universitario de Tonalá — 2026

---

# Licencia

Proyecto académico desarrollado para el Centro Universitario de Tonalá.
