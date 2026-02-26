package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Nota;
import com.weg.centroweg.gestaoescolar.domain.repository.NotaRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class NotaRepositoryImpl implements NotaRepository {

    @Override
    public Nota save(Nota nota) throws SQLException {
        String sql = """
                INSERT INTO nota (
                aluno_id,
                aula_id,
                valor )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, nota.getAlunoId());
            ps.setInt(2, nota.getAulaId());
            ps.setDouble(3, nota.getValor());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                nota.setId(idGerado);
                return nota;
            }
        }
        throw new RuntimeException("Nota não pôde ser salva no banco de dados.");
    }

    @Override
    public List<Nota> findAll() throws SQLException {
        String sql = """
                SELECT id,
                aluno_id,
                aula_id,
                valor 
                FROM nota
                """;

        List<Nota> notas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setAlunoId(rs.getInt("aluno_id"));
                nota.setAulaId(rs.getInt("aula_id"));
                nota.setValor(rs.getDouble("valor"));

                notas.add(nota);
            }
        }
        return notas;
    }

    @Override
    public Nota findById(int id) throws SQLException {
        String sql = """
                SELECT id,
                aluno_id,
                aula_id,
                valor 
                FROM nota 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setAlunoId(rs.getInt("aluno_id"));
                nota.setAulaId(rs.getInt("aula_id"));
                nota.setValor(rs.getDouble("valor"));

                return nota;
            }
        }
        return null;
    }

    @Override
    public List<Nota> findByAlunoId(int alunoId) throws SQLException {
        String sql = """
                SELECT id,
                aluno_id,
                aula_id,
                valor 
                FROM nota 
                WHERE aluno_id = ?
                """;

        List<Nota> notas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setAlunoId(rs.getInt("aluno_id"));
                nota.setAulaId(rs.getInt("aula_id"));
                nota.setValor(rs.getDouble("valor"));

                notas.add(nota);
            }
        }
        return notas;
    }

    @Override
    public void update(Nota nota) throws SQLException {
        String sql = """
                UPDATE nota SET 
                aluno_id = ?, 
                aula_id = ?, 
                valor = ? 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nota.getAlunoId());
            ps.setInt(2, nota.getAulaId());
            ps.setDouble(3, nota.getValor());
            ps.setInt(4, nota.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = """
                DELETE FROM nota 
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
