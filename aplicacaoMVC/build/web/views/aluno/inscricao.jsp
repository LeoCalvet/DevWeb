<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscrição em Disciplinas</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Inscrição em Disciplinas</h2>
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Ementa</th>
                    <th>Carga Horária</th>
                    <th>Ação</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="disciplina" items="${disciplinas}">
                    <tr>
                        <td><c:out value="${disciplina.nome}" /></td>
                        <td><c:out value="${disciplina.ementa}" /></td>
                        <td><c:out value="${disciplina.cargaHoraria}" /></td>
                        <td>
                            <form action="<c:url value='/aluno' />" method="post">
                                <input type="hidden" name="acao" value="inscrever">
                                <input type="hidden" name="disciplinaId" value="<c:out value='${disciplina.id}' />">
                                <button type="submit" class="btn btn-primary btn-sm">Inscrever-se</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
