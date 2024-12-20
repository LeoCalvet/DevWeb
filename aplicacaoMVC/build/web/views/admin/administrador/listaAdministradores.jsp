<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Administradores</title>
    <link href="<%= request.getContextPath() %>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Lista de Administradores</h2>
    <a href="<%= request.getContextPath() %>/admin/administrador?acao=cadastrar" class="btn btn-success mb-3">Novo Administrador</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Endereço</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% 
            java.util.List<entidade.Administrador> lista = (java.util.List<entidade.Administrador>) request.getAttribute("lista");
            for (entidade.Administrador administrador : lista) { 
        %>
        <tr>
            <td><%= administrador.getNome() %></td>
            <td><%= administrador.getCpf() %></td>
            <td><%= administrador.getEndereco() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/admin/administrador?acao=editar&id=<%= administrador.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                <a href="<%= request.getContextPath() %>/admin/administrador?acao=excluir&id=<%= administrador.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="<%= request.getContextPath() %>/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
