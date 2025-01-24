package controller;

import entidade.Administrador;
import entidade.Aluno;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;
import model.AlunoDAO;
import model.ProfessorDAO;

@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");

        // Verifique se os campos obrigatórios estão preenchidos
        if (cpf_user == null || cpf_user.isEmpty() || senha_user == null || senha_user.isEmpty()) {
            request.setAttribute("msgError", "Usuário e/ou senha incorretos!");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            // Autenticação do Aluno
            AlunoDAO alunoDAO = new AlunoDAO();
            Aluno aluno = alunoDAO.autenticar(cpf_user, senha_user);

            if (aluno != null) {
                HttpSession session = request.getSession();
                session.setAttribute("alunoLogado", aluno);
                response.sendRedirect(request.getContextPath() + "/aluno?acao=login");
                return;
            }

            // Se não encontrar o aluno, retorne um erro
            request.setAttribute("msgError", "Usuário e/ou senha incorretos!");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar login.", e);
        }
    }

}
