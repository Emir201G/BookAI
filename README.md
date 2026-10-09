# ✂️ BookAi

### Sistema inteligente de gestión de turnos para barberías mediante WhatsApp

BookAi es un proyecto personal de desarrollo **backend** orientado a la gestión de turnos para barberías.

El sistema permite gestionar **clientes, barberos, servicios, horarios y turnos**, utilizando **WhatsApp como principal canal de interacción** e integrando inteligencia artificial para interpretar las solicitudes de los usuarios.

El backend está desarrollado con **Java 17 y Spring Boot 3.5.5** y utiliza una **Arquitectura Hexagonal (Ports & Adapters)** para mantener separada la lógica de negocio de la infraestructura y de los servicios externos.

> **WhatsApp funciona como canal de interacción, mientras que BookAi concentra las reglas de negocio, persistencia, disponibilidad, gestión de turnos e integración con inteligencia artificial.**

---

## 🎯 ¿Qué es BookAi?

BookAi busca simplificar la gestión de turnos de una barbería mediante una conversación natural.

Un cliente puede realizar una solicitud como:

> **"Hola, quiero cortarme el pelo mañana por la tarde."**

A partir de esa solicitud, el sistema puede:

1. Recibir el mensaje desde WhatsApp.
2. Identificar al usuario y la conversación.
3. Interpretar la solicitud mediante inteligencia artificial.
4. Consultar información real del sistema.
5. Verificar la disponibilidad.
6. Solicitar los datos que sean necesarios.
7. Proponer alternativas disponibles.
8. Confirmar la operación.
9. Registrar el turno.
10. Responder nuevamente mediante WhatsApp.

La inteligencia artificial funciona como **capa de interpretación y comunicación**.

Las reglas de negocio y las operaciones reales permanecen dentro del backend.

---

# ✨ Características principales

* 💬 Interacción mediante WhatsApp.
* 🤖 Integración con inteligencia artificial mediante Spring AI.
* 📅 Gestión de turnos.
* 👤 Gestión de clientes.
* 💈 Gestión de barberos.
* ✂️ Gestión de servicios y tratamientos.
* 🕐 Gestión de horarios laborales.
* 📆 Gestión de días libres.
* 🔄 Gestión de excepciones de horarios.
* 🔎 Consulta de disponibilidad.
* 🧠 Herramientas controladas para interacción entre IA y backend.
* 🔌 API REST.
* 📩 Recepción de mensajes mediante Webhook.
* 🔗 Integración con WhatsApp Cloud API de Meta.
* 🗄️ Persistencia mediante MySQL.
* 🧩 Arquitectura Hexagonal.
* 🔐 Separación entre dominio, aplicación e infraestructura.
* 💳 Estructura preparada para futuras integraciones de pago.

---

# 🏗️ Arquitectura

BookAi utiliza una **Arquitectura Hexagonal (Ports & Adapters)**.

El objetivo es mantener las reglas de negocio independientes de las tecnologías utilizadas en los límites de la aplicación.

Entre las dependencias aisladas se encuentran:

* Spring.
* JPA / Hibernate.
* MySQL.
* WhatsApp.
* Meta.
* Proveedores de inteligencia artificial.
* APIs externas.

El dominio define las reglas y contratos necesarios, mientras que las implementaciones concretas se encuentran en los adaptadores.

### Arquitectura de alto nivel

![Arquitectura general de BookAi](docs/diagrams/architecture.svg)

La arquitectura general puede resumirse de la siguiente manera:

```text
Usuario
   │
   ▼
WhatsApp
   │
   ▼
Meta
   │
   ▼
Webhook
   │
   ▼
BookAi Backend
   │
   ├── Inteligencia Artificial
   ├── Aplicación
   ├── Dominio
   └── Infraestructura
           │
           ├── MySQL
           ├── WhatsApp
           └── Servicios externos
```

---

# 🧩 Arquitectura Hexagonal

La aplicación está organizada alrededor del dominio.

