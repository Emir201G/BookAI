# ✂️ BookAi

### Sistema inteligente de gestión de turnos para barberías mediante WhatsApp

BookAi es un proyecto personal de desarrollo backend orientado a la gestión de turnos para barberías.

El sistema permite gestionar **clientes, barberos, servicios, horarios y turnos**, utilizando **WhatsApp como principal canal de interacción** e integrando inteligencia artificial para interpretar las solicitudes de los usuarios.

El backend está desarrollado principalmente con **Java y Spring Boot** y utiliza una **Arquitectura Hexagonal (Ports & Adapters)** para separar la lógica de negocio de la infraestructura y de los servicios externos.

> 💡 **BookAi no utiliza un frontend web tradicional.**
>
> La interacción con los usuarios está pensada principalmente mediante WhatsApp, mientras que el backend concentra la lógica de negocio, persistencia, inteligencia artificial e integración con servicios externos.

---

# 🎯 Objetivo del proyecto

El objetivo de BookAi es desarrollar un asistente capaz de facilitar la gestión de una barbería a través de conversaciones naturales.

Por ejemplo, un cliente podría escribir:

> "Hola, quiero cortarme el pelo mañana por la tarde."

A partir de esa solicitud, el sistema debe poder:

1. Recibir el mensaje desde WhatsApp.
2. Identificar al usuario.
3. Mantener el contexto de la conversación.
4. Interpretar la intención mediante inteligencia artificial.
5. Consultar información real del sistema.
6. Verificar la disponibilidad.
7. Solicitar los datos que sean necesarios.
8. Proponer horarios disponibles.
9. Confirmar la operación con el usuario.
10. Registrar el turno.
11. Responder nuevamente mediante WhatsApp.

La inteligencia artificial se utiliza principalmente como **capa de interpretación y comunicación**, mientras que las reglas y operaciones reales permanecen dentro del backend.

---

# ✨ Características principales

* 💬 Interacción mediante WhatsApp.
* 🤖 Integración con inteligencia artificial.
* 📅 Gestión de turnos.
* 👤 Gestión de clientes.
* 💈 Gestión de barberos.
* ✂️ Gestión de servicios y tratamientos.
* 🕐 Gestión de horarios laborales.
* 📆 Gestión de días libres.
* 🔄 Gestión de excepciones de horarios.
* 🔎 Consulta de disponibilidad.
* 🔌 API REST.
* 🧠 Herramientas para que la IA consulte información real del sistema.
* 🗄️ Persistencia con MySQL.
* 🧩 Arquitectura Hexagonal.
* 🔗 Integración con WhatsApp Cloud API de Meta.
* 📩 Recepción de mensajes mediante Webhook.
* 💳 Estructura preparada para futuras integraciones de pago.
* 🔐 Separación entre dominio, aplicación e infraestructura.

---

# 🏗️ Arquitectura

BookAi utiliza una **Arquitectura Hexagonal (Ports & Adapters)**.

El objetivo es mantener la lógica de negocio independiente de los detalles técnicos y de los servicios externos utilizados por la aplicación.

Entre las dependencias que se mantienen aisladas se encuentran:

* Spring.
* JPA / Hibernate.
* MySQL.
* WhatsApp.
* Meta.
* Proveedores de inteligencia artificial.
* APIs externas.

El dominio define las reglas y contratos necesarios, mientras que las implementaciones concretas se encuentran en los adaptadores de infraestructura.

## Arquitectura de alto nivel

---

# 🧩 Organización del proyecto

El proyecto utiliza una organización por funcionalidades:

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

Dentro de cada funcionalidad se separan las responsabilidades:

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

Esta organización permite evitar que las diferentes funcionalidades del sistema terminen concentrando toda la lógica en unas pocas clases.

---

# 🧠 Inteligencia Artificial

BookAi utiliza **Spring AI** para integrar modelos de lenguaje.

La inteligencia artificial no tiene acceso directo a la base de datos ni ejecuta directamente las operaciones de negocio.

En su lugar, se utilizan **Tools**, que funcionan como una interfaz controlada entre el modelo y el backend.

Por ejemplo, cuando el usuario consulta información sobre un servicio, la IA puede utilizar una herramienta que consulta los datos reales almacenados en el sistema.

Actualmente se trabaja con herramientas relacionadas con:

* Tratamientos.
* Barberos.
* Horarios.
* Operaciones relacionadas con los turnos.

---

# 🤖 Principio de funcionamiento de la IA

Uno de los principios importantes del proyecto es evitar que el modelo invente información relacionada con el negocio.

