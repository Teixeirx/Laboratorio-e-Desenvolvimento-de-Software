
package com.escola.model;

/**
 * Representa um Professor na instituição.
 * Armazena informações básicas do professor.
 */
public class Professor {

    private int id;
    private String nome;
    private int idade;
    private String disciplina;

    public Professor() {
    }

    public Professor(String nome, int idade, String disciplina) {
        this.nome = nome;
        this.idade = idade;
        this.disciplina = disciplina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return getNome(); // Para exibição em JComboBox
    }
}
