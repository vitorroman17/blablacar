package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Passageiro {
    private int numeroDeViagens;
    private final List<Viagem> viagens;

    public Passageiro() {
        this.numeroDeViagens = 0;
        this.viagens = new ArrayList<>();
    }
    
    public int getNumeroDeViagens() {
        return numeroDeViagens;
    }

    public void addViagem() {
        this.numeroDeViagens ++;
    }
    public List<Viagem> getViagens() {
        return viagens;
    }
    public void addViagem(Viagem viagem) {
        this.viagens.add(viagem);
    }
}