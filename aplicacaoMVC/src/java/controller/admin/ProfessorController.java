package controller.admin;

import entidade.Professor;
import model.ProfessorDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfessorController", urlPatterns = {"/admin/professor"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", professorDAO.listar());
                        request.getRequestDispatcher("/views/admin/professor/listaProfessores.jsp").forward(request, response);
                        break;
                    case "editar": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Professor professor = professorDAO.getProfessor(id);
                        request.setAttribute("professor", professor);
                        request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp").forward(request, response);
                        break;
                    }
                    case "excluir": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        professorDAO.excluir(id);
                        response.sendRedirect(request.getContextPath() + "/admin/professor?acao=listar");
                        break;
                    }
                    case "cadastrar": // Novo caso
                        request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp").forward(request, response);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin/professor?acao=listar");
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
        Professor professor = new Professor();

        professor.setNome(request.getParameter("nome"));
        professor.setEmail(request.getParameter("email"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setSenha(request.getParameter("senha"));

        ProfessorDAO professorDAO = new ProfessorDAO();

        try {
            if (professor.getNome() == null || professor.getNome().isEmpty() || professor.getNome().length() > 50) {
                throw new IllegalArgumentException("O nome é obrigatório e deve ter no máximo 50 caracteres.");
            }
            if (professor.getEmail() == null || !professor.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new IllegalArgumentException("E-mail inválido.");
            }
            if (professor.getCpf() == null || !validarCPF(professor.getCpf())) {
                throw new IllegalArgumentException("CPF inválido.");
            }
            if (professor.getSenha() == null || professor.getSenha().length() < 8) {
                throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
            }

            if ("cadastrar".equals(acao)) {
                professorDAO.inserir(professor);
            } else if ("alterar".equals(acao)) {
                professor.setId(Integer.parseInt(request.getParameter("id")));
                professorDAO.alterar(professor);
            }
            response.sendRedirect(request.getContextPath() + "/admin/professor?acao=listar");
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("professor", professor);
            request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao salvar professor no banco de dados", e);
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
