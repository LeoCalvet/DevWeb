package controller.admin;

import entidade.Administrador;
import model.AdministradorDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdministradorController", urlPatterns = {"/admin/administrador"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        AdministradorDAO administradorDAO = new AdministradorDAO();
        try {
            if (acao != null) {
                switch (acao) {
                    case "listar":
                        request.setAttribute("lista", administradorDAO.ListaDeAdministrador());
                        request.getRequestDispatcher("/views/admin/administrador/listaAdministradores.jsp").forward(request, response);
                        break;
                    case "editar": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Administrador administrador = administradorDAO.getAdministrador(id);
                        request.setAttribute("administrador", administrador);
                        request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp").forward(request, response);
                        break;
                    }
                    case "excluir": {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Administrador administrador = new Administrador();
                        administrador.setId(id);
                        administradorDAO.Excluir(administrador);
                        response.sendRedirect(request.getContextPath() + "/admin/administrador?acao=listar");
                        break;
                    }
                    case "cadastrar": {
                        // Redireciona para o formulário de cadastro
                        request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp").forward(request, response);
                        break;
                    }
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin/administrador?acao=listar");
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
        Administrador administrador = new Administrador();

        administrador.setNome(request.getParameter("nome"));
        administrador.setCpf(request.getParameter("cpf"));
        administrador.setEndereco(request.getParameter("endereco"));
        administrador.setSenha(request.getParameter("senha"));
        administrador.setAprovado(request.getParameter("aprovado"));

        AdministradorDAO administradorDAO = new AdministradorDAO();

        try {
            if (administrador.getNome() == null || administrador.getNome().isEmpty() || administrador.getNome().length() > 50) {
                throw new IllegalArgumentException("O nome é obrigatório e deve ter até 50 caracteres.");
            }
            if (administrador.getCpf() == null || !validarCPF(administrador.getCpf())) {
                throw new IllegalArgumentException("CPF inválido.");
            }
            if (administrador.getSenha() == null || administrador.getSenha().length() < 8) {
                throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
            }
            if (administrador.getEndereco() == null || administrador.getEndereco().isEmpty() || administrador.getEndereco().length() > 100) {
                throw new IllegalArgumentException("O endereço é obrigatório e deve ter até 100 caracteres.");
            }

            // Operação de banco
            if ("cadastrar".equals(acao)) {
                administradorDAO.Inserir(administrador);
            } else if ("alterar".equals(acao)) {
                administrador.setId(Integer.parseInt(request.getParameter("id")));
                administradorDAO.Alterar(administrador);
            }
            response.sendRedirect(request.getContextPath() + "/admin/administrador?acao=listar");

        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.setAttribute("administrador", administrador);
            request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao salvar administrador", e);
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
