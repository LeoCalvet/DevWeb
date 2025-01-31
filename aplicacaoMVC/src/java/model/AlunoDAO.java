package model;

import entidade.Aluno;
import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {

    public void inserir(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());

            System.out.println("Executando query: " + sqlQuery);
            sql.executeUpdate();
            System.out.println("Aluno inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro ao inserir aluno no banco de dados.", e);
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Aluno> listar() throws Exception {
        ArrayList<Aluno> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCelular(rs.getString("celular"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCep(rs.getString("cep"));
                lista.add(aluno);
            }
        } finally {
            conexao.closeConexao();
        }
        return lista;
    }

    public void alterar(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE alunos SET nome=?, email=?, celular=?, cpf=?, senha=?, endereco=?, cidade=?, bairro=?, cep=? WHERE id=?"
            );
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            sql.setInt(10, aluno.getId());
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public void excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM alunos WHERE id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } finally {
            conexao.closeConexao();
        }
    }

    public Aluno getAluno(int id) throws Exception {
        Conexao conexao = new Conexao();
        Aluno aluno = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos WHERE id = ?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCelular(rs.getString("celular"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCep(rs.getString("cep"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o aluno no banco de dados.", e);
        } finally {
            conexao.closeConexao();
        }
        return aluno;
    }

    public Aluno autenticar(String cpf, String senha) throws Exception {
        Conexao conexao = new Conexao();
        Aluno aluno = null;

        try {
            String sql = "SELECT * FROM alunos WHERE cpf = ? AND senha = ?";
            PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEmail(rs.getString("email"));
            }
        } finally {
            conexao.closeConexao();
        }

        return aluno;
    }
    
}
