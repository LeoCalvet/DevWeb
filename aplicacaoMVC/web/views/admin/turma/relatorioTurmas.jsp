<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%= request.getContextPath() %>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Relatório de Disciplinas e Turmas</title>
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Relatório de Disciplinas e Turmas</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Disciplina</th>
                    <th>Código da Turma</th>
                    <th>Professor</th>
                    <th>Aluno</th>
                    <th>Nota</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Map<String, Object>> relatorio = (List<Map<String, Object>>) request.getAttribute("relatorio");
                    if (relatorio != null) {
                        for (Map<String, Object> linha : relatorio) {
                %>
                <tr>
                    <td><%= linha.get("disciplina_nome") %></td>
                    <td><%= linha.get("codigo_turma") %></td>
                    <td><%= linha.get("professor_nome") %></td>
                    <td><%= linha.get("aluno_nome") %></td>
                    <td><%= linha.get("nota") %></td>
                </tr>
                <%      }
                    } else { %>
                <tr>
                    <td colspan="5" class="text-center">Nenhum dado disponível</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="<%= request.getContextPath() %>/admin/dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
    </div>
    <script src="<%= request.getContextPath() %>/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
