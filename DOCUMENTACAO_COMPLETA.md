# 📚 Documentação Completa - Sistema TodoList

## 📋 Índice

1. [Visão Geral do Projeto](#visão-geral-do-projeto)
2. [Estrutura do Projeto](#estrutura-do-projeto)
3. [Configurações e Dependências](#configurações-e-dependências)
4. [Modelos de Dados](#modelos-de-dados)
5. [Repositórios](#repositórios)
6. [Controladores e API REST](#controladores-e-api-rest)
7. [Testes](#testes)
8. [Como Executar o Projeto](#como-executar-o-projeto)
9. [Guia de Uso da API](#guia-de-uso-da-api)
10. [Conceitos Técnicos Aplicados](#conceitos-técnicos-aplicados)

---

## 🎯 Visão Geral do Projeto

Este é um sistema de gerenciamento de tarefas (TodoList) desenvolvido em **Spring Boot** com **Java 17**. O projeto implementa uma API REST para criação e gerenciamento de usuários e suas respectivas tarefas.

### Funcionalidades Principais:

- ✅ Cadastro de usuários com criptografia de senha
- ✅ Criação de tarefas associadas a usuários
- ✅ Banco de dados em memória (H2) para desenvolvimento
- ✅ API REST completa
- ✅ Validações de segurança

---

## 📁 Estrutura do Projeto

```
todolist/
├── pom.xml                          # Configuração do Maven
├── mvnw / mvnw.cmd                  # Wrapper do Maven
├── src/
│   ├── main/
│   │   ├── java/br/com/leandro/todolist/
│   │   │   ├── TodolistApplication.java    # Classe principal
│   │   │   ├── user/                       # Pacote de usuários
│   │   │   │   ├── UserModel.java         # Modelo de usuário
│   │   │   │   ├── UserRepository.java    # Repositório de usuários
│   │   │   │   └── UserController.java    # Controlador de usuários
│   │   │   └── task/                      # Pacote de tarefas
│   │   │       ├── TaskModel.java         # Modelo de tarefa
│   │   │       ├── TaskRepository.java    # Repositório de tarefas
│   │   │       └── TaskController.java    # Controlador de tarefas
│   │   └── resources/
│   │       └── application.properties     # Configurações da aplicação
│   └── test/
│       └── java/br/com/leandro/todolist/
│           └── TodolistApplicationTests.java  # Testes da aplicação
└── target/                          # Arquivos compilados (gerados automaticamente)
```

---

## ⚙️ Configurações e Dependências

### 📄 pom.xml - Configuração do Maven

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Herda configurações do Spring Boot -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <!-- Informações do projeto -->
    <groupId>br.com.leandro</groupId>
    <artifactId>todolist</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>todolist</name>
    <description>Gerenciador de tarefas</description>

    <!-- Versão do Java -->
    <properties>
        <java.version>17</java.version>
    </properties>
```

#### 🔍 Explicação Linha por Linha:

**Linhas 1-5**: Cabeçalho XML padrão do Maven com declaração de namespace e schema.

**Linhas 7-12**: Herança do `spring-boot-starter-parent` que fornece:

- Configurações padrão do Spring Boot
- Gerenciamento de versões das dependências
- Plugins do Maven pré-configurados

**Linhas 14-18**: Metadados do projeto:

- `groupId`: Identificador único da organização
- `artifactId`: Nome do artefato
- `version`: Versão do projeto
- `name` e `description`: Nome e descrição legíveis

**Linhas 20-22**: Propriedades do projeto, definindo Java 17 como versão.

### 📦 Dependências do Projeto

```xml
<dependencies>
    <!-- Spring Boot Web - Para APIs REST -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Data JPA - Para persistência -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Banco H2 - Banco em memória -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok - Reduz código boilerplate -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.42</version>
        <scope>provided</scope>
    </dependency>

    <!-- Testes -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- BCrypt - Para criptografia de senhas -->
    <dependency>
        <groupId>at.favre.lib</groupId>
        <artifactId>bcrypt</artifactId>
        <version>0.10.2</version>
    </dependency>
</dependencies>
```

#### 🔍 Explicação das Dependências:

1. **spring-boot-starter-web**: Fornece tudo necessário para criar APIs REST
2. **spring-boot-starter-data-jpa**: Hibernate + JPA para persistência de dados
3. **h2**: Banco de dados em memória para desenvolvimento
4. **lombok**: Anotações que geram código automaticamente
5. **spring-boot-starter-test**: Framework de testes do Spring Boot
6. **bcrypt**: Biblioteca para criptografia segura de senhas

### ⚙️ application.properties - Configurações da Aplicação

```properties
# Configuração do banco H2
spring.datasource.url=jdbc:h2:mem:todolistdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Console do H2 habilitado
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
```

#### 🔍 Explicação Linha por Linha:

**Linha 1**: URL do banco H2 em memória com nome `todolistdb`

- `DB_CLOSE_DELAY=-1`: Mantém o banco ativo mesmo sem conexões
- `DB_CLOSE_ON_EXIT=FALSE`: Não fecha o banco ao sair da aplicação

**Linhas 2-4**: Configurações de conexão com o banco

- Driver: `org.h2.Driver`
- Usuário: `sa` (padrão do H2)
- Senha: vazia

**Linhas 5-6**: Habilita o console web do H2

- Acessível em: `http://localhost:8080/h2-console`

**Linha 7**: `ddl-auto=update` - Hibernate cria/atualiza tabelas automaticamente

**Linha 8**: Dialeto específico do H2 para otimizações

**Linha 9**: `show-sql=true` - Exibe SQL gerado no console (útil para debug)

---

## 🗃️ Modelos de Dados

### 👤 UserModel.java - Modelo de Usuário

```java
package br.com.leandro.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

#### 🔍 Explicação Linha por Linha:

**Linhas 1-10**: Imports necessários:

- `jakarta.persistence.*`: Anotações JPA para mapeamento ORM
- `lombok.Data`: Gera getters, setters, toString, equals, hashCode
- `org.hibernate.annotations.CreationTimestamp`: Timestamp automático
- `java.time.LocalDateTime`: Para datas
- `java.util.UUID`: Para IDs únicos

**Linha 13**: `@Data` (Lombok) - Gera automaticamente:

- Getters e setters para todos os campos
- Método `toString()`
- Métodos `equals()` e `hashCode()`

**Linha 14**: `@Entity(name = "tb_users")` - Marca a classe como entidade JPA

- `name = "tb_users"`: Define o nome da tabela no banco

**Linha 17**: `@Id` - Marca o campo como chave primária

**Linha 18**: `@GeneratedValue(generator = "UUID")` - Gera UUID automaticamente

**Linha 19**: `private UUID id` - Chave primária do tipo UUID

**Linha 21**: `@Column(unique = true)` - Campo único no banco (não permite duplicatas)

**Linha 22**: `private String username` - Nome de usuário único

**Linha 23**: `private String name` - Nome completo do usuário

**Linha 24**: `private String password` - Senha (será criptografada)

**Linha 26**: `@CreationTimestamp` - Hibernate preenche automaticamente com data/hora de criação

**Linha 27**: `private LocalDateTime createdAt` - Data de criação do usuário

### 📝 TaskModel.java - Modelo de Tarefa

```java
package br.com.leandro.todolist.task;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUser;
}
```

#### 🔍 Explicação Linha por Linha:

**Linha 11**: `@Entity(name = "tb_tasks")` - Entidade JPA para tabela `tb_tasks`

**Linha 15**: `@Id` - Chave primária

**Linha 16**: `@GeneratedValue(generator = "UUID")` - UUID automático

**Linha 17**: `private UUID id` - ID único da tarefa

**Linha 18**: `private String description` - Descrição da tarefa

**Linha 20**: `@Column(length = 50)` - Limita o título a 50 caracteres

**Linha 21**: `private String title` - Título da tarefa (máximo 50 chars)

**Linha 22**: `private LocalDateTime startAt` - Data/hora de início

**Linha 23**: `private LocalDateTime endAt` - Data/hora de fim

**Linha 24**: `private String priority` - Prioridade da tarefa (ex: "ALTA", "MÉDIA", "BAIXA")

**Linha 26**: `@CreationTimestamp` - Data de criação automática

**Linha 27**: `private LocalDateTime createdAt` - Quando a tarefa foi criada

**Linha 29**: `private UUID idUser` - Referência ao usuário proprietário da tarefa

---

## 🗄️ Repositórios

### 👤 UserRepository.java - Repositório de Usuários

```java
package br.com.leandro.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
```

#### 🔍 Explicação Linha por Linha:

**Linha 7**: `extends JpaRepository<UserModel, UUID>` - Herda funcionalidades do Spring Data JPA

- `UserModel`: Tipo da entidade
- `UUID`: Tipo da chave primária

**Funcionalidades herdadas automaticamente:**

- `save(UserModel)` - Salva/atualiza usuário
- `findById(UUID)` - Busca por ID
- `findAll()` - Lista todos os usuários
- `deleteById(UUID)` - Remove usuário
- `count()` - Conta usuários
- E muito mais...

**Linha 8**: `UserModel findByUsername(String username)` - Método customizado

- Spring Data JPA gera implementação automaticamente
- Busca usuário pelo nome de usuário
- Retorna `null` se não encontrar

### 📝 TaskRepository.java - Repositório de Tarefas

```java
package br.com.leandro.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
```

#### 🔍 Explicação:

**Linha 7**: Interface vazia que herda todas as funcionalidades básicas do JPA

- Pode ser expandida com métodos customizados conforme necessário
- Exemplo futuro: `List<TaskModel> findByIdUser(UUID idUser)`

---

## 🎮 Controladores e API REST

### 👤 UserController.java - Controlador de Usuários

```java
package br.com.leandro.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
```

#### 🔍 Explicação Linha por Linha:

**Linhas 1-10**: Imports necessários:

- `BCrypt`: Para criptografia de senhas
- `@Autowired`: Injeção de dependência
- `ResponseEntity`: Para controle de status HTTP
- Anotações do Spring Web

**Linha 12**: `@RestController` - Marca como controlador REST

- Combina `@Controller` + `@ResponseBody`
- Retorna JSON automaticamente

**Linha 13**: `@RequestMapping("/users")` - Define prefixo da URL

- Todas as rotas começam com `/users`

**Linha 16**: `@Autowired` - Injeção automática do repositório

- Spring cria e injeta a implementação automaticamente

**Linha 17**: `private UserRepository userRepository` - Dependência injetada

**Linha 19**: `@PostMapping("/")` - Mapeia requisições POST para `/users/`

**Linha 20**: `public ResponseEntity createUser(@RequestBody UserModel userModel)`

- `@RequestBody`: Converte JSON da requisição para objeto UserModel
- `ResponseEntity`: Permite controlar status HTTP e corpo da resposta

**Linha 21**: `var user = this.userRepository.findByUsername(userModel.getUsername())`

- Busca se já existe usuário com o mesmo username
- `var`: Inferência de tipo (Java 10+)

**Linhas 23-25**: Validação de duplicata

- Se usuário já existe, retorna erro 400 (Bad Request)
- Mensagem: "User already exists"

**Linha 27**: `var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray())`

- Criptografa a senha usando BCrypt
- `12`: Nível de segurança (maior = mais seguro, mais lento)
- Converte string para array de chars

**Linha 29**: `userModel.setPassword(passwordHashred)` - Substitui senha original pela criptografada

**Linha 31**: `var userCreated = this.userRepository.save(userModel)` - Salva no banco

**Linha 32**: `return ResponseEntity.status(HttpStatus.CREATED).body(userCreated)`

- Retorna status 201 (Created) com o usuário criado

### 📝 TaskController.java - Controlador de Tarefas

```java
package br.com.leandro.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel createTask(@RequestBody TaskModel taskModel) {
        var task = this.taskRepository.save(taskModel);
        return taskModel;
    }
}
```

#### 🔍 Explicação Linha por Linha:

**Linha 9**: `@RequestMapping("/tasks")` - Prefixo `/tasks` para todas as rotas

**Linha 12**: `@Autowired` - Injeção do TaskRepository

**Linha 15**: `@PostMapping("/")` - POST para `/tasks/`

**Linha 16**: `public TaskModel createTask(@RequestBody TaskModel taskModel)`

- Recebe JSON e converte para TaskModel
- Retorna TaskModel diretamente (Spring converte para JSON)

**Linha 17**: `var task = this.taskRepository.save(taskModel)` - Salva no banco

**Linha 18**: `return taskModel` - Retorna a tarefa criada

---

## 🧪 Testes

### 📋 TodolistApplicationTests.java - Teste da Aplicação

```java
package br.com.leandro.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodolistApplicationTests {

	@Test
	void contextLoads() {
	}
}
```

#### 🔍 Explicação Linha por Linha:

**Linha 5**: `@SpringBootTest` - Teste de integração completo

- Carrega todo o contexto do Spring
- Inicializa banco de dados
- Testa integração entre componentes

**Linha 8**: `@Test` - Marca método como teste

**Linha 9**: `void contextLoads()` - Testa se o contexto Spring carrega corretamente

- Se falhar, indica problema de configuração
- Teste básico mas importante

---

## 🚀 Como Executar o Projeto

### Pré-requisitos:

- Java 17 ou superior
- Maven 3.6+ (ou use o wrapper incluído)

### Comandos para Executar:

```bash
# 1. Navegar para o diretório do projeto
cd /Users/leandrorc/Projetos\ Pessoais/todolist

# 2. Executar com Maven wrapper (recomendado)
./mvnw spring-boot:run

# OU com Maven instalado globalmente
mvn spring-boot:run

# 3. A aplicação estará disponível em:
# http://localhost:8080
```

### Acessar o Console do H2:

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:todolistdb
Username: sa
Password: (deixe vazio)
```

---

## 📖 Guia de Uso da API

### 1. Criar Usuário

**Endpoint:** `POST /users/`

**Exemplo de Requisição:**

```json
{
  "username": "joao123",
  "name": "João Silva",
  "password": "minhasenha123"
}
```

**Resposta de Sucesso (201):**

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "username": "joao123",
  "name": "João Silva",
  "password": "$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8K5K5K5K",
  "createdAt": "2024-01-15T10:30:00"
}
```

**Resposta de Erro (400):**

```json
"User already exists"
```

### 2. Criar Tarefa

**Endpoint:** `POST /tasks/`

**Exemplo de Requisição:**

```json
{
  "title": "Estudar Spring Boot",
  "description": "Revisar conceitos de Spring Boot e JPA",
  "startAt": "2024-01-15T09:00:00",
  "endAt": "2024-01-15T17:00:00",
  "priority": "ALTA",
  "idUser": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Resposta de Sucesso:**

```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "title": "Estudar Spring Boot",
  "description": "Revisar conceitos de Spring Boot e JPA",
  "startAt": "2024-01-15T09:00:00",
  "endAt": "2024-01-15T17:00:00",
  "priority": "ALTA",
  "createdAt": "2024-01-15T10:30:00",
  "idUser": "550e8400-e29b-41d4-a716-446655440000"
}
```

### 3. Testando com cURL

```bash
# Criar usuário
curl -X POST http://localhost:8080/users/ \
  -H "Content-Type: application/json" \
  -d '{
    "username": "maria456",
    "name": "Maria Santos",
    "password": "senha123"
  }'

# Criar tarefa
curl -X POST http://localhost:8080/tasks/ \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Fazer compras",
    "description": "Comprar ingredientes para o jantar",
    "startAt": "2024-01-15T14:00:00",
    "endAt": "2024-01-15T16:00:00",
    "priority": "MÉDIA",
    "idUser": "ID_DO_USUARIO_AQUI"
  }'
```

---

## 🎓 Conceitos Técnicos Aplicados

### 1. **Spring Boot**

- Framework que simplifica desenvolvimento Java
- Auto-configuração baseada em dependências
- Servidor embarcado (Tomcat)
- Starter dependencies para funcionalidades comuns

### 2. **JPA (Java Persistence API)**

- Padrão para mapeamento objeto-relacional
- Anotações como `@Entity`, `@Id`, `@Column`
- Abstração sobre diferentes bancos de dados

### 3. **Hibernate**

- Implementação de referência do JPA
- Geração automática de SQL
- Mapeamento objeto-relacional
- Cache de primeiro e segundo nível

### 4. **Spring Data JPA**

- Abstração sobre JPA/Hibernate
- Reduz código boilerplate
- Métodos de consulta automáticos
- Query methods por convenção de nomenclatura

### 5. **REST (Representational State Transfer)**

- Arquitetura para APIs web
- Uso correto de verbos HTTP (GET, POST, PUT, DELETE)
- Status codes semânticos
- JSON como formato de dados

### 6. **Injeção de Dependência**

- `@Autowired` para injeção automática
- Inversão de controle (IoC)
- Facilita testes e manutenção

### 7. **Segurança**

- Criptografia de senhas com BCrypt
- Hash com salt automático
- Resistente a ataques de força bruta

### 8. **Lombok**

- Reduz código boilerplate
- `@Data` gera getters, setters, toString, etc.
- Anotações de tempo de compilação

### 9. **Banco de Dados H2**

- Banco em memória para desenvolvimento
- Console web para visualização
- Compatível com SQL padrão
- Não requer instalação

### 10. **Maven**

- Gerenciamento de dependências
- Build automation
- Wrapper para portabilidade
- Lifecycle de build padronizado

---

## 🔧 Melhorias Futuras Sugeridas

1. **Validações:**

   - `@Valid` nos controladores
   - Bean Validation (JSR-303)
   - Validação de campos obrigatórios

2. **Segurança:**

   - Autenticação JWT
   - Autorização por roles
   - CORS configurado

3. **Funcionalidades:**

   - CRUD completo (GET, PUT, DELETE)
   - Paginação e filtros
   - Busca de tarefas por usuário

4. **Qualidade:**

   - Testes unitários
   - Testes de integração
   - Logging estruturado

5. **Performance:**
   - Cache Redis
   - Connection pooling
   - Índices no banco

---

## 📚 Recursos para Aprofundamento

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hibernate User Guide](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html)
- [REST API Best Practices](https://restfulapi.net/)
- [BCrypt Algorithm](https://en.wikipedia.org/wiki/Bcrypt)

---

_Esta documentação foi gerada automaticamente para servir como guia completo de estudo do projeto TodoList._
