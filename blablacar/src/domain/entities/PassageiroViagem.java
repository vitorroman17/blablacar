package domain.entities;

import domain.enuns.StatusParticipacao;
public class PassageiroViagem {
    private Usuario passageiro;
    private int numeroDeLugares;
    private String status;

    public PassageiroViagem(Usuario passageiro, int numeroDeLugares) {
        this.passageiro = passageiro;
        this.numeroDeLugares = numeroDeLugares;
        this.status = StatusParticipacao.RESERVADO;
    }

    public Usuario getPassageiro() {
        return passageiro;
    }

    public int getNumeroDeLugares() {
        return numeroDeLugares;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void alterarStatus(String novoStatus) {
        if ((!novoStatus.equals(StatusParticipacao.RESERVADO) &&
                !novoStatus.equals(StatusParticipacao.CANCELADO) &&
                !novoStatus.equals(StatusParticipacao.CONCLUIDO))) {
            throw new IllegalArgumentException("Status inválido para a participação do passageiro.");
        }
        this.status = novoStatus;
    }
}
