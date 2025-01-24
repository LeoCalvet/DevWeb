<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Notas e Histórico</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Notas e Histórico</h2>
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>Disciplina</th>
                    <th>Nota</th>
                </tr>
            </thead>
            <tbody>
                <!-- Exemplo de dados estáticos -->
                <tr>
                    <td>Matemática</td>
                    <td>9.5</td>
                </tr>
                <tr>
                    <td>Física</td>
                    <td>8.7</td>
                </tr>
            </tbody>
        </table>
        <a href="/aplicacaoMVC/aluno?acao=login" class="btn btn-secondary mt-3">Voltar</a>
    </div>
</body>
</html>
