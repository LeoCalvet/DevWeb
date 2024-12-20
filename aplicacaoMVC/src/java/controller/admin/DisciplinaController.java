package controller.admin;

import entidade.Disciplina;
import model.DisciplinaDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/disciplina"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", disciplinaDAO.listar());
                        request.getRequestDispatcher("/views/admin/disciplina/listaDisciplinas.jsp").forward(request, response);
                        break;
                    case "editar": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Disciplina disciplina = disciplinaDAO.getDisciplina(id);
                        request.setAttribute("disciplina", disciplina);
                        request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp").forward(request, response);
                        break;
                    }
                    case "cadastrar": // Novo caso para cadastrar
                        request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp").forward(request, response);
                        break;
                    case "excluir": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        disciplinaDAO.excluir(id);
                        response.sendRedirect(request.getContextPath() + "/admin/disciplina?acao=listar");
                        break;
                    }
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin/disciplina?acao=listar");
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
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.getParameter("nome"));
        disciplina.setRequisito(request.getParameter("requisito"));
        disciplina.setEmenta(request.getParameter("ementa"));
        disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("carga_horaria")));

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            if ("cadastrar".equals(acao)) {
                disciplinaDAO.inserir(disciplina);
            } else if ("alterar".equals(acao)) {
                disciplina.setId(Integer.parseInt(request.getParameter("id")));
                disciplinaDAO.alterar(disciplina);
            }
            response.sendRedirect(request.getContextPath() + "/admin/disciplina?acao=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
