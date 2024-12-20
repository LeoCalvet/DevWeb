<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Disciplina"%>
<%@page import="entidade.Aluno"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%= request.getContextPath()%>/views/bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Cadastro de Turma</title>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4"><%= request.getAttribute("turma") == null ? "Cadastrar Turma" : "Editar Turma"%></h2>
            <% if (request.getAttribute("erro") != null) {%>
            <div class="alert alert-danger"><%= request.getAttribute("erro")%></div>
            <% }%>
            <form action="<%= request.getContextPath()%>/admin/turma" method="POST">
                <input type="hidden" name="acao" value="<%= request.getAttribute("turma") == null ? "cadastrar" : "alterar"%>">
                <%
                    entidade.Turma turma = (entidade.Turma) request.getAttribute("turma");
                    List<Professor> professores = (List<Professor>) request.getAttribute("professores");
                    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
                    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
                %>
                <input type="hidden" name="id" value="<%= turma != null ? turma.getId() : ""%>">

                <div class="mb-3">
                    <label for="professor_id" class="form-label">Professor</label>
                    <select class="form-select" id="professor_id" name="professor_id" required>
                        <option value="">Selecione um professor</option>
                        <% for (Professor professor : professores) {%>
                        <option value="<%= professor.getId()%>" <%= turma != null && professor.getId() == turma.getProfessorId() ? "selected" : ""%>>
                            <%= professor.getNome()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="disciplina_id" class="form-label">Disciplina</label>
                    <select class="form-select" id="disciplina_id" name="disciplina_id" required>
                        <option value="">Selecione uma disciplina</option>
                        <% for (Disciplina disciplina : disciplinas) {%>
                        <option value="<%= disciplina.getId()%>" <%= turma != null && disciplina.getId() == turma.getDisciplinaId() ? "selected" : ""%>>
                            <%= disciplina.getNome()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="aluno_id" class="form-label">Aluno</label>
                    <select class="form-select" id="aluno_id" name="aluno_id" required>
                        <option value="">Selecione um aluno</option>
                        <% for (Aluno aluno : alunos) {%>
                        <option value="<%= aluno.getId()%>" <%= turma != null && aluno.getId() == turma.getAlunoId() ? "selected" : ""%>>
                            <%= aluno.getNome()%>
                        </option>
                        <% }%>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="codigo_turma" class="form-label">Código da Turma <span class="text-muted">(Até 2 caracteres)</span></label>
                    <input type="text" class="form-control" id="codigo_turma" name="codigo_turma" value="<%= turma != null ? turma.getCodigoTurma() : ""%>" maxlength="2" required>
                </div>

                <div class="mb-3">
                    <label for="nota" class="form-label">Nota <span class="text-muted">(Entre 0 e 10)</span></label>
                    <input type="number" step="0.1" class="form-control" id="nota" name="nota" value="<%= turma != null ? turma.getNota() : ""%>" min="0" max="10" required>
                </div>

                <button type="submit" class="btn btn-primary">Salvar</button>
                <a href="<%= request.getContextPath()%>/admin/turma?acao=listar" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
        <script src="<%= request.getContextPath()%>/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
