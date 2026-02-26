package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Aula;
import com.weg.centroweg.gestaoescolar.domain.repository.AulaRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepositoryImpl implements AulaRepository {

    @Override
    public Aula save(Aula aula) throws SQLException {
        String sql = """
                INSERT INTO aula (
                turma_id,
                data_hora,
                assunto )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, aula.getTurmaId());
            ps.setObject(2, aula.getDataHora());
            ps.setString(3, aula.getAssunto());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                aula.setId(idGerado);
                return aula;
            }
        }
        throw new RuntimeException("Aula não pôde ser salva no banco de dados.");
    }

    @Override
    public List<Aula> findAll() throws SQLException {
        String sql = """
                SELECT id,
                turma_id,
                data_hora,
                assunto 
                FROM aula
                """;

        List<Aula> aulas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aula aula = new Aula();
                aula.setId(rs.getInt("id"));
                aula.setTurmaId(rs.getInt("turma_id"));
                aula.setDataHora(Timestamp.valueOf(rs.getObject("data_hora", LocalDateTime.class)));
                aula.setAssunto(rs.getString("assunto"));

                aulas.add(aula);
            }
        }
        return aulas;
    }

    @Override
    public Aula findById(int id) throws SQLException {
        String sql = """
                SELECT id,
                turma_id,
                data_hora,
                assunto 
                FROM aula 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Aula aula = new Aula();
                aula.setId(rs.getInt("id"));
                aula.setTurmaId(rs.getInt("turma_id"));
                aula.setDataHora(Timestamp.valueOf(rs.getObject("data_hora", java.time.LocalDateTime.class)));
                aula.setAssunto(rs.getString("assunto"));

                return aula;
            }
        }
        return null;
    }

    @Override
    public void update(Aula aula) throws SQLException {
        String sql = """
                UPDATE aula SET 
                turma_id = ?, 
                data_hora = ?, 
                assunto = ? 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, aula.getTurmaId());
            ps.setObject(2, aula.getDataHora());
            ps.setString(3, aula.getAssunto());
            ps.setInt(4, aula.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = """
                DELETE FROM aula 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