Los adaptadores de entrada permiten que diferentes componentes interactúen con la aplicación, mientras que los adaptadores de salida permiten comunicarse con servicios externos.

![Arquitectura Hexagonal](docs/diagrams/hexagonal-architecture.svg)

Conceptualmente:

```text
             ADAPTADORES DE ENTRADA
       REST API · Webhook · AI Tools
                    │
                    ▼
              Puertos de entrada
                    │
                    ▼
              ┌─────────────┐
              │   DOMINIO   │
              │             │
              │  Entidades  │
              │   Reglas    │
              │   Negocio   │
              └──────┬──────┘
                     │
                     ▼
              Puertos de salida
                     │
          ┌──────────┼──────────┐
          ▼          ▼          ▼
        MySQL     WhatsApp      IA
```

Esta separación permite reemplazar una implementación externa sin modificar la lógica principal del sistema.

---

# 🤖 Inteligencia Artificial

BookAi utiliza **Spring AI** para integrar modelos de lenguaje.

La inteligencia artificial se utiliza principalmente para:

* Interpretar mensajes.
* Identificar intenciones.
* Obtener información necesaria de la conversación.
* Utilizar herramientas disponibles.
* Generar respuestas naturales.

La IA **no accede directamente a la base de datos** ni debería contener las reglas principales del negocio.

En su lugar, utiliza herramientas que funcionan como una interfaz controlada entre el modelo y los casos de uso del backend.

---

## 🧠 AI Tools

Las herramientas permiten que el modelo solicite información o acciones al backend sin acceder directamente a la infraestructura.

![Flujo de AI Tools](docs/diagrams/ai-tools-flow.svg)

El recorrido conceptual es:

```text
Usuario
   │
   ▼
LLM
   │
   ▼
AI Tool
   │
   ▼
Use Case
   │
   ▼
Reglas de negocio
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

Actualmente se utilizan herramientas relacionadas con:

* Tratamientos.
* Barberos.
* Horarios.
* Disponibilidad.
* Operaciones relacionadas con turnos.

La herramienta actúa como punto de entrada hacia la aplicación, pero la validación y ejecución de la operación permanecen dentro del backend.

---

# 💬 Integración con WhatsApp

WhatsApp funciona como el principal canal de interacción entre el usuario y BookAi.

La integración utiliza **WhatsApp Cloud API de Meta**.

Los mensajes entrantes son recibidos mediante un Webhook.

![Flujo de integración con WhatsApp](docs/diagrams/whatsapp-flow.svg)

### Entrada de mensajes

```text
Usuario
   │
   ▼
WhatsApp
   │
   ▼
Meta
   │
   ▼
Webhook
   │
   ▼
BookAi
```

### Salida de mensajes

```text
BookAi
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
   │
   ▼
WhatsApp
   │
   ▼
Usuario
```

La integración está aislada mediante adaptadores para evitar que la lógica del negocio dependa directamente de la API externa.

---

# 📅 Gestión de turnos

Los turnos constituyen una de las funcionalidades principales de BookAi.

Un turno puede relacionar:

* Cliente.
* Barbero.
* Uno o varios servicios.
* Fecha.
* Hora de inicio.
* Hora de finalización.
* Estado.
* Información del servicio utilizada en el momento de la reserva.

El proceso de creación valida previamente las condiciones necesarias antes de registrar la operación.

---

## 🔄 Flujo de creación de un turno

![Flujo de creación de un turno](docs/diagrams/appointment-flow.svg)

El flujo general es:

```text
Mensaje del cliente
        │
        ▼
WhatsApp / Meta
        │
        ▼
Webhook
        │
        ▼
Conversación
        │
        ▼
Inteligencia Artificial
        │
        ▼
AI Tool / Use Case
        │
        ▼
Validación de disponibilidad
        │
        ▼
Confirmación
        │
        ▼
Persistencia
        │
        ▼
