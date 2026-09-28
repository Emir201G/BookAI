# ✂️ BookAi

### Sistema inteligente de gestión de turnos para barberías mediante WhatsApp

BookAi es un sistema backend desarrollado con **Java y Spring Boot** que permite gestionar turnos, clientes, barberos, servicios y horarios de una barbería mediante **WhatsApp**, utilizando inteligencia artificial para interpretar las solicitudes de los clientes y ejecutar operaciones sobre el sistema.

El proyecto está diseñado siguiendo una **arquitectura hexagonal (Ports & Adapters)**, buscando mantener separada la lógica de negocio de los detalles de infraestructura y de los servicios externos.

> 💡 **BookAi no utiliza un frontend web tradicional.**
> La interacción con el cliente está pensada principalmente a través de **WhatsApp**, mientras que el backend centraliza la lógica de negocio, persistencia, IA y comunicación con Meta.

---

## 📌 Características principales

* 💬 Atención de clientes mediante WhatsApp.
* 🤖 Integración con modelos de Inteligencia Artificial.
* 📅 Gestión de turnos.
* 👤 Gestión de clientes.
* 💈 Gestión de barberos.
* ✂️ Gestión de tratamientos/servicios.
* 🕐 Gestión de horarios laborales.
* 📆 Gestión de días libres.
* 🔄 Gestión de excepciones de horarios.
* 💳 Estructura preparada para integración de pagos.
* 🗄️ Persistencia con MySQL.
* 🧩 Arquitectura hexagonal.
* 🔌 Integración mediante API REST.
* 🛠️ Tools para permitir que la IA consulte información real del sistema.
* 🔐 Separación entre dominio, aplicación e infraestructura.

---

# 🎯 Objetivo

El objetivo de BookAi es construir un asistente inteligente capaz de actuar como punto de entrada para la gestión de una barbería.

Por ejemplo, un cliente podría enviar por WhatsApp:

> "Hola, quiero cortarme el pelo mañana por la tarde."

El sistema debe ser capaz de:

1. Recibir el mensaje desde WhatsApp.
2. Identificar al cliente.
3. Mantener el contexto de la conversación.
4. Interpretar la intención mediante IA.
5. Consultar la información real del sistema.
6. Verificar disponibilidad.
7. Solicitar los datos que falten.
8. Proponer horarios disponibles.
9. Confirmar la operación con el usuario.
10. Registrar el turno en la base de datos.
11. Responder nuevamente mediante WhatsApp.

La IA funciona como una **capa de interpretación**, mientras que las reglas reales del negocio permanecen dentro del backend.

---

# 🏗️ Arquitectura

BookAi utiliza una arquitectura basada en **Hexagonal Architecture (Ports & Adapters)**.

La idea principal es que el dominio no dependa directamente de:

* MySQL
* JPA
* Spring
* WhatsApp
* Meta
* proveedores de IA
* APIs externas

En lugar de eso, el dominio define **puertos**, y las implementaciones concretas viven en infraestructura.

```text
                    ┌──────────────────────┐
                    │      WhatsApp        │
                    │   Meta Cloud API     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   WhatsApp Adapter   │
                    └──────────┬───────────┘
                               │
                               ▼
              ┌────────────────────────────────┐
              │          APPLICATION           │
              │                                │
              │  Use Cases / Services          │
              │                                │
              └───────────────┬────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │             DOMAIN             │
              │                                │
              │ Entities / Ports / Rules       │
              │                                │
              └───────────────┬────────────────┘
                              │
                 ┌────────────┴────────────┐
                 ▼                         ▼
        ┌──────────────────┐      ┌──────────────────┐
        │ Persistence      │      │ AI Adapter       │
        │ Adapter          │      │                  │
        │                  │      │ Spring AI       │
        │ JPA / MySQL      │      │ LLM / Tools      │
        └──────────────────┘      └──────────────────┘
```

---

# 📐 Arquitectura de alto nivel

