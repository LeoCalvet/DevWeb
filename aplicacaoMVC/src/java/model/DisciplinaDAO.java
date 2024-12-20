package model;

import entidade.Disciplina;
import java.sql.*;
import java.util.ArrayList;

public class DisciplinaDAO {

    public void inserir(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "INSERT INTO disciplina (nome, requisito, ementa, carga_horaria) VALUES (?, ?, ?, ?)"
            );
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Disciplina> listar() throws Exception {
        ArrayList<Disciplina> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM disciplina");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setRequisito(rs.getString("requisito"));
                disciplina.setEmenta(rs.getString("ementa"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                lista.add(disciplina);
            }
        } finally {
            conexao.closeConexao();
        }
        return lista;
    }

    public void alterar(Disciplina disciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "UPDATE disciplina SET nome=?, requisito=?, ementa=?, carga_horaria=? WHERE id=?"
            );
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.setInt(5, disciplina.getId());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public void excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM disciplina WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public Disciplina getDisciplina(int id) throws Exception {
        Conexao conexao = new Conexao();
        Disciplina disciplina = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM disciplina WHERE id=?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setRequisito(rs.getString("requisito"));
                disciplina.setEmenta(rs.getString("ementa"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
            }
        } finally {
            conexao.closeConexao();
        }
        return disciplina;
    }
}