Respuesta por WhatsApp
```

> Este flujo representa la arquitectura objetivo del sistema y continúa evolucionando junto con el desarrollo de BookAi.

---

# 🗓️ Gestión de disponibilidad

La disponibilidad de un barbero no depende únicamente de su horario semanal.

BookAi contempla diferentes factores:

* Horarios laborales.
* Días libres.
* Excepciones de horarios.
* Turnos existentes.
* Fecha solicitada.
* Hora solicitada.
* Duración de los servicios.

![Flujo de disponibilidad](docs/diagrams/availability-flow.svg)

Conceptualmente:

```text
Fecha solicitada
       │
       ▼
Horario habitual
       │
       ▼
Día libre
       │
       ▼
Excepción de horario
       │
       ▼
Turnos existentes
       │
       ▼
Duración del servicio
       │
       ▼
Intervalos disponibles
```

La lógica de disponibilidad pertenece al backend y no depende de la inteligencia artificial.

---

# 🧠 Modelo de dominio

Las principales entidades del dominio son:

### Customer

Representa al cliente que utiliza el sistema.

### Barber

Representa al profesional que presta los servicios.

### Treatment

Representa un servicio ofrecido por la barbería.

Entre sus datos se encuentran:

* Nombre.
* Precio.
* Duración.
* Estado.

### Appointment

Representa una reserva realizada por un cliente.

Relaciona:

* Cliente.
* Barbero.
* Servicios.
* Fecha.
* Horario.
* Estado.

### WorkingHour

Representa los horarios habituales de trabajo de un barbero.

### DayOff

Representa un día en el que un barbero no está disponible.

### WorkingHourOverride

Permite modificar el horario habitual de un barbero para una fecha determinada.

### Modelo de dominio

![Modelo de dominio de BookAi](docs/diagrams/domain-model.svg)

---

# 📊 Diagrama de clases

El modelo de clases representa las principales entidades y relaciones del dominio.

![Diagrama de clases](docs/diagrams/class-diagram.svg)

Las entidades de infraestructura y persistencia se mantienen separadas cuando corresponde, evitando trasladar decisiones específicas de JPA directamente al dominio.

---

# 🗄️ Persistencia y modelo de datos

BookAi utiliza:

* **MySQL** como base de datos.
* **Spring Data JPA** para acceso a datos.
* **Hibernate** como ORM.

La persistencia se encuentra aislada mediante repositorios y adaptadores.

```text
Domain
   │
   ▼
Repository Port
   │
   ▼
Repository Adapter
   │
   ▼
Spring Data JPA
   │
   ▼
Hibernate
   │
   ▼
MySQL
```

### Esquema de base de datos

![Modelo de base de datos](docs/diagrams/database-schema.svg)

---

# 🧾 Información histórica de los servicios

Cuando un turno utiliza un determinado tratamiento, es importante conservar la información relevante correspondiente al momento de la reserva.

Por ejemplo:

```text
Tratamiento
Precio actual: $5000
       │
       ▼
Creación del turno
       │
       ▼
Precio registrado en la reserva
       │
       ▼
Posteriormente:
Tratamiento → $7000
```

El cambio posterior del precio del tratamiento no debería modificar el historial de una reserva ya registrada.

Por este motivo, el modelo contempla conservar información relevante del servicio utilizado durante la creación del turno.

---

# 🔌 Ports & Adapters

Los puertos representan contratos utilizados por el sistema.

Por ejemplo:

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

### Diagrama

![Ports & Adapters](docs/diagrams/ports-and-adapters.svg)

La aplicación depende de la abstracción y no de la implementación concreta.

Esto facilita:

* Reemplazar proveedores externos.
* Crear implementaciones alternativas.
* Realizar pruebas con adaptadores simulados.
* Reducir el acoplamiento.
* Mantener el dominio independiente de infraestructura.

---

# 🧠 Casos de uso

Los casos de uso representan las acciones que el sistema puede realizar.

Su objetivo es separar:

* Qué puede hacer el sistema.
* Cómo se ejecuta una operación.
* Qué reglas deben cumplirse.
* Cómo se accede a los datos.
* Qué tecnología se utiliza para persistir la información.

Por ejemplo:

```text
CreateAppointmentUseCase
          │
          ▼
     Application
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

