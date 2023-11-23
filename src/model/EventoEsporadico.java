package model;

public class EventoEsporadico extends Evento{
    protected String finalidade;
    public EventoEsporadico(int ano, String semestre, String curso, String finalidade,int vagas, Horario horario) {
        super(ano, semestre, curso, vagas, horario);
        this.finalidade = finalidade;
    }

    public String getFinalidade() {
        return finalidade;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", finalidade: '" + finalidade + '\'' +
                '}';
    }
}
