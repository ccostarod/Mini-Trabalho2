package model;

public class SalaDeAula extends EspacoFisico{
    public SalaDeAula(String nome, int capacidade) {
        super(nome, capacidade);
        disponibilidade = true;
    }
}