Esto permite que la lógica de negocio no dependa directamente de la tecnología de persistencia.

---

# 📁 Organización del proyecto

BookAi utiliza una organización **por funcionalidades**.

![Estructura del proyecto](docs/diagrams/project-structure.svg)

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

Dentro de cada funcionalidad:

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

Esta estructura permite mantener separadas las responsabilidades y evitar que toda la lógica termine concentrada en unas pocas clases.

---

# 🛠️ Tecnologías utilizadas

| Tecnología             | Uso                                     |
| ---------------------- | --------------------------------------- |
| **Java 17**            | Lenguaje principal                      |
| **Spring Boot 3.5.5**  | Desarrollo del backend                  |
| **Spring AI**          | Integración con inteligencia artificial |
| **Spring Web**         | Desarrollo de API REST                  |
| **Spring Data JPA**    | Persistencia                            |
| **Hibernate**          | ORM                                     |
| **MySQL**              | Base de datos                           |
| **MapStruct**          | Mapeo entre objetos                     |
| **Lombok**             | Reducción de código repetitivo          |
| **Maven**              | Gestión del proyecto                    |
| **WhatsApp Cloud API** | Comunicación con usuarios               |
| **Meta Webhooks**      | Recepción de mensajes                   |
| **Git**                | Control de versiones                    |

---

# 📦 Dependencias principales

```text
Spring Boot
├── Spring Web
├── Spring Data JPA
├── Validation
└── Spring AI

Persistencia
├── Hibernate
└── MySQL Driver

Mapeo
└── MapStruct

Productividad
└── Lombok
```

---

# 🔌 API

BookAi expone endpoints REST para diferentes funcionalidades del sistema.

## Inteligencia artificial

```http
POST /api/v1/ai/chat
```

Ejemplo:

```json
{
  "message": "¿Qué servicios tienen disponibles?"
}
```

## Barberos

```http
GET /api/v1/barbers
```

La API continúa evolucionando a medida que se incorporan nuevos casos de uso.

---

# ⚙️ Configuración

Las credenciales y datos sensibles no se almacenan directamente en el repositorio.

Se utilizan variables de entorno.

Ejemplo:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

whatsapp.api.phone-number-id=${WHATSAPP_PHONE_NUMBER_ID}
whatsapp.api.access-token=${WHATSAPP_ACCESS_TOKEN}
whatsapp.api.version=${WHATSAPP_API_VERSION}
```

Dependiendo del proveedor de inteligencia artificial utilizado, también deben configurarse las credenciales correspondientes mediante variables de entorno.

### Variables principales

```text
DB_URL
DB_USERNAME
DB_PASSWORD

WHATSAPP_PHONE_NUMBER_ID
WHATSAPP_ACCESS_TOKEN
WHATSAPP_API_VERSION

AI_PROVIDER
AI_API_KEY
```

---

# 🚀 Instalación

## 1. Clonar el repositorio

```bash
git clone https://github.com/Emir201G/BookAI.git
```

Entrar al proyecto:

```bash
cd BookAI
```

## 2. Crear la base de datos

Crear una base de datos MySQL:

```sql
CREATE DATABASE bookai;
```

Configurar posteriormente las credenciales mediante variables de entorno.

## 3. Configurar WhatsApp

Para utilizar la integración con WhatsApp es necesario configurar las credenciales correspondientes de Meta:

```text
WHATSAPP_PHONE_NUMBER_ID
WHATSAPP_ACCESS_TOKEN
WHATSAPP_API_VERSION
```

También debe configurarse el Webhook de Meta apuntando al endpoint correspondiente de la aplicación.

## 4. Configurar inteligencia artificial

Configurar el proveedor de IA y las credenciales necesarias mediante variables de entorno.

## 5. Ejecutar la aplicación

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

---

# 🧪 Pruebas

El proyecto contempla diferentes niveles de pruebas:

![Estrategia de pruebas](docs/diagrams/testing.svg)

```text
                 PRUEBAS
                    │
        ┌───────────┼───────────┐
        ▼           ▼           ▼
     Unitarias  Integración   E2E
        │           │           │
        ▼           ▼           ▼
     Dominio     Repositorios  WhatsApp
     Servicios   Base de datos     │
     Reglas      API              ▼
                               BookAi
