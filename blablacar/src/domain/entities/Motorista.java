package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Motorista {
    private  final String cnh;
    private  int numeroDeViagens = 0;
    private final List<Veiculo> veiculos;
    private final List<Viagem> viagens;

    public Motorista( String cnh) {
        this.cnh = cnh;
        this.veiculos = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }

    public String getCnh() {
        return cnh;
    }

    public int getNumeroDeViagens() {
        return numeroDeViagens;
    }
    public void addViagem() {
        this.numeroDeViagens ++;
    }
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }
    public List<Viagem> getViagens() {
        return viagens;
    }
    public void addViagem(Viagem viagem) {
        this.viagens.add(viagem);
    }
}