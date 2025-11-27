# 🚚 Plataforma de Gestión de Envíos y Recolección de Encomiendas

**Proyecto Académico** | Patrones de Diseño de Software (2025-2)

[![Java](https://img.shields.io/badge/Language-Java-007396?style=for-the-badge&logo=java)](https://www.java.com/)
[![Patrones de Diseño](https://img.shields.io/badge/Design_Patterns-Applied-795548?style=for-the-badge&logo=abstract)](https://en.wikipedia.org/wiki/Software_design_pattern)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

---

## 📘 Descripción General del Proyecto

Este proyecto implementa un **modelo digital de gestión logística de encomiendas**, inspirado en el flujo operativo de empresas reales como Servientrega, Coordinadora o JDG Cargo.

El objetivo principal es modelar el **flujo logístico completo**, desde que un **Remitente** solicita la recolección de un paquete hasta su **clasificación** en un *hub* logístico, aplicando **buenas prácticas de diseño** y **Patrones de Diseño Orientados a Objetos (POO)** para construir un sistema extensible y mantenible.

### Funcionalidades Clave

* Solicitar **recolecciones a domicilio**.
* Registro de **remitente, destinatario** y datos del envío.
* **Cálculo de tarifas** según reglas de negocio.
* Procesamiento de **pagos**.
* **Clasificación** de encomiendas en el hub logístico (prioridad, ruta/vehículo, tanda de salida).
* **Persistencia** de datos mediante el patrón **DAO**.
* Modelos de sistema **extensibles y mantenibles**.

### Contenido del Repositorio

| Elemento | Descripción | Estado |
| :--- | :--- | :--- |
| **Casos de Uso** | Diagrama UML + especificaciones detalladas. | ✔️ Incluido |
| **Diagramas de Secuencia** | Generados automáticamente a partir del código Java. | ✔️ Incluido |
| **Implementación** | Código fuente con aplicación de Patrones de Diseño. | ✔️ Incluido |
| **Documentación** | Detalle del diseño y las decisiones de arquitectura. | ✔️ Incluido |

---

## 🧩 Actores Principales del Sistema

El sistema interactúa con diversos roles que representan el flujo logístico real:

* **Remitente:** El cliente que inicia el proceso al solicitar la recolección de un paquete.
* **Empleado de Clasificación (Hub):** El personal encargado de procesar y clasificar físicamente las encomiendas recién llegadas al centro logístico.
* **Recolector:** El encargado de recoger el paquete en el domicilio (rol no central en esta versión).
* **Servicios Externos:** Módulos de integración para **Pago**, **Geolocalización** y **Cálculo de Tarifas**.
* **Administrador (Opcional):** Rol de supervisión y manejo de procesos especiales.

---

## 🎯 Casos de Uso Seleccionados

El proyecto se enfoca en implementar los dos casos de uso más representativos del ciclo logístico profesional:

### **UC-001: Solicitar Recolección de Encomienda**

Representa el **inicio del ciclo logístico** (equivalente a "Recogida a Domicilio" en empresas reales).

| Funcionalidad | Descripción |
| :--- | :--- |
| **Registro** | Validación de datos del remitente y destinatario. |
| **Tarifas y Pago** | Cálculo de tarifas y procesamiento del pago asociado. |
| **Cierre** | Registro de la solicitud y emisión del comprobante al remitente. |

### **UC-002: Clasificar Encomienda en el Hub Logístico**

Refleja la **operación interna** del centro de distribución, fundamental para la eficiencia y trazabilidad.

| Proceso | Descripción |
| :--- | :--- |
| **Identificación** | Escaneo de etiqueta/ID y recuperación de la información del envío. |
| **Priorización** | Cálculo de prioridad (**urgente**, frágil, alto valor, etc.). |
| **Agrupamiento** | Procesos de agrupación de paquetes por destino (tanda de salida). |
| **Optimización** | Sugerencia de ruta y vehículo mediante lógica de optimización. |

---

## 🛠️ Patrones de Diseño Utilizados

Se incorporaron 4 patrones de diseño clave para optimizar la arquitectura, la modularidad y la extensibilidad del sistema:

### 🟦 1. **DAO (Data Access Object)**

* **Propósito:** **Desacoplar** la lógica de negocio de la implementación de la persistencia de datos.
* **Aplicación:** Utilizado para manejar la persistencia de **Solicitudes**, **Encomiendas**, **Tandas de Salida** y **Auditorías**.
* **Beneficio:** Permite cambiar la tecnología de almacenamiento (BD relacional, NoSQL, archivos, etc.) sin impactar el *core* del negocio.

### 🟨 2. **Template Method**

* **Propósito:** Definir el **esqueleto** de un algoritmo en una operación, dejando que las subclases redefinan ciertos pasos sin cambiar la estructura del algoritmo.
* **Aplicación:** Modelado de **flujos estándar** como `ProcesarSolicitud` o `ClasificarEncomienda`, donde los pasos varían según las reglas específicas (ej. manejo de paquetes frágiles vs. estándar).

### 🟧 3. **Decorator**

* **Propósito:** Añadir dinámicamente **responsabilidades adicionales** a un objeto sin modificar su clase base.
* **Aplicación:** Extender las características de la `Encomienda` base con servicios adicionales (**frágil**, **seguro adicional**, **envío urgente**, etc.).
* **Beneficio:** Facilita la adición de nuevos atributos o servicios logísticos al servicio base de manera flexible.

### 🟩 4. **Proxy**

* **Propósito:** Proporcionar un **sustituto o intermediario** para otro objeto, controlando el acceso a él.
* **Aplicación:** Encapsular y controlar las llamadas a **Servicios Externos** costosos o de alto tráfico.
* **Uso Específico:** Implementación de **caché** para evitar el uso excesivo de APIs de rutas/geolocalización o para simular servicios durante las pruebas.

---

### src/main/java/com/logistica/encomiendas
### │
### ├── controller/
### │     ├── SolicitudRecoleccionController.java
### │     └── ClasificacionController.java
### │
### ├── service/
### │     ├── SolicitudRecoleccionService.java
### │     ├── ClasificacionService.java
### │     ├── TarifaService.java
### │     ├── PrioridadService.java
### │     ├── NotificacionService.java
### │     ├── RutaServiceProxy.java        <-- Patrón Proxy
### │     └── template/
### │         ├── ProcesoEncomiendaTemplate.java     <-- Patrón Template
### │         ├── ProcesoSolicitud.java
### │         └── ProcesoClasificacion.java
### │
### ├── decorator/
### │     ├── EncomiendaBase.java
### │     ├── EncomiendaDecorator.java
### │     ├── EncomiendaUrgente.java
### │     ├── EncomiendaFragil.java
### │     └── EncomiendaSeguroAdicional.java
### │
### ├── repository/
### │     ├── SolicitudRepository.java   <-- Extiende JpaRepository (DAO)
### │     └── EncomiendaRepository.java  <-- También es DAO
### │
### ├── entity/
### │     ├── Solicitud.java
### │     ├── Encomienda.java
### │     ├── Direccion.java
### │     ├── Usuario.java
### │     ├── HistorialEstado.java
### │     └── Ruta.java
### │
### ├── dto/
### │     ├── SolicitudRecoleccionRequest.java
### │     ├── SolicitudRecoleccionResponse.java
### │     └── ClasificacionResponse.java
### │
### ├── config/
### │     ├── SwaggerConfig.java
### │     ├── SecurityConfig.java (opcional)
### │     └── ModelMapperConfig.java

## 🚀 Cómo Empezar

1.  **Clonar el repositorio:** `git clone [URL-DEL-REPOSITORIO]`
2.  **Configuración:** Asegúrate de tener **Java JDK 17+** instalado.
3.  **Ejecución:** Abrir el proyecto en **IntelliJ IDEA** o **Eclipse** e iniciar la clase principal.