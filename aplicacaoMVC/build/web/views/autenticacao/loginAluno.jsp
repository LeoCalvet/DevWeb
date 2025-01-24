<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Aluno</title>
    <!-- Você pode incluir o CSS do Bootstrap se necessário -->
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center">Login - Área do Aluno</h2>
                <form action="${pageContext.request.contextPath}/aluno" method="post">
    
                <!--<form action="<c:out value='${pageContext.request.contextPath}/admin/aluno'/>" method="post">-->
                    
                    <input type="hidden" name="acao" value="login">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="senha">Senha:</label>
                        <input type="password" name="senha" id="senha" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                </form>
                <c:if test="${not empty mensagemErro}">
                    <div class="alert alert-danger mt-3">
                        <c:out value="${mensagemErro}"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
