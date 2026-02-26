package com.weg.centroweg.gestaoescolar.domain.repository;

import com.weg.centroweg.gestaoescolar.domain.entity.Professor;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorRepository {

    Professor save (Professor professor) throws SQLException;
    List<Professor> findAll () throws SQLException;
    Professor findById(int id) throws SQLException;
    void update(Professor professor) throws SQLException;
    void delete (int id) throws SQLException;
}
