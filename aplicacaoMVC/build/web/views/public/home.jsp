<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Home</title>
        <link href="<%= request.getContextPath() %>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5 text-center">
                <h3>Seja Bem-vindo ao Blogado</h3>
                <h4>Aqui comentamos sobre vários assuntos</h4>
                <p class="mt-3">
                    Explore nosso conteúdo e aproveite os artigos disponíveis!
                </p>
                <a href="<%= request.getContextPath() %>/public/comentarios.jsp" class="btn btn-primary mt-3">
                    Veja os comentários
                </a>
            </div>
        </div>
        <script src="<%= request.getContextPath() %>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
