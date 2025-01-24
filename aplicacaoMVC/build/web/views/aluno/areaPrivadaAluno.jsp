<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard do Aluno</title>
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">

    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Bem-vindo(a), <c:out value="${alunoLogado.nome}" /></h2>
            <p class="text-center text-muted">Escolha uma das opções abaixo para continuar</p>
            <div class="row mt-4">
                <!-- Minhas Disciplinas -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Minhas Disciplinas</h5>
                            <p class="card-text">Veja as disciplinas em que você está inscrito.</p>
                            <a href="/aplicacaoMVC/aluno?acao=disciplinas" class="btn btn-primary">Acessar</a>

                        </div>
                    </div>
                </div>

                <!-- Notas e Histórico -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Notas e Histórico</h5>
                            <p class="card-text">Visualize suas notas e histórico acadêmico.</p>
                            <a href="/aplicacaoMVC/aluno?acao=historico" class="btn btn-primary">Acessar</a>
                        </div>
                    </div>
                </div>

                <!-- Editar Perfil -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <h5 class="card-title">Editar Perfil</h5>
                            <p class="card-text">Atualize suas informações pessoais.</p>
                            <a href="/aplicacaoMVC/aluno?acao=perfil" class="btn btn-primary">Editar</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center mt-5">
                <a href="/aplicacaoMVC/AutenticaController?acao=logout" class="btn btn-danger">Sair</a>
            </div>
        </div>

        <script src="../bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
