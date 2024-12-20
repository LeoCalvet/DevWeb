<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Lista de Disciplinas</title>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Lista de Disciplinas</h2>
            <a href="<%= request.getContextPath()%>/admin/disciplina?acao=cadastrar" class="btn btn-success mb-3">Nova Disciplina</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Requisito</th>
                        <th>Ementa</th>
                        <th>Carga Horária</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        java.util.List<entidade.Disciplina> lista = (java.util.List<entidade.Disciplina>) request.getAttribute("lista");
                        for (entidade.Disciplina disciplina : lista) {
                    %>
                    <tr>
                        <td><%= disciplina.getNome()%></td>
                        <td><%= disciplina.getRequisito()%></td>
                        <td><%= disciplina.getEmenta()%></td>
                        <td><%= disciplina.getCargaHoraria()%> horas</td>
                        <td>
                            <a href="<%= request.getContextPath()%>/admin/disciplina?acao=editar&id=<%= disciplina.getId()%>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath()%>/admin/disciplina?acao=excluir&id=<%= disciplina.getId()%>" class="btn btn-danger btn-sm">Excluir</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <a href="/aplicacaoMVC/admin/dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>

        </div>
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
