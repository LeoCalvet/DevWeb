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
            System.out.println("Ação recebida: " + acao);
            System.out.println("Dados do aluno: " + aluno);

            if ("cadastrar".equals(acao)) {
                alunoDAO.inserir(aluno);
                System.out.println("Aluno cadastrado com sucesso.");
            } else if ("alterar".equals(acao)) {
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                alunoDAO.alterar(aluno);
                System.out.println("Aluno alterado com sucesso.");
            }
            response.sendRedirect(request.getContextPath() + "/admin/aluno?acao=listar");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("aluno", aluno);
            request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao salvar aluno", e);
        }
    }

    private boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0, resto;
        for (int i = 1; i <= 9; i++) {
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if ((resto == 10) || (resto == 11)) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }

        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if ((resto == 10) || (resto == 11)) {
            resto = 0;
        }
        return resto == Integer.parseInt(cpf.substring(10, 11));
    }

}
