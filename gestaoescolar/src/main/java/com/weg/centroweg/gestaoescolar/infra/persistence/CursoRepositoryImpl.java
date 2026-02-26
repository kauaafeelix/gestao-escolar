package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Curso;
import com.weg.centroweg.gestaoescolar.domain.repository.CursoRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CursoRepositoryImpl implements CursoRepository {
    @Override
    public Curso save(Curso curso) throws SQLException {
        return null;
    }

    @Override
    public List<Curso> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public Curso findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Curso curso) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
