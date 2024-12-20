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
            <form action="<%= request.getContextPath()%>/admin/administrador" method="POST">
                <input type="hidden" name="acao" value="<%= request.getAttribute("administrador") == null ? "cadastrar" : "alterar"%>">
                <input type="hidden" name="id" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getId() : ""%>">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getNome() : ""%>" required>
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getCpf() : ""%>" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getSenha() : ""%>" required>
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço</label>
                    <input type="text" class="form-control" id="endereco" name="endereco" value="<%= request.getAttribute("administrador") != null ? ((entidade.Administrador) request.getAttribute("administrador")).getEndereco() : ""%>" required>
                </div>
                <div class="mb-3">
                    <label for="aprovado" class="form-label">Aprovado</label>
                    <select class="form-select" id="aprovado" name="aprovado">
                        <option value="S">Sim</option>
                        <option value="N" selected>Não</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
            </form>
        </div>
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
