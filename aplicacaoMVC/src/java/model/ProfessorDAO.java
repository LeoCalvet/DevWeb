package model;

import entidade.Professor;
import entidade.Turma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void inserir(Professor professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO professores (nome, email, cpf, senha) VALUES (?,?,?,?)"
            );
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Professor> listar() throws Exception {
        ArrayList<Professor> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM professores");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSenha(rs.getString("senha"));
                lista.add(professor);
            }
        } finally {
            conexao.closeConexao();
        }
        return lista;
    }

    public void alterar(Professor professor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE professores SET nome=?, email=?, cpf=?, senha=? WHERE id=?"
            );
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.setInt(5, professor.getId());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public void excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM professores WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public Professor getProfessor(int id) throws Exception {
        Conexao conexao = new Conexao();
        Professor professor = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM professores WHERE id=?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSenha(rs.getString("senha"));
            }
        } finally {
            conexao.closeConexao();
        }
        return professor;
    }

    public Professor autenticar(String cpf, String senha) throws Exception {
        Conexao conexao = new Conexao();
        Professor professor = null;

        try {
            String sql = "SELECT * FROM professores WHERE cpf = ? AND senha = ?";
            PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setSenha(rs.getString("senha"));
            }
        } finally {
            conexao.closeConexao();
        }

        return professor;
    }

    public List<Turma> listarTurmasPorProfessor(int professorId) throws Exception {
        List<Turma> turmas = new ArrayList<>();
        Conexao conexao = new Conexao();

        try {
            String sqlQuery = "SELECT t.id, t.codigo_turma, t.nota, d.nome AS disciplina_nome, a.nome AS aluno_nome "
                    + "FROM turmas t "
                    + "JOIN disciplina d ON t.disciplina_id = d.id "
                    + "JOIN alunos a ON t.aluno_id = a.id "
                    + "WHERE t.professor_id = ?";
            PreparedStatement stmt = conexao.getConexao().prepareStatement(sqlQuery);
            stmt.setInt(1, professorId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setCodigoTurma(rs.getString("codigo_turma"));
                turma.setNota(rs.getDouble("nota"));
                turma.setDisciplinaNome(rs.getString("disciplina_nome"));
                turma.setAlunoNome(rs.getString("aluno_nome"));
                turmas.add(turma);
            }
        } finally {
            conexao.closeConexao();
        }

        return turmas;
    }
}
