<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath() %>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Lista de Professores</title>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Lista de Professores</h2>
            <a href="<%= request.getContextPath() %>/admin/professor?acao=cadastrar" class="btn btn-success mb-3">Novo Professor</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>CPF</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        java.util.List<entidade.Professor> lista = (java.util.List<entidade.Professor>) request.getAttribute("lista");
                        for (entidade.Professor professor : lista) {
                    %>
                    <tr>
                        <td><%= professor.getNome() %></td>
                        <td><%= professor.getEmail() %></td>
                        <td><%= professor.getCpf() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/professor?acao=editar&id=<%= professor.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath() %>/admin/professor?acao=excluir&id=<%= professor.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <script src="<%= request.getContextPath() %>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
