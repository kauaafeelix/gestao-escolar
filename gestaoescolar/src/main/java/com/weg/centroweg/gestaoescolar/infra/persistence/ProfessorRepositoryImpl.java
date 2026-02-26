package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Professor;
import com.weg.centroweg.gestaoescolar.domain.repository.ProfessorRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {

    @Override
    public Professor save(Professor professor) throws SQLException {
        String sql = """
                INSERT INTO professor (
                nome,
                email,
                disciplina )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getDisciplina());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                professor.setId(idGerado);
                return professor;
            }
        }
        throw new RuntimeException("Professor não pôde ser salvo no banco de dados.");
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        String sql = """
                SELECT id,
                nome,
                email,
                disciplina 
                FROM professor
                """;

        List<Professor> professores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setDisciplina(rs.getString("disciplina"));

                professores.add(professor);
            }
        }
        return professores;
    }

    @Override
    public Professor findById(int id) throws SQLException {
        String sql = """
                SELECT id,
                nome,
                email,
                disciplina 
                FROM professor 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setDisciplina(rs.getString("disciplina"));

                return professor;
            }
        }
        return null;
    }

    @Override
    public void update(Professor professor) throws SQLException {
        String sql = """
                UPDATE professor SET 
                nome = ?, 
                email = ?, 
                disciplina = ? 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setString(3, professor.getDisciplina());
            ps.setInt(4, professor.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = """
                DELETE FROM professor 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