```text
                         ┌───────────────────┐
                         │     CUSTOMER      │
                         │                   │
                         │    WhatsApp       │
                         └─────────┬─────────┘
                                   │
                                   ▼
                         ┌───────────────────┐
                         │   Meta WhatsApp   │
                         │     Cloud API     │
                         └─────────┬─────────┘
                                   │
                                   ▼
                         ┌───────────────────┐
                         │ WhatsApp Adapter  │
                         └─────────┬─────────┘
                                   │
                                   ▼
                    ┌──────────────────────────┐
                    │       BookAi Backend     │
                    │                          │
                    │ ┌──────────────────────┐ │
                    │ │ Conversation         │ │
                    │ │ Management           │ │
                    │ └──────────┬───────────┘ │
                    │            │             │
                    │            ▼             │
                    │ ┌──────────────────────┐ │
                    │ │ AI / Spring AI       │ │
                    │ └──────────┬───────────┘ │
                    │            │             │
                    │            ▼             │
                    │ ┌──────────────────────┐ │
                    │ │ Application Layer    │ │
                    │ └──────────┬───────────┘ │
                    │            │             │
                    │            ▼             │
                    │ ┌──────────────────────┐ │
                    │ │ Domain Layer         │ │
                    │ └──────────┬───────────┘ │
                    │            │             │
                    └────────────┼─────────────┘
                                 │
                                 ▼
                         ┌───────────────────┐
                         │      MySQL        │
                         └───────────────────┘
```

---

# 🧩 Arquitectura Hexagonal

El proyecto está organizado por funcionalidades y separa claramente las responsabilidades.

Una estructura simplificada es:

```text
com.app.bookai
│
├── ai
│   ├── application
│   ├── domain
│   └── infrastructure
│
├── appointment
│   ├── application
│   ├── domain
│   └── infrastructure
│
├── barber
│   ├── application
│   ├── domain
│   └── infrastructure
│
├── customer
│   ├── application
│   ├── domain
│   └── infrastructure
│
├── payment
│   ├── application
│   ├── domain
│   └── infrastructure
│
├── whatsapp
│   ├── application
│   ├── domain
│   └── infrastructure
│
└── BookAiApplication.java
```

Cada módulo puede contener:

```text
domain
├── model
├── port
│   ├── in
│   └── out
└── exception

application
├── service
└── dto

infrastructure
├── adapter
├── controller
├── repository
├── mapper
└── config
```

---

# 🧠 Inteligencia Artificial

BookAi utiliza **Spring AI** para integrar modelos de lenguaje.

La IA no tiene acceso directo a la base de datos.

En su lugar, se utilizan **Tools** que funcionan como una interfaz controlada entre el modelo y el backend.

Por ejemplo:

```text
                    ┌───────────────────┐
                    │       LLM         │
                    │                   │
                    │ "¿Qué servicios   │
                    │  existen?"        │
                    └─────────┬─────────┘
                              │
                              ▼
                    ┌───────────────────┐
                    │ TreatmentTool     │
                    └─────────┬─────────┘
                              │
                              ▼
                    ┌───────────────────┐
                    │ Use Case          │
                    └─────────┬─────────┘
                              │
                              ▼
                    ┌───────────────────┐
                    │ Repository        │
                    └─────────┬─────────┘
                              │
                              ▼
                         ┌─────────┐
                         │ MySQL   │
                         └─────────┘
```

Actualmente se utilizan herramientas relacionadas con:

* Tratamientos.
* Barberos.
* Horarios de trabajo.

Esto permite que la IA trabaje con información real del sistema en lugar de inventar datos.

---

# 🤖 Principio importante de la IA

La IA **no debe inventar información relacionada con el negocio**.

Por ejemplo, si un cliente pregunta:

> "¿Cuánto cuesta el corte?"

La IA debe consultar los tratamientos registrados.

No debería responder utilizando un precio inventado.

De la misma forma, si pregunta:

> "¿Qué horarios tiene Juan?"

La información debe obtenerse mediante las herramientas del backend.

---

# 💬 Flujo de una conversación

Ejemplo simplificado:

```text
Cliente
   │
   │ "Hola"
   ▼
WhatsApp
   │
   ▼
Webhook
   │
   ▼
Conversation Service
   │
   ▼
AIChatAdapter
   │
   ▼
LLM
   │
   ├──────► TreatmentTool
   │
   ├──────► BarberTool
   │
   └──────► Appointment Use Cases
   │
   ▼
Respuesta
   │
   ▼
WhatsApp
   │
   ▼
Cliente
```

---

# 🗓️ Gestión de disponibilidad

Uno de los componentes importantes de BookAi es el sistema de disponibilidad.

La disponibilidad de un barbero puede depender de:

* Horarios semanales.
* Días libres.
* Excepciones de horarios.
* Fecha solicitada.
* Duración del servicio.
* Turnos existentes.

La lógica sigue una estructura similar a:

```text
                    Fecha solicitada
                           │
                           ▼
                  ┌─────────────────┐
                  │ ¿Existe         │
                  │ excepción?      │
                  └───────┬─────────┘
                          │
                 ┌────────┴────────┐
                 │                 │
                Sí                 No
                 │                 │
                 ▼                 ▼
          Usar override      Horario semanal
                 │                 │
                 └────────┬────────┘
                          ▼
                  Verificar día libre
                          │
                          ▼
                   Buscar turnos
                          │
                          ▼
                  Calcular espacios
                          │
                          ▼
                    Disponibilidad
```

