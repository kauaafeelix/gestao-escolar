package com.weg.centroweg.gestaoescolar.domain.entity;

public class Curso {

    private int id;
    private String nome;
    private String codigo;

    public Curso(int id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public Curso() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
