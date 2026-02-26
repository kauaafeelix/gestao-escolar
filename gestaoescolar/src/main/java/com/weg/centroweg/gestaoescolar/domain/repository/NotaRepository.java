package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Nota;

import java.sql.SQLException;
import java.util.List;

public interface NotaRepository {


    Nota save (Nota nota) throws SQLException;
    List<Nota> findAll () throws SQLException;
    Nota findById(int id) throws SQLException;
    List<Nota> findByAlunoId(int alunoId) throws SQLException;
    void update(Nota nota) throws SQLException;
    void delete (int id) throws SQLException;
}
