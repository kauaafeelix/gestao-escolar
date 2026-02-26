package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Aluno;

import java.sql.SQLException;
import java.util.List;


public interface AlunoRepository {

    Aluno save (Aluno aluno) throws SQLException;
    List<Aluno> findAll () throws SQLException;
    Aluno findById(int id) throws SQLException;
    void update(Aluno aluno) throws SQLException;
    void delete (int id) throws SQLException;
    Aluno findByEmail(String email) throws SQLException;
}
