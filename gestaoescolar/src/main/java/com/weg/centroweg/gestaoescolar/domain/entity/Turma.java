package com.weg.centroweg.gestaoescolar.domain.entity;

public class Turma {

    private int id;
    private String nome;
    private int cursoId;
    private int professorId;

    public Turma(int id, String nome, int cursoId, int professorId) {
        this.id = id;
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
    }

    public Turma(String nome, int cursoId, int professorId) {
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
    }

    public Turma() {
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

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
