<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Minhas Disciplinas</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Minhas Disciplinas</h2>
            <table class="table table-striped mt-4">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Requisito</th>
                        <th>Ementa</th>
                        <th>Carga Horária</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Exemplo de dados estáticos -->
                    <tr>
                        <td>1</td>
                        <td>Matemática</td>
                        <td>-</td>
                        <td>Introdução aos conceitos matemáticos</td>
                        <td>60h</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Física</td>
                        <td>Matemática</td>
                        <td>Leis do movimento</td>
                        <td>40h</td>
                    </tr>
                </tbody>
            </table>
            <a href="/aplicacaoMVC/aluno?acao=login" class="btn btn-secondary mt-3">Voltar</a>
        </div>
    </body>
</html>
