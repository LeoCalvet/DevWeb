package controller;

import entidade.Professor;
import entidade.Turma;
import model.ProfessorDAO;
import model.TurmaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfessorController", urlPatterns = {"/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        HttpSession sessao = request.getSession();
        Professor professorLogado = (Professor) sessao.getAttribute("professorLogado");

        if (professorLogado == null) {
            response.sendRedirect(request.getContextPath() + "/ProfessorController?acao=login");
            return;
        }

        try {
            switch (acao) {
                case "login":
                    request.getRequestDispatcher("/views/professor/formLogin.jsp").forward(request, response);
                    break;
                case "dashboard":
                    request.getRequestDispatcher("/views/professor/areaPrivadaProfessor.jsp").forward(request, response);
                    break;
                case "notas":
                    TurmaDAO turmaDAO = new TurmaDAO();
                    List<Turma> turmas = turmaDAO.listarPorProfessor(professorLogado.getId());
                    request.setAttribute("turmas", turmas);
                    request.getRequestDispatcher("/views/professor/listaNotas.jsp").forward(request, response);
                    break;
                case "editarNota":
                    int turmaId = Integer.parseInt(request.getParameter("turmaId"));
                    Turma turma = new TurmaDAO().getTurma(turmaId);
                    request.setAttribute("turma", turma);
                    request.getRequestDispatcher("/views/professor/editarNota.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/ProfessorController?acao=login");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            switch (acao) {
                case "login":
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    Professor professor = new ProfessorDAO().autenticar(cpf, senha);

                    if (professor != null) {
                        HttpSession sessao = request.getSession();
                        sessao.setAttribute("professorLogado", professor);
                        response.sendRedirect(request.getContextPath() + "/ProfessorController?acao=dashboard");
                    } else {
                        request.setAttribute("msgError", "CPF ou senha inválidos!");
                        request.getRequestDispatcher("/views/professor/formLogin.jsp").forward(request, response);
                    }
                    break;
                case "salvarNota":
                    int turmaId = Integer.parseInt(request.getParameter("turmaId"));
                    double nota = Double.parseDouble(request.getParameter("nota"));

                    TurmaDAO turmaDAO = new TurmaDAO();
                    turmaDAO.atualizarNota(turmaId, nota);

                    response.sendRedirect(request.getContextPath() + "/ProfessorController?acao=notas");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/ProfessorController?acao=login");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
