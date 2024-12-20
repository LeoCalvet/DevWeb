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

        AdministradorDAO administradorDAO = new AdministradorDAO();
        try {
            if ("cadastrar".equals(acao)) {
                administradorDAO.Inserir(administrador);
            } else if ("alterar".equals(acao)) {
                administrador.setId(Integer.parseInt(request.getParameter("id")));
                administradorDAO.Alterar(administrador);
            }
            response.sendRedirect(request.getContextPath() + "/admin/administrador?acao=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
