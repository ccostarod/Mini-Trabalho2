package model;

public abstract class Evento {
    protected int ano;
    protected String semestre;
    protected String curso;
    protected int vagas;
    protected Horario horario;

    public Evento(int ano, String semestre, String curso, int vagas, Horario horario) {
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
        this.vagas = vagas;
        this.horario = horario;
    }

    public int getAno() {
        return ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getCurso() {
        return curso;
    }

    public int getVagas() {
        return vagas;
    }

    public Horario getHorario() {
        return horario;
    }


    @Override
    public String toString() {
        return "Evento: " +
                "ano:" + ano +
                ", semestre:'" + semestre + '\'' +
                ", curso:'" + curso + '\'' +
                ", vagas:" + vagas +
                ", horario: " + horario.getHorario();
    }

}
