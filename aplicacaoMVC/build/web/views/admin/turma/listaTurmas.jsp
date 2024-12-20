<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Lista de Turmas</title>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Lista de Turmas</h2>
            <a href="<%= request.getContextPath()%>/admin/turma?acao=cadastrar" class="btn btn-success mb-3">Nova Turma</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Professor</th>
                        <th>Disciplina</th>
                        <th>Aluno</th>
                        <th>Código</th>
                        <th>Nota</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        java.util.List<entidade.Turma> lista = (java.util.List<entidade.Turma>) request.getAttribute("lista");
                        for (entidade.Turma turma : lista) {
                    %>
                    <tr>
                        <td><%= turma.getId()%></td>
                        <td><%= turma.getProfessorNome()%></td>
                        <td><%= turma.getDisciplinaNome()%></td>
                        <td><%= turma.getAlunoNome()%></td>
                        <td><%= turma.getCodigoTurma()%></td>
                        <td><%= turma.getNota()%></td>
                        <td>
                            <a href="<%= request.getContextPath()%>/admin/turma?acao=editar&id=<%= turma.getId()%>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath()%>/admin/turma?acao=excluir&id=<%= turma.getId()%>" class="btn btn-danger btn-sm">Excluir</a>
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
