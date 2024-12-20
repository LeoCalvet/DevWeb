<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Administrador</title>
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Cadastro de Administrador</h2>

            <% String erro = (String) request.getAttribute("erro"); %>
            <% if (erro != null) {%>
            <div class="alert alert-danger" role="alert">
                <%= erro%>
            </div>
            <% }%>

            <form action="<%= request.getContextPath()%>/admin/administrador" method="POST">
                <input type="hidden" name="acao" value="<%= request.getAttribute("administrador") == null ? "cadastrar" : "alterar"%>">
                <input type="hidden" name="id" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getId() : ""%>">

                <div class="mb-3">
                    <label for="nome" class="form-label">Nome <span class="text-muted">(Apenas letras, até 50 caracteres)</span></label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getNome() : ""%>" 
                           placeholder="Ex.: João da Silva" maxlength="50" pattern="[A-Za-zÀ-ú ]+" required>
                </div>

                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF <span class="text-muted">(Formato: 000.000.000-00)</span></label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getCpf() : ""%>" 
                           placeholder="Ex.: 123.456.789-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" required>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha <span class="text-muted">(Mínimo 8 caracteres, incluindo letra e número)</span></label>
                    <input type="password" class="form-control" id="senha" name="senha" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getSenha() : ""%>" 
                           placeholder="Ex.: Senha123" minlength="8" required>
                </div>

                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço <span class="text-muted">(Até 100 caracteres)</span></label>
                    <input type="text" class="form-control" id="endereco" name="endereco" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getEndereco() : ""%>" 
                           placeholder="Ex.: Rua das Flores, 123" maxlength="100" required>
                </div>

                <div class="mb-3">
                    <label for="aprovado" class="form-label">Aprovado</label>
                    <select class="form-select" id="aprovado" name="aprovado">
                        <option value="S" <%= (request.getAttribute("administrador") != null && "S".equals(((entidade.Administrador) request.getAttribute("administrador")).getAprovado())) ? "selected" : ""%>>Sim</option>
                        <option value="N" <%= (request.getAttribute("administrador") == null || "N".equals(((entidade.Administrador) request.getAttribute("administrador")).getAprovado())) ? "selected" : ""%>>Não</option>
                    </select>
                </div>


                <button type="submit" class="btn btn-primary">Salvar</button>
            </form>
        </div>
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
