<form action="/aplicacaoMVC/ProfessorController?acao=salvarNota" method="post">
    <input type="hidden" name="turmaId" value="${turma.id}" />
    <label>Nota:</label>
    <input type="number" step="0.01" name="nota" value="${turma.nota}" />
    <button type="submit">Salvar</button>
</form>