Por ejemplo, ante una pregunta como:

> "¿Cuánto cuesta el corte?"

La IA debe consultar los tratamientos registrados en el sistema.

De la misma forma, si el usuario pregunta:

> "¿Qué horarios tiene Juan?"

La información debe obtenerse mediante las herramientas disponibles.

Esto permite separar las responsabilidades:

```text
IA
│
├── Interpreta la solicitud.
├── Identifica la intención.
└── Decide qué herramienta necesita utilizar.
        │
        ▼
Backend
│
├── Valida la operación.
├── Ejecuta las reglas de negocio.
├── Consulta o modifica los datos.
└── Devuelve el resultado.
```

La IA no reemplaza la lógica del backend.

---

# 💬 Conversaciones mediante WhatsApp

WhatsApp funciona como el principal canal de comunicación con los usuarios.

El flujo general de una conversación es:

El backend recibe los mensajes mediante un **Webhook**, identifica la conversación y procesa la solicitud correspondiente.

Cuando es necesario utilizar inteligencia artificial, la conversación se deriva al componente encargado de comunicarse con el modelo.

Finalmente, la respuesta se envía nuevamente al usuario mediante la API de WhatsApp.

---

# 📱 Integración con WhatsApp

BookAi utiliza la **WhatsApp Cloud API de Meta**.

La integración se encuentra aislada mediante un adaptador, evitando que la lógica de negocio dependa directamente de la API externa.

El flujo de salida se estructura conceptualmente de la siguiente manera:

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

Para los mensajes entrantes:

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

Esta separación permite modificar la implementación de comunicación sin tener que modificar la lógica principal de la aplicación.

---

# 📅 Gestión de turnos

La gestión de turnos es una de las partes principales del sistema.

Un turno puede relacionar:

* Cliente.
* Barbero.
* Uno o varios servicios.
* Fecha.
* Hora de inicio.
* Hora de finalización.
* Estado del turno.

El proceso de creación busca validar la disponibilidad antes de registrar la operación.

---

# 🗓️ Gestión de disponibilidad

La disponibilidad de un barbero no depende únicamente de su horario semanal.

El sistema puede considerar:

* Horarios laborales.
* Días libres.
* Excepciones de horarios.
* Turnos existentes.
* Fecha solicitada.
* Duración de los servicios.

Por ejemplo, una excepción de horario puede modificar el horario habitual de un determinado día.

La lógica de disponibilidad permanece dentro del backend y no depende del modelo de inteligencia artificial.

---

# 👤 Modelo de dominio

Entre las principales entidades del dominio se encuentran:

### Customer

Representa al cliente que utiliza el sistema.

### Barber

Representa al profesional que presta los servicios.

### Treatment

Representa un servicio ofrecido por la barbería.

Entre sus datos se encuentran, entre otros:

* Nombre.
* Precio.
* Duración.
* Estado.

### Appointment

Representa un turno reservado.

Puede relacionar:

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

Permite establecer una excepción sobre el horario habitual de un determinado día.

---

# 📊 Diagrama de clases

El modelo de clases principal del proyecto se encuentra documentado mediante el siguiente diagrama:

---

# 🗄️ Persistencia y modelo de datos

BookAi utiliza **MySQL** como base de datos y **Spring Data JPA / Hibernate** para la persistencia.

Las entidades del dominio se mantienen separadas de las entidades utilizadas específicamente para persistencia.

Esto permite evitar que las decisiones relacionadas con la base de datos se propaguen directamente hacia el dominio.

---

# 🧾 Snapshot de tratamientos

Cuando un turno utiliza un determinado servicio, es importante conservar la información relevante correspondiente al momento de la reserva.

Por ejemplo, si posteriormente cambia el precio de un tratamiento, ese cambio no debería modificar el historial de un turno que ya había sido registrado.

Por este motivo, el proyecto contempla la conservación de datos relevantes del servicio utilizado en el momento de crear la reserva.

---

# 🔄 Flujo de creación de un turno

De forma general, una solicitud puede seguir este proceso:

```text
Solicitud del cliente
        │
        ▼
WhatsApp
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
Identificación de información
        │
        ├── Servicio
        ├── Barbero
        ├── Fecha
        └── Horario
        │
        ▼
Validación de disponibilidad
        │
        ▼
Confirmación
        │
        ▼
Caso de uso
        │
        ▼
Persistencia
        │
        ▼
Respuesta por WhatsApp
```

La implementación concreta continúa evolucionando junto con el proyecto.

---

# 🔌 Ports & Adapters

