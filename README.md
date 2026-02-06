# 🚀 User Management System - CRUD Java 21

Uma aplicação Back-End robusta para gerenciamento de usuários, desenvolvida para demonstrar conceitos avançados de Programação Orientada a Objetos (POO), 
manipulação de coleções em memória e tratamento rigoroso de exceções.



## 📋 Sobre o Projeto
O sistema simula o comportamento de um banco de dados local, permitindo realizar as quatro operações básicas (**CRUD**):
- **C**reate: Cadastro de novos usuários com geração automática de ID.
- **R**ead: Listagem geral ou busca específica por ID.
- **U**pdate: Atualização de dados cadastrais.
- **D**elete: Remoção de registros com validação de existência.

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java 21 (LTS)
- **API de Data:** `java.time.OffsetDateTime` para persistência temporal precisa.
- **Estrutura de Dados:** `ArrayList` para simulação de banco de dados.
- **Arquitetura:** Padrão **DAO** (Data Access Object) para separação de responsabilidades.

## ✨ Diferenciais Implementados
- **Tratamento de Exceções Customizadas:** Criação de exceções específicas como `UserNotFoundException` e `InvalidUserDataException`.
- **Validação de Integridade:** Verificação de e-mails (presença de `@` e `.`) e tratamento de formatos de data.
- **Segurança de Fluxo:** Implementação de `try-catch` para evitar o fechamento inesperado da aplicação em caso de entradas inválidas no console.

## 📂 Estrutura do Projeto
```text
src/
 └── br/com/
      ├── dao/          # Camada de persistência (Simulação de DB)
      ├── exception/    # Exceções personalizadas
      ├── model/        # Classes de entidade (UserModel) e Enums
      └── Main.java     # Classe principal com interface via console

```

## 🚀 Como Executar:

   1. Certifique-se de ter o JDK 21 instalado.

   2. Clone o repositório:

      git clone: [https://github.com/Efra85/SISTEMA-GESTOR-DE-USUARIOS-COM-DB-LOCAL.git]

   3. Compile o projeto:

      bash
      javac br/com/Main.java

   4. Execute:

      bash
      java br.com.Main



