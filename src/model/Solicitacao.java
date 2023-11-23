package model;

public class Solicitacao {
    private String solicitacao;
    private boolean tipo; //TRUE -> FIXO, FALSE -> Esporadico

    public Solicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }
    
    public boolean obterTipoSolicitacao(){
        String solicitacaoFormatada = this.solicitacao.toUpperCase();
        if (solicitacaoFormatada.equals("FIXA")){
            this.tipo = true;

        } else if (solicitacaoFormatada.equals("EVENTUAL")) {
            this.tipo = false;
        }
        return tipo;
    }

    public boolean isTipo() {
        return tipo;
    }
}
