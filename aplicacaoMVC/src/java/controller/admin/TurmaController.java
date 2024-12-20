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

        try {
            String professorIdStr = request.getParameter("professor_id");
            String disciplinaIdStr = request.getParameter("disciplina_id");
            String alunoIdStr = request.getParameter("aluno_id");
            String codigoTurma = request.getParameter("codigo_turma");
            String notaStr = request.getParameter("nota");

            if (professorIdStr == null || professorIdStr.isEmpty()) {
                throw new IllegalArgumentException("O professor é obrigatório.");
            }
            if (disciplinaIdStr == null || disciplinaIdStr.isEmpty()) {
                throw new IllegalArgumentException("A disciplina é obrigatória.");
            }
            if (alunoIdStr == null || alunoIdStr.isEmpty()) {
                throw new IllegalArgumentException("O aluno é obrigatório.");
            }
            if (codigoTurma == null || codigoTurma.isEmpty() || codigoTurma.length() > 2) {
                throw new IllegalArgumentException("O código da turma é obrigatório e deve ter até 2 caracteres.");
            }
            if (notaStr == null || notaStr.isEmpty()) {
                throw new IllegalArgumentException("A nota é obrigatória.");
            }

            int professorId = Integer.parseInt(professorIdStr);
            int disciplinaId = Integer.parseInt(disciplinaIdStr);
            int alunoId = Integer.parseInt(alunoIdStr);
            double nota = Double.parseDouble(notaStr);
            if (nota < 0 || nota > 10) {
                throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
            }

            turma.setProfessorId(professorId);
            turma.setDisciplinaId(disciplinaId);
            turma.setAlunoId(alunoId);
            turma.setCodigoTurma(codigoTurma);
            turma.setNota(nota);

            TurmaDAO turmaDAO = new TurmaDAO();
            if ("cadastrar".equals(acao)) {
                turmaDAO.inserir(turma);
            } else if ("alterar".equals(acao)) {
                turma.setId(Integer.parseInt(request.getParameter("id")));
                turmaDAO.alterar(turma);
            }
            response.sendRedirect(request.getContextPath() + "/admin/turma?acao=listar");
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("turma", turma);
            request.getRequestDispatcher("/views/admin/turma/formTurma.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao salvar turma", e);
        }
    }

}
