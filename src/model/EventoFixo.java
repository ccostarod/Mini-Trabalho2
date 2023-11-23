package model;

public class EventoFixo extends Evento{
    protected String disciplina;
    public EventoFixo(int ano, String semestre, String curso, String disciplina, int vagas, Horario horario) {
        super(ano, semestre, curso, vagas, horario);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public String toString() {
        return super.toString()  +
                ", disciplina: '" + disciplina + '\'' +
                '}';
    }
}
