<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Dashboard - Área Restrita</title>
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h1 class="text-center mb-4">Dashboard Administrativo</h1>

                <%
                    Administrador administradorLogado = (Administrador) session.getAttribute("administrador");
                %>
                <div class="alert alert-success text-center" role="alert">
                    Usuário logado com sucesso. Bem-vindo, <strong><%= administradorLogado.getNome()%></strong>!
                </div>

                <div class="row mt-4">
                    <div class="col-md-3">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gerenciar Alunos</h5>
                                <p class="card-text">Cadastre, edite ou exclua alunos no sistema.</p>
                                <a href="<%= request.getContextPath()%>/admin/aluno?acao=listar" class="btn btn-primary">Acessar</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gerenciar Professores</h5>
                                <p class="card-text">Cadastre, edite ou exclua professores no sistema.</p>
                                <a href="<%= request.getContextPath()%>/admin/professor?acao=listar" class="btn btn-primary">Acessar</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gerenciar Administradores</h5>
                                <p class="card-text">Cadastre, edite ou exclua administradores no sistema.</p>
                                <a href="<%= request.getContextPath()%>/admin/administrador?acao=listar" class="btn btn-primary">Acessar</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title">Gerenciar Disciplinas</h5>
                                <p class="card-text">Cadastre, edite ou exclua disciplinas no sistema.</p>
                                <a href="<%= request.getContextPath()%>/admin/disciplina?acao=listar" class="btn btn-primary">Acessar</a>
                            </div>
                        </div>
                    </div>


                    <!-- Card para Logout -->
                    <div class="col-md-3">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title">Sair do Sistema</h5>
                                <p class="card-text">Faça logout de maneira segura.</p>
                                <a href="<%= request.getContextPath()%>/admin/logOut" class="btn btn-danger">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