Una de las decisiones principales de diseño consiste en utilizar puertos para definir las dependencias que necesita la aplicación.

Por ejemplo, para enviar un mensaje de WhatsApp, la aplicación puede depender de un puerto:

```text
SendWhatsAppMessagePort
```

La implementación concreta se encuentra en infraestructura:

```text
WhatsAppAdapter
```

De esta forma:

```text
Application
     │
     ▼
Port
     │
     ▼
Adapter
     │
     ▼
External Service
```

Esto permite reemplazar una implementación externa sin modificar la lógica de negocio.

---

# 🧠 AI Tools y casos de uso

Las herramientas utilizadas por la inteligencia artificial siguen la misma idea de separación de responsabilidades.

Una operación puede seguir el siguiente recorrido:

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

La herramienta no debería contener toda la lógica de negocio.

Su responsabilidad principal es actuar como punto de entrada entre el modelo y los casos de uso de la aplicación.

---

# 🔐 Configuración y variables de entorno

Las credenciales y datos sensibles no deben almacenarse directamente en el repositorio.

Por ejemplo:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

whatsapp.api.phone-number-id=${WHATSAPP_PHONE_NUMBER_ID}
whatsapp.api.access-token=${WHATSAPP_ACCESS_TOKEN}
whatsapp.api.version=${WHATSAPP_API_VERSION}
```

Dependiendo del proveedor de inteligencia artificial utilizado, las credenciales correspondientes también deben configurarse mediante variables de entorno.

Se recomienda utilizar variables de entorno para:

* Credenciales de MySQL.
* Tokens de WhatsApp.
* Claves de proveedores de IA.
* Credenciales de servicios externos.

---

# 🛠️ Tecnologías utilizadas

| Tecnología             | Uso                                     |
| ---------------------- | --------------------------------------- |
| **Java 17**            | Lenguaje principal                      |
| **Spring Boot 3.5.5**  | Desarrollo del backend                  |
| **Spring AI**          | Integración con inteligencia artificial |
| **Spring Data JPA**    | Persistencia                            |
| **Hibernate**          | ORM                                     |
| **MySQL**              | Base de datos                           |
| **MapStruct**          | Mapeo entre objetos                     |
| **Lombok**             | Reducción de código repetitivo          |
| **REST API**           | Comunicación HTTP                       |
| **WhatsApp Cloud API** | Comunicación con usuarios               |
| **Meta Webhooks**      | Recepción de mensajes                   |
| **Maven**              | Gestión del proyecto                    |
| **Git**                | Control de versiones                    |

---

# 📦 Dependencias principales

Entre las principales dependencias utilizadas se encuentran:

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
git clone https://github.com/Emir201G/BookAI.git
```

Entrar al proyecto:

```bash
cd BookAI
```

---

## 2. Crear la base de datos

Crear una base de datos MySQL:

```sql
CREATE DATABASE bookai;
```

Configurar las credenciales mediante variables de entorno.

Por ejemplo:

```properties
DB_URL=jdbc:mysql://localhost:3306/bookai
DB_USERNAME=root
DB_PASSWORD=
```

---

## 3. Configurar WhatsApp

Para utilizar la integración con WhatsApp es necesario configurar las credenciales correspondientes de Meta:

```properties
WHATSAPP_PHONE_NUMBER_ID=...
WHATSAPP_ACCESS_TOKEN=...
WHATSAPP_API_VERSION=...
```

También es necesario configurar el Webhook de Meta apuntando al endpoint correspondiente de la aplicación.

---

## 4. Configurar el proveedor de IA

BookAi utiliza **Spring AI** para abstraer la integración con modelos de lenguaje.

La configuración del proveedor y las credenciales correspondientes deben realizarse mediante variables de entorno.

---

## 5. Ejecutar la aplicación

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

BookAi expone diferentes endpoints REST para las funcionalidades del sistema.

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

Ejemplo:

```http
GET /api/v1/barbers
```

La API continúa evolucionando a medida que se incorporan nuevos casos de uso.

---

# 🧪 Pruebas

El proyecto contempla pruebas en diferentes niveles:

```text
Pruebas unitarias
        │
        ├── Dominio
        ├── Servicios
        └── Reglas de negocio

Pruebas de integración
        │
        ├── Repositorios
        ├── Base de datos
        └── API

Pruebas de extremo a extremo
        │
        └── WhatsApp → BookAi → Base de datos
```

La cobertura de pruebas continúa siendo parte del desarrollo del proyecto.

---

# 🧱 Principios de diseño

