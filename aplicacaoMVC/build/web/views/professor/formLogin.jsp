<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login - Professor</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Login - Professor</h2>
        <form action="/aplicacaoMVC/ProfessorController?acao=login" method="post" class="mt-4">
            <div class="mb-3">
                <label for="cpf" class="form-label">CPF</label>
                <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Digite seu CPF" required>
            </div>
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite sua senha" required>
            </div>
            <button type="submit" class="btn btn-primary">Entrar</button>
            <a href="/" class="btn btn-secondary">Voltar</a>
        </form>
        <%-- Exibe mensagem de erro, se houver --%>
        <c:if test="${not empty msgError}">
            <div class="alert alert-danger mt-3">
                <c:out value="${msgError}" />
            </div>
        </c:if>
    </div>
</body>
</html>
