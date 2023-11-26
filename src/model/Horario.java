package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Horario {
    private String horario;

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    private static boolean colideDias(String[] horario1, String[] horario2) {
        ArrayList<String> diasHorario1 = new ArrayList<>();
        ArrayList<String> diasHorario2 = new ArrayList<>();
        for (String x : horario1) {
            if (!(x.equals("M") || x.equals("T") || x.equals("N"))) {
                diasHorario1.add(x);
            }
        }
        for (String x : horario2) {
            if (!(x.equals("M") || x.equals("T") || x.equals("N"))) {
                diasHorario2.add(x);
            }
        }
        if (!(diasHorario1.isEmpty() && diasHorario2.isEmpty())) {
            for (String x : diasHorario1)
                for (String y : diasHorario2)
                    if (x.equals(y)) {
                    return true;
                    }
        }
        return false;
    }

    private static boolean colideTurno(String[] horario1, String[] horario2) {
        for (String x : horario1){
            if (x.equals("M") || x.equals("T") || x.equals("N")){
                for (String y : horario2){
                    if (y.equals("M") || y.equals("T") || y.equals("N")){
                        if(x.equals(y)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean colideHorario(String[] horario1, String[] horario2){
        ArrayList<String> diasHorario1 = new ArrayList<>();
        ArrayList<String> diasHorario2 = new ArrayList<>();
        boolean coletar = false;

        for (String x : horario1){
            if (x.equals("M") || x.equals("T") || x.equals("N")){
                coletar = true;
            }
            else if (coletar){
                diasHorario1.add(x);
            }
        }
        for (String x : horario2){
            if (x.equals("M") || x.equals("T") || x.equals("N")){
                coletar = true;
            }
            else if (coletar){
                diasHorario2.add(x);
            }
        }
        if (!(diasHorario1.isEmpty() && diasHorario2.isEmpty())){
            for (String x : diasHorario1)
                for (String y : diasHorario2)
                    if (x.equals(y)) {
                        return true;
                    }
        }
        return false;
    }


    public static boolean haColisao(String horario1, String horario2) {
        if (horario1 != null && horario2 != null) {
            String[] horario1Split = horario1.split("");
            String[] horario2Split = horario2.split("");
            return colideDias(horario1Split, horario2Split) && colideTurno(horario1Split, horario2Split) && colideHorario(horario1Split, horario2Split);
        }
        return false;
    }
}

