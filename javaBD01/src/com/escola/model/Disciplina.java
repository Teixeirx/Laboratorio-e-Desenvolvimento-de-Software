
package com.escola.model;

/**
 * Representa uma Disciplina na instituição.
 * Armazena informações da disciplina e a associação com um professor.
 */
public class Disciplina {

    private int id;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    public Disciplina() {
    }

    public Disciplina(String nome, int cargaHoraria, Professor professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + ''' +
                ", cargaHoraria=" + cargaHoraria +
                ", professor=" + professor +
                '}';
    }
}
