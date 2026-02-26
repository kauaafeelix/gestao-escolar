package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Aula;

import java.sql.SQLException;
import java.util.List;

public interface AulaRepository {
    Aula save (Aula aula) throws SQLException;
    List<Aula> findAll () throws SQLException;
    Aula findById(int id) throws SQLException;
    void update(Aula aula) throws SQLException;
    void delete (int id) throws SQLException;

}
