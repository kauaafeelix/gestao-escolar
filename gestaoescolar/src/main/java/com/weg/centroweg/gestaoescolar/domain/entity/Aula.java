package com.weg.centroweg.gestaoescolar.domain.entity;

import java.sql.Timestamp;

public class Aula {

    private int id;
    private int turmaId;
    private Timestamp dataHora;
    private String assunto;


    public Aula(int id, int turmaId, Timestamp dataHora, String assunto) {
        this.id = id;
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula(int turmaId, Timestamp dataHora, String assunto) {
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
