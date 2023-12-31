package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EspacoFisico {
    protected int capacidade;
    protected String nome;
    protected List<Evento> eventosAtuais;


    public EspacoFisico(String nome,int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        eventosAtuais = new ArrayList<>();
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

    public List<Evento> getEventosAtuais() {
        return eventosAtuais;
    }

    public void setEventosAtuais(List<Evento> eventosAtuais) {
        this.eventosAtuais = eventosAtuais;
    }

    public void addEventosAtuais(Evento evento){
        this.eventosAtuais.add(evento);
    }


    public List<Evento> consultarEventosAtuaisPorCurso(String curso){
        List<Evento> consulta = new ArrayList<>();
        consulta = eventosAtuais.stream().filter(x-> x.getCurso().equals(curso)).collect(Collectors.toList());
        if (!consulta.isEmpty()){
            return consulta;
        }
        return null;
    }

    @Override
    public String toString() {
        return "EspacoFisico{" +
                "capacidade=" + capacidade +
                ", nome='" + nome + '\'' +
                ", eventoAtuais" + eventosAtuais +
                '}';
    }
}
