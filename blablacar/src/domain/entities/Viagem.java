package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Viagem {
    private int id = 0;
    private  Veiculo veiculo;
    private  List<Usuario> passageiros;
    private  Usuario motorista;
    private  String cidadeOrigem;
    private  String cidadeDestino;
    private  double preco;
    private  String data;
    private  int vagas;
    private int lugaresOcupados;
    private double avaliacao = 0;
    int somaNotas = 0;
    int totalAvaliacoes;
    

    public Viagem(Veiculo veiculo, Usuario motorista, String cidadeOrigem, String cidadeDestino, double preco, String data, int vagas) {
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.preco = preco;
        this.passageiros = new ArrayList<>();
        this.data = data;
        this.vagas = vagas;
    }
    
     public Veiculo getVeiculo() {
        return veiculo;
    }

    public List<Usuario> getPassageiros() {
        return passageiros;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public void avaliar(double novaAvaliacao) {
        if (novaAvaliacao < 0 || novaAvaliacao > 5) {
            throw new IllegalArgumentException("Avaliação deve ser entre 0 e 5");
        }else {
            somaNotas += novaAvaliacao;
            totalAvaliacoes++;
            avaliacao = somaNotas / totalAvaliacoes;
        }
    }
    public double getAvaliacao() {
        return avaliacao;
    }
    public String getCidadeOrigem() {
        return cidadeOrigem;
    }
    public String getCidadeDestino() {
        return cidadeDestino;
    }
    public double getPreco() {
        return preco;
    }

    public void adicionarPassageiro(Usuario passageiro, int pessoas) {
        if (pessoas <= 0) {
            throw new IllegalArgumentException("Número de pessoas deve ser maior que zero.");
        }else if (pessoas > vagas - lugaresOcupados) {
            throw new IllegalStateException("Não há vagas suficientes. Disponíveis: " + (vagas - lugaresOcupados));
        }else{
            passageiros.add(passageiro);
            lugaresOcupados += pessoas;
        }
    }
    public void removerPassageiro(Usuario passageiro, int pessoas) {
        if (passageiros.contains(passageiro)) {
            passageiros.remove(passageiro);
            lugaresOcupados -= pessoas;
        } else {
            throw new IllegalArgumentException("Passageiro não encontrado na viagem.");
        }
    }
    public String getData() {
        return data;
    }
    public int getVagas() {
        return vagas - passageiros.size();
    }
    public int getId() {
        return id;
    }
}