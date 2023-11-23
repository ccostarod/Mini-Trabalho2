package model;

import java.util.Hashtable;

public abstract class EspacoFisico {
    protected int capacidade;
    protected String nome;
    protected Evento eventoAtual;
    protected boolean disponibilidade;

    public EspacoFisico(String nome,int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.disponibilidade = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Evento getEventoAtual() {
        return eventoAtual;
    }

    public void setEventoAtual(Evento eventoAtual) {
        this.eventoAtual = eventoAtual;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return "EspacoFisico{" +
                "capacidade=" + capacidade +
                ", nome='" + nome + '\'' +
                ", eventoAtual=" + eventoAtual +
                '}';
    }
}
