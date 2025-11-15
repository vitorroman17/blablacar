package domain.entities;

import java.util.List;

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
                    if (novoStatus.equals(StatusParticipacao.CANCELADO)) {
                        
                    }
            throw new IllegalArgumentException("Status inválido para a participação do passageiro.");
        }
        this.status = novoStatus;
    }
    
    public void diminuirLugares(int lugares) {
        for (Usuario u : List.of(passageiro)) {
            if (u.equals(this.passageiro)) {
                if (lugares <= 0 || lugares > this.numeroDeLugares) {
                    throw new IllegalArgumentException("Número de lugares inválido.");
                }
                this.numeroDeLugares -= lugares;
                return;
            }
        }
    }
    
    public void aumentarLugares(Usuario usuario,int lugares) {
        if (lugares <= 0) {
            
        }
        this.numeroDeLugares += lugares;
    }
}