```

El desarrollo de pruebas continúa evolucionando junto con el proyecto.

---

# 🧱 Principios y patrones

BookAi busca aplicar principios y patrones de diseño orientados a mantener un backend modular y desacoplado.

### Principios

* SOLID.
* Responsabilidad única.
* Inversión de dependencias.
* Separación de responsabilidades.
* Bajo acoplamiento.

### Patrones y conceptos

* Arquitectura Hexagonal.
* Ports & Adapters.
* Use Case Pattern.
* DTO Pattern.
* Mapper Pattern.
* Dependency Injection.
* Repository Pattern.

---

# 🗺️ Estado del proyecto

BookAi se encuentra actualmente **en desarrollo activo**.

### ✅ Implementado

* [x] Arquitectura Hexagonal.
* [x] Organización por funcionalidades.
* [x] Gestión de clientes.
* [x] Gestión de barberos.
* [x] Gestión de tratamientos.
* [x] Gestión de horarios.
* [x] Gestión de días libres.
* [x] Gestión de excepciones de horarios.
* [x] Lógica inicial de disponibilidad.
* [x] Integración con Spring AI.
* [x] Herramientas para la IA.
* [x] Integración con WhatsApp Cloud API.
* [x] Webhook de WhatsApp.
* [x] Gestión de conversaciones.
* [x] API REST.

### 🚧 En desarrollo

* [ ] Flujo completo de reservas mediante WhatsApp.
* [ ] Confirmación de turnos mediante conversación.
* [ ] Cancelación de turnos.
* [ ] Modificación de turnos.
* [ ] Mejoras en la gestión de disponibilidad.
* [ ] Tests de integración.
* [ ] Dockerización.
* [ ] Deployment productivo.

### 🔮 Futuras mejoras

* [ ] Integración con Mercado Pago.
* [ ] Recordatorios automáticos.
* [ ] Estadísticas para propietarios.
* [ ] Gestión administrativa.
* [ ] Memoria conversacional avanzada.
* [ ] RAG para información adicional de la barbería.
* [ ] Docker Compose.
* [ ] Deployment en la nube.
* [ ] Observabilidad y métricas.
* [ ] Mejoras de autenticación y autorización.

---

# 📚 Documentación visual

Los diagramas técnicos se encuentran en:

```text
docs/
└── diagrams/
    ├── architecture.svg
    ├── hexagonal-architecture.svg
    ├── ai-tools-flow.svg
    ├── whatsapp-flow.svg
    ├── appointment-flow.svg
    ├── availability-flow.svg
    ├── domain-model.svg
    ├── class-diagram.svg
    ├── database-schema.svg
    ├── project-structure.svg
    ├── testing.svg
    └── ports-and-adapters.svg
