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
            <h1><%= request.getAttribute("aluno") == null ? "Cadastrar Aluno" : "Editar Aluno"%></h1>

            <%-- Exibir mensagem de erro, caso exista --%>
            <% if (request.getAttribute("erro") != null) {%>
            <div class="alert alert-danger">
                <%= request.getAttribute("erro")%>
            </div>
            <% }%>

            <form action="/aplicacaoMVC/admin/aluno" method="POST">
                <input type="hidden" name="acao" value="<%= request.getAttribute("aluno") == null ? "cadastrar" : "alterar"%>">
                <%
                    Aluno aluno = (Aluno) request.getAttribute("aluno");
                    if (aluno != null) {
                %>
                <input type="hidden" name="id" value="<%= aluno.getId()%>">
                <% }%>
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome <span class="text-muted">(Apenas letras, até 50 caracteres)</span></label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= aluno != null ? aluno.getNome() : ""%>" 
                           placeholder="Ex.: João da Silva" maxlength="50" pattern="[A-Za-zÀ-ú ]+" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email <span class="text-muted">(Formato: exemplo@email.com)</span></label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= aluno != null ? aluno.getEmail() : ""%>" 
                           placeholder="Ex.: aluno@email.com" required>
                </div>
                <div class="mb-3">
                    <label for="celular" class="form-label">Celular <span class="text-muted">(Formato: 99 99999-9999)</span></label>
                    <input type="text" class="form-control" id="celular" name="celular" value="<%= aluno != null ? aluno.getCelular() : ""%>" 
                           placeholder="Ex.: 21 98765-4321" pattern="\d{2} \d{5}-\d{4}" required>
                </div>

                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF <span class="text-muted">(Formato: 000.000.000-00)</span></label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="<%= aluno != null ? aluno.getCpf() : ""%>" 
                           placeholder="Ex.: 123.456.789-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha <span class="text-muted">(Mínimo 8 caracteres, incluindo letra e número)</span></label>
                    <input type="password" class="form-control" id="senha" name="senha" value="<%= aluno != null ? aluno.getSenha() : ""%>" 
                           placeholder="Ex.: Senha123" minlength="8" required>
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço <span class="text-muted">(Opcional, até 100 caracteres)</span></label>
                    <input type="text" class="form-control" id="endereco" name="endereco" value="<%= aluno != null ? aluno.getEndereco() : ""%>" 
                           placeholder="Ex.: Rua das Flores, 123" maxlength="100">
                </div>
                <div class="mb-3">
                    <label for="cidade" class="form-label">Cidade <span class="text-muted">(Opcional, até 50 caracteres)</span></label>
                    <input type="text" class="form-control" id="cidade" name="cidade" value="<%= aluno != null ? aluno.getCidade() : ""%>" 
                           placeholder="Ex.: Rio de Janeiro" maxlength="50">
                </div>
                <div class="mb-3">
                    <label for="bairro" class="form-label">Bairro <span class="text-muted">(Opcional, até 50 caracteres)</span></label>
                    <input type="text" class="form-control" id="bairro" name="bairro" value="<%= aluno != null ? aluno.getBairro() : ""%>" 
                           placeholder="Ex.: Centro" maxlength="50">
                </div>
                <div class="mb-3">
                    <label for="cep" class="form-label">CEP <span class="text-muted">(Formato: 00000-000, opcional)</span></label>
                    <input type="text" class="form-control" id="cep" name="cep" value="<%= aluno != null ? aluno.getCep() : ""%>" 
                           placeholder="Ex.: 12345-678" pattern="\d{5}-\d{3}">
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="/aplicacaoMVC/admin/aluno?acao=listar" class="btn btn-secondary">Voltar</a>
            </form>
        </div>
        <script src="http://localhost:8081/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
