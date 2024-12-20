package controller.admin;

import entidade.Turma;
import model.TurmaDAO;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TurmaController", urlPatterns = {"/admin/turma"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        TurmaDAO turmaDAO = new TurmaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", turmaDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/listaTurmas.jsp").forward(request, response);
                        break;
                    case "cadastrar":
                        request.setAttribute("professores", professorDAO.listar());
                        request.setAttribute("disciplinas", disciplinaDAO.listar());
                        request.setAttribute("alunos", alunoDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/formTurma.jsp").forward(request, response);
                        break;
                    case "editar":
                        int id = Integer.parseInt(request.getParameter("id"));
                        request.setAttribute("turma", turmaDAO.getTurma(id));
                        request.setAttribute("professores", professorDAO.listar());
                        request.setAttribute("disciplinas", disciplinaDAO.listar());
                        request.setAttribute("alunos", alunoDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/formTurma.jsp").forward(request, response);
                        break;
                    case "excluir":
                        turmaDAO.excluir(Integer.parseInt(request.getParameter("id")));
                        response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao gerenciar turmas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        Turma turma = new Turma();

        turma.setProfessorId(Integer.parseInt(request.getParameter("professor_id")));
        turma.setDisciplinaId(Integer.parseInt(request.getParameter("disciplina_id")));
        turma.setAlunoId(Integer.parseInt(request.getParameter("aluno_id")));
        turma.setCodigoTurma(request.getParameter("codigo_turma"));
        turma.setNota(Double.parseDouble(request.getParameter("nota")));

        TurmaDAO turmaDAO = new TurmaDAO();
        try {
            if ("cadastrar".equals(acao)) {
                turmaDAO.inserir(turma);
            } else if ("alterar".equals(acao)) {
                turma.setId(Integer.parseInt(request.getParameter("id")));
                turmaDAO.alterar(turma);
            }
            response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
        } catch (Exception e) {
            throw new ServletException("Erro ao salvar turma", e);
        }
    }
}
