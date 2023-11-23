package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Departamento {
    private List<Evento> eventos;
    private Hashtable<String, EspacoFisico> espacosFisicos;
    public Departamento() {
        eventos = new ArrayList<>();
        espacosFisicos = new Hashtable<>();
    }

    public ArrayList<Evento> lerAquivo(){
        try{
            int ano, vagas;
            String semestre;
            String curso;
            Horario horario = null;
            Solicitacao solicitacao = null;
            BufferedReader leitor = new BufferedReader(new FileReader("C:\\Users\\RodrigoDev\\Documents\\UFMA\\3P\\LP2\\miniProjeto2\\miniTrabalho2\\miniTrabalho2\\src\\arquivo"));
            String linha;
            String disciplina = null, finalidade = null;
            while ((linha = leitor.readLine()) != null){
                String[] separado = linha.split(";");
                if (separado.length > 1){
                    solicitacao = new Solicitacao(separado[0]);

                    ano = Integer.parseInt(separado[1]);
                    semestre = separado[2];
                    curso = separado[3];
                    if (solicitacao.obterTipoSolicitacao()){
                        disciplina = separado[4];
                    }
                    else{
                        finalidade = separado[4];
                    }
                    vagas = Integer.parseInt(separado[5]);

                    horario = new Horario();
                    horario.setHorario(separado[6]);
                    Evento evento;
                    if (solicitacao.isTipo()){
                        evento = new EventoFixo(ano, semestre, curso, disciplina, vagas, horario);
                    }
                    else{
                        evento = new EventoEsporadico(ano, semestre, curso, finalidade, vagas, horario);
                    }
                    eventos.add(evento);

                }

            }
            leitor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (ArrayList<Evento>) eventos;
    }
    public EspacoFisico buscarEspaco(Evento evento){
        //Tenho que fazer a verificacao do HORARIO TBM!!!! hashtable<Horario (evento.getHorario), Evento>
        if (!(espacosFisicos.isEmpty())){
            int menorDiferenca = Integer.MAX_VALUE;
            EspacoFisico espacoFisicoEscolhido = null;
            int capacidade = evento.getVagas();
            for (String x : espacosFisicos.keySet()){
                if (evento instanceof EventoEsporadico){
                    if(espacosFisicos.get(x) instanceof Auditorio){
                        EspacoFisico espacoFisicoAtual = espacosFisicos.get(x);
                        if (espacoFisicoAtual.isDisponibilidade()) {
                            int diferenca = espacoFisicoAtual.getCapacidade() - capacidade;
                            if (diferenca < menorDiferenca && diferenca > 0){
                                menorDiferenca = diferenca;
                                espacoFisicoEscolhido = espacoFisicoAtual;
                            }
                        }
                    }
                }
                else if (evento instanceof EventoFixo){
                    EspacoFisico espacoFisicoAtual = espacosFisicos.get(x);
                    if (espacoFisicoAtual.isDisponibilidade()){
                        int diferenca = espacoFisicoAtual.getCapacidade() - capacidade;
                        if (diferenca < menorDiferenca && diferenca > 0){
                            menorDiferenca = diferenca;
                            espacoFisicoEscolhido = espacoFisicoAtual;
                        }
                    }
                }
                else {
                    return null;
                }
            }
            if (espacoFisicoEscolhido != null){
                espacoFisicoEscolhido.setDisponibilidade(false);
                return espacoFisicoEscolhido;
            }
        }
        return null;
    }
    public void alocarEventos(){
        List<Evento> eventosParaRemover = new ArrayList<>();
        if (!(eventos.isEmpty())){
            for(Evento x : eventos){ //SUBSTITUIR POR eventos.get(0) e dps remover.
                EspacoFisico espaco = buscarEspaco(x);
                if (espaco != null){
                    espaco.setEventoAtual(x);
                    eventosParaRemover.add(x);
                }
                else{
                    return;
                }
            }
        }
        eventos.removeAll(eventosParaRemover);
        eventosParaRemover.clear();
    }
    public void inserirEspaco(String nomeEspaco,EspacoFisico espacoFisico){
        espacosFisicos.put(nomeEspaco, espacoFisico);
    }
}
