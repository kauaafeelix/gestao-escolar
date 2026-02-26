package com.weg.centroweg.gestaoescolar.domain.entity;

public class TurmaAluno {
    private int turmaId;
    private int alunoId;


    public TurmaAluno(int turmaId, int alunoId) {
        this.turmaId = turmaId;
        this.alunoId = alunoId;
    }

    public TurmaAluno() {
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
}