---

# 👤 Entidades principales

Entre las principales entidades del dominio se encuentran:

```text
Customer
   │
   └── RoleType.CUSTOMER


Barber
   │
   ├── RoleType.WORKER
   ├── WorkingHour
   └── DayOff


Treatment
   │
   ├── name
   ├── price
   ├── durationMinutes
   └── isActive


Appointment
   │
   ├── Customer
   ├── Barber
   ├── Treatments
   ├── startTime
   ├── endTime
   └── status
```

---

# 📊 Diagrama de clases

Los diagramas del proyecto se almacenan dentro de:

```text
docs/
└── diagrams/
    ├── class-diagram.png
    ├── domain-model.png
    ├── appointment-flow.png
    ├── architecture.png
    └── database-schema.png
```

Cuando estén agregados al repositorio pueden mostrarse directamente en GitHub:

```markdown
## Diagrama de clases

![Diagrama de clases](docs/diagrams/class-diagram.png)
```

---

# 🗄️ Modelo de datos

BookAi utiliza **MySQL** como sistema de persistencia.

Modelo conceptual simplificado:

```text
┌──────────────┐
│   CUSTOMER   │
├──────────────┤
│ id           │
│ name         │
│ phoneNumber  │
│ role         │
└──────┬───────┘
       │
       │
       ▼
┌──────────────┐
│ APPOINTMENT  │
├──────────────┤
│ id           │
│ customer_id  │
│ barber_id    │
│ status       │
│ start_time   │
│ end_time     │
└──────┬───────┘
       │
       │
       ▼
┌──────────────┐
│    BARBER    │
├──────────────┤
│ id           │
│ name         │
│ phoneNumber  │
│ role         │
│ isActive     │
└──────┬───────┘
       │
       ├────────────────────┐
       ▼                    ▼
┌──────────────┐     ┌──────────────┐
│ WORKING_HOUR │     │   DAY_OFF    │
├──────────────┤     ├──────────────┤
│ id           │     │ id           │
│ barber_id    │     │ barber_id    │
│ dayOfWeek    │     │ date         │
│ startTime    │     └──────────────┘
│ endTime      │
└──────────────┘
```

---

# 🧾 Snapshot de tratamientos

Cuando un turno utiliza tratamientos, la información relevante del servicio debe conservarse asociada al turno.

Esto permite evitar que cambios posteriores en el catálogo alteren el historial de un turno existente.

Por ejemplo:

```text
Treatment
──────────────
name
price
duration


             │
             │ momento de reservar
             ▼

AppointmentTreatment
────────────────────
treatmentName
price
durationMinutes
```

Esto permite conservar el estado relevante del servicio en el momento de la reserva.

---

# 🔄 Flujo de creación de un turno

```text
Cliente
   │
   │ Solicita turno
   ▼
WhatsApp
   │
   ▼
Webhook
   │
   ▼
AI
   │
   ▼
Identificar:
   ├── Servicio
   ├── Barbero
   ├── Fecha
   └── Horario
   │
   ▼
Availability Service
   │
   ▼
¿Horario disponible?
   │
   ├── No ──────► Proponer alternativas
   │
   └── Sí
         │
         ▼
   Solicitar confirmación
         │
         ▼
      Confirmado
         │
         ▼
   CreateAppointmentUseCase
         │
         ▼
       MySQL
         │
         ▼
   WhatsApp Response
```

---

# 📱 Integración con WhatsApp

BookAi utiliza la **WhatsApp Cloud API de Meta**.

La integración se encuentra aislada mediante un adapter:

```text
Application
     │
     ▼
SendWhatsAppMessagePort
     │
     ▼
WhatsAppAdapter
     │
     ▼
RestClient
     │
     ▼
Meta WhatsApp Cloud API
```

Para recibir mensajes:

```text
WhatsApp User
      │
      ▼
Meta
      │
      ▼
Webhook
      │
      ▼
BookAi
      │
      ▼
Conversation
      │
      ▼
AI
```

---

# 🔐 Seguridad y configuración

Las credenciales y configuraciones sensibles no deben almacenarse directamente en el repositorio.

Ejemplo:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

