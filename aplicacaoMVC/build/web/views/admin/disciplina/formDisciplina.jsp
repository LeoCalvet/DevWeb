<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath() %>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Cadastro de Disciplina</title>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Cadastro de Disciplina</h2>
            <form action="<%= request.getContextPath() %>/admin/disciplina" method="POST">
                <input type="hidden" name="acao" value="<%= request.getAttribute("disciplina") == null ? "cadastrar" : "alterar" %>">
                <%
                    entidade.Disciplina disciplina = (entidade.Disciplina) request.getAttribute("disciplina");
                %>
                <input type="hidden" name="id" value="<%= disciplina != null ? disciplina.getId() : "" %>">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="<%= disciplina != null ? disciplina.getNome() : "" %>" required>
                </div>
                <div class="mb-3">
                    <label for="requisito" class="form-label">Requisito</label>
                    <input type="text" class="form-control" id="requisito" name="requisito" value="<%= disciplina != null ? disciplina.getRequisito() : "" %>">
                </div>
                <div class="mb-3">
                    <label for="ementa" class="form-label">Ementa</label>
                    <textarea class="form-control" id="ementa" name="ementa" rows="3"><%= disciplina != null ? disciplina.getEmenta() : "" %></textarea>
                </div>
                <div class="mb-3">
                    <label for="carga_horaria" class="form-label">Carga Horária</label>
                    <input type="number" class="form-control" id="carga_horaria" name="carga_horaria" value="<%= disciplina != null ? disciplina.getCargaHoraria() : "" %>" required>
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="<%= request.getContextPath() %>/admin/disciplina?acao=listar" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
        <script src="<%= request.getContextPath() %>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
