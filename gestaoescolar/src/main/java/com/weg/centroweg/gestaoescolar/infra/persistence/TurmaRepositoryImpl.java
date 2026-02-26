package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Turma;
import com.weg.centroweg.gestaoescolar.domain.entity.TurmaAluno;
import com.weg.centroweg.gestaoescolar.domain.repository.TurmaRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepositoryImpl implements TurmaRepository {

    @Override
    public Turma save(Turma turma) throws SQLException {
        String sql = """
                INSERT INTO turma (
                nome,
                curso_id,
                professor_id )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, turma.getNome());
            ps.setInt(2, turma.getCursoId());
            ps.setInt(3, turma.getProfessorId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                turma.setId(idGerado);
                return turma;
            }
        }
        throw new RuntimeException("Turma não pôde ser salva no banco de dados.");
    }

    @Override
    public List<Turma> findAll() throws SQLException {
        String sql = """
                SELECT id,
                nome,
                curso_id,
                professor_id 
                FROM turma
                """;

        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setNome(rs.getString("nome"));
                turma.setCursoId(rs.getInt("curso_id"));
                turma.setProfessorId(rs.getInt("professor_id"));

                turmas.add(turma);
            }
        }
        return turmas;
    }

    @Override
    public Turma findById(int id) throws SQLException {
        String sql = """
                SELECT id,
                nome,
                curso_id,
                professor_id 
                FROM turma 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setNome(rs.getString("nome"));
                turma.setCursoId(rs.getInt("curso_id"));
                turma.setProfessorId(rs.getInt("professor_id"));

                return turma;
            }
        }
        return null;
    }

    @Override
    public void saveTurmaAluno(TurmaAluno turmaAluno) throws SQLException{
        String sql = """
                INSERT INTO turma_aluno (
                turma_id,
                aluno_id )
                VALUES (?, ?)
            """;

        try (Connection conn = Conexao.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, turmaAluno.getTurmaId());
            ps.setInt(2, turmaAluno.getAlunoId());

            ps.executeUpdate();
        }

    }

    @Override
    public void update(Turma turma) throws SQLException {
        String sql = """
                UPDATE turma SET 
                nome = ?, 
                curso_id = ?, 
                professor_id = ? 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, turma.getNome());
            ps.setInt(2, turma.getCursoId());
            ps.setInt(3, turma.getProfessorId());
            ps.setInt(4, turma.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = """
                DELETE FROM turma 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
