package controller;

import entidade.Aluno;
import entidade.Turma;
import entidade.Disciplina;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.TurmaDAO;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AlunoController", urlPatterns = {"/aluno"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        HttpSession sessao = request.getSession();
        Aluno alunoLogado = (Aluno) sessao.getAttribute("alunoLogado");

        if (alunoLogado == null) {
            System.out.println("Aluno não logado. Redirecionando para a tela de login.");
            response.sendRedirect(request.getContextPath() + "/AutenticaController?acao=login");
            return;
        }

        try {
            if (acao != null) {
                switch (acao) {
                    case "login":
                        request.getRequestDispatcher("/views/aluno/areaPrivadaAluno.jsp").forward(request, response);
                        break;
                    case "disciplinas":
                        // Exemplo de lógica para disciplinas
                        request.getRequestDispatcher("/views/aluno/minhasDisciplinas.jsp").forward(request, response);
                        break;
                    case "historico":
                        // Exemplo de lógica para histórico
                        request.getRequestDispatcher("/views/aluno/historico.jsp").forward(request, response);
                        break;
                    case "perfil":
                        // Exemplo de lógica para perfil
                        request.getRequestDispatcher("/views/aluno/perfil.jsp").forward(request, response);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/AutenticaController?acao=login");
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
        AlunoDAO alunoDAO = new AlunoDAO();
        TurmaDAO turmaDAO = new TurmaDAO();

        try {
            if ("cadastrar".equals(acao)) {
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

                alunoDAO.inserir(aluno);
                response.sendRedirect(request.getContextPath() + "/admin/aluno?acao=listar");

            } else if ("alterar".equals(acao)) {
                Aluno aluno = new Aluno();
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                aluno.setNome(request.getParameter("nome"));
                aluno.setEmail(request.getParameter("email"));
                aluno.setCelular(request.getParameter("celular"));
                aluno.setCpf(request.getParameter("cpf"));
                aluno.setSenha(request.getParameter("senha"));
                aluno.setEndereco(request.getParameter("endereco"));
                aluno.setCidade(request.getParameter("cidade"));
                aluno.setBairro(request.getParameter("bairro"));
                aluno.setCep(request.getParameter("cep"));

                alunoDAO.alterar(aluno);
                response.sendRedirect(request.getContextPath() + "/admin/aluno?acao=listar");

            } else if ("login".equals(acao)) {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");

                Aluno aluno = alunoDAO.autenticar(email, senha);

                if (aluno != null) {
                    // Login bem-sucedido
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("alunoLogado", aluno);
                    response.sendRedirect("areaPrivadaAluno.jsp");
                } else {
                    // Credenciais inválidas
                    request.setAttribute("mensagemErro", "Credenciais inválidas! Verifique seu email e senha.");
                    request.getRequestDispatcher("loginAluno.jsp").forward(request, response);
                }
            } else if ("inscrever".equals(acao)) {
                // RF9: Inscrever aluno na turma
                int alunoId = ((Aluno) request.getSession().getAttribute("alunoLogado")).getId();
                int disciplinaId = Integer.parseInt(request.getParameter("disciplinaId"));
                Turma turma = new Turma();
                turma.setAlunoId(alunoId);
                turma.setDisciplinaId(disciplinaId);
                turma.setCodigoTurma("A"); // Código fictício
                turma.setNota(0); // Nota inicial
                turmaDAO.inserir(turma);
                response.sendRedirect(request.getContextPath() + "/aluno?acao=inscricao");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao processar requisição.", e);
        }
    }
}
