# Projeto de Revisão - Cadastro de Professores e Disciplinas

Este projeto é uma aplicação de desktop desenvolvida em Java para a gestão de professores e disciplinas, como parte de uma revisão de conceitos de programação.

## Funcionalidades

O sistema permite realizar as seguintes operações:

*   **Cadastro de Professores:**
    *   Inserir novos professores com nome e e-mail.
    *   Listar os professores cadastrados.

*   **Cadastro de Disciplinas:**
    *   Inserir novas disciplinas com nome, carga horária e associar um professor a ela.
    *   Listar as disciplinas cadastradas, exibindo os dados da disciplina e do professor associado.

*   **Consulta via Cliente-Servidor:**
    *   Um servidor fica ativo para consultas.
    *   Um cliente pode se conectar ao servidor para buscar, através do código da disciplina, o nome e o e-mail do professor que a leciona.

## Tecnologias Utilizadas

*   **Linguagem:** Java
*   **Interface Gráfica:** Java Swing
*   **Banco de Dados:** MySQL
*   **Comunicação:** Java Sockets para a funcionalidade cliente-servidor.
*   **Build:** Apache Ant (integrado com NetBeans)

## Como Executar o Projeto

### Pré-requisitos

*   Java JDK instalado.
*   Servidor de banco de dados MySQL ativo.

### 1. Configuração do Banco de Dados

1.  Crie um banco de dados no MySQL com o nome `escola`.
2.  Execute o script SQL contido no arquivo `src/BancoSQL.txt` para criar as tabelas `professores` e `disciplinas`.
3.  Abra o arquivo `src/dao/Conexao.java` e, se necessário, altere o usuário e a senha do banco de dados na string de conexão.

### 2. Executando a Aplicação Principal (GUI)

A aplicação principal, que contém a interface gráfica para os cadastros, pode ser iniciada executando a classe `main.Main.java`. No NetBeans, basta executar o projeto.

### 3. Executando o Servidor de Consultas

Para a funcionalidade de consulta, o servidor deve ser iniciado. Execute a classe `servidor.Servidor.java`.

### 4. Executando o Cliente de Consultas

Com o servidor ativo, execute a classe `cliente.Cliente.java`. Será solicitado o código de uma disciplina para a consulta.

## Estrutura do Projeto

O código-fonte está organizado nos seguintes pacotes dentro de `src/`:

*   `main`: Contém a classe principal que inicia a aplicação Swing.
*   `model`: Contém as classes de modelo `Professor` e `Disciplina`.
*   `dao`: Contém as classes de acesso a dados (`ProfessorDAO`, `DisciplinaDAO`) e a classe de conexão com o banco de dados (`Conexao`).
*   `view`: Contém os formulários Swing (`FrmProfessor`, `FrmDisciplina`, `MenuPrincipal`).
*   `servidor`: Contém a classe `Servidor` para a comunicação via socket.
*   `cliente`: Contém a classe `Cliente` para a comunicação via socket.
