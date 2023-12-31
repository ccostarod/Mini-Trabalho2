package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import model.Horario;

public class Departamento {
    private List<Evento> eventos;
    private Hashtable<String, EspacoFisico> espacosFisicos;
    public Departamento() {
        eventos = new ArrayList<>();
        espacosFisicos = new Hashtable<>();
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public Hashtable<String, EspacoFisico> getEspacosFisicos() {
        return espacosFisicos;
    }
    public void inserirEspaco(String nomeEspaco,EspacoFisico espacoFisico){
        if (!espacosFisicos.containsKey(nomeEspaco)){
            espacosFisicos.put(nomeEspaco, espacoFisico);
        }
        else{
            System.out.println("Já existe um local com esse nome no sistema");
        }
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
    private EspacoFisico haDisponibilidadeHorario(EspacoFisico espacoFisico, Evento evento) {
        if (!espacoFisico.getEventosAtuais().isEmpty()) {
            EspacoFisico espacoFisico1 = null;
            for (Evento x : espacoFisico.getEventosAtuais()) {
                if (!evento.getSemestre().equals(x.getSemestre())){
                    return espacoFisico;
                }
                if (!Horario.haColisao(x.getHorario().getHorario(), evento.getHorario().getHorario())) {
                    espacoFisico1 = espacoFisico;
                }
            }
            return espacoFisico1;
        }
        return espacoFisico;
    }
    public EspacoFisico buscarEspaco(Evento evento){
        if (!(espacosFisicos.isEmpty())){
            int menorDiferenca = Integer.MAX_VALUE;
            EspacoFisico espacoFisicoEscolhido = null;
            int capacidade = evento.getVagas();
            for (String x : espacosFisicos.keySet()){
                EspacoFisico espacoFisicoAtual = espacosFisicos.get(x);
                if (evento instanceof EventoEsporadico && espacoFisicoAtual instanceof Auditorio){
                    espacoFisicoAtual = haDisponibilidadeHorario(espacoFisicoAtual, evento);
                    if (espacoFisicoAtual != null){
                        int diferenca = espacoFisicoAtual.getCapacidade() - capacidade;
                        if (diferenca < menorDiferenca && diferenca > 0) {
                            menorDiferenca = diferenca;
                            espacoFisicoEscolhido = espacoFisicoAtual;
                        }
                    }
                }
                else if (evento instanceof EventoFixo){
                    espacoFisicoAtual = haDisponibilidadeHorario(espacoFisicoAtual, evento);
                    if (espacoFisicoAtual != null)
                    {
                        int diferenca = espacoFisicoAtual.getCapacidade() - capacidade;
                        if (diferenca < menorDiferenca && diferenca > 0){
                            menorDiferenca = diferenca;
                            espacoFisicoEscolhido = espacoFisicoAtual;
                        }
                    }
                }
            }
            return espacoFisicoEscolhido;
        }
        return null;
    }
    public void alocarEventos(){
        List<Evento> eventosParaRemover = new ArrayList<>();
        if (!(eventos.isEmpty())){

            EspacoFisico espaco = buscarEspaco(eventos.get(0));
            if (espaco != null){
                espaco.addEventosAtuais(eventos.get(0));
                eventosParaRemover.add(eventos.get(0));
                System.out.println(eventos.get(0) + " Alocado em: " + espaco);
            }
            else{
                System.out.println("Parece que não há espaco para colocar o " + eventos.get(0));
                eventosParaRemover.add(eventos.get(0));
            }
        }
        eventos.removeAll(eventosParaRemover);
        eventosParaRemover.clear();
    }

    public List<Evento> gerarRelatorioAlocacoesCurso(String curso){
        List<Evento> relatorio = new ArrayList<>();
        for (String x : espacosFisicos.keySet()){
            EspacoFisico espacoFisicoAtual = espacosFisicos.get(x);
            if (espacoFisicoAtual.consultarEventosAtuaisPorCurso(curso) != null){
                relatorio.addAll(espacoFisicoAtual.consultarEventosAtuaisPorCurso(curso));
            }
        }
        return relatorio;
    }

    public List<Evento> gerarRelatorioAlocacoesPorEspaco(String espaco){
        for (String x : espacosFisicos.keySet()){
            if (x.equals(espaco)){
                return espacosFisicos.get(x).getEventosAtuais();
            }
        }
        return null;
    }
}
