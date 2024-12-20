package controller;

import entidade.Aluno;
import model.AlunoDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlunoController", urlPatterns = {"/admin/aluno"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", alunoDAO.listar());
                        request.getRequestDispatcher("/views/admin/aluno/listaAlunos.jsp").forward(request, response);
                        break;
                    case "editar":
                        int id = Integer.parseInt(request.getParameter("id"));
                        Aluno aluno = alunoDAO.getAluno(id);
                        request.setAttribute("aluno", aluno);
                        request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp").forward(request, response);
                        break;
                    case "excluir":
                        int idExcluir = Integer.parseInt(request.getParameter("id"));
                        alunoDAO.excluir(idExcluir);
                        response.sendRedirect(request.getContextPath() + "/admin/aluno?acao=listar");
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        Aluno aluno = new Aluno();
        aluno.setNome(request.getParameter("nome"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setCpf(request.getParameter("cpf"));
        aluno.setSenha(request.getParameter("senha"));
        aluno.setEndereco(request.getParameter("endereco"));
        aluno.setCidade(request.getParameter("cidade"));
        aluno.setBairro(request.getParameter("bairro"));
        aluno.setCep(request.getParameter("cep"));

        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            if ("cadastrar".equals(acao)) {
                alunoDAO.inserir(aluno);
            } else if ("alterar".equals(acao)) {
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                alunoDAO.alterar(aluno);
            }
            response.sendRedirect(request.getContextPath() + "/admin/aluno?acao=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
