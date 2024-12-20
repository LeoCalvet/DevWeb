# Projeto MVC para Gerenciamento Acadêmico

Este é um projeto acadêmico desenvolvido em **Java** utilizando o padrão **MVC** (Model-View-Controller). Ele é voltado para a gestão de informações relacionadas a alunos, professores, disciplinas, turmas e administradores em um sistema simples e funcional.

## Propósito do Projeto

O objetivo principal do projeto é oferecer uma plataforma básica para gerenciar dados de uma instituição acadêmica. Isso inclui o cadastro, edição, exclusão e listagem de registros relacionados a alunos, professores, disciplinas, turmas e administradores, além de validações para assegurar a integridade dos dados.

## Estrutura do Projeto

O projeto foi desenvolvido com foco na organização do código e utiliza o padrão **MVC** para separar as responsabilidades:

- **Model**: Representa os dados do sistema e as operações sobre eles (DAO e entidades).
- **View**: Responsável pela interface com o usuário (arquivos JSP).
- **Controller**: Coordena a interação entre a View e o Model.

## Por que há um `.jar` no diretório inicial?

Há um arquivo `.jar` no diretório inicial do projeto, utilizado como dependência para o funcionamento do sistema. Foi necessário posicioná-lo nesta localização para que o **NetBeans** pudesse identificá-lo corretamente e permitir a compilação do projeto. Essa abordagem foi uma solução prática para resolver problemas relacionados à configuração automática de bibliotecas.

## Funcionalidades Criadas

As seguintes funcionalidades foram implementadas:

### **Gerenciamento de Usuários**
- [x] **Cadastro de Alunos**: Campos obrigatórios validados (nome, e-mail, celular, CPF, senha) e lógica para campos opcionais.
- [x] **Edição de Alunos**
- [x] **Listagem de Alunos**
- [x] **Exclusão de Alunos**
- [x] **Cadastro de Professores**: Inclui validações semelhantes às de Alunos.
- [x] **Edição de Professores**
- [x] **Listagem de Professores**
- [x] **Exclusão de Professores**
- [x] **Cadastro de Administradores**: Validação adicional para o campo "aprovado".
- [x] **Edição de Administradores**
- [x] **Listagem de Administradores**
- [x] **Exclusão de Administradores**

### **Gerenciamento Acadêmico**
- [x] **Cadastro de Disciplinas**: Validação de campos como nome, ementa e carga horária.
- [x] **Edição de Disciplinas**
- [x] **Listagem de Disciplinas**
- [x] **Exclusão de Disciplinas**
- [x] **Cadastro de Turmas**: Inclui seleção de professores, disciplinas e alunos.
- [x] **Edição de Turmas**
- [x] **Listagem de Turmas**
- [x] **Exclusão de Turmas**

### **Autenticação e Controle de Acesso**
- [x] **Login de Administradores**: Validação de CPF e senha.
- [x] **Sessões**: Controle de acesso baseado em sessões para proteger áreas administrativas.

## Requisitos Funcionais Atendidos

- [x] **Validações no Servidor**: Implementadas para verificar campos obrigatórios, formatos de dados e regras de negócio.
- [x] **Validações no Cliente**: Adição de atributos HTML como `pattern`, `maxlength` e `required` para validações básicas.
- [x] **Separação de Responsabilidades**: Código organizado com o padrão MVC para fácil manutenção.
- [x] **Interface Responsiva**: Utilização do **Bootstrap** para estilização e responsividade das telas.
- [x] **Mensagens de Erro**: Feedback ao usuário em caso de entradas inválidas ou erros de autenticação.

## Como Executar o Projeto

1. Clone o repositório para sua máquina local.
2. Abra o projeto no **NetBeans**.
3. Certifique-se de que o servidor **Tomcat** esteja configurado corretamente.
4. Certifique-se de que o banco de dados MySQL está configurado e as tabelas necessárias foram criadas.
5. Execute o projeto e acesse no navegador a URL base, como `http://localhost:8081/aplicacaoMVC`.

---

Este projeto demonstra a aplicação prática do padrão MVC em um sistema acadêmico básico, com foco em validação de dados e organização do código. Caso tenha dúvidas ou sugestões, sinta-se à vontade para contribuir!
