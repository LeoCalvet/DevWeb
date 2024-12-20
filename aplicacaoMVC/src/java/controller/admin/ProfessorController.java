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
            if ("cadastrar".equals(acao)) {
                professorDAO.inserir(professor);
            } else if ("alterar".equals(acao)) {
                professor.setId(Integer.parseInt(request.getParameter("id")));
                professorDAO.alterar(professor);
            }
            response.sendRedirect(request.getContextPath() + "/admin/professor?acao=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
