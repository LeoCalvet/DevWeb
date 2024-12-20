package controller.admin;

import entidade.Turma;
import model.TurmaDAO;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;

@WebServlet(name = "TurmaController", urlPatterns = {"/admin/turma"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        TurmaDAO turmaDAO = new TurmaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", turmaDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/listaTurmas.jsp").forward(request, response);
                        break;
                    case "editar": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Turma turma = turmaDAO.getTurma(id);
                        request.setAttribute("turma", turma);
                        request.setAttribute("professores", professorDAO.listar());
                        request.setAttribute("disciplinas", disciplinaDAO.listar());
                        request.setAttribute("alunos", alunoDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/formTurma.jsp").forward(request, response);
                        break;
                    }
                    case "cadastrar":
                        request.setAttribute("professores", professorDAO.listar());
                        request.setAttribute("disciplinas", disciplinaDAO.listar());
                        request.setAttribute("alunos", alunoDAO.listar());
                        request.getRequestDispatcher("/views/admin/turma/formTurma.jsp").forward(request, response);
                        break;
                    case "excluir": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        turmaDAO.excluir(id);
                        response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
                        break;
                    }
                    case "gerarRelatorio":
                        List<Map<String, Object>> relatorio = turmaDAO.gerarRelatorio();
                        request.setAttribute("relatorio", relatorio);
                        request.getRequestDispatcher("/views/admin/turma/relatorioTurmas.jsp").forward(request, response);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
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
            throw new ServletException(e);
        }
    }
}
