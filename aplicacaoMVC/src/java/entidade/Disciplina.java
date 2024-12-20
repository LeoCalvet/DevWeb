package entidade;

public class Disciplina {
    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private int cargaHoraria;

    public Disciplina() {
    }

    public Disciplina(String nome, String requisito, String ementa, int cargaHoraria) {
        this.nome = nome;
        this.requisito = requisito;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
