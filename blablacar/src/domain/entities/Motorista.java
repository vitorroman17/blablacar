package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Motorista {
    private  final String cnh;
    private  int numeroDeViagens;
    private final List<Veiculo> veiculos;

    public Motorista( String cnh) {
        this.cnh = cnh;
        this.veiculos = new ArrayList<>();
        this.numeroDeViagens = 0;
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
}