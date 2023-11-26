import controller.Departamento;
import model.*;

import java.io.File;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Departamento departamento = new Departamento();
        ArrayList<Evento> eventos;
        eventos = departamento.lerAquivo();
        int a = 1;
        if (!(eventos.isEmpty()))
            for (Evento x : eventos){
                System.out.print(a + " -> ");
                System.out.println(x);
                a++;
            }
        else{
            System.out.println("Paece que nao ha nada no arquivo");
        }
        System.out.println();
        EspacoFisico auditorio = new Auditorio("Auditorio CCET", 34);
        EspacoFisico sala = new SalaDeAula("Sala 203",26);
        departamento.inserirEspaco(auditorio.getNome(),auditorio);
        departamento.inserirEspaco(sala.getNome(), sala);
        //AGORA ESTA ALOCANDO UM POR UM. A PESSOA PODE PARAR QUANDO QUISER POIS OS OUTROS EVENTOS ESTAO NUMA LISTA
        departamento.alocarEventos();
        departamento.alocarEventos();
        departamento.alocarEventos();
        departamento.alocarEventos();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (Evento x : departamento.gerarRelatorioAlocacoesCurso("CC")){
            System.out.println(x);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for(Evento x : departamento.gerarRelatorioAlocacoesPorEspaco("Sala 203")){
            System.out.println(x);
        }
    }
}