# API para gerenciamento de livros
## Descrição
Essa API foi criada para exercitar o aprendizado com o Spring Security. Ela faz um CRUD básico com relacionamentos 'many to many' entre as entidades Book and Author.
A autenticação e a autorização é feita com os JWTs (Json Web Tokens). Dentro desses tokens estão as informações de Login de um usuário junto da sua ROLE, que dá a autorização das requisições que os usuários podem fazer.
## O que a API pode fazer?
- Registrar usuários
- Fazer login desses usuários e retornar um token
- Registrar Livros e Autores (Apenas para usuários com ROLE admin)
- Listar Livros e Autores
- Retornar livros ou autores pelo seu id, nome ou título
- Deletar livros e autores (Apenas para usuários com ROLE admin)

## Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Security
- Auth0 JWT
- H2 database
- Log4j

## Como clonar o projeto
Para clonar o repositório, execute o seguinte comando no terminal:

```bash
git clone https://github.com/wendoxx/library-api.git