whatsapp.api.phone-number-id=${WHATSAPP_PHONE_NUMBER_ID}
whatsapp.api.access-token=${WHATSAPP_ACCESS_TOKEN}
whatsapp.api.version=${WHATSAPP_API_VERSION}
```

Se recomienda utilizar variables de entorno para:

* Credenciales de MySQL.
* Tokens de WhatsApp.
* API keys de proveedores de IA.
* Credenciales de servicios externos.

---

# 🛠️ Tecnologías utilizadas

| Tecnología         | Uso                            |
| ------------------ | ------------------------------ |
| Java 17            | Lenguaje principal             |
| Spring Boot 3.5.5  | Framework backend              |
| Spring AI 1.1.8    | Integración con IA             |
| Spring Data JPA    | Persistencia                   |
| Hibernate          | ORM                            |
| MySQL              | Base de datos                  |
| MapStruct          | Mapeo DTO / Entity             |
| Lombok             | Reducción de código repetitivo |
| REST API           | Comunicación HTTP              |
| WhatsApp Cloud API | Comunicación con usuarios      |
| Meta Webhooks      | Recepción de mensajes          |
| Maven              | Gestión de dependencias        |
| Git                | Control de versiones           |

---

# 📦 Dependencias principales

El proyecto utiliza, entre otras:

```text
Spring Boot
Spring Web
Spring Data JPA
Spring AI
MySQL Driver
MapStruct
Lombok
Validation
```

---

# 🚀 Instalación

## 1. Clonar el repositorio

```bash
git clone https://github.com/Emir201/BookAi.git
```

```bash
cd BookAi
```

## 2. Configurar MySQL

Crear una base de datos:

```sql
CREATE DATABASE bookai;
```

Configurar las variables necesarias:

```properties
DB_URL=jdbc:mysql://localhost:3306/bookai
DB_USERNAME=root
DB_PASSWORD=
```

---

## 3. Configurar WhatsApp

Configurar las credenciales de WhatsApp Cloud API:

```properties
WHATSAPP_PHONE_NUMBER_ID=...
WHATSAPP_ACCESS_TOKEN=...
WHATSAPP_API_VERSION=vXX.X
```

---

## 4. Configurar el proveedor de IA

BookAi utiliza Spring AI para abstraer la comunicación con el modelo.

La implementación puede utilizar diferentes proveedores/modelos compatibles con la arquitectura del proyecto.

Las credenciales deben configurarse mediante variables de entorno.

---

## 5. Ejecutar el proyecto

Con Maven:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

---

# 🔌 API

## AI Chat

Endpoint:

```http
POST /api/v1/ai/chat
```

Ejemplo:

```json
{
  "message": "¿Qué servicios tienen disponibles?"
}
```

---

## Barbers

Ejemplo de endpoint:

```http
GET /api/v1/barbers
```

Los endpoints pueden ampliarse según los casos de uso implementados.

---

# 🧪 Pruebas

El proyecto está pensado para incorporar pruebas en diferentes niveles:

```text
Unit Tests
    │
    ├── Domain
    ├── Application Services
    └── Business Rules

Integration Tests
    │
    ├── Repositories
    ├── Database
    └── API

End-to-End
    │
    └── WhatsApp → BookAi → Database
```

---

# 🧱 Principios de diseño

BookAi busca aplicar principios de:

* **SOLID**
* **Clean Architecture**
* **Hexagonal Architecture**
* **Separation of Concerns**
* **Dependency Inversion**
* **Single Responsibility**
* **DTO Pattern**
* **Mapper Pattern**
* **Use Case Pattern**
* **Ports & Adapters**

Un objetivo importante es evitar que la lógica de negocio quede acoplada a frameworks o proveedores externos.

---

# 🔌 Ports & Adapters

Ejemplo conceptual:

```text
                    PORT
                     │
                     ▼
          ┌─────────────────────┐
          │ SendWhatsAppMessage │
          │       Port          │
          └──────────┬──────────┘
                     │
                     ▼
          ┌─────────────────────┐
          │ WhatsAppAdapter     │
          │                     │
          │ Meta Cloud API      │
          └─────────────────────┘
```

La aplicación depende del **puerto**, no directamente de Meta.

Esto permite reemplazar posteriormente el proveedor sin modificar la lógica principal del sistema.

---

# 🤖 AI Tools

Las herramientas disponibles para el modelo siguen una arquitectura similar:

```text
LLM
 │
 ▼
Tool
 │
 ▼
Use Case
 │
 ▼
Domain
 │
 ▼
Repository Port
 │
 ▼
Repository Adapter
 │
 ▼
