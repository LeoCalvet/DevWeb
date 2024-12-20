<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Professor"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title><%= request.getAttribute("professor") == null ? "Cadastro de Professor" : "Edição de Professor"%></title>
    </head>
    <body>

        <div class="container mt-5">
            <h2><%= request.getAttribute("professor") == null ? "Cadastro de Professor" : "Edição de Professor"%></h2>

            <%
                Professor professor = (Professor) request.getAttribute("professor");
            %>

            <% String erro = (String) request.getAttribute("erro"); %>
            <% if (erro != null) {%>
            <div class="alert alert-danger" role="alert">
                <%= erro%>
            </div>
            <% }%>

            <form action="<%= request.getContextPath()%>/admin/professor" method="POST">
                <input type="hidden" name="acao" value="<%= professor == null ? "cadastrar" : "alterar"%>">
                <% if (professor != null) {%>
                <input type="hidden" name="id" value="<%= professor.getId()%>">
                <% }%>

                <div class="mb-3">
                    <label for="nome" class="form-label">Nome <span class="text-muted">(Apenas letras, até 50 caracteres)</span></label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= professor != null ? professor.getNome() : ""%>"
                           placeholder="Ex.: João da Silva" maxlength="50" pattern="[A-Za-zÀ-ú ]+" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">E-mail <span class="text-muted">(Formato: exemplo@email.com)</span></label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= professor != null ? professor.getEmail() : ""%>"
                           placeholder="Ex.: professor@email.com" required>
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF <span class="text-muted">(Formato: 000.000.000-00)</span></label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="<%= professor != null ? professor.getCpf() : ""%>"
                           placeholder="Ex.: 123.456.789-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha <span class="text-muted">(Mínimo 8 caracteres)</span></label>
                    <input type="password" class="form-control" id="senha" name="senha" value="<%= professor != null ? professor.getSenha() : ""%>"
                           placeholder="Ex.: Senha123" minlength="8" required>
                </div>
                <button type="submit" class="btn btn-primary"><%= professor == null ? "Cadastrar" : "Salvar Alterações"%></button>
            </form>
        </div>

        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
