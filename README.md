# 🏫 Gestão Escolar

Sistema de gestão escolar desenvolvido com Java e Spring Boot, utilizando JDBC puro para acesso ao banco de dados MySQL. Permite gerenciar alunos, professores, cursos, turmas, aulas e notas por meio de uma API REST.

---

## 📋 Sumário

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Camadas da Aplicação](#camadas-da-aplicação)
- [Endpoints da API](#endpoints-da-api)
- [Exemplos de Requisições](#exemplos-de-requisições)
- [Como Rodar Localmente](#como-rodar-localmente)
- [Testando com Postman](#testando-com-postman)

---

## 🎯 Visão Geral

O **Gestão Escolar** é uma API REST para gerenciamento de instituições de ensino. Ela permite:

- Cadastrar e gerenciar **alunos** e **professores**
- Criar **cursos** e **turmas** vinculadas a cursos e professores
- Registrar **aulas** (com data/hora e assunto) associadas a turmas
- Lançar e consultar **notas** de alunos por aula
- Vincular alunos a turmas

> O código-fonte Java está na pasta `gestaoescolar/`.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia          | Versão   | Uso                                      |
|---------------------|----------|------------------------------------------|
| Java                | 21       | Linguagem principal                      |
| Spring Boot         | 3.5.11   | Framework web / injeção de dependências  |
| Spring Web (MVC)    | —        | Criação dos controllers REST             |
| Maven               | (Wrapper)| Gerenciamento de dependências e build    |
| JDBC                | —        | Acesso direto ao banco de dados          |
| MySQL               | 8+       | Banco de dados relacional                |
| MySQL Connector/J   | —        | Driver JDBC para MySQL                   |
| Spring Boot DevTools| —        | Recarregamento automático em dev         |

---

## 📁 Estrutura do Projeto

```
gestao-escolar/
└── gestaoescolar/                          ← projeto Maven
    ├── pom.xml
    ├── mvnw / mvnw.cmd                     ← Maven Wrapper
    └── src/
        ├── main/
        │   ├── java/com/weg/centroweg/gestaoescolar/
        │   │   ├── GestaoescolarApplication.java   ← classe principal
        │   │   ├── application/
        │   │   │   ├── dto/                        ← DTOs de entrada e saída
        │   │   │   │   ├── aluno/
        │   │   │   │   ├── aula/
        │   │   │   │   ├── curso/
        │   │   │   │   ├── nota/
        │   │   │   │   ├── professor/
        │   │   │   │   └── turma/
        │   │   │   ├── mapper/                     ← conversão Entity ↔ DTO
        │   │   │   └── service/                    ← regras de negócio
        │   │   ├── domain/
        │   │   │   ├── entity/                     ← entidades de domínio
        │   │   │   └── repository/                 ← interfaces de repositório
        │   │   └── infra/
        │   │       ├── controller/                 ← controllers REST
        │   │       ├── database/                   ← configuração da conexão JDBC
        │   │       └── persistence/                ← implementações dos repositórios
        │   └── resources/
        │       └── application.properties
        └── test/
            └── java/...
```

---

## 🏗️ Camadas da Aplicação

### `infra/controller`
Contém os **controllers REST** (Spring `@RestController`). Recebem as requisições HTTP, delegam ao Service e retornam os Response DTOs.

> Exemplos: `AlunoController`, `ProfessorController`, `CursoController`, `TurmaController`, `AulaController`, `NotaController`

### `application/service`
Camada de **serviço / regras de negócio**. Orquestra chamadas aos repositórios, aplica validações e converte entidades em DTOs usando os Mappers.

> Exemplos: `AlunoService`, `TurmaService`, `NotaService`

### `application/dto`
**Data Transfer Objects** usados para entrada (`RequestDto`) e saída (`ResponseDto`) da API. São `record`s Java imutáveis.

### `application/mapper`
Classes responsáveis pela **conversão** entre entidades de domínio e DTOs.

> Exemplos: `AlunoMapper`, `TurmaMapper`, `NotaMapper`

### `domain/entity`
**Entidades** que representam os objetos de negócio (Aluno, Professor, Curso, Turma, Aula, Nota).

### `domain/repository`
**Interfaces** de repositório que definem os contratos de persistência (ex.: `AlunoRepository`, `CursoRepository`).

### `infra/persistence`
**Implementações** dos repositórios usando JDBC puro (sem ORM). Executam queries SQL diretamente via `java.sql.Connection`.

> Exemplos: `AlunoRepositoryImpl`, `CursoRepositoryImpl`

### `infra/database`
Classe `Conexao` responsável por criar e retornar a conexão JDBC com o MySQL.

---

## 🚀 Endpoints da API

A API roda por padrão em `http://localhost:8080`.

### 👤 Alunos — `/aluno`

| Método   | Rota           | Descrição                        |
|----------|----------------|----------------------------------|
| `POST`   | `/aluno`       | Cadastrar novo aluno             |
| `GET`    | `/aluno`       | Listar todos os alunos           |
| `GET`    | `/aluno/{id}`  | Buscar aluno por ID              |
| `PUT`    | `/aluno/{id}`  | Atualizar dados do aluno         |
| `DELETE` | `/aluno/{id}`  | Remover aluno                    |

### 👨‍🏫 Professores — `/professor`

| Método   | Rota                | Descrição                        |
|----------|---------------------|----------------------------------|
| `POST`   | `/professor`        | Cadastrar novo professor         |
| `GET`    | `/professor`        | Listar todos os professores      |
| `GET`    | `/professor/{id}`   | Buscar professor por ID          |
| `PUT`    | `/professor/{id}`   | Atualizar dados do professor     |
| `DELETE` | `/professor/{id}`   | Remover professor                |

### 📚 Cursos — `/curso`

| Método   | Rota                    | Descrição                          |
|----------|-------------------------|------------------------------------|
| `POST`   | `/curso`                | Cadastrar novo curso               |
| `GET`    | `/curso`                | Listar todos os cursos             |
| `GET`    | `/curso/{id}`           | Buscar curso por ID                |
| `GET`    | `/curso/{id}/turmas`    | Listar turmas de um curso          |
| `PUT`    | `/curso/{id}`           | Atualizar dados do curso           |
| `DELETE` | `/curso/{id}`           | Remover curso                      |

### 🏛️ Turmas — `/turma`

| Método   | Rota                         | Descrição                          |
|----------|------------------------------|------------------------------------|
| `POST`   | `/turma`                     | Cadastrar nova turma               |
| `GET`    | `/turma`                     | Listar todas as turmas             |
| `GET`    | `/turma/{id}`                | Buscar turma por ID                |
| `PUT`    | `/turma/{id}`                | Atualizar dados da turma           |
| `DELETE` | `/turma/{id}`                | Remover turma                      |
| `GET`    | `/turma/curso/{cursoId}`     | Listar turmas de um curso          |
| `GET`    | `/turma/aluno/{turmaId}`     | Listar alunos de uma turma         |
| `POST`   | `/turma/aluno`               | Vincular aluno a uma turma         |

### 📖 Aulas — `/aula`

| Método   | Rota           | Descrição                        |
|----------|----------------|----------------------------------|
| `POST`   | `/aula`        | Registrar nova aula              |
| `GET`    | `/aula`        | Listar todas as aulas            |
| `GET`    | `/aula/{id}`   | Buscar aula por ID               |
| `PUT`    | `/aula/{id}`   | Atualizar dados da aula          |
| `DELETE` | `/aula/{id}`   | Remover aula                     |

### 📝 Notas — `/nota`

| Método   | Rota                    | Descrição                          |
|----------|-------------------------|------------------------------------|
| `POST`   | `/nota`                 | Lançar nota                        |
| `GET`    | `/nota`                 | Listar todas as notas              |
| `GET`    | `/nota/{id}`            | Buscar nota por ID                 |
| `GET`    | `/nota/aluno/{alunoId}` | Listar notas de um aluno           |
| `PUT`    | `/nota/{id}`            | Atualizar nota                     |
| `DELETE` | `/nota/{id}`            | Remover nota                       |

---

## 💻 Exemplos de Requisições

### Aluno

**Criar aluno**
```bash
curl -X POST http://localhost:8080/aluno \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "matricula": "2024001",
    "dataNascimento": "2000-05-15"
  }'
```

**Listar todos os alunos**
```bash
curl -X GET http://localhost:8080/aluno
```

**Buscar aluno por ID**
```bash
curl -X GET http://localhost:8080/aluno/1
```

**Atualizar aluno**
```bash
curl -X PUT http://localhost:8080/aluno/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva Atualizado",
    "email": "joao.novo@email.com",
    "matricula": "2024001",
    "dataNascimento": "2000-05-15"
  }'
```

**Deletar aluno**
```bash
curl -X DELETE http://localhost:8080/aluno/1
```

---

### Professor

**Criar professor**
```bash
curl -X POST http://localhost:8080/professor \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Souza",
    "email": "maria.souza@escola.com",
    "disciplina": "Matemática"
  }'
```

---

### Curso

**Criar curso**
```bash
curl -X POST http://localhost:8080/curso \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Engenharia de Software",
    "codigo": "ES2024"
  }'
```

**Listar turmas de um curso**
```bash
curl -X GET http://localhost:8080/curso/1/turmas
```

---

### Turma

**Criar turma** *(requer `cursoId` e `professorId` existentes)*
```bash
curl -X POST http://localhost:8080/turma \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Turma A - 2024",
    "cursoId": 1,
    "professorId": 1
  }'
```

**Vincular aluno a turma**
```bash
curl -X POST http://localhost:8080/turma/aluno \
  -H "Content-Type: application/json" \
  -d '{
    "idAluno": 1,
    "idTurma": 1
  }'
```

**Listar alunos de uma turma**
```bash
curl -X GET http://localhost:8080/turma/aluno/1
```

---

### Aula

**Registrar aula** *(requer `turmaId` existente)*
```bash
curl -X POST http://localhost:8080/aula \
  -H "Content-Type: application/json" \
  -d '{
    "turmaId": 1,
    "dataHora": "2024-09-10T08:00:00.000Z",
    "assunto": "Introdução ao Spring Boot"
  }'
```

---

### Nota

**Lançar nota** *(requer `alunoId` e `aulaId` existentes)*
```bash
curl -X POST http://localhost:8080/nota \
  -H "Content-Type: application/json" \
  -d '{
    "alunoId": 1,
    "aulaId": 1,
    "valor": 8.5
  }'
```

**Listar notas de um aluno**
```bash
curl -X GET http://localhost:8080/nota/aluno/1
```

---

## ⚙️ Como Rodar Localmente

### Pré-requisitos

- **Java 21** ([Download](https://adoptium.net/))
- **MySQL 8+** instalado e rodando
- **Maven** (ou usar o Maven Wrapper incluído no projeto)

### 1. Configurar o Banco de Dados MySQL

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE gestao_escolar;
```

> Por padrão, a aplicação espera:
> - **Host**: `localhost:3306`
> - **Banco**: `gestao_escolar`
> - **Usuário**: `root`
> - **Senha**: `mysqlPW`

Para alterar essas configurações, edite o arquivo:

```
gestaoescolar/src/main/java/com/weg/centroweg/gestaoescolar/infra/database/Conexao.java
```

```java
private static final String URL  = "jdbc:mysql://localhost:3306/gestao_escolar?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASS = "mysqlPW";
```

> ⚠️ **Atenção**: As credenciais estão diretamente no código. Recomenda-se movê-las para variáveis de ambiente ou para `application.properties` em ambientes de produção.

### 2. Clonar o Repositório

```bash
git clone https://github.com/kauaafeelix/gestao-escolar.git
cd gestao-escolar
```

### 3. Executar a Aplicação

Acesse a pasta do projeto Maven e execute com o Maven Wrapper:

```bash
cd gestaoescolar

# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

### 4. Build (opcional)

```bash
cd gestaoescolar
./mvnw clean package
java -jar target/gestaoescolar-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Testando com Postman

Como o projeto não possui Swagger/OpenAPI configurado, você pode testar os endpoints usando o **Postman** ou **cURL** (conforme os exemplos acima).

### Importar Coleção no Postman

1. Abra o **Postman**
2. Clique em **Import** → **Raw Text** (ou **File** se exportar uma coleção)
3. Configure a variável de ambiente `base_url` como `http://localhost:8080`
4. Use os endpoints listados na seção [Endpoints da API](#endpoints-da-api)

### Sugestão de Ordem para Testes

Para evitar erros de chave estrangeira, crie os registros nesta ordem:

1. `POST /professor` → cria o professor
2. `POST /curso` → cria o curso
3. `POST /turma` → cria a turma (usando `professorId` e `cursoId`)
4. `POST /aluno` → cria o aluno
5. `POST /turma/aluno` → vincula aluno à turma
6. `POST /aula` → cria a aula (usando `turmaId`)
7. `POST /nota` → lança a nota (usando `alunoId` e `aulaId`)

---

## 📄 Licença

Este projeto foi desenvolvido como aplicação de estudo. Sinta-se à vontade para utilizá-lo como referência.
