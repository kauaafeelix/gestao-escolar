package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Curso;
import com.weg.centroweg.gestaoescolar.domain.repository.CursoRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @Override
    public Curso save(Curso curso) throws SQLException {
        String sql = """
                INSERT INTO curso (
                nome,
                codigo )
                VALUES (?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getCodigo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                curso.setId(idGerado);
                return curso;
            }
        }
        throw new RuntimeException("Curso não pôde ser salvo no banco de dados.");
    }

    @Override
    public List<Curso> findAll() throws SQLException {
        String sql = """
                SELECT id,
                nome,
                codigo 
                FROM curso
                """;

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setCodigo(rs.getString("codigo"));

                cursos.add(curso);
            }
        }
        return cursos;
    }

    @Override
    public Curso findById(int id) throws SQLException {
        String sql = """
                SELECT id,
                nome,
                codigo 
                FROM curso 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setCodigo(rs.getString("codigo"));

                return curso;
            }
        }
        return null;
    }

    @Override
    public void update(Curso curso) throws SQLException {
        String sql = """
                UPDATE curso SET 
                nome = ?, 
                codigo = ? 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getCodigo());
            ps.setInt(3, curso.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = """
                DELETE FROM curso 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