BookAi busca aplicar diferentes principios y patrones de diseño:

* **SOLID**
* **Arquitectura Hexagonal**
* **Separación de responsabilidades**
* **Inversión de dependencias**
* **Responsabilidad única**
* **DTO Pattern**
* **Mapper Pattern**
* **Use Case Pattern**
* **Ports & Adapters**

El objetivo principal es mantener la lógica de negocio independiente de frameworks, bases de datos y proveedores externos.

---

# 📚 Documentación visual

Los principales componentes técnicos del proyecto se documentan mediante diagramas en formato SVG.

```text
docs/
└── diagrams/
    ├── architecture.svg
    ├── class-diagram.svg
    ├── domain-model.svg
    ├── database-schema.svg
    ├── appointment-flow.svg
    ├── availability-flow.svg
    ├── whatsapp-flow.svg
    ├── ai-tools-flow.svg
    └── ports-and-adapters.svg
```

Los diagramas se incluyen directamente en el README para facilitar la comprensión de la arquitectura, el dominio y los principales flujos del sistema.

---

# 🗺️ Estado del proyecto

BookAi se encuentra actualmente **en desarrollo**.

### Implementado

* [x] Arquitectura hexagonal.
* [x] Organización por funcionalidades.
* [x] Gestión de clientes.
* [x] Gestión de barberos.
* [x] Gestión de tratamientos.
* [x] Gestión de horarios.
* [x] Gestión de días libres.
* [x] Gestión de excepciones de horarios.
* [x] Lógica inicial de disponibilidad.
* [x] Integración con Spring AI.
* [x] Integración de herramientas para la IA.
* [x] Integración con WhatsApp Cloud API.
* [x] Webhook de WhatsApp.
* [x] Gestión de conversaciones.
* [x] API REST.

### En desarrollo

* [ ] Flujo completo de reservas mediante WhatsApp.
* [ ] Confirmación de turnos mediante conversación.
* [ ] Cancelación de turnos.
* [ ] Modificación de turnos.
* [ ] Mejoras en la gestión de disponibilidad.
* [ ] Tests de integración.
* [ ] Dockerización.
* [ ] Deployment productivo.

### Futuras mejoras

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
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 🧠 Decisiones arquitectónicas

## ¿Por qué Arquitectura Hexagonal?

BookAi integra diferentes tecnologías y servicios externos, como:

* MySQL.
* WhatsApp.
* Meta.
* Modelos de inteligencia artificial.
* APIs externas.

La Arquitectura Hexagonal permite mantener estas dependencias en los límites del sistema y evitar que la lógica de negocio quede directamente acoplada a ellas.

---

## ¿Por qué utilizar Tools para la IA?

La inteligencia artificial puede interpretar una solicitud como:

> "¿Qué cortes tienen disponibles?"

Pero no debería inventar la respuesta.

El modelo utiliza una herramienta para consultar la información real del sistema.

El recorrido esperado es:

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

Esto permite mantener las operaciones reales bajo el control del backend.

---

## ¿Por qué utilizar casos de uso?

Los casos de uso representan las acciones que el sistema puede realizar.

Esto permite separar:

* Lo que el sistema puede hacer.
* Cómo se ejecuta la operación.
* Dónde se almacenan los datos.
* Qué tecnología se utiliza para acceder a ellos.

Por ejemplo, la creación de un turno puede exponerse mediante un caso de uso sin que el dominio tenga que conocer si los datos finalmente se almacenan en MySQL, PostgreSQL u otra tecnología.

---

# 🎓 Objetivo de aprendizaje

Además de ser un proyecto funcional, BookAi es un proyecto personal orientado a profundizar conocimientos de desarrollo backend y arquitectura de software.

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
* Uso de herramientas con modelos de lenguaje.
* DTOs y mappers.
* Casos de uso.
* Separación de responsabilidades.
* Principios SOLID.
* Git.

---

# 👨‍💻 Autor

**Emir Claudio Marcelo Guanactolay**

Proyecto personal de desarrollo backend.

GitHub:

**[github.com/Emir201G/BookAI](https://github.com/Emir201G/BookAI)**

---

# ⭐ Estado

> 🚧 **BookAi se encuentra actualmente en desarrollo.**

El proyecto continúa evolucionando hacia un sistema de gestión de barberías donde **WhatsApp funciona como canal de interacción y la inteligencia artificial ayuda a interpretar las solicitudes de los usuarios**, manteniendo las reglas de negocio dentro del backend.

---

## 📄 Licencia

Proyecto personal y educativo.

La licencia definitiva será definida posteriormente.
