<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Aluno"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8081/aplicacaoMVC/views/bootstrap/bootstrap.min.css">
    <title>Lista de Alunos</title>
</head>
<body>
    <div class="container mt-5">
        <h1>Lista de Alunos</h1>
        <a href="/aplicacaoMVC/views/admin/aluno/formAluno.jsp" class="btn btn-primary mb-3">Cadastrar Novo Aluno</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Celular</th>
                    <th>CPF</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Aluno> lista = (ArrayList<Aluno>) request.getAttribute("lista");
                    if (lista != null && !lista.isEmpty()) {
                        for (Aluno aluno : lista) {
                %>
                <tr>
                    <td><%= aluno.getId() %></td>
                    <td><%= aluno.getNome() %></td>
                    <td><%= aluno.getEmail() %></td>
                    <td><%= aluno.getCelular() %></td>
                    <td><%= aluno.getCpf() %></td>
                    <td>
                        <a href="/aplicacaoMVC/admin/aluno?acao=editar&id=<%= aluno.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                        <a href="/aplicacaoMVC/admin/aluno?acao=excluir&id=<%= aluno.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Deseja realmente excluir este aluno?');">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="6" class="text-center">Nenhum aluno cadastrado</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="/aplicacaoMVC/admin/dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
    </div>
    <script src="http://localhost:8081/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
