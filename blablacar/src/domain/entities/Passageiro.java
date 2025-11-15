package domain.entities;

public class Passageiro {
    private int numeroDeViagens;

    public Passageiro() {
        this.numeroDeViagens = 0;
    }
    
    public int getNumeroDeViagens() {
        return numeroDeViagens;
    }

    public void addViagem() {
        this.numeroDeViagens ++;
    }
}