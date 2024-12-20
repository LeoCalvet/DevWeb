<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Professor"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title><%= request.getAttribute("professor") == null ? "Cadastro de Professor" : "Edição de Professor" %></title>
    </head>
    <body>
        <div class="container mt-5">
            <h2><%= request.getAttribute("professor") == null ? "Cadastro de Professor" : "Edição de Professor" %></h2>

            <%
                Professor professor = (Professor) request.getAttribute("professor");
            %>

            <form action="<%= request.getContextPath()%>/admin/professor" method="POST">
                <input type="hidden" name="acao" value="<%= professor == null ? "cadastrar" : "alterar" %>">
                <% if (professor != null) { %>
                    <input type="hidden" name="id" value="<%= professor.getId() %>">
                <% } %>
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= professor != null ? professor.getNome() : "" %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">E-mail</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= professor != null ? professor.getEmail() : "" %>" required>
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="<%= professor != null ? professor.getCpf() : "" %>" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" value="<%= professor != null ? professor.getSenha() : "" %>" required>
                </div>
                <button type="submit" class="btn btn-primary"><%= professor == null ? "Cadastrar" : "Salvar Alterações" %></button>
            </form>

        </div>
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
