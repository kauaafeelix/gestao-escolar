package com.weg.centroweg.gestaoescolar.domain.entity;

import java.time.LocalDate;

public class Aluno {

    private int id;
    private String nome;
    private String email;
    private String matricula;
    private LocalDate dataNascimento;


    public Aluno(int id, String nome, String email, String matricula, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(String nome, String email, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Aluno() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
