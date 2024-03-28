# Case Técnico Alura - Dev Back-End Java 🤿

![Alura banner](https://github.com/vinelouzada/alurex/assets/56182156/6feb85f9-b5f2-4899-94e5-29737d14378a)


Desafio: Implementação da plataforma da [Alura](https://www.alura.com.br/) 🎓

## Pré-requisitos

- Docker
- Docker Compose

## Tecnologias utilizadas
- Java 21 ☕
- Spring Boot 🌱
- MySQL 🐬
- Docker 🐳
- Docker Compose 🐙
  
## Instalação
- Faça o clone do projeto: `git clone https://github.com/vinelouzada/alurex`
- Abra o projeto e execute: `docker-compose up --build`

## Documentação 
Para explorar a documentação completa, acesse o Swagger em: `http://localhost:8080/swagger-ui.html`

### Modelagem do banco de dados

![modelagem-alurex](https://github.com/vinelouzada/alurex/assets/56182156/b6c31c5b-2f17-4080-bf59-ba356f8530e8)


## Endpoints

### `UserController`

 - `POST /user`: Cria um novo usuário.

 - `GET /user/{username}`: Recupera informações sobre um usuário específico com base no nome de usuário fornecido.

### `LoginController`

- `POST /login`: Permite que um usuário faça login no sistema.

### `FeedbackController`

- `GET /feedback`: Retorna um relatório com o **NPS** dos cursos.

- `POST /feedback`: Cria um novo feedback.

### `EnrollmentController`
- `POST /enrollment`: Permite que um usuário se matricule em um curso.
- `GET /enrollment/{userId}/{courseId}`: Recupera informações sobre a matricula de um usuário em um curso.

### `CourseController`

- `GET /course`: Recupera informações sobre todos os cursos disponíveis.
- `POST /course`: Cria um novo curso.
- `PATCH /course/inactive`: Atualiza informações para inativar um curso



## Demonstração de dados

A aplicação já inclui alguns registros de demonstração. Abaixo será apresentado um **resumo** desses registros

### Users

| id | name             | role       |
|----|------------------|------------|
| 1  | Claire Redfield  | STUDENT    |
| 2  | Jill Valentine   | INSTRUCTOR |
| 4  | Ada Wong         | ADMIN      |

### Courses

| id | Course                        | Status   | User ID |
|----|-------------------------------|----------|---------|
| 1  | PHP e MySQL                | ACTIVE   | 2       |
| 4  | PHP: Conceito                | ACTIVE   | 3       |
| 5  | Terminal                      | INACTIVE | 3       |

## Enrollments

| user_id | course_id | score | reason |
|---------|-----------|-------|--------|
| 1       | 1         | 5     | xpto1  |
| 1       | 4         | NULL  | NULL   |
| 5       | 1         | 5     | xpto2  |



Desenvolvido 💜 por <a href="https://github.com/vinelouzada">vinelouzada</a>