```

Cada diagrama representa una vista diferente del sistema:

| Diagrama                     | Objetivo                                       |
| ---------------------------- | ---------------------------------------------- |
| `architecture.svg`           | Arquitectura general del sistema               |
| `hexagonal-architecture.svg` | Arquitectura Hexagonal                         |
| `ai-tools-flow.svg`          | Comunicación entre IA, Tools y backend         |
| `whatsapp-flow.svg`          | Entrada y salida de mensajes mediante WhatsApp |
| `appointment-flow.svg`       | Flujo de creación de turnos                    |
| `availability-flow.svg`      | Cálculo de disponibilidad                      |
| `domain-model.svg`           | Entidades principales del dominio              |
| `class-diagram.svg`          | Relaciones entre clases principales            |
| `database-schema.svg`        | Modelo relacional de MySQL                     |
| `project-structure.svg`      | Organización de paquetes                       |
| `testing.svg`                | Estrategia de pruebas                          |
| `ports-and-adapters.svg`     | Comunicación entre puertos y adaptadores       |

---

# 🧠 Decisiones arquitectónicas

## ¿Por qué Arquitectura Hexagonal?

BookAi integra diferentes tecnologías y servicios externos:

* MySQL.
* WhatsApp.
* Meta.
* Proveedores de inteligencia artificial.
* APIs externas.

La Arquitectura Hexagonal permite mantener estas dependencias en los límites del sistema.

De esta forma, el dominio no necesita conocer cómo se comunica la aplicación con WhatsApp, cómo se almacena la información o qué proveedor de IA se utiliza.

---

## ¿Por qué utilizar Tools para la IA?

Un modelo de lenguaje puede interpretar una solicitud como:

> **"¿Qué cortes tienen disponibles?"**

Sin embargo, el modelo no debería inventar información.

En su lugar:

```text
Usuario
   │
   ▼
IA
   │
   ▼
Tool
   │
   ▼
Caso de uso
   │
   ▼
Reglas de negocio
   │
   ▼
Repositorio
   │
   ▼
Base de datos
```

Esto permite que la IA consulte información real del sistema.

La inteligencia artificial interpreta la solicitud, mientras que el backend mantiene el control sobre las operaciones.

---

## ¿Por qué utilizar casos de uso?

Los casos de uso permiten representar las acciones que el sistema puede realizar sin acoplarlas a una tecnología concreta.

Por ejemplo:

```text
CreateAppointmentUseCase
```

puede utilizarse desde diferentes puntos de entrada:

```text
WhatsApp
   │
   ▼
AI Tool
   │
   ▼
CreateAppointmentUseCase
```

o:

```text
REST API
   │
   ▼
Controller
   │
   ▼
CreateAppointmentUseCase
```

La operación principal permanece centralizada en el caso de uso.

---

# 🎓 Objetivo del proyecto

BookAi es también un proyecto personal orientado a profundizar conocimientos de desarrollo backend y arquitectura de software.

Durante su desarrollo se trabajan conceptos como:

* Java.
* Spring Boot.
* Arquitectura Hexagonal.
* Diseño orientado al dominio.
* APIs REST.
* JPA / Hibernate.
* Bases de datos relacionales.
* Integración con APIs externas.
* Webhooks.
* WhatsApp Cloud API.
* Inteligencia artificial.
* Spring AI.
* AI Tools.
* DTOs y mappers.
* Casos de uso.
* Principios SOLID.
* Git.

El objetivo es construir un sistema realista que combine **desarrollo backend, arquitectura, integración de servicios externos e inteligencia artificial**.

---

# 📁 Estructura general del proyecto

```text
BookAI
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
│       ├── architecture.svg
│       ├── hexagonal-architecture.svg
│       ├── ai-tools-flow.svg
│       ├── whatsapp-flow.svg
│       ├── appointment-flow.svg
│       ├── availability-flow.svg
│       ├── domain-model.svg
│       ├── class-diagram.svg
│       ├── database-schema.svg
│       ├── project-structure.svg
│       ├── testing.svg
│       └── ports-and-adapters.svg
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 👨‍💻 Autor

**Emir Claudio Marcelo Guanactolay**

Proyecto personal de desarrollo backend enfocado en **Java, Spring Boot, arquitectura de software e integración de inteligencia artificial**.

**GitHub:**
https://github.com/Emir201G/BookAI

---

# ⭐ Estado

> 🚧 **BookAi se encuentra actualmente en desarrollo.**

El proyecto continúa evolucionando hacia un sistema de gestión para barberías donde **WhatsApp funciona como canal de interacción, la inteligencia artificial interpreta las solicitudes y el backend mantiene las reglas y operaciones del negocio**.

---

## 📄 Licencia

Proyecto personal orientado al aprendizaje y desarrollo de portfolio.

La licencia definitiva será definida posteriormente.
