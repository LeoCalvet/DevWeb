<c:forEach var="turma" items="${turmas}">
    <tr>
        <td>${turma.codigoTurma}</td>
        <td>${turma.alunoNome}</td>
        <td>${turma.nota}</td>
        <td><a href="/aplicacaoMVC/ProfessorController?acao=editarNota&turmaId=${turma.id}" class="btn btn-warning">Editar Nota</a></td>
    </tr>
</c:forEach>
