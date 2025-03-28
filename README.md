# Projeto Integrador - Cesmac

Este é um projeto de API REST desenvolvido em Java como parte do projeto integrador da Faculdade Cesmac. Ele utiliza **Spring Boot 3**, **Java 21**, **Gradle** e **Docker Compose** para criar uma solução robusta de backend. O projeto inclui um banco de dados PostgreSQL e é amplamente testado com **Mockito** e **AssertJ** para testes unitários e de integração.

A API gerencia registros de alunos, permitindo listar e criar alunos, e serve como base para expansões futuras no âmbito acadêmico ou prático.

## Funcionalidades
- **API RESTful**: Endpoints para listar e criar alunos.
- **Banco de Dados**: PostgreSQL gerenciado via Docker Compose.
- **Testes**: Testes unitários e de integração abrangentes com Mockito e AssertJ.

## Tecnologias Utilizadas
- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.2.4
- **Ferramenta de Build**: Gradle
- **Banco de Dados**: PostgreSQL 15
- **Testes**: Mockito, AssertJ, Spring Boot Test
- **Containerização**: Docker, Docker Compose

## Estrutura do Projeto
```
projeto-integrador/
├── src/
│   ├── main/
│   │   ├── java/br/cesmac/integrador/
│   │   │   ├── controllers/       # Controladores REST
│   │   │   ├── services/          # Lógica de negócio
│   │   │   ├── repositories/       # Camada de acesso a dados
│   │   │   ├── entities/            # Entidades
│   │   │   └── PiApplication.java  # Aplicação principal
│   │   └── resources/
│   │       ├── application.yml   # Configuração
│   └── test/                     # Testes unitários e de integração
├── docker-compose.yml            # Configuração do Docker Compose
├── build.gradle                  # Script de build do Gradle
└── README.md                     # Documentação do projeto
```

## Pré-requisitos
- **Java 21**: Certifique-se de ter o JDK 21 instalado (ex.: Eclipse Temurin).
- **Docker**: Banco de dados.

## Endpoints da API
| Método | Endpoint        | Descrição                | Exemplo de Corpo da Requisição           |
|--------|-----------------|--------------------------|------------------------------------------|
| GET    | `/api/students` | Lista todos os alunos    | N/A                                      |
| POST   | `/api/students` | Cria um novo aluno       | `{"name": "João", "registrationNumber": "12345"}` |

### Exemplos de Requisições
- **Listar Alunos**:
  ```
  curl http://localhost:8080/api/students
  ```
- **Criar Aluno**:
  ```
  curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d '{"name": "João", "registrationNumber": "12345"}'
  ```

## Executando Testes
O projeto inclui testes unitários para o `StudentService` e testes de integração para o `StudentController`.

1. Execute todos os testes:
   ```
   ./gradlew test
   ```
2. Cenários de teste incluem:
    - Listagem de alunos (com e sem dados).
    - Criação de alunos (entradas válidas e inválidas).
    - Tratamento de exceções e casos extremos.

## Cobertura de Testes
- **Testes Unitários**: Verificam a lógica do serviço com repositórios simulados.
- **Testes de Integração**: Testam os endpoints do controlador e requisições HTTP reais.
- **Ferramentas**: Mockito para simulação, AssertJ para asserções fluentes.