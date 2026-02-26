package com.weg.centroweg.gestaoescolar.infra.persistence;

import com.weg.centroweg.gestaoescolar.domain.entity.Aluno;
import com.weg.centroweg.gestaoescolar.domain.repository.AlunoRepository;
import com.weg.centroweg.gestaoescolar.infra.database.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository

    public class AlunoRepositoryImpl implements AlunoRepository {

        @Override
        public Aluno save(Aluno aluno) throws SQLException {
            String sql = """
                INSERT INTO aluno (
                nome, 
                email, 
                matricula, 
                data_nascimento) 
                VALUES (?, ?, ?, ?)
                """;

            try (Connection conn = Conexao.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEmail());
                ps.setString(3, aluno.getMatricula());
                ps.setObject(4, aluno.getDataNascimento());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        aluno.setId(rs.getInt(1));
                        return aluno;
                    }
            }
            throw new RuntimeException("Aluno não pôde ser salvo no banco de dados.");
        }

        @Override
        public List<Aluno> findAll() throws SQLException {
            String sql = """
                SELECT 
                    id,
                    nome,
                    email,
                    matricula,
                    data_nascimento
                FROM aluno

            """;
            List<Aluno> alunos = new ArrayList<>();

            try (Connection conn = Conexao.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("matricula"),
                            rs.getObject("data_nascimento", LocalDate.class)
                    );
                    alunos.add(aluno);
                }
            }
            return alunos;
        }

        @Override
        public Aluno findById(int id) throws SQLException {
            String sql = "SELECT id, nome, email, matricula, data_nascimento FROM aluno WHERE id = ?";

            try (Connection conn = Conexao.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int idBanco = rs.getInt("id");
                        String nome = rs.getString("nome");
                        String email = rs.getString("email");
                        String matricula = rs.getString("matricula");
                        LocalDate dataNascimento = rs.getObject("data_nascimento", LocalDate.class);

                        return new Aluno(idBanco, nome, email, matricula, dataNascimento);
                    }

            }
            throw new RuntimeException("Aluno não encontrado");
        }

        @Override
        public void update(Aluno aluno) throws SQLException {
            String sql = """
                UPDATE aluno SET 
                nome = ?, 
                email = ?, 
                matricula = ?, 
                data_nascimento = ? 
                WHERE id = ?
                """;

            try (Connection conn = Conexao.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEmail());
                ps.setString(3, aluno.getMatricula());
                ps.setObject(4, aluno.getDataNascimento());
                ps.setLong(5, aluno.getId());

                ps.executeUpdate();
            }
        }

        @Override
        public void delete(int id) throws SQLException {
            String sql = "DELETE FROM aluno WHERE id = ?";

            try (Connection conn = Conexao.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ps.executeUpdate();
            }
    }
}
