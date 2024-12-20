<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Aluno"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8081/aplicacaoMVC/views/bootstrap/bootstrap.min.css">
    <title>Cadastro de Aluno</title>
</head>
<body>
    <div class="container mt-5">
        <h1><%= request.getAttribute("aluno") == null ? "Cadastrar Aluno" : "Editar Aluno" %></h1>
        <form action="/aplicacaoMVC/admin/aluno" method="POST">
            <input type="hidden" name="acao" value="<%= request.getAttribute("aluno") == null ? "cadastrar" : "alterar" %>">
            <%
                Aluno aluno = (Aluno) request.getAttribute("aluno");
                if (aluno != null) {
            %>
                <input type="hidden" name="id" value="<%= aluno.getId() %>">
            <% } %>
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" value="<%= aluno != null ? aluno.getNome() : "" %>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= aluno != null ? aluno.getEmail() : "" %>" required>
            </div>
            <div class="mb-3">
                <label for="celular" class="form-label">Celular</label>
                <input type="text" class="form-control" id="celular" name="celular" value="<%= aluno != null ? aluno.getCelular() : "" %>" required>
            </div>
            <div class="mb-3">
                <label for="cpf" class="form-label">CPF</label>
                <input type="text" class="form-control" id="cpf" name="cpf" value="<%= aluno != null ? aluno.getCpf() : "" %>" required>
            </div>
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control" id="senha" name="senha" value="<%= aluno != null ? aluno.getSenha() : "" %>" required>
            </div>
            <div class="mb-3">
                <label for="endereco" class="form-label">Endereço</label>
                <input type="text" class="form-control" id="endereco" name="endereco" value="<%= aluno != null ? aluno.getEndereco() : "" %>">
            </div>
            <div class="mb-3">
                <label for="cidade" class="form-label">Cidade</label>
                <input type="text" class="form-control" id="cidade" name="cidade" value="<%= aluno != null ? aluno.getCidade() : "" %>">
            </div>
            <div class="mb-3">
                <label for="bairro" class="form-label">Bairro</label>
                <input type="text" class="form-control" id="bairro" name="bairro" value="<%= aluno != null ? aluno.getBairro() : "" %>">
            </div>
            <div class="mb-3">
                <label for="cep" class="form-label">CEP</label>
                <input type="text" class="form-control" id="cep" name="cep" value="<%= aluno != null ? aluno.getCep() : "" %>">
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
            <a href="/aplicacaoMVC/admin/aluno?acao=listar" class="btn btn-secondary">Voltar</a>
        </form>
    </div>
    <script src="http://localhost:8081/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
