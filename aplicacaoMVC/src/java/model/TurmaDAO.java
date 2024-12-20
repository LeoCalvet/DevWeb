package model;

import entidade.Turma;
import java.sql.*;
import java.util.ArrayList;

public class TurmaDAO {

    public void inserir(Turma turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?,?,?,?,?)"
            );
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setDouble(5, turma.getNota());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turma> listar() throws Exception {
        ArrayList<Turma> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String query
                    = "SELECT t.id, t.codigo_turma, t.nota, "
                    + "       p.nome AS professor_nome, "
                    + "       d.nome AS disciplina_nome, "
                    + "       a.nome AS aluno_nome "
                    + "FROM turmas t "
                    + "INNER JOIN professores p ON t.professor_id = p.id "
                    + "INNER JOIN disciplina d ON t.disciplina_id = d.id "
                    + "INNER JOIN alunos a ON t.aluno_id = a.id";

            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setCodigoTurma(rs.getString("codigo_turma"));
                turma.setNota(rs.getBigDecimal("nota").doubleValue());
                turma.setProfessorNome(rs.getString("professor_nome"));
                turma.setDisciplinaNome(rs.getString("disciplina_nome"));
                turma.setAlunoNome(rs.getString("aluno_nome"));
                lista.add(turma);
            }
        } finally {
            conexao.closeConexao();
        }
        return lista;
    }

    public void alterar(Turma turma) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE turmas SET professor_id=?, disciplina_id=?, aluno_id=?, codigo_turma=?, nota=? WHERE id=?"
            );
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setDouble(5, turma.getNota());
            sql.setInt(6, turma.getId());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public void excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM turmas WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public Turma getTurma(int id) throws Exception {
        Conexao conexao = new Conexao();
        Turma turma = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM turmas WHERE id=?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setProfessorId(rs.getInt("professor_id"));
                turma.setDisciplinaId(rs.getInt("disciplina_id"));
                turma.setAlunoId(rs.getInt("aluno_id"));
                turma.setCodigoTurma(rs.getString("codigo_turma"));
                turma.setNota(rs.getDouble("nota"));
            }
        } finally {
            conexao.closeConexao();
        }
        return turma;
    }
}
