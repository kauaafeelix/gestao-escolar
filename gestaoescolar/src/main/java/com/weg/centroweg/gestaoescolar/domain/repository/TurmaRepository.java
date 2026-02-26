package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Turma;

import java.sql.SQLException;
import java.util.List;

public interface TurmaRepository {

    Turma save (Turma turma) throws SQLException;
    List<Turma> findAll () throws SQLException;
    Turma findById(int id) throws SQLException;
    void update(Turma turma) throws SQLException;
    void delete (int id) throws SQLException;

}
