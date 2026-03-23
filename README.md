# 🏥 Sistema de Gestão de Clínica Médica

Este projeto é uma **API REST** para gerenciamento de pacientes e fichas médicas, desenvolvida com **Spring Boot**, **Spring Data JPA** e banco de dados **H2**. O objetivo é explorar relacionamentos `@OneToOne` e a separação entre dados cadastrais e clínicos.

### 🚀 Como Rodar o Projeto

**Pré-requisitos:** * Ter o **Java 21** instalado.

1. **Execute a aplicação:**
   * **Linux/Mac:** `./mvnw spring-boot:run`
   * **Windows:** `mvnw.cmd spring-boot:run`

2. **Acesso:** A API estará disponível em: [http://localhost:8080](http://localhost:8080)

---

### 🛠️ Tecnologias e Configuração

* **Framework:** Spring Boot 3.5.11.
* **Persistência:** Spring Data JPA com Hibernate.
* **Banco de Dados:** H2 (Baseada em arquivo em `./data/db-api`).
* **Console H2:** Acessível em `/h2-console` 
  * **JDBC URL:** `jdbc:h2:file:./data/db-api`
* **Validação:** Bean Validation para integridade de dados.
