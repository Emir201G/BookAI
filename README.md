# ✂️ BookAi

### Sistema inteligente de gestión de turnos para barberías mediante WhatsApp

BookAi es un proyecto personal de desarrollo **backend** creado con Java y Spring Boot para gestionar clientes, barberos, servicios, horarios y turnos mediante WhatsApp.

Integra inteligencia artificial para interpretar los mensajes de los usuarios y consultar o ejecutar operaciones a través de herramientas controladas por el backend.

El proyecto utiliza **Arquitectura Hexagonal (Ports & Adapters)** para separar la lógica de negocio de la infraestructura y los servicios externos.

## ✨ Características

* 💬 Integración con WhatsApp Cloud API de Meta.
* 🤖 Inteligencia artificial mediante Spring AI.
* 📅 Gestión de turnos y disponibilidad.
* 👤 Gestión de clientes y barberos.
* ✂️ Gestión de servicios y tratamientos.
* 🕐 Gestión de horarios, días libres y excepciones.
* 🔌 API REST y Webhooks.
* 🗄️ Persistencia con MySQL.
* 🧩 Arquitectura Hexagonal.

## 🏗️ Arquitectura

BookAi mantiene las reglas de negocio dentro del backend, independientemente de WhatsApp o del proveedor de inteligencia artificial.

![Arquitectura general](docs/diagrams/architecture.svg)

### Tecnologías principales

* Java 17
* Spring Boot 3.5.5
* Spring AI
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* MapStruct
* Lombok
* Maven
* WhatsApp Cloud API

## 💬 ¿Cómo funciona?

1. El usuario envía un mensaje por WhatsApp.
2. Meta envía el mensaje al Webhook de BookAi.
3. La inteligencia artificial interpreta la solicitud.
4. Las herramientas consultan los casos de uso del backend.
5. El sistema valida las reglas de negocio y la disponibilidad.
6. BookAi responde al usuario mediante WhatsApp.

![Flujo de WhatsApp e inteligencia artificial](docs/diagrams/whatsapp-flow.svg)

## 🤖 Integración con inteligencia artificial

BookAi utiliza Spring AI para conectar un modelo de lenguaje con herramientas que permiten consultar información y solicitar operaciones al backend.

La IA interpreta los mensajes, pero **el backend conserva el control sobre las validaciones, las reglas de negocio y la persistencia**.

![Flujo de herramientas de IA](docs/diagrams/ai-tools-flow.svg)

## 📅 Gestión de turnos

El sistema contempla la creación y gestión de turnos considerando:

* Cliente y barbero.
* Servicios solicitados.
* Fecha y horario.
* Duración de los servicios.
* Horarios laborales y días libres.
* Excepciones y turnos existentes.

![Flujo de creación de turnos](docs/diagrams/appointment-flow.svg)

## 🧩 Organización del proyecto

El código está organizado por funcionalidades, separando dominio, aplicación e infraestructura.

```text
com.app.bookai
├── ai
├── appointment
├── barber
├── customer
├── payment
└── whatsapp
```

Cada módulo puede contener:

* `domain`: entidades, reglas y puertos.
* `application`: casos de uso y servicios.
* `infrastructure`: controladores, adaptadores, repositorios y configuraciones.

## 🔌 API REST

Algunos endpoints disponibles:

| Método | Endpoint          | Descripción           |
| ------ | ----------------- | --------------------- |
| POST   | `/api/v1/ai/chat` | Interacción con la IA |
| GET    | `/api/v1/barbers` | Consulta de barberos  |

La API continúa evolucionando junto con el proyecto.

## ⚙️ Configuración

Se necesita Java 17, Maven y MySQL.

Crear la base de datos:

```sql
CREATE DATABASE bookai;
```

Configurar las credenciales de base de datos, WhatsApp y el proveedor de inteligencia artificial mediante variables de entorno.

Ejecutar el proyecto en Windows:

```bash
mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

Para utilizar WhatsApp, también es necesario configurar las credenciales de Meta y el Webhook correspondiente.

## 🗺️ Estado del proyecto

**En desarrollo activo.**

### Implementado

* [x] Arquitectura Hexagonal.
* [x] Gestión de clientes, barberos y tratamientos.
* [x] Gestión de horarios y días libres.
* [x] Lógica inicial de disponibilidad.
* [x] Integración con Spring AI y herramientas.
* [x] Integración con WhatsApp Cloud API.
* [x] Webhook y gestión de conversaciones.
* [x] API REST.

### En desarrollo

* [ ] Completar el flujo de reservas mediante WhatsApp.
* [ ] Confirmación, modificación y cancelación de turnos.
* [ ] Ampliar las pruebas automatizadas.
* [ ] Dockerización y despliegue productivo.

### Futuras mejoras

* [ ] Integración con Mercado Pago.
* [ ] Recordatorios automáticos.
* [ ] Estadísticas para propietarios.
* [ ] Mejoras de seguridad y observabilidad.

## 📊 Diagramas

Los diagramas técnicos se encuentran en `docs/diagrams/`.

| Archivo                      | Descripción                         |
| ---------------------------- | ----------------------------------- |
| `architecture.svg`           | Arquitectura general                |
| `hexagonal-architecture.svg` | Arquitectura Hexagonal              |
| `whatsapp-flow.svg`          | Flujo de WhatsApp                   |
| `ai-tools-flow.svg`          | Comunicación con herramientas de IA |
| `appointment-flow.svg`       | Creación de turnos                  |
| `availability-flow.svg`      | Cálculo de disponibilidad           |
| `domain-model.svg`           | Modelo de dominio                   |
| `class-diagram.svg`          | Diagrama de clases                  |
| `database-schema.svg`        | Esquema de base de datos            |
| `project-structure.svg`      | Estructura del proyecto             |
| `ports-and-adapters.svg`     | Puertos y adaptadores               |
| `testing.svg`                | Estrategia de pruebas               |

## 👨‍💻 Autor

**Emir Claudio Marcelo Guanactolay**

Proyecto personal enfocado en desarrollo backend con Java, Spring Boot, arquitectura de software e inteligencia artificial.

[GitHub: Emir201G](https://github.com/Emir201G)

## 📄 Licencia

Proyecto personal orientado al aprendizaje y la construcción de un portfolio profesional. La licencia definitiva está pendiente de definición.

---

> **BookAi:** WhatsApp como canal de interacción, inteligencia artificial para interpretar solicitudes y un backend responsable de las reglas de negocio.
