package model;

import java.util.Arrays;

public class Horario {
    private String horario;
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public StringBuilder verificarHorario(){
        String[] horarioSeparado = horario.split("");
        boolean coletar = false;
        StringBuilder resultado =new StringBuilder();
        for (String letra : horarioSeparado){
            if (letra.equals("M") || letra.equals("T") || letra.equals("N")){
                coletar = true;
                resultado.append(letra);
            }
            else if(coletar){
                resultado.append(letra);
            }
        }
        return resultado;
    }

    public static boolean compararHorarios(StringBuilder horario1, StringBuilder horario2){
        if (horario1.length() > 0 && horario2.length() > 0){

            if (horario1.charAt(0) == horario2.charAt(0)){
                horario1.deleteCharAt(0);
                horario2.deleteCharAt(0);
                String horario1Formatado = horario1.toString();
                String horario2Formatado = horario2.toString();
                String[] horarios1 = horario1Formatado.split("");
                String[] horarios2 = horario2Formatado.split("");
                for (String x : horarios1){
                    for (String y : horarios2)
                        if (x.equalsIgnoreCase(y)){
                            return false; //Os horarios concidem
                        }
                }
            }
            return true;
        }
        return false;
    }
}
