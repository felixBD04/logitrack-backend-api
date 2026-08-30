<div align="center">
  
# 📦 LogiTrack API 
**Enterprise-Grade Logistics & Inventory Management Backend**

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JWT](https://img.shields.io/badge/JWT-Security-000000?style=for-the-badge&logo=JSON%20web%20tokens)](https://jwt.io/)

*LogiTrack es una plataforma backend de alto rendimiento diseñada para orquestar operaciones logísticas, control de bodegas y auditoría de inventarios en tiempo real, garantizando la integridad de los datos mediante una arquitectura segura y escalable.*

</div>

---

## 🚀 Arquitectura y Tecnologías

El núcleo del sistema está construido sobre **Java 17** y **Spring Boot 3.3.0**, utilizando un patrón de diseño multicapa (Controller-Service-Repository) para separar responsabilidades y facilitar la escalabilidad.

| Capa / Componente | Tecnología Implementada | Propósito |
| :--- | :--- | :--- |
| **Core Framework** | Spring Boot Web | Enrutamiento RESTful y orquestación. |
| **Persistencia** | Spring Data JPA / Hibernate | Mapeo Objeto-Relacional (ORM) y persistencia segura. |
| **Base de Datos** | MySQL | Almacenamiento relacional transaccional. |
| **Seguridad** | Spring Security + JWT | Autenticación *stateless* y protección estricta de rutas. |
| **Validación** | Jakarta Validation | Reglas de negocio rígidas en la capa de modelo (DTOs). |
| **Documentación** | Swagger / OpenAPI 3.0 | Interfaz interactiva y autogenerada de la API. |

---

## ✨ Características Principales

- 🛡️ **Seguridad Stateless (JWT):** Sistema de autenticación robusto. Las rutas están protegidas mediante *JSON Web Tokens*, limitando el acceso solo a personal autorizado (Roles de ADMIN y EMPLEADO).
- 🗼 **Torre de Control de Errores (Global Exception Handler):** Uso avanzado de `@ControllerAdvice` para interceptar anomalías en toda la aplicación. Centraliza los errores y devuelve un formato JSON estandarizado (400, 404, 500) evitando la exposición de las trazas del servidor al cliente.
- 👁️ **Auditoría Silenciosa Automatizada:** Implementación de `EntityListeners` (`@PostPersist`, `@PreRemove`) para registrar automáticamente quién, cuándo y qué acción se realizó sobre el inventario, generando un historial inmutable.
- 🔍 **Consultas JPA Optimizadas:** Uso de *Query Methods* para ejecutar lógicas complejas de negocio (ej. Detección de stock crítico `< 10` y filtros de movimientos por línea de tiempo `LocalDateTime`).
- 🎨 **Dashboard Integrado:** Incluye un cliente frontend minimalista (Vanilla JS/HTML/CSS) desplegado desde el propio servidor estático de Spring Boot.

---

## 📂 Estructura del Proyecto

```text
logitrack-backend-api/
├── src/main/java/com/logitrack/backend/
│   ├── config/        # Configuraciones globales y Beans (Swagger, Utilidades)
│   ├── controller/    # Endpoints REST (Auth, Productos, Bodegas, Movimientos)
│   ├── exception/     # Manejo global de excepciones (Torre de Control)
│   ├── listener/      # Interceptores de eventos JPA (Auditoría)
│   ├── model/         # Entidades de la Base de Datos
│   ├── repository/    # Interfaces de Spring Data JPA
│   ├── security/      # Filtros JWT y configuraciones de Spring Security
│   └── service/       # Lógica central del negocio
├── src/main/resources/
│   ├── static/        # Frontend minimalista integrado (index.html)
│   ├── application.properties # Variables de entorno y DB
│   ├── schema.sql     # DDL de la arquitectura de tablas
│   └── data.sql       # DML de inyección de datos de prueba
└── pom.xml            # Gestión de dependencias de Maven
