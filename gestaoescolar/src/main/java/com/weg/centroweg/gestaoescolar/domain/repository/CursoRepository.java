package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Curso;

import java.sql.SQLException;
import java.util.List;

public interface CursoRepository {

    Curso save (Curso curso) throws SQLException;
    List<Curso> findAll () throws SQLException;
    Curso findById(int id) throws SQLException;
    void update(Curso curso) throws SQLException;
    void delete (int id) throws SQLException;
}
