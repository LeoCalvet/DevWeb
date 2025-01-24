<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notas do Aluno</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Histórico de Notas</h2>
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>Disciplina</th>
                    <th>Nota</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="nota" items="${notas}">
                    <tr>
                        <td><c:out value="${nota.disciplinaNome}" /></td>
                        <td><c:out value="${nota.nota}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