MySQL
```

Esto mantiene las operaciones reales dentro de los casos de uso del sistema.

---

# 📚 Documentación visual

El proyecto incluye documentación mediante diagramas UML y esquemas técnicos.

```text
docs/
│
├── diagrams/
│   ├── architecture.png
│   ├── class-diagram.png
│   ├── domain-model.png
│   ├── database-schema.png
│   ├── appointment-flow.png
│   ├── whatsapp-flow.png
│   └── ai-tools-flow.png
│
└── README.md
```

## Arquitectura

![Arquitectura](docs/diagrams/architecture.png)

## Diagrama de clases

![Diagrama de clases](docs/diagrams/class-diagram.png)

## Modelo de base de datos

![Modelo de base de datos](docs/diagrams/database-schema.png)

## Flujo de WhatsApp

![Flujo de WhatsApp](docs/diagrams/whatsapp-flow.png)

## Flujo de Inteligencia Artificial

![Flujo de IA](docs/diagrams/ai-tools-flow.png)

---

# 🗺️ Roadmap

### ✅ Implementado / en desarrollo

* [x] Arquitectura hexagonal.
* [x] Gestión de clientes.
* [x] Gestión de barberos.
* [x] Gestión de tratamientos.
* [x] Gestión de horarios.
* [x] Gestión de días libres.
* [x] Sistema de disponibilidad.
* [x] Integración inicial con Spring AI.
* [x] AI Tools.
* [x] Integración con WhatsApp Cloud API.
* [x] Webhook de WhatsApp.
* [x] Gestión de conversaciones.
* [ ] Flujo completo de reserva mediante WhatsApp.
* [ ] Confirmación de turnos.
* [ ] Cancelación y modificación de turnos.
* [ ] Integración de pagos.
* [ ] Tests de integración.
* [ ] Dockerización.
* [ ] Deployment productivo.

---

# 🔮 Futuras mejoras

Algunas funcionalidades previstas:

* 💳 Integración con Mercado Pago.
* 📅 Reservas y cancelaciones mediante lenguaje natural.
* 🔔 Recordatorios automáticos.
* 📊 Estadísticas para propietarios.
* 👨‍💼 Gestión administrativa.
* 🧠 Memoria conversacional avanzada.
* 🔎 RAG para información adicional de la barbería.
* 🐳 Docker / Docker Compose.
* ☁️ Deployment en cloud.
* 📈 Observabilidad y métricas.
* 🔐 Autenticación y autorización para funcionalidades administrativas.

---

# 📁 Organización del proyecto

```text
BookAi
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.app.bookai
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── prompts
│   │           └── bookai-system-prompt.st
│   │
│   └── test
│
├── docs
│   └── diagrams
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 🧠 Decisiones arquitectónicas

### ¿Por qué arquitectura hexagonal?

Porque BookAi integra diferentes tecnologías externas:

```text
                  ┌──────────────┐
                  │   WhatsApp   │
                  └──────┬───────┘
                         │
                  ┌──────▼───────┐
                  │              │
                  │    BookAi    │
                  │              │
                  └──────┬───────┘
                         │
             ┌───────────┼────────────┐
             │           │            │
             ▼           ▼            ▼
           MySQL         AI         APIs
```

La arquitectura permite mantener estas dependencias en los límites del sistema.

### ¿Por qué usar Tools para la IA?

Porque el modelo no debería encargarse directamente de modificar el estado de la aplicación.

La IA interpreta la intención:

```text
"Agregá un barbero llamado Juan"
```

Pero la operación real pasa por:

```text
AI
 ↓
Tool
 ↓
Use Case
 ↓
Domain
 ↓
Repository
 ↓
Database
```

De esta manera, las reglas de negocio siguen perteneciendo al backend.

---

# 🎓 Objetivo del proyecto

BookAi también funciona como proyecto de aprendizaje y portfolio enfocado en backend.

El proyecto busca aplicar conceptos de desarrollo profesional utilizando:

* Java
* Spring Boot
* Arquitectura hexagonal
* Diseño orientado al dominio
* APIs REST
* Persistencia relacional
* Integración con servicios externos
* Inteligencia artificial
* Webhooks
* Integración con WhatsApp
* Patrones de diseño
* Separación de responsabilidades

---

# 👨‍💻 Autor

**Emir**

Proyecto personal de desarrollo backend.

---

# ⭐ Estado del proyecto

> 🚧 **En desarrollo**

BookAi continúa evolucionando hacia un sistema completo de gestión de barberías mediante WhatsApp e Inteligencia Artificial.

---

## 📄 Licencia

Este proyecto puede ser utilizado como proyecto personal y educativo.

La licencia definitiva será definida posteriormente.
